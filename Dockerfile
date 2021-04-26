FROM openjdk:11

USER root
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} notes.jar
ENTRYPOINT ["java","-jar","/notes.jar"]