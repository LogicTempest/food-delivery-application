package com.example.payment_service.controller;

import com.example.payment_service.model.Payment;
import com.example.payment_service.model.OrderDTO;
import com.example.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentRepository paymentRepository;

    // Create a payment for an order
    @PostMapping("/createPayment")
    public Payment createPayment(@RequestBody Payment payment) {
        String orderUrl = "http://order-service/orders/getOrder/" + payment.getOrderId();
        ResponseEntity<OrderDTO> orderResponse = restTemplate.getForEntity(orderUrl, OrderDTO.class); // Fetch the OrderDTO
        OrderDTO order = orderResponse.getBody(); // Get the OrderDTO from the response
        if (order == null) {
            throw new RuntimeException("Order not found"); // If order not found, throw error
        }
        payment.setCreatedAt(LocalDateTime.now());
        payment.setOrderStatus(order.getStatus());
        return paymentRepository.save(payment);
    }

    // Get payment details by ID
    @GetMapping("/getPayment/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Get all payment records
    @GetMapping("/getAllPayments")
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Update payment status
    @PutMapping("/updatePayment/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment updatedPayment) {
        return paymentRepository.findById(id).map(payment -> {
            payment.setPaymentStatus(updatedPayment.getPaymentStatus());
            paymentRepository.save(payment);
            return ResponseEntity.ok(payment);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete payment record
    @DeleteMapping("/deletePayment/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
