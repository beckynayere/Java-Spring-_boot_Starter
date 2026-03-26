package com.example.ai;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AIServiceTest {
    
    @Autowired
    private AIService aiService;
    
    @Test
    void testSentimentAnalysis() {
        String result = aiService.analyzeSentiment("I love this project");
        assertTrue(result.contains("Positive"));
    }
    
    @Test
    void testTextAnalysis() {
        var result = aiService.analyzeText("Hello World");
        assertEquals(2, result.get("wordCount"));
    }
    
    @Test
    void testVowelCount() {
        int count = aiService.countVowels("hello");
        assertEquals(2, count);
    }
    
    @Test
    void testPalindrome() {
        assertTrue(aiService.isPalindrome("racecar"));
        assertFalse(aiService.isPalindrome("hello"));
    }
}
