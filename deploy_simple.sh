#!/bin/bash

echo "🚀 Building and preparing for deployment..."

# Build the JAR
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Build successful!"
    echo ""
    echo "📦 JAR file created: target/ai-project-1.0.0.jar"
    echo ""
    echo "To run locally:"
    echo "  java -jar target/ai-project-1.0.0.jar"
    echo ""
    echo "To deploy to Render:"
    echo "  1. Go to https://render.com"
    echo "  2. Click 'New Web Service'"
    echo "  3. Upload this JAR file directly"
    echo "  4. Set start command: java -jar ai-project-1.0.0.jar"
    echo ""
    echo "To deploy to Railway:"
    echo "  railway run java -jar target/ai-project-1.0.0.jar"
else
    echo "❌ Build failed!"
fi
