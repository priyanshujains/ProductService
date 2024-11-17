Product Service

The Product Service is a microservice that manages product information in an e-commerce application. 

It is designed to:
Use User Service for OAuth2 authentication to obtain client credentials (client ID and secret).
Generate tokens for secure communication.
Support paging and sorting for retrieving product details efficiently.

Features:
OAuth2 Integration: Connects with the User Service to obtain a client ID and secret, and uses these to generate OAuth2 tokens.
Token Generation: Tokens are used for secure API communication between services.
Paging and Sorting: Implements pagination and sorting mechanisms to handle large product datasets effectively.


Technologies Used:
Java 17: Programming language.
Spring Boot: Framework for building microservices.
Spring Security: For authentication and authorization.
OAuth2: Protocol for secure token-based communication.
Hibernate: ORM for database interaction.
Spring Data JPA: For database operations.
MySQL: Relational database for storing product data.
Maven: Build tool.
