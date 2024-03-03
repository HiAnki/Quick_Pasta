package com.example.QuickPasta.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_entity")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String orderId; // uuid

    int orderTotal;

    @CreationTimestamp
    Date orderTime;

    @ManyToOne
    @JoinColumn
    Customer customer;

//    @ManyToOne
//    @JoinColumn
//    DeliveryPartner deliveryPartner;

//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    List<MenuItem> foodItemList = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn
//    Restaurant restaurant;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    List<RestaurantOrderItem> orderItemList = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<CartItem> cartItemList = new ArrayList<>();

}
