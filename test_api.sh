#!/bin/bash

GREEN='\033[0;32m'
RED='\033[0;31m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m'

TESTS_RUN=0
TESTS_PASSED=0
TESTS_FAILED=0

echo -e "${BLUE}╔══════════════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║     🧪 Spring Boot AI Toolkit - API Test Suite           ║${NC}"
echo -e "${BLUE}╚══════════════════════════════════════════════════════════╝${NC}"
echo ""

run_test() {
    local test_name="$1"
    local test_command="$2"
    local expected_pattern="$3"
    
    echo -e "${YELLOW}Testing: $test_name${NC}"
    response=$(eval $test_command 2>/dev/null)
    
    if echo "$response" | grep -q "$expected_pattern"; then
        echo -e "${GREEN}✓ PASSED${NC}"
        ((TESTS_PASSED++))
    else
        echo -e "${RED}✗ FAILED${NC}"
        echo "Expected pattern: $expected_pattern"
        echo "Got: $response"
        ((TESTS_FAILED++))
    fi
    ((TESTS_RUN++))
    echo "-----------------------------------"
}

# Health Check
run_test "Health Check" \
    "curl -s http://localhost:8080/api/ai/health" \
    "UP"

# Greeting
run_test "Greeting" \
    "curl -s 'http://localhost:8080/api/ai/greet?name=John'" \
    "John"

# Sentiment Positive
run_test "Sentiment Positive" \
    "curl -s 'http://localhost:8080/api/ai/sentiment?text=I%20love%20this'" \
    "Positive"

# Sentiment Negative
run_test "Sentiment Negative" \
    "curl -s 'http://localhost:8080/api/ai/sentiment?text=This%20is%20terrible'" \
    "Negative"

# Text Analysis
run_test "Text Analysis" \
    "curl -s 'http://localhost:8080/api/ai/analyze?text=Hello%20World'" \
    "wordCount"

# Vowel Count
run_test "Vowel Count" \
    "curl -s 'http://localhost:8080/api/ai/vowels?text=Hello'" \
    "vowelCount"

# Palindrome True
run_test "Palindrome True" \
    "curl -s 'http://localhost:8080/api/ai/palindrome?text=racecar'" \
    "true"

# Palindrome False
run_test "Palindrome False" \
    "curl -s 'http://localhost:8080/api/ai/palindrome?text=hello'" \
    "false"

# Batch Analysis
run_test "Batch Analysis" \
    "curl -s -X POST http://localhost:8080/api/ai/analyze-batch -H 'Content-Type: application/json' -d '{\"text\":\"This is amazing\"}'" \
    "SUCCESS"

# History
run_test "History" \
    "curl -s http://localhost:8080/api/ai/history" \
    "totalEntries"

echo ""
echo -e "${BLUE}╔══════════════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║                    📊 Test Summary                       ║${NC}"
echo -e "${BLUE}╚══════════════════════════════════════════════════════════╝${NC}"
echo -e "Total Tests: ${YELLOW}$TESTS_RUN${NC}"
echo -e "Passed: ${GREEN}$TESTS_PASSED${NC}"
echo -e "Failed: ${RED}$TESTS_FAILED${NC}"

if [ $TESTS_FAILED -eq 0 ]; then
    echo -e "\n${GREEN}🎉 All tests passed!${NC}"
else
    echo -e "\n${RED}⚠️  Some tests failed. Check the output above.${NC}"
fi
