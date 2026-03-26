package com.example.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*") // Allow frontend access
public class AIController {
    
    @Autowired
    private AIService aiService;
    
    // Health check endpoint
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "AI Service");
        response.put("version", "1.0.0");
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
    
    // Sentiment analysis endpoint
    @GetMapping("/sentiment")
    public Map<String, Object> analyzeSentiment(@RequestParam String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("input", text);
        response.put("sentiment", aiService.analyzeSentiment(text));
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
    
    // Text analysis endpoint
    @GetMapping("/analyze")
    public Map<String, Object> analyzeText(@RequestParam String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("input", text);
        response.put("analysis", aiService.analyzeText(text));
        response.put("vowelCount", aiService.countVowels(text));
        response.put("consonantCount", aiService.countConsonants(text));
        response.put("isPalindrome", aiService.isPalindrome(text));
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
    
    // Greeting endpoint
    @GetMapping("/greet")
    public Map<String, Object> greet(@RequestParam(required = false) String name) {
        Map<String, Object> response = new HashMap<>();
        response.put("greeting", aiService.generateGreeting(name));
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
    
    // Vowel count endpoint
    @GetMapping("/vowels")
    public Map<String, Object> countVowels(@RequestParam String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("text", text);
        response.put("vowelCount", aiService.countVowels(text));
        response.put("consonantCount", aiService.countConsonants(text));
        return response;
    }
    
    // Palindrome check endpoint
    @GetMapping("/palindrome")
    public Map<String, Object> checkPalindrome(@RequestParam String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("text", text);
        response.put("isPalindrome", aiService.isPalindrome(text));
        return response;
    }
    
    // Combined analysis endpoint (POST example)
    @PostMapping("/analyze-batch")
    public Map<String, Object> analyzeBatch(@RequestBody Map<String, String> request) {
        String text = request.get("text");
        Map<String, Object> response = new HashMap<>();
        
        if (text == null) {
            response.put("error", "No text provided");
            response.put("status", "ERROR");
            return response;
        }
        
        response.put("input", text);
        response.put("sentiment", aiService.analyzeSentiment(text));
        response.put("analysis", aiService.analyzeText(text));
        response.put("vowelCount", aiService.countVowels(text));
        response.put("consonantCount", aiService.countConsonants(text));
        response.put("isPalindrome", aiService.isPalindrome(text));
        response.put("status", "SUCCESS");
        response.put("timestamp", System.currentTimeMillis());
        
        return response;
    }
    
    // Get analysis history
    @GetMapping("/history")
    public Map<String, Object> getHistory() {
        Map<String, Object> response = new HashMap<>();
        response.put("history", aiService.getHistory());
        response.put("totalEntries", aiService.getHistory().size());
        return response;
    }
    
    // Clear history
    @DeleteMapping("/history")
    public Map<String, String> clearHistory() {
        aiService.clearHistory();
        Map<String, String> response = new HashMap<>();
        response.put("status", "SUCCESS");
        response.put("message", "History cleared successfully");
        return response;
    }
}
