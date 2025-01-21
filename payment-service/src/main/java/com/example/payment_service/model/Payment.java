package com.example.payment_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generated ID
    private Long id;

    @Column(nullable = false)
    private Long orderId;  // Changed from rideId to orderId

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false, length = 20)  // Length added for paymentStatus
    private String paymentStatus;

    @Column(nullable = false, length = 20)  // Length added for paymentMethod
    private String paymentMethod;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, length = 20)  // Length added for orderStatus
    private String orderStatus;  // Changed from rideStatus to orderStatus

    // Default constructor
    public Payment() {}

    // Parameterized constructor
    public Payment(Long id, Long orderId, double amount, String paymentStatus,
                   String paymentMethod, LocalDateTime createdAt, String orderStatus) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
        this.orderStatus = orderStatus;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
