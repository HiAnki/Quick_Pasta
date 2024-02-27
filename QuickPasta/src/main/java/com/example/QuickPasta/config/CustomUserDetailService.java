package com.example.QuickPasta.config;

import com.example.QuickPasta.model.Customer;
import com.example.QuickPasta.model.Restaurant;
import com.example.QuickPasta.repository.CustomerRepository;
import com.example.QuickPasta.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.findByEmail(username);
        if(!customer.isEmpty()) {
            return new UserDetailCreator(customer.get());
        }

        Optional<Restaurant> restaurant = restaurantRepository.findByEmail(username);
        if(!restaurant.isEmpty()) return new UserDetailCreator(restaurant.get());

        throw new UsernameNotFoundException("Invalid username");
    }
}
