package com.example.ride_service.controller;

import com.example.ride_service.model.Ride;
import com.example.ride_service.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.example.ride_service.model.DriverDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rides")
public class RideController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RideRepository rideRepository;

    @PostMapping("/createRide")
    public Ride createRide(@RequestBody Ride ride) {
        String driverUrl = "http://driver-service/drivers/getDriver/" + ride.getDriverId();  // Call to the driver service
        ResponseEntity<DriverDTO> driverResponse = restTemplate.getForEntity(driverUrl, DriverDTO.class); // Fetch the DriverDTO
        DriverDTO driver = driverResponse.getBody();  // Get the DriverDTO from the response
        if (driver == null) {
            throw new RuntimeException("Driver not found");  // If driver not found, throw error
        }
        ride.setDriverId(driver.getId());
        ride.setDriverName(driver.getName());
        ride.setDriverLicenseNumber(driver.getLicenseNumber());
        ride.setDriverVehicleType(driver.getVehicleType());
        ride.setStatus("ONGOING");
        ride.setCreatedAt(LocalDateTime.now());
        ride.setUpdatedAt(LocalDateTime.now());
        return rideRepository.save(ride);
    }

    @GetMapping("/getRide/{id}")
    public ResponseEntity<Ride> getRide(@PathVariable Long id) {
        Optional<Ride> ride = rideRepository.findById(id);
        return ride.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAllRides")
    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    @PutMapping("/updateRide/{id}")
    public ResponseEntity<Ride> updateRide(@PathVariable Long id, @RequestBody Ride updatedRide) {
        return rideRepository.findById(id).map(ride -> {
            ride.setStatus(updatedRide.getStatus());
            ride.setUpdatedAt(LocalDateTime.now());
            rideRepository.save(ride);
            return ResponseEntity.ok(ride);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteRide/{id}")
    public ResponseEntity<Void> deleteRide(@PathVariable Long id) {
        if (rideRepository.existsById(id)) {
            rideRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

