# Programming Courses Platform API

A robust REST API for managing programming courses, built with Spring Boot and featuring JWT authentication, PostgreSQL database, and comprehensive course management capabilities.

## 🚀 Overview

This application provides a complete backend solution for a programming courses platform, allowing course owners to create, manage, and organize their educational content. The API includes user authentication, course CRUD operations, and role-based access control.

## 🛠️ Technologies & Stack

### Core Framework
- **Spring Boot 3.5.3** - Main application framework
- **Java 17** - Programming language
- **Maven** - Dependency management and build tool

### Database & Persistence
- **PostgreSQL** - Primary database
- **Spring Data JPA** - Object-relational mapping
- **Hibernate** - JPA implementation

### Security & Authentication
- **Spring Security** - Security framework
- **JWT (JSON Web Tokens)** - Stateless authentication
- **BCrypt** - Password encryption

### Additional Dependencies
- **Lombok** - Reduces boilerplate code
- **Spring Validation** - Input validation
- **Docker Compose** - Container orchestration

## 🏗️ Architecture

The application follows a layered architecture pattern:

```
src/main/java/com/leonardosanner/programming_courses/
├── config/                 # Configuration classes
├── controller/            # REST API endpoints
│   ├── course/           # Course-related endpoints
│   └── owner/            # Owner management endpoints
├── dto/                  # Data Transfer Objects
│   ├── course/           # Course DTOs
│   └── owner/            # Owner DTOs
├── entity/               # JPA entities
│   ├── course/           # Course entities
│   └── owner/            # Owner entities
├── exception/            # Custom exceptions and handlers
├── repository/           # Data access layer
├── service/              # Business logic layer
│   ├── persistenceService/  # Data persistence services
│   ├── security/            # Security services
│   └── useCases/            # Business use cases
└── ProgrammingCoursesApplication.java
```

## 🔐 Security Features

- **JWT-based Authentication**: Stateless token-based authentication
- **Role-based Access Control**: Different permissions for owners and users
- **Password Encryption**: BCrypt hashing for secure password storage
- **Input Validation**: Comprehensive validation using Bean Validation
- **CORS Configuration**: Cross-origin resource sharing setup

## 📋 API Endpoints

### Owner Management (`/owner`)
- `POST /owner/` - Create new owner account
- `GET /owner/` - List all owners
- `POST /owner/auth/token` - Authenticate and generate JWT token

### Course Management (`/course`)
- `POST /course/` - Create new course (requires authentication)
- `GET /course/` - Search courses by name/category
- `PUT /course/{id}` - Update course (owner only)
- `DELETE /course/{id}` - Delete course (owner only)
- `PATCH /course/{id}/active` - Toggle course status (owner only)

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose
- PostgreSQL (or use Docker)

### Environment Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd programming_courses
   ```

2. **Set up environment variables**
   Create a `.env` file in the root directory:
   ```env
   POSTGRES_USER=your_username
   POSTGRES_PASSWORD=your_password
   POSTGRES_DB=programming_courses_db
   ```

3. **Configure application properties**
   Copy the example configuration:
   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```
   
   Update `src/main/resources/application.properties` with your database settings:
   ```properties
   server.port=8080
   spring.datasource.url=jdbc:postgresql://localhost:5432/programming_courses_db
   spring.datasource.username=${POSTGRES_USER}
   spring.datasource.password=${POSTGRES_PASSWORD}
   spring.jpa.hibernate.ddl-auto=update
   
   # JWT Configuration
   spring.security.jwt.owner.secret=your_jwt_secret_key_here
   spring.security.jwt.issuer=programming-courses-api
   ```

### Running the Application

1. **Start the database using Docker**
   ```bash
   docker-compose up -d db
   ```

2. **Build and run the application**
   ```bash
   # Using Maven wrapper
   ./mvnw clean install
   ./mvnw spring-boot:run
   
   # Or using Maven directly
   mvn clean install
   mvn spring-boot:run
   ```

3. **Access the API**
   The application will be available at `http://localhost:8080`

### Using Docker (Future Implementation)
The project includes a `docker-compose.yml` file with database configuration. A Dockerfile for the application will be added in future updates.

## 📝 Usage Examples

### Creating an Owner Account
```bash
curl -X POST http://localhost:8080/owner/ \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "securepassword123"
  }'
```

### Authenticating and Getting JWT Token
```bash
curl -X POST http://localhost:8080/owner/auth/token \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "securepassword123"
  }'
```

### Creating a Course (with JWT token)
```bash
curl -X POST http://localhost:8080/course/ \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "name": "Spring Boot Fundamentals",
    "category": "Backend Development"
  }'
```

## 🔧 Development

### Project Structure
- **Controllers**: Handle HTTP requests and responses
- **Services**: Contain business logic and data processing
- **Repositories**: Data access layer for database operations
- **DTOs**: Data transfer objects for API communication
- **Entities**: JPA entities representing database tables
- **Exceptions**: Custom exception handling

### Key Features
- **Validation**: Comprehensive input validation using Bean Validation
- **Error Handling**: Global exception handlers for consistent error responses
- **Security**: JWT-based authentication with role-based access control
- **Database**: PostgreSQL with JPA/Hibernate for data persistence

## 🚧 Project Status

This project is in **constant evolution** and active development. New features, improvements, and optimizations are regularly added. The current version includes:

- ✅ Basic CRUD operations for courses
- ✅ Owner authentication and authorization
- ✅ JWT token-based security
- ✅ Input validation and error handling
- ✅ PostgreSQL database integration
- 🔄 Docker containerization (in progress)
- 🔄 Additional course features (planned)
- 🔄 User enrollment system (planned)
- 🔄 Course content management (planned)

## 🤝 Contributing

This is a learning project that's constantly evolving. Feel free to explore the codebase and understand the implementation patterns used.

## 📄 License

This project is part of a learning journey and is not intended for commercial use.

---

**Note**: This application is designed as a learning platform and is continuously being improved with new features and best practices. 