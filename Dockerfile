FROM tomcat:9.0.70-jdk17-corretto-al2

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the host machine to the container
COPY target/dsa-0.0.1-SNAPSHOT.jar /app/app.jar

# Specify the command to run the JAR file
CMD ["java", "-jar", "app.jar"]
