# 📁 Project Structure

🚀 Spring Boot AI Project – Setup Guide

This guide explains how to set up and run the Spring Boot AI project locally.

📋 Prerequisites

Make sure you have the following installed:

Java 17+
Maven
Git
curl or Postman (for testing APIs)

Check installations:
Check installations:

java -version
mvn -version
git --version

📦 Clone the Project
git clone https://github.com/your-username/spring-ai-project.git

cd spring-ai-project

Current project structure:

```
src/
 └── main/
      ├── java/com/example/ai/
      │   ├── Application.java
      │   ├── AIController.java
      │   └── AIService.java
      │
      └── resources/
          └── application.properties
```

### File Roles

| File | Purpose |
|-----|------|
| Application.java | Entry point of the Spring Boot application |
| AIController.java | Handles HTTP requests and API endpoints |
| AIService.java | Contains the core AI and business logic |
| application.properties | Application configuration |

---

# 📦 Current Directory Walkthrough

From your terminal:

```bash
ls src/main/java/com/example/ai
```

Output:

```
AIController.java
AIService.java
Application.java
```

Configuration files are located in:

```
src/main/resources/application.properties
```

---

# 🧭 Recommended Future Structure (Best Practice)

As the project grows, you should restructure it like this:

```
src/main/java/com/example/ai
│
├── controller
│   └── AIController.java
│
├── service
│   └── AIService.java
│
├── model
│   └── Analysis.java
│
├── repository
│   └── AnalysisRepository.java
│
└── Application.java
```

Benefits:

• Better separation of concerns  
• Easier maintenance  
• Standard Spring Boot structure  
• Easier collaboration

---

# 📦 How to Refactor to This Structure

Create folders:

```bash
mkdir controller service model repository
```

Move files:

```bash
mv AIController.java controller/
mv AIService.java service/
```

Update package names:

Example:

```java
package com.example.ai.controller;
```
