package com.example.QuickPasta.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemResponse {
    int quantity;
    String foodItemName;
    int totalPrice;
    int howManyPeopleOrdered;
    String restaurantName;
    boolean available;
    int priceOfOne;
}
