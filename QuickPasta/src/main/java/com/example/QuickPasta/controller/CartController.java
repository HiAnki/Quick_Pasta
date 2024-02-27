package com.example.QuickPasta.controller;

import com.example.QuickPasta.dto.response.CartItemResponse;
import com.example.QuickPasta.dto.response.CartResponse;
import com.example.QuickPasta.repository.CartRepository;
import com.example.QuickPasta.serviceInterface.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("/add")
    public ResponseEntity addCartItemToCart(@RequestParam("cartId") int cartId, @RequestParam("foodItemId") int foodItemId, @RequestParam("quantity") int quantity) {
        String msg = cartService.addCartItemToCart(cartId,foodItemId,quantity);
        return new ResponseEntity(msg, HttpStatus.CREATED);
    }

    @GetMapping("/viewCart")
    public ResponseEntity viewCart(@RequestParam("customerId") int customerId) {
        CartResponse cartResponse = cartService.viewCart(customerId);
        return new ResponseEntity(cartResponse,HttpStatus.FOUND);
    }

    @DeleteMapping("/deleteFoodItem")
    public ResponseEntity deleteFoodItem(@RequestParam("cartItemId") int cartItemId, @RequestParam("customerId") int customerId) {
        CartItemResponse cartItemResponse = cartService.deleteFoodItem(cartItemId,customerId);
        return new ResponseEntity(cartItemResponse,HttpStatus.OK);
    }
}
