package com.epharm.inventory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {

        return inventoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(
            @PathVariable Long id,
            @RequestBody Inventory updatedInventory) {

        return inventoryRepository.findById(id)
                .map(existing -> {

                    existing.setProductId(updatedInventory.getProductId());
                    existing.setProductName(updatedInventory.getProductName());
                    existing.setSku(updatedInventory.getSku());
                    existing.setQuantity(updatedInventory.getQuantity());
                    existing.setReorderLevel(updatedInventory.getReorderLevel());
                    existing.setStatus(updatedInventory.getStatus());
                    existing.setLocation(updatedInventory.getLocation());

                    return ResponseEntity.ok(
                            inventoryRepository.save(existing)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {

        if (!inventoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        inventoryRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}