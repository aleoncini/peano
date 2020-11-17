# Build stage
FROM registry.access.redhat.com/ubi8/openjdk-11 as build
COPY --chown=jboss:jboss ./pom.xml ./
RUN mvn dependency:go-offline -B
COPY --chown=jboss:jboss ./ ./
RUN mvn clean package

# Create final image
FROM registry.access.redhat.com/ubi8/openjdk-11
COPY --from=build /home/jboss/target/orario-1.0.0-runner.jar ./
EXPOSE 8080
USER 1001

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dquarkus.mongodb.connection-string=${MONGOCONNSTRING}","-jar","./orario-1.0.0-runner.jar"]