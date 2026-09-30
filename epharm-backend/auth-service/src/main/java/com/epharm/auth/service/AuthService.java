package com.epharm.auth.service;

import com.epharm.auth.dto.AuthResponse;
import com.epharm.auth.dto.LoginRequest;
import com.epharm.auth.dto.SignupRequest;
import com.epharm.auth.entity.User;
import com.epharm.auth.exception.ApiException;
import com.epharm.auth.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class AuthService {

    private static final Set<String> ALLOWED_ROLES = Set.of(
            "ADMIN",
            "PHARMACIST",
            "WAREHOUSE_MANAGER",
            "SUPPLY_CHAIN_OFFICER",
            "LOGISTICS_COORDINATOR"
    );

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public AuthResponse signup(SignupRequest request) {
        String email = request.getEmail().trim().toLowerCase();
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new ApiException(HttpStatus.CONFLICT, "An account with this email already exists");
        }

        String role = normalizeRole(request.getRole());
        User user = new User(
                request.getName().trim(),
                email,
                passwordEncoder.encode(request.getPassword()),
                role
        );
        User saved = userRepository.save(user);
        return toResponse(saved);
    }

    public AuthResponse login(LoginRequest request) {
        String email = request.getEmail().trim().toLowerCase();
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        return toResponse(user);
    }

    private AuthResponse toResponse(User user) {
        String token = jwtService.generateToken(user.getEmail(), user.getRole());
        return new AuthResponse(token, user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    private String normalizeRole(String role) {
        if (role == null || role.isBlank()) {
            return "ADMIN";
        }
        String normalized = role.trim().toUpperCase();
        if (!ALLOWED_ROLES.contains(normalized)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Unsupported role: " + role);
        }
        return normalized;
    }
}
