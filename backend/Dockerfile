FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy only backend folder
COPY backend ./backend

# Move into backend where pom.xml exists
WORKDIR /app/backend

# Install Maven & build
RUN apt-get update && apt-get install -y maven
RUN mvn clean install -DskipTests

EXPOSE 8080

CMD ["sh", "-c", "java -jar target/*.jar"]