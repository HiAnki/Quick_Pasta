package com.example.QuickPasta.controller;

import com.example.QuickPasta.dto.request.FoodItemRequest;
import com.example.QuickPasta.serviceInterface.FoodItemService;
import com.example.QuickPasta.dto.response.FoodItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foodItem")
public class FoodItemController {

    final FoodItemService foodItemService;
    @Autowired
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping("/searchFood")
    public ResponseEntity searchFoodByName(@RequestParam("name") String keyWord) {
        List<FoodItemResponse> foodItemResponseList = foodItemService.findByKeyword(keyWord);
        return new ResponseEntity(foodItemResponseList, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteFoodItem(@RequestParam("foodItemId") int foodItemId) {
        String msg = foodItemService.deleteFoodItem(foodItemId);
        return new ResponseEntity(msg,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateFoodItem(@RequestParam("foodItemId") int id, @RequestBody FoodItemRequest foodItemRequest) {
        FoodItemResponse foodItemResponse = foodItemService.updateFoodItem(id,foodItemRequest);
        return new ResponseEntity(foodItemResponse,HttpStatus.OK);
    }

    @PutMapping("/available")
    public ResponseEntity changeFoodItemAvailability(@RequestParam("foodItemId") int id) {
        Boolean foodItemAvailable = foodItemService.changeFoodItemAvailability(id);
        return new ResponseEntity(foodItemAvailable,HttpStatus.OK);
    }

    @GetMapping("/searchFoodItem")
    public ResponseEntity searchFoodByDishName(@RequestBody FoodItemRequest foodItemRequest) {
        List<FoodItemResponse> foodItemResponseList = foodItemService.searchFoodByDishName(foodItemRequest);
        return new ResponseEntity(foodItemResponseList, HttpStatus.OK);
    }



}
