# 🛠️ Spring Boot AI Project – Development Guide

This guide explains how to extend and modify the project.

---

# 🏗️ Architecture

```
Client
  │
  ▼
Controller (AIController)
  │
  ▼
Service (AIService)
  │
  ▼
Response (JSON)
```

---

# 📌 Controller Example

`AIController.java`

Handles HTTP requests.

```java
@RestController
@RequestMapping("/api/ai")
public class AIController {

    @Autowired
    private AIService aiService;

    @GetMapping("/analyze")
    public Map<String, Object> analyze(@RequestParam String text){

        Map<String,Object> response = new HashMap<>();

        response.put("result", aiService.analyze(text));

        return response;
    }
}
```

---

# ⚙️ Service Example

`AIService.java`

Contains business logic.

```java
@Service
public class AIService {

    public String analyze(String text){

        return "Processed: " + text;

    }
}
```

---

# ➕ Adding a New Endpoint

Step 1 – Add controller method

```java
@GetMapping("/new-feature")
public Map<String,Object> newFeature(@RequestParam String input){

    Map<String,Object> response = new HashMap<>();

    response.put("result", aiService.newFeature(input));

    return response;
}
```

---

Step 2 – Add service logic

```java
public String newFeature(String input){

    return "Processed: " + input;

}
```

---

# ⚙️ Configuration

Add configuration in:

`application.properties`

```
ai.feature.enabled=true
ai.threshold=0.7
```

Use configuration:

```java
@Value("${ai.feature.enabled}")
private boolean featureEnabled;
```

---

# 🧪 Writing Tests

Unit Test Example:

```java
@Test
void testAnalyze(){

    String result = aiService.analyze("hello");

    assertEquals("Processed: hello", result);

}
```

Integration Test Example:

```java
mockMvc.perform(get("/api/ai/analyze")
.param("text","hello"))
.andExpect(status().isOk());
```

---

# 📦 Adding Dependencies

Edit `pom.xml`

Example:

```xml
<dependency>
 <groupId>org.springframework.boot</groupId>
 <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

Install:

```
mvn clean install
```

---

# 🔒 Adding Security

Add dependency:

```xml
spring-boot-starter-security
```

Basic configuration example:

```java
http
.authorizeRequests()
.antMatchers("/api/ai/health").permitAll()
.anyRequest().authenticated();
```

---

# 🎯 Best Practices

* Keep controllers small
* Move logic to services
* Validate user input
* Write tests
* Use logging
* Version APIs

Example:

```
/api/v1/ai/analyze
```

---

Happy coding 🚀
