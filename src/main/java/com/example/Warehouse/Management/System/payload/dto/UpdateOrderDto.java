package com.example.Warehouse.Management.System.payload.dto;

import com.example.Warehouse.Management.System.model.InventoryItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDto {
    private List<InventoryItems> inventoryItems;
}
