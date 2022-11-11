package com.example.Warehouse.Management.System.repository;

import com.example.Warehouse.Management.System.model.InventoryItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemsRepository extends JpaRepository<InventoryItems, Long> {


}
