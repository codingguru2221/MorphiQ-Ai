# MorphiQ - Adaptive AI Assistant

An innovative AI chatbot built entirely in Java that adapts its behavior based on the user's profession and work style.

## 🚀 Project Overview

MorphiQ is a behavior-adaptive AI assistant designed to understand and align with the user's profession and work style. Whether you're a doctor, engineer, teacher, or lawyer, the assistant adapts its tone, workflow, and decision patterns to improve comfort, trust, and usability.

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database** (In-Memory)
- **Lombok**
- **OpenAI Java SDK**
- **LangChain4j**

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/com/morphiq/morphiq/
│   │   ├── controller/     # REST API controllers
│   │   ├── service/        # Business logic
│   │   ├── model/          # JPA entities
│   │   ├── repository/     # Spring Data repositories
│   │   ├── dto/            # Data Transfer Objects
│   │   ├── config/         # Configuration classes
│   │   └── MorphiQApplication.java
│   └── resources/
│       ├── static/         # Frontend files
│       └── application.properties
└── test/
```

## 🚀 Getting Started

### Prerequisites

- Java 17
- Maven 3.8+
- IDE (IntelliJ IDEA, Eclipse, etc.)

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```bash
   cd MorphiQ
   ```

3. Build the project:
   ```bash
   ./mvnw clean install
   ```

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

### Accessing the Application

- **Frontend UI**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: jdbc:h2:mem:morphiqdb
  - Username: sa
  - Password: (empty)

### API Endpoints

- `POST /api/chat/message` - Send a message to the AI assistant

Example request:
```json
{
  "userId": "user123",
  "profession": "doctor",
  "message": "What are the symptoms of diabetes?"
}
```

## 🎯 Features

- **Profession-based Adaptation**: The AI adjusts its responses based on the user's profession
- **User Profile Management**: Stores user preferences and behavior patterns
- **Conversation History**: Maintains conversation context
- **RESTful API**: Clean and intuitive API design
- **Responsive Web Interface**: Simple chat interface for testing

## 📅 Project Timeline

- **Development Start**: December 2025
- **Goal Completion**: December 2025
- **Official Launch**: January 2026

## 🤝 Contributing

Contributions are welcome! Feel free to fork the repository and submit pull requests.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- OpenAI for their powerful language models
- LangChain4j for Java integration with language models
- Spring Boot community for excellent frameworks