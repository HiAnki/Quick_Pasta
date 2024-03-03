package com.example.QuickPasta.model;

import com.example.QuickPasta.Enum.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_item")
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String foodId;  // uuid

    String dishName;

    int price;

    int howManyTimesOrdered;

    String description;

    boolean available;

    boolean veg;

    @Enumerated(EnumType.STRING)
    FoodCategory foodCategory;

//    @ManyToOne
//    @JoinColumn
//    Restaurant restaurant;

//    @ManyToOne
//    @JoinColumn
//    Cart cart;

//    @ManyToOne
//    @JoinColumn
//    MenuItem menuItem;
//
//    @ManyToOne
//    @JoinColumn
//    OrderEntity order;
}
