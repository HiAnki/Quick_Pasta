package com.example.QuickPasta.repository;

import com.example.QuickPasta.Enum.FoodCategory;
import com.example.QuickPasta.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {
    List<FoodItem> findByDishName(String dishName);

}
