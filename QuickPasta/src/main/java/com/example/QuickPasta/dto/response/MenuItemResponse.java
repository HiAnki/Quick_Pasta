package com.example.QuickPasta.dto.response;

import com.example.QuickPasta.Enum.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuItemResponse {
    String dishName;
    double price;
    FoodCategory category;
    boolean veg;
    boolean available;
}
