package com.example.QuickPasta.utils;

import com.example.QuickPasta.model.Restaurant;
import com.example.QuickPasta.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationUtils {
    final RestaurantRepository restaurantRepository;

    public ValidationUtils(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public boolean validateRestaurantId(int id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        return optionalRestaurant.isPresent();
    }
}
