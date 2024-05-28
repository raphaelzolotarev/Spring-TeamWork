# Spring-TeamWork

## Overview
Spring-TeamWork is a collaborative project built using the Spring framework. This project serves as a demonstration of teamwork in developing a comprehensive application that integrates various Spring components and best practices.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Modules](#modules)
- [Database](#database)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Features
- Java-based backend
- Spring Boot integration
- RESTful APIs
- Data persistence with Spring Data JPA
- Security with Spring Security
- Comprehensive unit and integration testing

## Prerequisites
- Java 11 or higher
- Maven 3.6.3 or higher
- MySQL or any preferred relational database

## Getting Started
1. Clone the repository:
    ```bash
    git clone https://github.com/raphaelzolotarev/Spring-TeamWork.git
    ```
2. Navigate to the project directory:
    ```bash
    cd Spring-TeamWork
    ```
3. Configure the database in `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/spring_teamwork
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```
4. Build the project:
    ```bash
    mvn clean install
    ```
5. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## Project Structure
Spring-TeamWork/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/
│ │ │ └── teamwork/
│ │ │ ├── config/ # Configuration files
│ │ │ ├── controller/ # REST controllers
│ │ │ ├── dto/ # Data Transfer Objects
│ │ │ ├── model/ # Entity models
│ │ │ ├── repository/ # Repositories
│ │ │ ├── service/ # Service layer
│ │ │ └── SpringTeamworkApplication.java # Main application file
│ │ └── resources/
│ │ ├── static/ # Static resources
│ │ ├── templates/ # Thymeleaf templates
│ │ └── application.properties # Application properties
│ └── test/ # Test files
├── .gitignore
├── README.md
└── pom.xml

## Modules
### Config
Contains configuration classes for Spring Security and other configurations.

### Controller
Houses the REST controllers that handle HTTP requests and responses.

### DTO
Includes Data Transfer Objects used to transfer data between processes.

### Model
Defines the entity classes that map to the database tables.

### Repository
Interfaces for database operations, extending Spring Data JPA repositories.

### Service
Contains the service layer with business logic.

## Database
The project uses a relational database (e.g., MySQL). Make sure to update the `application.properties` file with your database credentials.

## API Endpoints
- **User Controller**
  - `GET /users` - Retrieve all users
  - `POST /users` - Create a new user
  - `GET /users/{id}` - Retrieve user by ID
  - `PUT /users/{id}` - Update user by ID
  - `DELETE /users/{id}` - Delete user by ID

- **Task Controller**
  - `GET /tasks` - Retrieve all tasks
  - `POST /tasks` - Create a new task
  - `GET /tasks/{id}` - Retrieve task by ID
  - `PUT /tasks/{id}` - Update task by ID
  - `DELETE /tasks/{id}` - Delete task by ID
    ## 🖥️ HTML Templates
The project includes several HTML templates located in `src/main/resources/templates`:
- **index.html**: The home page of the application.
- **users.html**: Page for managing users.
- **tasks.html**: Page for managing tasks.

## Testing
Unit and integration tests are included to ensure the correctness of the application. Run the tests using:
```bash
mvn test
