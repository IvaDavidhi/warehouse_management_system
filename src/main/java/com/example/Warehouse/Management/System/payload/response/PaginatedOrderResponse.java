package com.example.Warehouse.Management.System.payload.response;

import com.example.Warehouse.Management.System.model.Order;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginatedOrderResponse {

    private List<Order> orderList;
    private Long numberOfItems;
    private int numberOfPages;
}
