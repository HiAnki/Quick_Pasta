package com.example.QuickPasta.dto.request;

import com.example.QuickPasta.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemRequest {
    String dishName;

    FoodCategory category;

    int restaurantId;

    boolean veg;

    boolean available;

    double price;
}
