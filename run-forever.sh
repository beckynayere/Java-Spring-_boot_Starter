#!/bin/bash

GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m'

echo -e "${BLUE}╔══════════════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║     🚀 Spring Boot AI Toolkit - Application Launcher     ║${NC}"
echo -e "${BLUE}╚══════════════════════════════════════════════════════════╝${NC}"
echo ""

# Check if JAR exists
if [ ! -f "target/ai-project-1.0.0.jar" ]; then
    echo -e "${YELLOW}📦 JAR not found. Building application...${NC}"
    mvn clean package -DskipTests
    if [ $? -ne 0 ]; then
        echo -e "${RED}❌ Build failed!${NC}"
        exit 1
    fi
    echo -e "${GREEN}✅ Build successful!${NC}"
    echo ""
fi

# Kill any existing process on port 8080
echo -e "${YELLOW}Checking port 8080...${NC}"
if lsof -i :8080 > /dev/null 2>&1; then
    echo -e "⚠️  Port 8080 is in use. Stopping existing process..."
    sudo lsof -ti:8080 | xargs sudo kill -9 2>/dev/null
    sleep 2
fi

echo -e "${GREEN}✅ Port 8080 is free${NC}"
echo ""

# Start the application
echo -e "${BLUE}🚀 Starting Spring Boot AI Toolkit...${NC}"
echo -e "${GREEN}📱 Access at: http://localhost:8080${NC}"
echo -e "${GREEN}🔧 API: http://localhost:8080/api/ai/health${NC}"
echo ""
echo -e "${YELLOW}⚠️  Keep this terminal open! Press Ctrl+C to stop.${NC}"
echo "========================================="
echo ""

java -jar target/ai-project-1.0.0.jar
