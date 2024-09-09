# start with a base image containing java runtime
FROM openjdk:17-jdk-slim

# info of the maintainer
MAINTAINER saurabh

# adding the application jar to the docker image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# this command will be used to execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]