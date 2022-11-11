package com.example.Warehouse.Management.System.controller;

import com.example.Warehouse.Management.System.model.Order;
import com.example.Warehouse.Management.System.model.Truck;
import com.example.Warehouse.Management.System.model.enums.OrderStatus;

import com.example.Warehouse.Management.System.payload.dto.DeclineReasonDto;
import com.example.Warehouse.Management.System.payload.dto.UpdateOrderDto;
import com.example.Warehouse.Management.System.service.ServiceImpl.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/orders")
@SecurityRequirement(name = "warehouse")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RolesAllowed({"ROLE_USER"})
    @GetMapping("/user/search/filter")
    public ResponseEntity getOrdersWithFilter (@RequestParam("query") OrderStatus status, Pageable pageable) {
        return ResponseEntity.ok(orderService.filterOrder(status, pageable));
    }

    @RolesAllowed({"ROLE_MANAGER"})
    @GetMapping("/manager/sort")
    ResponseEntity<Page<Order>> getAllOrders(@RequestParam("query") OrderStatus status, Pageable pageable) {

        return ResponseEntity.ok().body(orderService.filterAndSortOrder(status, pageable));
    }

    @RolesAllowed({"ROLE_MANAGER","ROLE_USER"})
    @GetMapping(value = "/find/{id}")
    ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    ResponseEntity<Order> saveNewOrder(@RequestBody Order newOrder) {
        return ResponseEntity.ok().body(orderService.saveNewOrder(newOrder));
    }

    @RolesAllowed({"ROLE_USER"})
    @PutMapping(value = "user/updateOrder/{id}")
    public String updateOrder(@PathVariable Long id, @RequestBody UpdateOrderDto order) {
        orderService.updateOrderById(order, id);

        return "Order updated";
    }

    @RolesAllowed({"ROLE_USER"})
    @DeleteMapping(value = "user/delete/{id}")
    ResponseEntity<?> deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RolesAllowed({"ROLE_USER"})
    @PostMapping(value = "user/submitOrder/{id}")
    ResponseEntity<?> submitOrder(@PathVariable Long id) {
        orderService.submitOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RolesAllowed({"ROLE_MANAGER"})
    @PutMapping(value = "/scheduleDelivery/{id}")
    ResponseEntity<Order> scheduleDelivery(@PathVariable Long id,
                                            @RequestParam Date deliveryDate,
                                            @RequestParam List<Truck> truck) {
        Order scheduleOrder = orderService.getOrderById(id);
        return ResponseEntity.ok().body(orderService.scheduleDelivery(id));
    }

    @RolesAllowed({"ROLE_MANAGER"} )
    @GetMapping(value = "/declineOrder")
    public void declineOrder (@PathVariable Long id, @RequestParam DeclineReasonDto declineReasonDto){
        Order declineOrder = orderService.getOrderById(id);
        declineOrder.setStatus(OrderStatus.DECLINED);
        declineOrder.setDecliningReason(declineReasonDto.getId());
    }

    @RolesAllowed({"ROLE_MANAGER"})
    @PutMapping(value = "manager/orderFulfilled/{id}")
    public String updateOrderFulfilled(@PathVariable Long id) {
        orderService.orderFulfilled(id);
        return "Order fulfilled";
    }
}
