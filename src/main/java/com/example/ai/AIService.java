package com.example.ai;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AIService {
    
    @Value("${ai.sentiment.enabled:true}")
    private boolean sentimentEnabled;
    
    // Store analysis history (for learning purposes)
    private Map<String, List<String>> analysisHistory = new ConcurrentHashMap<>();
    
    // Simple sentiment analysis
    public String analyzeSentiment(String text) {
        if (!sentimentEnabled) {
            return "Sentiment analysis is disabled";
        }
        
        if (text == null || text.isEmpty()) {
            return "Neutral (No text provided)";
        }
        
        String lowerText = text.toLowerCase();
        
        // Enhanced keyword lists
        Map<String, Integer> positiveWords = new HashMap<>();
        positiveWords.put("good", 1);
        positiveWords.put("great", 2);
        positiveWords.put("awesome", 3);
        positiveWords.put("love", 3);
        positiveWords.put("excellent", 3);
        positiveWords.put("happy", 2);
        positiveWords.put("amazing", 3);
        positiveWords.put("wonderful", 3);
        positiveWords.put("fantastic", 3);
        
        Map<String, Integer> negativeWords = new HashMap<>();
        negativeWords.put("bad", 1);
        negativeWords.put("terrible", 3);
        negativeWords.put("hate", 3);
        negativeWords.put("awful", 3);
        negativeWords.put("sad", 2);
        negativeWords.put("poor", 2);
        negativeWords.put("worst", 3);
        negativeWords.put("horrible", 3);
        
        int positiveScore = 0;
        int negativeScore = 0;
        
        for (Map.Entry<String, Integer> entry : positiveWords.entrySet()) {
            if (lowerText.contains(entry.getKey())) {
                positiveScore += entry.getValue();
            }
        }
        
        for (Map.Entry<String, Integer> entry : negativeWords.entrySet()) {
            if (lowerText.contains(entry.getKey())) {
                negativeScore += entry.getValue();
            }
        }
        
        // Store in history
        String sentiment;
        if (positiveScore > negativeScore) {
            sentiment = "Positive 😊 (Score: " + positiveScore + ")";
        } else if (negativeScore > positiveScore) {
            sentiment = "Negative 😔 (Score: " + negativeScore + ")";
        } else {
            sentiment = "Neutral 😐 (Score: 0)";
        }
        
        // Save to history
        analysisHistory.computeIfAbsent("sentiment", k -> new ArrayList<>())
            .add(text + " -> " + sentiment);
        
        return sentiment;
    }
    
    // Count words and characters
    public Map<String, Object> analyzeText(String text) {
        Map<String, Object> analysis = new HashMap<>();
        
        if (text == null || text.isEmpty()) {
            analysis.put("wordCount", 0);
            analysis.put("charCount", 0);
            analysis.put("message", "No text provided");
            return analysis;
        }
        
        String[] words = text.trim().split("\\s+");
        analysis.put("wordCount", words.length);
        analysis.put("charCount", text.length());
        analysis.put("charCountWithoutSpaces", text.replace(" ", "").length());
        
        // Calculate average word length
        double avgWordLength = text.replace(" ", "").length() / (double) words.length;
        analysis.put("avgWordLength", String.format("%.2f", avgWordLength));
        
        return analysis;
    }
    
    // Count vowels in text
    public int countVowels(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        String lowerText = text.toLowerCase();
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        
        for (char c : lowerText.toCharArray()) {
            if (vowels.contains(c)) {
                count++;
            }
        }
        
        return count;
    }
    
    // Count consonants
    public int countConsonants(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        String lowerText = text.toLowerCase();
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        
        for (char c : lowerText.toCharArray()) {
            if (Character.isLetter(c) && !vowels.contains(c)) {
                count++;
            }
        }
        
        return count;
    }
    
    // Simple greeting service
    public String generateGreeting(String name) {
        if (name == null || name.trim().isEmpty()) {
            name = "Friend";
        }
        
        String[] greetings = {
            "Hello", "Hi", "Hey", "Greetings", "Welcome", 
            "Nice to see you", "Good to meet you", "Howdy"
        };
        
        String[] emojis = {"👋", "😊", "🎉", "✨", "🌟"};
        
        Random random = new Random();
        String greeting = greetings[random.nextInt(greetings.length)];
        String emoji = emojis[random.nextInt(emojis.length)];
        
        return greeting + ", " + name + "! " + emoji;
    }
    
    // Check if text is palindrome
    public boolean isPalindrome(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        
        String cleanText = text.toLowerCase().replaceAll("[^a-z0-9]", "");
        String reversed = new StringBuilder(cleanText).reverse().toString();
        
        return cleanText.equals(reversed);
    }
    
    // Get analysis history
    public Map<String, List<String>> getHistory() {
        return analysisHistory;
    }
    
    // Clear history
    public void clearHistory() {
        analysisHistory.clear();
    }
}
