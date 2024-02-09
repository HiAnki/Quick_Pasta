package com.example.QuickPasta.model;


import com.example.QuickPasta.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank
    String name;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Email
    @Column(unique = true)
    String email;

    @Size(min = 10, max=10, message = "Phone no must be of 10 characters")
    String phoneNo;

    @NotBlank
    String address;
    @NotBlank
    String dob;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<OrderEntity> orderList = new ArrayList<>();
}
