package com.oort.inventoryservice.repository;

import java.util.Optional;

import com.oort.inventoryservice.entity.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
    
    Optional<Inventory> findBySkuCode(String skuCode);
}
