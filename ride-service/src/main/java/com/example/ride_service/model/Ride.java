package com.example.ride_service.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Ride {
    @Id private Long id;
    @Column private Long userId;
    @Column private Long driverId;
    @Column private String startLocation;
    @Column private String endLocation;
    @Column private String status;
    @Column private LocalDateTime createdAt;
    @Column private LocalDateTime updatedAt;
    @Column private String driverName;
    @Column private String driverLicenseNumber;
    @Column private String driverVehicleType;

    public Ride() {}
    public Ride(Long id, Long userId, Long driverId, String startLocation,
                         String endLocation, String status, LocalDateTime createdAt,
                         LocalDateTime updatedAt, String driverName, String driverLicenseNumber, String driverVehicleType) {
        this.id = id;
        this.userId = userId;
        this.driverId = driverId;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.driverName = driverName;
        this.driverLicenseNumber = driverLicenseNumber;
        this.driverVehicleType = driverVehicleType;
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

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
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

    public void setDriverName(String driverName) { this.driverName = driverName; }

    public String getDriverName() { return driverName; }

    public void setDriverLicenseNumber(String driverLicenseNumber) { this.driverLicenseNumber = driverLicenseNumber; }

    public String getDriverLicenseNumber() { return driverLicenseNumber; }

    public void setDriverVehicleType(String driverVehicleType) { this.driverVehicleType = driverVehicleType; }

    public String getDriverVehicleType() { return driverVehicleType; }

}



