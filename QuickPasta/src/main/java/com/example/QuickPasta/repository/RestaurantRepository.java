package com.example.QuickPasta.repository;

import com.example.QuickPasta.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
}
