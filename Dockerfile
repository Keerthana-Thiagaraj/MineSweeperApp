# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy Maven project files
COPY pom.xml ./
COPY src ./src

# Install Maven and build the project
RUN apt-get update && apt-get install -y maven && mvn -f pom.xml clean package

# Run the application
CMD ["java", "-cp", "target/MinesweeperGame-1.0-SNAPSHOT.jar", "com.minesweepergame.MinesweeperMain"]
