package com.example.Warehouse.Management.System.payload.dto;

import com.example.Warehouse.Management.System.model.InventoryItems;
import com.example.Warehouse.Management.System.model.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class CreateOrderDto {


    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Date submittedDate;


    private String address;


    private List<InventoryItems> inventoryItems;

}
