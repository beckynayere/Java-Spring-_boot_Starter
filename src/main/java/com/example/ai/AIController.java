package com.example.ai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AIController {
    
    @Autowired
    private AIService aiService;
    
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Spring Boot AI Toolkit");
        response.put("version", "2.0.0");
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
    
    @GetMapping("/greet")
    public Map<String, Object> greet(@RequestParam(required = false) String name) {
        Map<String, Object> response = new HashMap<>();
        response.put("greeting", aiService.generateGreeting(name));
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
    
    @GetMapping("/sentiment")
    public Map<String, Object> analyzeSentiment(@RequestParam String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("input", text);
        response.put("sentiment", aiService.analyzeSentiment(text));
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
    
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
    
    @GetMapping("/vowels")
    public Map<String, Object> countVowels(@RequestParam String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("text", text);
        response.put("vowelCount", aiService.countVowels(text));
        response.put("consonantCount", aiService.countConsonants(text));
        return response;
    }
    
    @GetMapping("/palindrome")
    public Map<String, Object> checkPalindrome(@RequestParam String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("text", text);
        response.put("isPalindrome", aiService.isPalindrome(text));
        return response;
    }
    
    // NEW: Enhanced palindrome check with details
    @GetMapping("/palindrome-detail")
    public Map<String, Object> checkPalindromeDetail(@RequestParam String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("text", text);
        response.put("result", aiService.checkPalindrome(text));
        return response;
    }
    
    // NEW: Reverse string
    @GetMapping("/reverse")
    public Map<String, Object> reverseString(@RequestParam String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("text", text);
        response.put("result", aiService.reverseString(text));
        return response;
    }
    
    // NEW: Get last vowel
    @GetMapping("/last-vowel")
    public Map<String, Object> getLastVowel(@RequestParam String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("text", text);
        response.put("result", aiService.getLastVowel(text));
        return response;
    }
    
    // NEW: Get first vowel
    @GetMapping("/first-vowel")
    public Map<String, Object> getFirstVowel(@RequestParam String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("text", text);
        response.put("result", aiService.getFirstVowel(text));
        return response;
    }
    
    // NEW: Count specific vowel
    @GetMapping("/count-vowel")
    public Map<String, Object> countSpecificVowel(@RequestParam String text, @RequestParam char vowel) {
        Map<String, Object> response = new HashMap<>();
        response.put("text", text);
        response.put("vowel", vowel);
        response.put("result", aiService.countSpecificVowel(text, vowel));
        return response;
    }
    
    // NEW: Extract all vowels
    @GetMapping("/extract-vowels")
    public Map<String, Object> extractVowels(@RequestParam String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("text", text);
        response.put("result", aiService.extractVowels(text));
        return response;
    }
    
    // NEW: Check if alphabetic
    @GetMapping("/is-alphabetic")
    public Map<String, Object> isAlphabetic(@RequestParam String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("text", text);
        response.put("result", aiService.isAlphabetic(text));
        return response;
    }
    
    // NEW: Character frequency
    @GetMapping("/frequency")
    public Map<String, Object> getFrequency(@RequestParam String text) {
        Map<String, Object> response = new HashMap<>();
        response.put("text", text);
        response.put("result", aiService.getCharacterFrequency(text));
        return response;
    }
    
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
    
    @GetMapping("/history")
    public Map<String, Object> getHistory() {
        Map<String, Object> response = new HashMap<>();
        response.put("history", aiService.getHistory());
        response.put("totalEntries", aiService.getHistory().size());
        return response;
    }
    
    @DeleteMapping("/history")
    public Map<String, String> clearHistory() {
        aiService.clearHistory();
        Map<String, String> response = new HashMap<>();
        response.put("status", "SUCCESS");
        response.put("message", "History cleared successfully");
        return response;
    }
}
