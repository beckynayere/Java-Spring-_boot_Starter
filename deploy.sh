#!/bin/bash

echo "🚀 Starting Deployment Process..."
echo "================================"

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m'

# Function to print colored output
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Step 1: Build the application
print_status "Building Spring Boot application..."
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    print_success "Build completed successfully!"
else
    print_error "Build failed!"
    exit 1
fi

# Step 2: Check if JAR was created
if [ -f "target/ai-project-1.0.0.jar" ]; then
    print_success "JAR file created: target/ai-project-1.0.0.jar"
else
    print_error "JAR file not found!"
    exit 1
fi

# Step 3: Ask for deployment method
echo ""
print_status "Select deployment method:"
echo "1) Local Run"
echo "2) Deploy to Render"
echo "3) Deploy to Heroku"
echo "4) Deploy to Railway"
echo "5) Exit"
read -p "Enter choice (1-5): " choice

case $choice in
    1)
        print_status "Starting local server..."
        print_success "Access at: http://localhost:8080"
        java -jar target/ai-project-1.0.0.jar
        ;;
    2)
        print_status "Preparing for Render deployment..."
        if command -v render &> /dev/null; then
            render deploy
        else
            print_error "Render CLI not installed. Install with: npm install -g @render/cli"
        fi
        ;;
    3)
        print_status "Preparing for Heroku deployment..."
        if command -v heroku &> /dev/null; then
            heroku create spring-boot-ai-$(date +%s)
            git push heroku main
            heroku open
        else
            print_error "Heroku CLI not installed. Install from: https://devcenter.heroku.com/articles/heroku-cli"
        fi
        ;;
    4)
        print_status "Preparing for Railway deployment..."
        if command -v railway &> /dev/null; then
            railway up
        else
            print_error "Railway CLI not installed. Install with: npm install -g @railway/cli"
        fi
        ;;
    5)
        print_status "Exiting..."
        exit 0
        ;;
    *)
        print_error "Invalid choice!"
        exit 1
        ;;
esac

print_success "Deployment completed! 🎉"
