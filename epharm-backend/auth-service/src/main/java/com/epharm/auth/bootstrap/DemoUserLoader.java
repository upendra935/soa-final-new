package com.epharm.auth.bootstrap;

import com.epharm.auth.entity.User;
import com.epharm.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DemoUserLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DemoUserLoader.class);
    static final String DEMO_PASSWORD = "DemoPass123!";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DemoUserLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        seed("Dr. Aris Thorne", "aris.thorne@epharm.com", "ADMIN");
        seed("Elena Rostova", "elena.rostova@epharm.com", "PHARMACIST");
        seed("Marcus Vance", "marcus.vance@epharm.com", "WAREHOUSE_MANAGER");
        seed("Sophia Chen", "sophia.chen@epharm.com", "SUPPLY_CHAIN_OFFICER");
        seed("David Miller", "david.miller@epharm.com", "LOGISTICS_COORDINATOR");
    }

    private void seed(String name, String email, String role) {
        if (userRepository.existsByEmailIgnoreCase(email)) {
            return;
        }
        userRepository.save(new User(name, email, passwordEncoder.encode(DEMO_PASSWORD), role));
        log.info("Seeded demo user {} ({})", email, role);
    }
}
