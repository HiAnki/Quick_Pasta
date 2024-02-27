package com.example.QuickPasta.model;

import com.example.QuickPasta.Enum.FoodCategory;
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
@Table(name = "menu_item")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String dishName;

    @Enumerated(EnumType.STRING)
    FoodCategory category;

    boolean veg;

    boolean available;

    double price;

//    @ManyToOne
//    @JoinColumn
//    Cart cart;
//
//    @ManyToOne
//    @JoinColumn
//    OrderEntity order;

//    @ManyToOne
//    @JoinColumn
//    Restaurant restaurant;
//
//    @OneToMany(mappedBy = "menuItem",cascade = CascadeType.ALL)
//    List<FoodItem> foodItems = new ArrayList<>();

}
