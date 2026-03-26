#!/bin/bash

GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m'

echo -e "${BLUE}╔══════════════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║     🚀 Spring Boot AI Toolkit - One Click Deploy         ║${NC}"
echo -e "${BLUE}╚══════════════════════════════════════════════════════════╝${NC}"
echo ""

# Build
echo -e "${BLUE}[1/3] Building application...${NC}"
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "❌ Build failed. Check pom.xml"
    exit 1
fi

echo -e "${GREEN}✅ Build successful!${NC}"

# Test locally
echo -e "${BLUE}[2/3] Testing locally...${NC}"
java -jar target/ai-project-1.0.0.jar &
APP_PID=$!
sleep 8

curl -s http://localhost:8080/api/ai/health > /dev/null
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✅ Local test passed!${NC}"
else
    echo "❌ Local test failed"
    kill $APP_PID
    exit 1
fi

kill $APP_PID

# Deploy options
echo -e "${BLUE}[3/3] Choose deployment:${NC}"
echo "1) Run locally now"
echo "2) Create Docker image"
echo "3) Prepare for Render (create deploy package)"
echo ""
read -p "Choice: " choice

case $choice in
    1)
        echo -e "${GREEN}Starting application locally...${NC}"
        echo "Access at: http://localhost:8080"
        java -jar target/ai-project-1.0.0.jar
        ;;
    2)
        echo -e "${GREEN}Creating Docker image...${NC}"
        docker build -t spring-boot-ai-toolkit .
        echo "Run with: docker run -p 8080:8080 spring-boot-ai-toolkit"
        ;;
    3)
        echo -e "${GREEN}Creating deployment package...${NC}"
        mkdir -p deploy
        cp target/ai-project-1.0.0.jar deploy/
        cp Procfile deploy/ 2>/dev/null || echo "web: java -jar ai-project-1.0.0.jar" > deploy/Procfile
        cd deploy
        zip -r ../spring-boot-ai-deploy.zip .
        cd ..
        echo -e "${GREEN}✅ Created spring-boot-ai-deploy.zip${NC}"
        echo "Upload this to Render or Railway"
        ;;
esac
