package com.example.QuickPasta.model;

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
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int cartTotal;

    @OneToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    List<CartItem> cartItemList = new ArrayList<>();

//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//    List<MenuItem> foodItemList = new ArrayList<>();

//    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
//    List<FoodItem> foodItemList = new ArrayList<>();

}
