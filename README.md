# Todo App

A simple Todo application built with Java, Spring Boot, and Vue.js.

The application allows users to manage their tasks through a web interface. It includes user authentication and stores data in a MySQL database.

## Features

- User registration and authentication
- JWT-based authentication
- Create, view, update, and delete tasks
- REST API
- MySQL database integration
- Database migrations with Flyway
- Docker and Docker Compose support

## Tech Stack

**Backend:**
- Java 25
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT
- Maven

**Frontend:**
- Vue.js
- Node.js

**Database & Tools:**
- MySQL 8.4
- Flyway
- Docker
- Docker Compose

## Requirements

To run the application locally, you need:

- Docker
- Docker Compose

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/melekv/java-todo.git
cd java-todo
```

### 2. Start the application

Build and start the application using Docker Compose:

```bash
docker compose up --build
```

### 3. Access the application

Once the containers are running, open:

- **Frontend:** http://localhost:5173
- **Backend API:** http://localhost:8080

## Project Structure

The repository contains two main parts:

- `spring/` – Spring Boot backend
- `frontend/` – Vue.js frontend

The backend is responsible for business logic, authentication, REST API endpoints, and database communication.

The frontend provides the user interface for interacting with the application.

## Purpose

This project was created to learn Java and Spring Boot, practice backend development, and explore application containerization with Docker.

## Author

[melekv](https://github.com/melekv)
