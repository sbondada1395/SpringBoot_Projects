# SpringBoot_Projects

Price Reduction service for John Lewis

Introduction 

This project calculates and display the price reduction on items for sale. The "now" price  and discount is calculated based on the reduction on items.

Running the Application Locally
There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the main method in the com.johnlewis.pricereduction.demo.JohnLewisPriceReductionApplication class from your IDE.

mvn spring-boot:run

How to test the application?

  a. Need Postman to run the rest service
  b. GET request http://localhost:8080/products?labelType=s&dresses=d 
  
      queryParam:
      labelType is optional not used
      dresses=d is not used in the logic(yet add the condition on queryParam "dresses" in the code)
     Sample Compressed Response : added the response received in response.txt
    

Files:
Main class with main() method : JohnLewisPriceReductionApplication.java
The Spring Rest controller : JLPriceReductionController.java
The logic to calculate the price :PriceReductionResponseBuilder.java


Test cases:-
Not completed
