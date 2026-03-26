#!/bin/bash

echo "========================================="
echo "Testing Spring Boot AI API"
echo "========================================="

echo -e "\n1. Health Check:"
curl -s http://localhost:8080/api/ai/health | jq '.' 2>/dev/null || curl -s http://localhost:8080/api/ai/health

echo -e "\n\n2. Greeting:"
curl -s "http://localhost:8080/api/ai/greet?name=Developer" | jq '.' 2>/dev/null || curl -s "http://localhost:8080/api/ai/greet?name=Developer"

echo -e "\n\n3. Sentiment Analysis (Positive):"
curl -s "http://localhost:8080/api/ai/sentiment?text=I%20love%20this%20project" | jq '.' 2>/dev/null || curl -s "http://localhost:8080/api/ai/sentiment?text=I%20love%20this%20project"

echo -e "\n\n4. Sentiment Analysis (Negative):"
curl -s "http://localhost:8080/api/ai/sentiment?text=This%20is%20terrible" | jq '.' 2>/dev/null || curl -s "http://localhost:8080/api/ai/sentiment?text=This%20is%20terrible"

echo -e "\n\n5. Text Analysis:"
curl -s "http://localhost:8080/api/ai/analyze?text=Spring%20Boot%20is%20awesome" | jq '.' 2>/dev/null || curl -s "http://localhost:8080/api/ai/analyze?text=Spring%20Boot%20is%20awesome"

echo -e "\n\n6. Vowel Count:"
curl -s "http://localhost:8080/api/ai/vowels?text=Hello%20World" | jq '.' 2>/dev/null || curl -s "http://localhost:8080/api/ai/vowels?text=Hello%20World"

echo -e "\n\n7. Palindrome Check:"
curl -s "http://localhost:8080/api/ai/palindrome?text=racecar" | jq '.' 2>/dev/null || curl -s "http://localhost:8080/api/ai/palindrome?text=racecar"

echo -e "\n\n8. Batch Analysis (POST):"
curl -s -X POST http://localhost:8080/api/ai/analyze-batch \
  -H "Content-Type: application/json" \
  -d '{"text":"This is an amazing Spring Boot project"}' | jq '.' 2>/dev/null || curl -s -X POST http://localhost:8080/api/ai/analyze-batch -H "Content-Type: application/json" -d '{"text":"This is an amazing Spring Boot project"}'

echo -e "\n\n9. View History:"
curl -s http://localhost:8080/api/ai/history | jq '.' 2>/dev/null || curl -s http://localhost:8080/api/ai/history

echo -e "\n\n========================================="
echo "API Testing Complete!"
echo "========================================="
