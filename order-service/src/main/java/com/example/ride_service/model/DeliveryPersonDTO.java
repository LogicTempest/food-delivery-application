package com.example.order_service.model;

public class DeliveryPersonDTO {
    private Long id; // Unique delivery person ID
    private String name; // Name of the delivery person
    private String contactNumber; // Contact number of the delivery person
    private String vehicleType; // The type of vehicle used for delivery (e.g., bike, car)
    private boolean isAvailable; // Availability status of the delivery person (whether they're free to take orders)

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
