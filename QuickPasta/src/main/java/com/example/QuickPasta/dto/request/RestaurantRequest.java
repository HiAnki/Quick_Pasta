package com.example.QuickPasta.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RestaurantRequest {

    String name;

    String address;

    String email;

    String username;

    String password;

    boolean takingOrder;

    double rating;

}
