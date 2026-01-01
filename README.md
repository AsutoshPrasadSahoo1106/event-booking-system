# 🔐 Secure Online Event Booking System

A backend REST API built using **Spring Boot** that allows users to register, authenticate using **JWT**, browse events, and book tickets securely with proper business rules and exception handling.

This project demonstrates **real-world backend development practices** such as layered architecture, authentication & authorization, DTO usage, and database handling.

---

## 🚀 Features

### 🔑 Authentication & Security
- User Registration & Login
- JWT-based Authentication
- Role-based Authorization (`USER`, `ORGANIZER`)
- Password encryption using BCrypt
- Stateless REST APIs (no sessions)

### 🎉 Event Management
- Create Events (Organizer only)
- Update Events (Organizer only)
- Delete Events (Organizer only)
- View All Events (Public)
- View Event by ID (Public)

### 🎟️ Booking Management
- Book Event Tickets (User only)
- Cancel Booking (User only)
- View Booking History
- Prevent duplicate bookings
- Automatic seat availability management

### ⚠️ Error Handling
- Centralized global exception handling
- Validation using `@Valid`
- Proper HTTP status codes

---

## 🧩 Tech Stack

- **Java**
- **Spring Boot**
- Spring Security (JWT)
- Spring Data JPA & Hibernate
- MySQL / H2 Database
- Maven
- ModelMapper
- Git & GitHub
- Postman (API Testing)

---
## 🧠 Business Rules Implemented

- A user can book an event only once
- Booking is allowed only if seats are available
- Cancelling a booking restores seat count
- Event total seats cannot be reduced below already booked seats

---

## ⚙️ Configuration

Sensitive configuration is **not committed** to GitHub.

Create your own `application.properties` file using the template:
src/main/resources/application.properties.example
---

## 🧪 Testing

- APIs tested using **Postman**
- JWT token passed via `Authorization: Bearer <token>`
- Security, validation, and edge cases verified

---
## 👨‍💻 Author

**Asutosh Prasad Sahoo**  
Backend Developer | Java | Spring Boot

---