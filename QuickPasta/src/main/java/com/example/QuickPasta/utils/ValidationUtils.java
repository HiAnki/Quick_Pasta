package com.example.QuickPasta.utils;

import com.example.QuickPasta.exceptions.CustomerNotFoundException;
import com.example.QuickPasta.model.Customer;
import com.example.QuickPasta.model.FoodItem;
import com.example.QuickPasta.model.Restaurant;
import com.example.QuickPasta.repository.CustomerRepository;
import com.example.QuickPasta.repository.FoodItemRepository;
import com.example.QuickPasta.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationUtils {
    final RestaurantRepository restaurantRepository;
    final FoodItemRepository foodItemRepository;

    public ValidationUtils(RestaurantRepository restaurantRepository, FoodItemRepository foodItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.foodItemRepository = foodItemRepository;
    }

    public boolean validateRestaurantId(int id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        return optionalRestaurant.isPresent();
    }

    public static void validateCustomer(CustomerRepository customerRepository, int customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isEmpty()) throw new CustomerNotFoundException("Invalid Customer Id!!");
    }
}
