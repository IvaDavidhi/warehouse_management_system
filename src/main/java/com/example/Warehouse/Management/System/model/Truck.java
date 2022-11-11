package com.example.Warehouse.Management.System.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(unique = true)
    private String chassisNumber;

    @Column(unique = true)
    private String licensePlate;

    public Truck(String chassisNumber, String licensePlate) {
        this.chassisNumber = chassisNumber;
        this.licensePlate = licensePlate;
    }
}
