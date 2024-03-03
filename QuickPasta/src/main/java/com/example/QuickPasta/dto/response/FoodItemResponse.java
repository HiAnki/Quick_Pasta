package com.example.QuickPasta.dto.response;

import com.example.QuickPasta.Enum.FoodCategory;
import com.example.QuickPasta.model.MenuItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodItemResponse {
//    int totalQuantity;
//
//    int totalPrice;
//
//    String dishName;
//
//    String restaurantName;
    String id; // uuid
    String dishName;
    String description;
    int price;
    boolean available;
    String restaurantName;
    int howManyTimesOrdered;
    FoodCategory foodCategory;
    boolean veg;
}
