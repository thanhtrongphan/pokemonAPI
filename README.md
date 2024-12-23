# Pokemon Review API

This project is a Spring Boot application that provides a RESTful API for managing Pokemon reviews. It includes endpoints for user authentication, Pokemon management, and review management.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Maven
- JPA (Java Persistence API)
- Lombok

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/thanhtrongphan/pokemon-review-api.git
    cd pokemon-review-api
    ```

2. Build the project using Maven:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## API Endpoints

### Authentication

- **Register a new user**
    ```http
    POST /api/auth/register
    ```
    Request Body:
    ```json
    {
        "username": "string",
        "password": "string"
    }
    ```

- **Login**
    ```http
    POST /api/auth/login
    ```
    Request Body:
    ```json
    {
        "username": "string",
        "password": "string"
    }
    ```

### Pokemon

- **Get all Pokemon**
    ```http
    GET /api/pokemon
    ```

- **Get a Pokemon by ID**
    ```http
    GET /api/pokemon/{id}
    ```

- **Create a new Pokemon**
    ```http
    POST /api/pokemon
    ```
    Request Body:
    ```json
    {
        "name": "string",
        "type": "string"
    }
    ```

- **Update a Pokemon**
    ```http
    PUT /api/pokemon/{id}
    ```
    Request Body:
    ```json
    {
        "name": "string",
        "type": "string"
    }
    ```

- **Delete a Pokemon**
    ```http
    DELETE /api/pokemon/{id}
    ```

### Reviews

- **Get all reviews**
    ```http
    GET /api/review
    ```

- **Get reviews by Pokemon ID**
    ```http
    GET /api/review/{pokemonId}
    ```

- **Create a new review**
    ```http
    POST /api/review/create
    ```
    Request Body:
    ```json
    {
        "content": "string",
        "rating": "integer",
        "pokemonId": "integer"
    }
    ```

