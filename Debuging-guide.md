# 🐛 Debugging Guide - Spring Boot AI Project

Comprehensive guide for troubleshooting common issues, understanding errors, and debugging your Spring Boot AI application.

---

# 📋 Table of Contents

* Common Errors and Solutions
* Debugging Techniques
* Logging
* Testing with curl
* Testing with Postman
* Performance Issues
* Database Issues
* Network Issues

---

# ❌ Common Errors and Solutions

## 1️⃣ Connection Refused Error

**Error**

```
curl: (7) Failed to connect to localhost port 8080
```

**Cause**

The Spring Boot application is not running.

**Solution**

Start the application:

```bash
mvn spring-boot:run
```

or

```bash
java -jar target/ai-project-1.0.0.jar
```

Verify it is running:

```bash
curl http://localhost:8080/api/ai/health
```

Expected response:

```json
{
 "status": "UP"
}
```

---

# 2️⃣ Port Already in Use

**Error**

```
Port 8080 was already in use
```

**Cause**

Another application is already using port `8080`.

**Solution**

Find the process:

```bash
lsof -i :8080
```

Kill the process:

```bash
kill -9 PID
```

Or change the port.

Edit `application.properties`

```
server.port=8081
```

---

# 3️⃣ Maven Build Failure

**Error**

```
Failed to execute goal org.apache.maven.plugins
```

**Cause**

Dependencies failed to download or compile.

**Solution**

Clean and rebuild:

```bash
mvn clean install
```

If dependency issues persist:

```bash
mvn dependency:resolve
```

---

# 4️⃣ Endpoint Not Found

**Error**

```
404 Not Found
```

**Possible Causes**

* Wrong endpoint path
* Controller not loaded
* Missing annotations

**Check controller**

```java
@RestController
@RequestMapping("/api/ai")
public class AIController {
}
```

Correct endpoint example:

```
GET /api/ai/sentiment
```

---

# 🧰 Debugging Techniques

## Enable Debug Mode

Run the application with debug enabled:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--debug
```

This shows detailed startup logs.

---

# 🔎 Logging

Spring Boot uses **SLF4J + Logback** for logging.

Example logging inside a service:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AIService {

    private static final Logger log =
        LoggerFactory.getLogger(AIService.class);

    public String analyzeSentiment(String text){

        log.info("Analyzing sentiment for: {}", text);

        String result = calculateSentiment(text);

        log.debug("Result: {}", result);

        return result;
    }
}
```

Log levels:

```
TRACE
DEBUG
INFO
WARN
ERROR
```

---

# 🧪 Testing with curl

## Health Endpoint

```
curl http://localhost:8080/api/ai/health
```

---

## Sentiment Analysis

```
curl "http://localhost:8080/api/ai/sentiment?text=I%20love%20Spring"
```

Response

```json
{
 "input":"I love Spring",
 "sentiment":"Positive"
}
```

---

## Word Count Endpoint

```
curl "http://localhost:8080/api/ai/word-count?text=Hello%20Spring%20Boot"
```

Response

```json
{
 "text":"Hello Spring Boot",
 "wordCount":3
}
```

---

# 🧪 Testing POST APIs with curl

Example JSON request:

```
curl -X POST http://localhost:8080/api/ai/custom \
-H "Content-Type: application/json" \
-d '{"input":"Hello World","option":"uppercase"}'
```

Expected response:

```json
{
 "result":"HELLO WORLD"
}
```

---

# 🧪 Testing with Postman

Steps:

1. Open Postman
2. Create new request
3. Select method (GET / POST)
4. Enter URL

Example:

```
http://localhost:8080/api/ai/sentiment
```

Add query parameter:

```
text=Hello world
```

Click **Send**

---

# ⚡ Performance Issues

If API responses are slow:

### Enable Caching

Example:

```java
@Cacheable("sentiments")
public String analyzeSentiment(String text){
    return calculateSentiment(text);
}
```

---

### Increase Server Threads

Edit `application.properties`

```
server.tomcat.max-threads=200
server.tomcat.min-spare-threads=10
```

---

# 🗄️ Database Issues

If using JPA or H2 database:

Common error:

```
Failed to configure DataSource
```

Check dependencies:

```
spring-boot-starter-data-jpa
h2
```

Example configuration:

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
```

---

# 🌐 Network Issues

### Cannot access API from browser

Check firewall rules.

Verify server is listening:

```
netstat -tulnp | grep 8080
```

Expected output:

```
tcp6 0 0 :::8080 :::* LISTEN
```

---

# 🧹 Clean and Restart Project

If everything fails:

```
mvn clean
mvn install
mvn spring-boot:run
```

---

# 📌 Debugging Checklist

Before reporting an issue:

✔ Application running
✔ Correct endpoint path
✔ Correct HTTP method
✔ Parameters provided
✔ No port conflicts
✔ Logs checked

---

# 📚 Useful Tools

| Tool                 | Purpose                  |
| -------------------- | ------------------------ |
| curl                 | Test APIs from terminal  |
| Postman              | API testing GUI          |
| Maven                | Dependency management    |
| Docker               | Containerized deployment |
| Spring Boot DevTools | Automatic reload         |

---

Debugging is part of the development process.
Use logs, test endpoints, and isolate issues step by step.

Happy debugging! 🐛🚀
