package com.oort.orderservice.dto;

import java.util.List;

import com.oort.orderservice.entity.OrderLineItems;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    
    private List<OrderLineItems> orderLineItemsList;
}
