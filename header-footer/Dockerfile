FROM adoptopenjdk/openjdk11-openj9:alpine-jre
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8081