package com.example.Warehouse.Management.System.service.IService;

import com.example.Warehouse.Management.System.model.Order;
import com.example.Warehouse.Management.System.payload.dto.UpdateOrderDto;

import java.util.List;

public interface IOrder {

    List<Order> listAllOrders();

    Order getOrderById(Long id);

    Order saveNewOrder(Order order);

    String deleteOrderById(Long id);

    String updateOrderById(UpdateOrderDto order, Long id);
}
