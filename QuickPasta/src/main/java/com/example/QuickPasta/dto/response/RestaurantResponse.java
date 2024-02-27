package com.example.QuickPasta.dto.response;

import com.example.QuickPasta.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantResponse {

    String name;

    String address;

    boolean takingOrder;

}
