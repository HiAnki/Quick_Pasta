package com.example.QuickPasta.model;

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

    @Email
    @Column(unique = true)
    String email;

    boolean open;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<MenuItem> availableFoodItems = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();
}
