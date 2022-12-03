package com.oort.inventoryservice.controller;

import com.oort.inventoryservice.entity.Inventory;
import com.oort.inventoryservice.repository.InventoryRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    
    private final InventoryRepository inventoryRepository;

    @GetMapping("/{skuCode}")
    public Boolean isInStock(@PathVariable String skuCode){
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode).orElseThrow(() -> new RuntimeException("Cannot find product by skuCode " + skuCode));
        return inventory.getStock() > 0;
    }
}
