package com.example.Warehouse.Management.System.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {

    private String itemName;
    private double itemPrice;
    private int itemQuantity;
}
