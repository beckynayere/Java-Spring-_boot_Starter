package com.example.ai;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AIService {
    
    private Map<String, List<String>> analysisHistory = new HashMap<>();
    
    // Enhanced Sentiment Analysis with Weighted Scoring
    public String analyzeSentiment(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "Neutral 😐 (No text provided)";
        }
        
        String lowerText = text.toLowerCase();
        
        Map<String, Integer> positiveWords = new HashMap<>();
        positiveWords.put("love", 3);
        positiveWords.put("amazing", 3);
        positiveWords.put("excellent", 3);
        positiveWords.put("great", 2);
        positiveWords.put("good", 1);
        positiveWords.put("happy", 2);
        positiveWords.put("awesome", 3);
        
        Map<String, Integer> negativeWords = new HashMap<>();
        negativeWords.put("hate", 3);
        negativeWords.put("terrible", 3);
        negativeWords.put("awful", 3);
        negativeWords.put("bad", 1);
        negativeWords.put("sad", 2);
        negativeWords.put("worst", 3);
        
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
        
        String sentiment;
        if (positiveScore > negativeScore) {
            if (positiveScore >= 5) sentiment = "Positive 😊 (Very Strong)";
            else if (positiveScore >= 3) sentiment = "Positive 😊 (Strong)";
            else sentiment = "Positive 😊 (Moderate)";
        } else if (negativeScore > positiveScore) {
            if (negativeScore >= 5) sentiment = "Negative 😔 (Very Strong)";
            else if (negativeScore >= 3) sentiment = "Negative 😔 (Strong)";
            else sentiment = "Negative 😔 (Moderate)";
        } else {
            sentiment = "Neutral 😐 (Balanced)";
        }
        
        analysisHistory.computeIfAbsent("sentiment", k -> new ArrayList<>())
            .add(text + " -> " + sentiment);
        
        return sentiment;
    }
    
    // NEW: Check if a name/text is a palindrome
    public String checkPalindrome(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "❌ Please provide a word or name to check.";
        }
        
        String cleanText = text.toLowerCase().replaceAll("[^a-z0-9]", "");
        String reversed = new StringBuilder(cleanText).reverse().toString();
        boolean isPalindrome = cleanText.equals(reversed);
        
        if (isPalindrome) {
            return "✅ **\"" + text + "\" IS a palindrome!**\n\n" +
                   "It reads the same forwards and backwards: " + cleanText + " ↔ " + reversed;
        } else {
            return "❌ **\"" + text + "\" is NOT a palindrome.**\n\n" +
                   "Forward: " + cleanText + "\n" +
                   "Backward: " + reversed;
        }
    }
    
    // NEW: Reverse a string
    public String reverseString(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "❌ Please provide a string to reverse.";
        }
        
        String reversed = new StringBuilder(text).reverse().toString();
        
        return "🔄 **String Reversal Result:**\n\n" +
               "Original: \"" + text + "\"\n" +
               "Reversed: \"" + reversed + "\"";
    }
    
    // NEW: Find the last vowel in a string
    public String getLastVowel(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "❌ Please provide a string to analyze.";
        }
        
        String lowerText = text.toLowerCase();
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        Character lastVowel = null;
        int lastIndex = -1;
        
        for (int i = lowerText.length() - 1; i >= 0; i--) {
            char c = lowerText.charAt(i);
            if (vowels.contains(c)) {
                lastVowel = c;
                lastIndex = i;
                break;
            }
        }
        
        if (lastVowel != null) {
            return "🔊 **Last Vowel Analysis:**\n\n" +
                   "Text: \"" + text + "\"\n" +
                   "Last vowel: **" + lastVowel + "** at position " + (lastIndex + 1) + "\n" +
                   "Original character: " + text.charAt(lastIndex);
        } else {
            return "🔇 **No vowels found** in \"" + text + "\"";
        }
    }
    
    // NEW: Get first vowel
    public String getFirstVowel(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "❌ Please provide a string to analyze.";
        }
        
        String lowerText = text.toLowerCase();
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        
        for (int i = 0; i < lowerText.length(); i++) {
            char c = lowerText.charAt(i);
            if (vowels.contains(c)) {
                return "🔊 **First Vowel Analysis:**\n\n" +
                       "Text: \"" + text + "\"\n" +
                       "First vowel: **" + c + "** at position " + (i + 1) + "\n" +
                       "Original character: " + text.charAt(i);
            }
        }
        
        return "🔇 **No vowels found** in \"" + text + "\"";
    }
    
    // NEW: Count specific vowel
    public String countSpecificVowel(String text, char vowel) {
        if (text == null || text.trim().isEmpty()) {
            return "❌ Please provide a string to analyze.";
        }
        
        vowel = Character.toLowerCase(vowel);
        if (!Set.of('a', 'e', 'i', 'o', 'u').contains(vowel)) {
            return "❌ '" + vowel + "' is not a vowel. Please use a, e, i, o, or u.";
        }
        
        int count = 0;
        String lowerText = text.toLowerCase();
        for (char c : lowerText.toCharArray()) {
            if (c == vowel) count++;
        }
        
        return "🔊 **Vowel Count for '" + vowel + "':**\n\n" +
               "Text: \"" + text + "\"\n" +
               "Occurrences: **" + count + "** time" + (count != 1 ? "s" : "");
    }
    
    // NEW: Extract vowels from string
    public String extractVowels(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "❌ Please provide a string to analyze.";
        }
        
        StringBuilder vowels = new StringBuilder();
        Set<Character> vowelSet = Set.of('a', 'e', 'i', 'o', 'u');
        
        for (char c : text.toLowerCase().toCharArray()) {
            if (vowelSet.contains(c)) {
                vowels.append(c);
            }
        }
        
        if (vowels.length() > 0) {
            return "🔊 **Vowels extracted from \"" + text + "\":**\n\n" +
                   "Vowels: " + vowels.toString() + "\n" +
                   "Count: " + vowels.length();
        } else {
            return "🔇 **No vowels found** in \"" + text + "\"";
        }
    }
    
    // NEW: Check if string contains only letters
    public String isAlphabetic(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "❌ Please provide a string to check.";
        }
        
        boolean isAlpha = text.matches("[a-zA-Z]+");
        
        if (isAlpha) {
            return "✅ **\"" + text + "\" contains only letters** (no numbers or special characters)";
        } else {
            return "⚠️ **\"" + text + "\" contains non-letter characters** (numbers, spaces, or symbols)";
        }
    }
    
    // NEW: Get character frequency
    public String getCharacterFrequency(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "❌ Please provide a string to analyze.";
        }
        
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }
        }
        
        if (freq.isEmpty()) {
            return "No letters found in \"" + text + "\"";
        }
        
        StringBuilder result = new StringBuilder("📊 **Character Frequency:**\n\n");
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            result.append("• '").append(entry.getKey()).append("' : ")
                  .append(entry.getValue()).append(" time").append(entry.getValue() != 1 ? "s" : "").append("\n");
        }
        
        return result.toString();
    }
    
    // Existing methods
    public Map<String, Object> analyzeText(String text) {
        Map<String, Object> analysis = new HashMap<>();
        
        if (text == null || text.isEmpty()) {
            analysis.put("wordCount", 0);
            analysis.put("charCount", 0);
            return analysis;
        }
        
        String[] words = text.trim().split("\\s+");
        analysis.put("wordCount", words.length);
        analysis.put("charCount", text.length());
        analysis.put("charCountWithoutSpaces", text.replace(" ", "").length());
        
        double avgWordLength = text.replace(" ", "").length() / (double) words.length;
        analysis.put("avgWordLength", String.format("%.2f", avgWordLength));
        
        return analysis;
    }
    
    public int countVowels(String text) {
        if (text == null || text.isEmpty()) return 0;
        
        int count = 0;
        String lowerText = text.toLowerCase();
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        
        for (char c : lowerText.toCharArray()) {
            if (vowels.contains(c)) count++;
        }
        return count;
    }
    
    public int countConsonants(String text) {
        if (text == null || text.isEmpty()) return 0;
        
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
    
    public String generateGreeting(String name) {
        if (name == null || name.trim().isEmpty()) {
            name = "Friend";
        }
        
        String[] greetings = {"Hello", "Hi", "Hey", "Greetings", "Welcome", "Nice to see you"};
        String[] emojis = {"👋", "😊", "🎉", "✨", "🌟"};
        
        Random random = new Random();
        String greeting = greetings[random.nextInt(greetings.length)];
        String emoji = emojis[random.nextInt(emojis.length)];
        
        return greeting + ", " + name + "! " + emoji;
    }
    
    public boolean isPalindrome(String text) {
        if (text == null || text.isEmpty()) return false;
        String cleanText = text.toLowerCase().replaceAll("[^a-z0-9]", "");
        String reversed = new StringBuilder(cleanText).reverse().toString();
        return cleanText.equals(reversed);
    }
    
    public Map<String, List<String>> getHistory() {
        return analysisHistory;
    }
    
    public void clearHistory() {
        analysisHistory.clear();
    }
}
