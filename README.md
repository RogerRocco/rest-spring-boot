# rest-spring-boot

## Introduction
This project was made to tide up a bunch of back-end skills using Java, Rest, MVC, Spring, OOP, TDD and Persistence Data. For deeper technical look about the code, check out at the end of this page.

## Summary
This is a Restful Project that works giving support for bank lip payment. 
Also, calculate the fee depending on the due date for debt.    

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
To create new bank slip (using POST)

	localhost:8080/rest/bankslips
To get all bank slips (using GET)
>
	localhost:8080/rest/bankslips

To get one specific bank slip by id (using GET)
>
	localhost:8080/rest/bankslips/{id}
Change the bank slip payment status to "PAID" or "CANCELED" (using PUT)
>
	localhost:8080/rest/bankslips

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
