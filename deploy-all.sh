#!/bin/bash

GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo -e "${BLUE}╔══════════════════════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║     🚀 Spring Boot AI Toolkit - Universal Deployer       ║${NC}"
echo -e "${BLUE}╚══════════════════════════════════════════════════════════╝${NC}"
echo ""

echo -e "${YELLOW}Choose deployment platform:${NC}"
echo "1) Docker Local (Test container)"
echo "2) Push to Docker Hub"
echo "3) Deploy to Render (via Docker)"
echo "4) Deploy to Railway (via Docker)"
echo "5) Deploy to Heroku"
echo "6) Deploy to Google Cloud Run"
echo "7) Deploy to AWS ECS"
echo ""
read -p "Enter choice (1-7): " choice

case $choice in
    1)
        echo -e "${GREEN}Building and running locally...${NC}"
        ./docker-build.sh
        ;;
    2)
        echo -e "${GREEN}Building and pushing to Docker Hub...${NC}"
        echo "Enter your Docker Hub username:"
        read username
        docker build -t $username/spring-boot-ai-toolkit:latest .
        docker push $username/spring-boot-ai-toolkit:latest
        echo -e "${GREEN}✅ Pushed to Docker Hub!${NC}"
        ;;
    3)
        echo -e "${GREEN}Preparing for Render deployment...${NC}"
        echo "1. Push your code to GitHub:"
        echo "   git push origin main"
        echo ""
        echo "2. Go to https://render.com"
        echo "3. Click 'New +' → 'Web Service'"
        echo "4. Connect your GitHub repository"
        echo "5. Render will auto-detect Dockerfile"
        echo "6. Deploy!"
        ;;
    4)
        echo -e "${GREEN}Preparing for Railway deployment...${NC}"
        echo "Install Railway CLI: npm install -g @railway/cli"
        echo "Run: railway login"
        echo "Run: railway init"
        echo "Run: railway up"
        ;;
    5)
        echo -e "${GREEN}Preparing for Heroku deployment...${NC}"
        echo "Install Heroku CLI: curl https://cli-assets.heroku.com/install.sh | sh"
        echo "Run: heroku login"
        echo "Run: heroku create spring-boot-ai-toolkit"
        echo "Run: git push heroku main"
        ;;
    6)
        echo -e "${GREEN}Preparing for Google Cloud Run...${NC}"
        echo "Install Google Cloud SDK: https://cloud.google.com/sdk/docs/install"
        echo "Run: gcloud auth configure-docker"
        echo "Run: gcloud run deploy spring-boot-ai-toolkit --source ."
        ;;
    7)
        echo -e "${GREEN}Preparing for AWS ECS...${NC}"
        echo "1. Build and push to ECR:"
        echo "   aws ecr create-repository --repository-name spring-boot-ai-toolkit"
        echo "   docker tag spring-boot-ai-toolkit <account>.dkr.ecr.<region>.amazonaws.com/spring-boot-ai-toolkit"
        echo "   docker push <account>.dkr.ecr.<region>.amazonaws.com/spring-boot-ai-toolkit"
        echo ""
        echo "2. Deploy to ECS using AWS Console or CLI"
        ;;
esac
