# Auth Microservice

A Spring Boot-based authentication microservice providing user registration, authentication (JWT), and user management APIs. It supports role-based access control and department assignments.

---

## Features

- User registration and login (JWT-based authentication)
- Role-based authorization (e.g., admin, user)
- Assign users to departments
- Secure password storage (BCrypt)
- RESTful API design

---

## Installation

### Prerequisites

- Java 17 or higher
- Maven 3.x
- (Optional) Docker & Docker Compose (if containerizing)

### Steps

1. **Clone the repository:**
   ```bash
   git clone https://github.com/larbi1512/auth-microservice.git
   cd auth-microservice
   ```

2. **Configure application properties:**
   - Edit `src/main/resources/application.properties` and set your database and JWT secret configuration.

   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/auth_db
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   jwt.secret=your_jwt_secret
   jwt.expiration=3600000
   ```

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   The service will run at `http://localhost:8080`.

---

## API Endpoints

### Authentication

- **POST /auth/register**  
  Register a new user.  
  **Request Body:**  
  ```json
  {
    "username": "string",
    "email": "string",
    "password": "string",
    "role": "USER" // or "ADMIN"
  }
  ```

- **POST /auth/login**  
  Authenticate user and receive a JWT token.  
  **Request Body:**  
  ```json
  {
    "username": "string", // or "email"
    "password": "string"
  }
  ```
  **Response:**  
  ```json
  {
    "token": "jwt_token_here"
  }
  ```

---

### User Management (Admin Only)

- **GET /users**  
  Get a list of all users.  
  *Requires `ADMIN` role.*

- **GET /users/{id}**  
  Get user details by ID.  
  *Requires `ADMIN` role.*

- **PUT /users/{id}**  
  Update a user's information.  
  *Requires `ADMIN` role.*

- **DELETE /users/{id}**  
  Delete a user.  
  *Requires `ADMIN` role.*

---

### Department Assignment

- **POST /users/{userId}/departments**  
  Assign a user to one or more departments.  
  **Request Body:**  
  ```json
  {
    "departmentIds": [1, 2, 3]
  }
  ```

- **GET /users/{userId}/departments**  
  List departments assigned to a user.

- **DELETE /users/{userId}/departments/{departmentId}**  
  Remove a user from a department.

---

## Security

- Uses JWT tokens for stateless authentication.
- Passwords stored securely using BCrypt.
- `/auth/**` endpoints are public.  
- `/users/**` endpoints are restricted to users with the `ADMIN` role.

---

## Testing

Run all tests with:
```bash
mvn test
```

---

## License

MIT License. See [LICENSE](LICENSE) for details.

---

## Authors

- [larbi saidchikh](https://github.com/larbi1512)
- [Maroua Bouderka](https://github.com/MarouaBouderka)
- [Nihal Hocine](https://github.com/NihalHocine)
- 
