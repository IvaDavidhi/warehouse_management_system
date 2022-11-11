package com.example.Warehouse.Management.System.model;

import com.example.Warehouse.Management.System.model.enums.OrderStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "Order_Details")

public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Date submittedDate;

    private Date deliveryDate;

    private Date deadlineDate;

    private String address;

    private String decliningReason;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventoryItem_id", referencedColumnName = "id")
    private List<InventoryItems> inventoryItems;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    private List<Truck> trucks;

    public Order(OrderStatus status, Date submittedDate, String address, List<InventoryItems> inventoryItems) {
        this.status = status;
        this.submittedDate = submittedDate;
        this.address = address;
        this.inventoryItems = inventoryItems;
    }
}
