package com.example.QuickPasta.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
@Table(name = "delivery_partner")
public class DeliveryPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank
    String name;

    @Size(min = 10, max=10, message = "Phone no must be of 10 characters")
    String phoneNo;

    String DOB;

//    @OneToMany(mappedBy = "deliveryPartner", cascade = CascadeType.ALL)
//    List<OrderEntity> orderList = new ArrayList<>();

}
