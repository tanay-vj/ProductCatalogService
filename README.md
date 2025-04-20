# Product Catalog Service

# Overview
The **Product Catalog Service** is a microservice responsible for managing product-related data, including creation, storage, retrieval, searching, paging, and sorting of products. It is built using **Spring Boot** and follows a **RESTful API** approach for seamless integration with other services.

## Features
- **Product Management**: Create, update, delete, and retrieve product details.
- **Search & Filtering**: Search products by name, category, and other attributes.
- **Paging & Sorting**: Efficiently handle large datasets with pagination and sorting capabilities.
- **Database Integration**: Uses **JPA & Hibernate** for data persistence.
- **UUID Implementation**: Ensures unique product identification.

## Technologies Used
- **Spring Boot** (REST APIs, MVC, Security, JPA)
- **Hibernate & JPA** (Database connectivity)
- **MySQL** (DB Storage)

## API Endpoints
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/products` | Add a new product |
| `GET` | `/products/{id}` | Get product details by ID |
| `GET` | `/products` | Get all products with pagination & sorting |
| `PUT` | `/products/{id}` | Update an existing product |
| `DELETE` | `/products/{id}` | Remove a product |

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/product-catalog-service.git
   ```
2. Navigate to the project directory:
   ```bash
   cd product-catalog-service
   ```
3. Update **application.properties** with your database credentials.
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
5. API will be available at `http://localhost:8080/products`
