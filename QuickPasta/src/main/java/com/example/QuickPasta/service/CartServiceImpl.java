package com.example.QuickPasta.service;

import com.example.QuickPasta.dto.response.CartItemResponse;
import com.example.QuickPasta.dto.response.CartResponse;
import com.example.QuickPasta.exceptions.CartNotFoundException;
import com.example.QuickPasta.exceptions.CustomerNotFoundException;
import com.example.QuickPasta.exceptions.FoodItemNotFoundException;
import com.example.QuickPasta.model.Cart;
import com.example.QuickPasta.model.CartItem;
import com.example.QuickPasta.model.Customer;
import com.example.QuickPasta.model.FoodItem;
import com.example.QuickPasta.repository.CartItemRepository;
import com.example.QuickPasta.repository.CartRepository;
import com.example.QuickPasta.repository.CustomerRepository;
import com.example.QuickPasta.repository.FoodItemRepository;
import com.example.QuickPasta.serviceInterface.CartService;
import com.example.QuickPasta.transformer.CartItemTransformer;
import com.example.QuickPasta.transformer.CartTransformer;
import com.example.QuickPasta.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    final CartRepository cartRepository;
    final FoodItemRepository foodItemRepository;
    final CustomerRepository customerRepository;
    final CartItemRepository cartItemRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, FoodItemRepository foodItemRepository, CustomerRepository customerRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.foodItemRepository = foodItemRepository;
        this.customerRepository = customerRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public String addCartItemToCart(int cartId, int foodItemId, int quantity) {
        // check if foodItemId valid
        Optional<FoodItem> foodItemOptional = foodItemRepository.findById(foodItemId);
        if(foodItemOptional.isEmpty()) throw new FoodItemNotFoundException("Invalid food Item id!");

        FoodItem foodItem = foodItemOptional.get();

        // get cart
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if(cartOptional.isEmpty()) throw new CartNotFoundException("Cart id invalid!");
        Cart cart = cartOptional.get();
        // create cart item

        CartItem cartItem = new CartItem();
        cartItem.setFoodItem(foodItem);
        cartItem.setQuantity(quantity);
        // update cart total
        cart.setCartTotal(cart.getCartTotal()+(foodItem.getPrice()*quantity));
        //add cart
        cartItem.setCart(cart);
        cart.getCartItemList().add(cartItem);
        //save cart
        cartRepository.save(cart);

        return "Cart Item Added!!";
    }

    @Override
    public CartResponse viewCart(int customerId) {
        // get the customer
        ValidationUtils.validateCustomer(customerRepository,customerId);
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        Cart cart = customerOptional.get().getCart();

        return CartTransformer.CartToCartResponse(cart);
    }

    @Override
    public CartItemResponse deleteFoodItem(int cartItemId, int customerId) {
        // get customer
        ValidationUtils.validateCustomer(customerRepository,customerId);
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        // get cart
        Cart cart = customerOptional.get().getCart();
        // get cart item list
        List<CartItem> cartItems = cart.getCartItemList();
        CartItem deletedItem = null;

        // delete cart item with id cartItemId
        for(CartItem cartItem: cartItems) {
            if(cartItem.getId()==cartItemId) {
                cartItems.remove(cartItem);
                deletedItem = cartItem;
                break;
            }
        }

        cart.setCartItemList(cartItems);
        cart.setCartTotal(cart.getCartTotal()-(deletedItem.getFoodItem().getPrice()*deletedItem.getQuantity()));
        cartRepository.save(cart);

        if(deletedItem==null) throw new RuntimeException("No such Cart Item!");

        cartItemRepository.deleteById(cartItemId);
        return CartItemTransformer.cartItemToCartItemResponse(deletedItem);

    }
}
