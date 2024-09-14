## we are going to use jib to build our project so we dont need docker file, we will keep all the code uptill now in a seperate branch

# start with a base image containing java runtime
FROM openjdk:17-jdk-slim

# info of the maintainer
MAINTAINER saurabh

# adding the application jar to the docker image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# this command will be used to execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]