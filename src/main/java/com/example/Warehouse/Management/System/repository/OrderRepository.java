package com.example.Warehouse.Management.System.repository;

import com.example.Warehouse.Management.System.model.Order;
import com.example.Warehouse.Management.System.model.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order deleteByStatus(OrderStatus status);

    Page<Order> findAllByStatus(OrderStatus status, Pageable pageable);
}
