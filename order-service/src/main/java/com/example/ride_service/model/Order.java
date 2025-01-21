package com.example.order_service.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Order {
    @Id
    private Long id;
    @Column
    private Long userId;
    @Column
    private Long restaurantId;
    @Column
    private String orderDetails;
    @Column
    private String deliveryAddress;
    @Column
    private String status;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;
    @Column
    private String restaurantName;
    @Column
    private String restaurantLocation;

    public Order() {}

    public Order(Long id, Long userId, Long restaurantId, String orderDetails,
                 String deliveryAddress, String status, LocalDateTime createdAt,
                 LocalDateTime updatedAt, String restaurantName, String restaurantLocation) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.orderDetails = orderDetails;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }
}
