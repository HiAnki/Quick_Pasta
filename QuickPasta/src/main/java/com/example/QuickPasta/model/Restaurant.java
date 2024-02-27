package com.example.QuickPasta.model;

import com.example.QuickPasta.Enum.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 2, max = 30, message = "Name is too short or too long")
    String name;

    String address;

    String password;

    String username;

    String role;

    @Email
    @Column(unique = true)
    String email;

    boolean takingOrder;

    double rating;

//    @Enumerated(EnumType.STRING)
//    List<FoodCategory> menu = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<FoodItem> availableFoodItems = new ArrayList<>();

//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//    List<MenuItem> availableFoodItems = new ArrayList<>();

//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//    List<OrderEntity> orders = new ArrayList<>();
}
