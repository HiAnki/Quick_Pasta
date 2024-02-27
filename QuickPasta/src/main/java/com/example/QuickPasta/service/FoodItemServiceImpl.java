package com.example.QuickPasta.service;

import com.example.QuickPasta.Enum.FoodCategory;
import com.example.QuickPasta.dto.request.FoodItemRequest;
import com.example.QuickPasta.exceptions.FoodItemNotFoundException;
import com.example.QuickPasta.model.FoodItem;
import com.example.QuickPasta.serviceInterface.FoodItemService;
import com.example.QuickPasta.dto.response.FoodItemResponse;
import com.example.QuickPasta.repository.FoodItemRepository;
import com.example.QuickPasta.transformer.FoodItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    final FoodItemRepository foodItemRepository;

    @Autowired
    public FoodItemServiceImpl(FoodItemRepository foodItemRepository) {

        this.foodItemRepository = foodItemRepository;
    }
    @Override
    public List<FoodItemResponse> findByKeyword(String keyword) {
        List<FoodItem> foodItemList = foodItemRepository.findByDishName(keyword);
        List<FoodItemResponse> foodItemResponseList = new ArrayList<>();

        for(FoodItem foodItem: foodItemList) {
            foodItemResponseList.add(FoodItemTransformer.foodItemToFoodItemResponse(foodItem));
        }
        return  foodItemResponseList;
    }

    @Override
    public String deleteFoodItem(int foodItemId) {
        Optional<FoodItem> foodItemOptional = foodItemRepository.findById(foodItemId);
        if(foodItemOptional.isEmpty()) throw new FoodItemNotFoundException("invalid Food Item Id!");


        FoodItem foodItem = foodItemOptional.get();
        foodItemRepository.deleteById(foodItemId);
        return "Food Item Deleted!! DishName-> "+foodItem.getDishName()+" Category-> "+foodItem.getFoodCategory();
    }

    @Override
    public FoodItemResponse updateFoodItem(int id, FoodItemRequest foodItemRequest) {
        Optional<FoodItem> foodItemOptional = foodItemRepository.findById(id);
        if(foodItemOptional.isEmpty()) throw new FoodItemNotFoundException("Invalid food item!!");

        FoodItem foodItem = foodItemOptional.get();
        FoodItem newFoodItem = FoodItemTransformer.foodItemRequestToFoodItem(foodItemRequest);

        // update the fields which are not null in new food item
        if(newFoodItem.getDishName()!=null) foodItem.setDishName(newFoodItem.getDishName());
        if(newFoodItem.getFoodCategory()!=null) foodItem.setFoodCategory(newFoodItem.getFoodCategory());
        if(newFoodItem.getPrice()!=0) foodItem.setPrice(newFoodItem.getPrice());

        FoodItem updatedFoodItem = foodItemRepository.save(foodItem);
        return FoodItemTransformer.foodItemToFoodItemResponse(updatedFoodItem);
    }

    @Override
    public Boolean changeFoodItemAvailability(int id) {
        Optional<FoodItem> foodItemOptional = foodItemRepository.findById(id);
        if(foodItemOptional.isEmpty()) throw new FoodItemNotFoundException("Invalid Food Item!");

        FoodItem foodItem = foodItemOptional.get();
        foodItem.setAvailable(!foodItem.isAvailable());
        foodItemRepository.save(foodItem);

        return foodItem.isAvailable();
    }

    @Override
    public List<FoodItemResponse> searchFoodByDishName(FoodItemRequest foodItemRequest) {
        String dishName = foodItemRequest.getDishName();
        List<FoodItem> foodItemList = foodItemRepository.findByDishName(dishName);

        List<FoodItemResponse> foodItemResponseList = new ArrayList<>();

        for(FoodItem foodItem: foodItemList) {
            foodItemResponseList.add(FoodItemTransformer.foodItemToFoodItemResponse(foodItem));
        }
        return foodItemResponseList;
    }


}
