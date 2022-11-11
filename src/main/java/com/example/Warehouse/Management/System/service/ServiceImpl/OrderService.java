package com.example.Warehouse.Management.System.service.ServiceImpl;

import com.example.Warehouse.Management.System.exceptions.ResourceNotFoundException;
import com.example.Warehouse.Management.System.model.Order;
import com.example.Warehouse.Management.System.model.enums.OrderStatus;
import com.example.Warehouse.Management.System.payload.dto.DeclineReasonDto;
import com.example.Warehouse.Management.System.payload.dto.UpdateOrderDto;
import com.example.Warehouse.Management.System.payload.response.PaginatedOrderResponse;
import com.example.Warehouse.Management.System.repository.InventoryItemsRepository;
import com.example.Warehouse.Management.System.repository.OrderRepository;
import com.example.Warehouse.Management.System.service.IService.IOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService implements IOrder {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryItemsRepository itemsRepository;

    @Override
    public List<Order> listAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order by id " + id + " was not found"));
    }

    @Override
    public Order saveNewOrder(Order order) {
        log.info("Saving new order {} to the database", order.getId());
        Order newOrder = new Order(order.getStatus(),
                order.getSubmittedDate(),
                order.getAddress(),
                order.getInventoryItems());
        newOrder.setStatus(OrderStatus.PENDING);
        return orderRepository.save(newOrder);
    }

    @Override
    public String deleteOrderById(Long id) {
        Order deleteOrder = orderRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Order not found"));
        if(deleteOrder.getStatus() == OrderStatus.DECLINED ||
            deleteOrder.getStatus() == OrderStatus.AWAITING_APPROVAL ||
            deleteOrder.getStatus() == OrderStatus.CREATED) {
            orderRepository.deleteById(id);
            return "Order deleted";
        }
        return "You can't delete your order";
    }

    @Override
    public String updateOrderById(UpdateOrderDto order, Long id) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Order not found"));
        if (existingOrder.getStatus() == OrderStatus.CREATED || existingOrder.getStatus() == OrderStatus.DECLINED) {
            existingOrder.setInventoryItems(order.getInventoryItems());

            return "Order updated";
        }
        return "You can't update your order";

    }

    public PaginatedOrderResponse filterOrder(OrderStatus status, Pageable pageable) {
        Page<Order> orders = orderRepository.findAllByStatus(status, pageable);

        return PaginatedOrderResponse.builder().numberOfItems(orders.getTotalElements()).numberOfPages(orders.getTotalPages())
                .orderList(orders.getContent())
                .build();
    }

    public Page<Order> filterAndSortOrder(OrderStatus status, Pageable pageable) {
       return  orderRepository
               .findAll(PageRequest.of(0,1, Sort.by(Sort.Direction.DESC, "submittedDate")));

    }

    public void declineWithReason(Long id, DeclineReasonDto declineReasonDto) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Order not found"));
        existingOrder.setDecliningReason(declineReasonDto.getId());
    }

    public Order scheduleDelivery(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Order not found"));

        order.setDeliveryDate(order.getDeliveryDate());
        order.setTrucks(order.getTrucks());
        order.setInventoryItems(order.getInventoryItems());
        order.setStatus(OrderStatus.UNDER_DELIVERY);

        return orderRepository.save(order);
    }
}