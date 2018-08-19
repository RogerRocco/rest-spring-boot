# rest-spring-boot

## Introduction
This project was made to tide up a bunch of back-end skills using Java, Rest, MVC, Spring, OOP, TDD, Persistence Data and custom HTTP message status.

For deeper technical look about the code, check out at the end of this page.

## Summary

This is a Restful Project that works giving support for bank lip payment, in a web environment with custom HTTP message output when HTTP requested.

Also, calculate the fee depending on the due date for payment.    


## Requirements

 - JDK 1.8
- Spring Boot / Spring Framework
- JPA
- Maven
- JUnit
- SimpleJson
- HSQLDB
- JSON
- Postman (optional)
- Eclipse IDE (optional, use your preference)

## Installation and Getting Started
This project is ready to use. You just need to clone and import in Eclipse IDE.

## How to use
### Create new bank slip (using POST)
	localhost:8080/rest/bankslips
Custom header response messages:
- 201- Bankslip created
- 422 - A field of the provided bankslip was null or with invalid values
- 400 - Bankslip not provided in the request body
 
### Get all bank slips (using GET)
	localhost:8080/rest/bankslips
Custom header response messages:
- 200 - OK (standard response)
- 404 - No Bankslips was found
### Get one specific bank slip by id (using GET)
	localhost:8080/rest/bankslips/{id}
Custom header response messages:
- 200 - OK (standard response)
- 404 - Bankslip not found with the specified id
### Change the bank slip payment status to "PAID" or "CANCELED" (using PUT)
	localhost:8080/rest/bankslips
Custom header response messages:,
- 200- Bankslip cancelled
- 200- Bankslip paid
- 404 - Bankslip not modified. The status is the same or ID of link does not corresponding the ID of body or Status was invalid
## Valid JSON Structures

Full json structure:
>
	[
		{
	        "total_in_cents": 1000,
	        "fine": 70,
	        "due_date": "2018-01-01",
	        "id": "068c2cfd-44d3-4006-9d3b-d7fbec0d50f0",
	        "customer": "A Nice Company",
	        "status": "PENDING"
	    }
    ]


Required Structure to create a new bank slip
>
	{
		"due_date" : "2018-01-01",
		"total_in_cents" : "100000",
		"customer" : "A Nice Company",
		"status" : "PENDING"
	}
	

Required Structure to change payment status
>
	{
		"status" : "PENDING"
	}
				or
	{
		"status" : "CANCELED"
	}


## Technical Look about the code features
The theoretical feature of coding: use of Inheritance, Encapsulation, Polymorphism; as well as the use of S.O.L.I.D and CLEAN code.

The technical feature of code: 
- MVC, 
- Generics, 
- Interface, 
- Abstraction, 
- Optional class, 
- POJOs, 
- DAOs, 
- Enums, 
- Functions and Methods, 
- Annotations, 
- Monetary and Date Calculations, 
- Throwing Exceptions, 
- Scope 
- and CamelCase.
