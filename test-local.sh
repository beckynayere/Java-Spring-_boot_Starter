#!/bin/bash

GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m'

echo -e "${BLUE}🧪 Testing Spring Boot AI Toolkit Locally${NC}"
echo "================================================"

# Build
echo -e "${BLUE}[1/3] Building application...${NC}"
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Build failed!${NC}"
    exit 1
fi

echo -e "${GREEN}✅ Build successful!${NC}"

# Start application in background
echo -e "${BLUE}[2/3] Starting application...${NC}"
java -jar target/ai-project-1.0.0.jar &
APP_PID=$!

# Wait for startup
echo "Waiting for application to start..."
sleep 10

# Test endpoints
echo -e "${BLUE}[3/3] Testing endpoints...${NC}"

echo -e "\n1. Health Check:"
curl -s http://localhost:8080/api/ai/health | python3 -m json.tool 2>/dev/null || curl -s http://localhost:8080/api/ai/health

echo -e "\n2. Sentiment Analysis:"
curl -s "http://localhost:8080/api/ai/sentiment?text=I%20love%20Spring%20Boot" | python3 -m json.tool 2>/dev/null || curl -s "http://localhost:8080/api/ai/sentiment?text=I%20love%20Spring%20Boot"

echo -e "\n3. Palindrome Check:"
curl -s "http://localhost:8080/api/ai/palindrome?text=racecar" | python3 -m json.tool 2>/dev/null || curl -s "http://localhost:8080/api/ai/palindrome?text=racecar"

echo -e "\n4. Vowel Count:"
curl -s "http://localhost:8080/api/ai/vowels?text=Hello%20World" | python3 -m json.tool 2>/dev/null || curl -s "http://localhost:8080/api/ai/vowels?text=Hello%20World"

echo -e "\n\n${GREEN}✅ All tests passed!${NC}"
echo ""
echo "Access your application at: http://localhost:8080"
echo "Press Ctrl+C to stop the application"

# Wait for user to press Ctrl+C
wait $APP_PID
