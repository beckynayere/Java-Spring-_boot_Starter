#!/bin/bash

# Colors
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m'

echo -e "${BLUE}🐳 Building Docker Image for Spring Boot AI Toolkit${NC}"
echo "================================================"

# Build the JAR first
echo -e "${BLUE}[1/4] Building JAR with Maven...${NC}"
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Maven build failed!${NC}"
    exit 1
fi

echo -e "${GREEN}✅ JAR built successfully!${NC}"

# Build Docker image
echo -e "${BLUE}[2/4] Building Docker image...${NC}"
docker build -t spring-boot-ai-toolkit .

if [ $? -ne 0 ]; then
    echo -e "${RED}❌ Docker build failed!${NC}"
    exit 1
fi

echo -e "${GREEN}✅ Docker image built: spring-boot-ai-toolkit${NC}"

# Show image info
echo -e "${BLUE}[3/4] Image details:${NC}"
docker images | grep spring-boot-ai-toolkit

# Test run
echo -e "${BLUE}[4/4] Testing container...${NC}"
docker run -d -p 8080:8080 --name ai-toolkit-test spring-boot-ai-toolkit

echo -e "${GREEN}✅ Container started!${NC}"
echo -e "${BLUE}Testing health endpoint...${NC}"
sleep 5
curl http://localhost:8080/api/ai/health

echo ""
echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}✅ Deployment Ready!${NC}"
echo -e "${GREEN}========================================${NC}"
echo ""
echo "To stop and remove test container:"
echo "  docker stop ai-toolkit-test && docker rm ai-toolkit-test"
echo ""
echo "To push to Docker Hub:"
echo "  docker tag spring-boot-ai-toolkit yourusername/spring-boot-ai-toolkit:latest"
echo "  docker push yourusername/spring-boot-ai-toolkit:latest"
