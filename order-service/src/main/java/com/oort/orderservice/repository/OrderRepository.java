package com.oort.orderservice.repository;

import com.oort.orderservice.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long>{
    
}
