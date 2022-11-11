package com.example.Warehouse.Management.System.repository;

import com.example.Warehouse.Management.System.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {
}
