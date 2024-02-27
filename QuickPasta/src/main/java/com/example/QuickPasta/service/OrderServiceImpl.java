package com.example.QuickPasta.service;

import com.example.QuickPasta.Enum.OrderStatus;
import com.example.QuickPasta.dto.response.OrderResponse;
import com.example.QuickPasta.model.*;
import com.example.QuickPasta.repository.CartItemRepository;
import com.example.QuickPasta.repository.CartRepository;
import com.example.QuickPasta.repository.CustomerRepository;
import com.example.QuickPasta.repository.OrderRepository;
import com.example.QuickPasta.serviceInterface.OrderService;
import com.example.QuickPasta.transformer.OrderTransformer;
import com.example.QuickPasta.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    final CustomerRepository customerRepository;
    final CartItemRepository cartItemRepository;
    final CartRepository cartRepository;
    final OrderRepository orderRepository;


    @Autowired
    public OrderServiceImpl(CustomerRepository customerRepository, CartItemRepository cartItemRepository, CartRepository cartRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;

    }

    @Override
    public OrderResponse placeOrder(int customerId) {
        ValidationUtils.validateCustomer(customerRepository,customerId);
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Customer customer = customerOptional.get();

        List<CartItem> cartItemList = new ArrayList<>(customer.getCart().getCartItemList());
        List<RestaurantOrderItem> restaurantOrderItemList = new ArrayList<>();

        HashMap<Integer,List<CartItem>> map = new HashMap<>();
        for(CartItem cartItem: cartItemList) {
            int restId = cartItem.getFoodItem().getRestaurant().getId();
            List<CartItem> temp = map.getOrDefault(restId,new ArrayList<>());
            temp.add(cartItem);
            map.put(restId,temp);
        }

        for(int key: map.keySet()) {
            int restId = key;
            List<CartItem> restItems = map.get(key);
            int total = 0;
            for(CartItem cartItem: restItems) {
                total += (cartItem.getQuantity()*cartItem.getFoodItem().getPrice());
            }
            RestaurantOrderItem restaurantOrderItem = RestaurantOrderItem.builder()
                    .restaurantId(key)
                    .totalPrice(total)
                    .status(OrderStatus.RECEIVED)
                    .cartItemList(restItems)
                    .build();
            restaurantOrderItemList.add(restaurantOrderItem);
        }
        OrderEntity order = OrderEntity.builder()
                .orderTotal(customer.getCart().getCartTotal())
                .orderId(String.valueOf(UUID.randomUUID()))
                .orderItemList(restaurantOrderItemList)
                .customer(customer)
                .build();

        customer.getOrderList().add(order);
        customer.getCart().getCartItemList().clear();
        customerRepository.save(customer);

        OrderEntity savedOrder = orderRepository.save(order);
        return OrderTransformer.orderToOrderResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> viewAllOrders(int customerId) {
        List<OrderEntity> orderEntityList = orderRepository.findByCustomerId(customerId);
        List<OrderResponse> orderResponseList = new ArrayList<>();

        for(OrderEntity order: orderEntityList) {
            orderResponseList.add(OrderTransformer.orderToOrderResponse(order));
        }
        return orderResponseList;
    }
}
