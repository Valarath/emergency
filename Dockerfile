# Start with a base image containing Java runtime
FROM eclipse-temurin:17-jre
ENV SERVICE_NAME="emergency"
ENV USER="emergencyuser"

# Add the application's jar to the container
COPY ./build/libs/emergency.jar /emergency.jar

# Create a group and user so we are not running our container and application as root and thus user 0 which is a security issue.
RUN addgroup --system --gid 1000 $SERVICE_NAME && \
    adduser --system --uid 1000 --ingroup $SERVICE_NAME --shell /bin/sh $USER

# Make port 8080 available to the world outside this container
EXPOSE 8080
# Use previously created user to run all following cmds
USER 1000
# Run the jar file
ENTRYPOINT ["java", "-jar", "/emergency.jar"]