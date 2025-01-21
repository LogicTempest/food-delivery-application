# Project Name:
Food-delivery system using microservices and spring-boot.

# Features:

1. Three microservices: order-service, ride-service and payment-service.
2. Ride-service communicates with the driver-service to get the order and vehicle details while ordering.
3. Payment-service collects information regarding the order status before generating the payment procedures.

# Flow of control;

Order-Service ----> Ride-Service ----> Payment-Service

# Tools Used:

1. Eureka server for registering the three services and to generate the dynamic addresses.
2. Postman for testing the REST API calls using GET, POST, PUT and DELETE methods.
3. MySQL Xaamp Server for database.

# Steps to run:

1. Run the eureka server and open the dashboard.
2. Make sure you have created three databases named order, ride and payment in your xaamp server.
3. Run all the three services and check if they are registered in the eureka dashboard.
4. Open Postman and type the url:

    http://localhost:8081/order/createOrder

    Set the method to POST
5. Add two driver entities in JSON format, for example:

    {
    "id": 1,
    "name": "Alice Johnson",
    "Food": "Dosa",
    "availability": true
    }

    {
   "id": 2,
   "name": "Bob Smith",
   "Food": "Paratha",
   "availability": false
   }

6. Next type the url:

    http://localhost:8082/rides/createRide

    Set the method to POST
7. Add one ride entity in JSON format, for example:

   {
   "id": 1,
   "userId": 101,
   "driverId": 1,
   "startLocation": "123 Main St, Springfield",
   "endLocation": "456 Elm St, Springfield",
   "status": "Completed",
   "createdAt": "2025-01-14T10:15:30",
   "updatedAt": "2025-01-14T11:15:30"
   }

8. Using the driverId, ride-service will fetch the driver details from the driver-service using REST API calls and displays the complete details.
9. Next type the url:

    http://localhost:8083/payments/createPayment

    Set the method to POST
10. Add one payment entity in JSON format, for example:

   {
   "id": 3,
   "rideId": 1,
   "amount": 250.75,
   "paymentStatus": "Completed",
   "paymentMethod": "Credit Card",
   "createdAt": "2025-01-14T10:30:45"
   }
   
11. Using the OrderId, payment-service will fetch the ride status from the ride-service using REST API calls and displays the complete payment details.

12. Download the demo video: screenrecording.mp4
