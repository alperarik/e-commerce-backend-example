package com.oort.orderservice.controller;

import java.util.UUID;

import com.oort.orderservice.client.InventoryClient;
import com.oort.orderservice.dto.OrderDto;
import com.oort.orderservice.entity.Order;
import com.oort.orderservice.repository.OrderRepository;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/order/")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final StreamBridge streamBridge;

    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto) {
        boolean productsInStock = orderDto.getOrderLineItemsList().stream()
        .allMatch(lineItem -> {
            log.info("Making Call to Inventory Service for SkuCode {}", lineItem.getSkuCode());
            return inventoryClient.checkStock(lineItem.getSkuCode());
        });


        if (productsInStock) {
            Order order = new Order();
            order.setOrderLineItems(orderDto.getOrderLineItemsList());
            order.setOrderNumber(UUID.randomUUID().toString());

            orderRepository.save(order);
            log.info("Sending Order Details with Order Id {} to Notification Service", order.getId());
            streamBridge.send("notificationEventSupplier-out-0", MessageBuilder.withPayload(order.getId()).build());
            return "Order Place Successfully";
        } else {
            return "Order Failed - One of the Product in your Order is out of stock";
        }
    }
}
