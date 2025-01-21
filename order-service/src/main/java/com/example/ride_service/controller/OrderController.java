package com.example.order_service.controller;

import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.example.order_service.model.RestaurantDTO;
import com.example.order_service.model.DeliveryPersonDTO; // Added the DeliveryPersonDTO

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/createOrder")
    public Order createOrder(@RequestBody Order order) {
        // Fetch the restaurant details
        String restaurantUrl = "http://restaurant-service/restaurants/getRestaurant/" + order.getRestaurantId();  
        ResponseEntity<RestaurantDTO> restaurantResponse = restTemplate.getForEntity(restaurantUrl, RestaurantDTO.class); 
        RestaurantDTO restaurant = restaurantResponse.getBody();
        
        if (restaurant == null) {
            throw new RuntimeException("Restaurant not found");
        }
        
        // Fetch the delivery person details (assuming delivery service exists)
        String deliveryPersonUrl = "http://delivery-service/deliveryPerson/getDeliveryPerson/" + order.getDeliveryPersonId();
        ResponseEntity<DeliveryPersonDTO> deliveryPersonResponse = restTemplate.getForEntity(deliveryPersonUrl, DeliveryPersonDTO.class);
        DeliveryPersonDTO deliveryPerson = deliveryPersonResponse.getBody();
        
        if (deliveryPerson == null) {
            throw new RuntimeException("Delivery person not found");
        }

        // Setting order details
        order.setRestaurantId(restaurant.getId());
        order.setRestaurantName(restaurant.getName());
        order.setRestaurantLocation(restaurant.getLocation());
        order.setDeliveryPersonName(deliveryPerson.getName());
        order.setDeliveryPersonContact(deliveryPerson.getContactNumber());
        order.setDeliveryPersonVehicleType(deliveryPerson.getVehicleType());
        order.setStatus("ONGOING");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        
        // Save the order to the database
        return orderRepository.save(order);
    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAllOrders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return orderRepository.findById(id).map(order -> {
            order.setStatus(updatedOrder.getStatus());
            order.setUpdatedAt(LocalDateTime.now());
            orderRepository.save(order);
            return ResponseEntity.ok(order);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
