# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine
#RUN apk add --no-cache bash

# Set the working directory in the container
WORKDIR /app

# Copy the build files from the host to the container
COPY build/libs/*.jar app.jar
#COPY wait-for-it.sh wait-for-it.sh
#
## Add permissions to the wait-for-it script executable for ping other containers
#RUN chmod +x wait-for-it.sh

# Create and set the volume directory
VOLUME /app/volume

# Expose the port the application runs on
EXPOSE 8080

# Run the application
#ENTRYPOINT ["./wait-for-it.sh","db:5432","--","./wait-for-it.sh","rabbit-mq:5672","--","java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]