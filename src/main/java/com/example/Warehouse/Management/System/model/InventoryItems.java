package com.example.Warehouse.Management.System.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Inventory_Items")
public class InventoryItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column()
    private String itemName;

    @Column
    private double itemPrice;

    @Column()
    private int itemQuantity;

    public InventoryItems(String itemName, double itemPrice, int itemQuantity) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }

    public double getTotalValue() {
        return getItemPrice()*getItemQuantity();
    }
}
