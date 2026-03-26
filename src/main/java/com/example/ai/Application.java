package com.example.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("\n========================================");
        System.out.println("🚀 AI Application Started Successfully!");
        System.out.println("========================================");
        System.out.println("📡 Server running at: http://localhost:8080");
        System.out.println("\n📝 Available Endpoints:");
        System.out.println("   GET  /api/ai/health           - Check service health");
        System.out.println("   GET  /api/ai/greet            - Get a greeting");
        System.out.println("   GET  /api/ai/sentiment        - Analyze sentiment");
        System.out.println("   GET  /api/ai/analyze          - Full text analysis");
        System.out.println("   GET  /api/ai/vowels           - Count vowels");
        System.out.println("   GET  /api/ai/palindrome       - Check palindrome");
        System.out.println("   POST /api/ai/analyze-batch    - Batch analysis");
        System.out.println("\n💡 Try it now: curl http://localhost:8080/api/ai/health");
        System.out.println("========================================\n");
    }
}
