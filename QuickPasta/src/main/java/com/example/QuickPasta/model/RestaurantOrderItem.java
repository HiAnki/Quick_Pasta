package com.example.QuickPasta.model;

import com.example.QuickPasta.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant_order_item")
public class RestaurantOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int totalPrice;

    OrderStatus status;

    int restaurantId;

    @ManyToOne
    @JoinColumn
    OrderEntity order;

    @OneToMany(mappedBy = "restaurantOrderItem", cascade = CascadeType.ALL)
    List<CartItem> cartItemList = new ArrayList<>();
}
