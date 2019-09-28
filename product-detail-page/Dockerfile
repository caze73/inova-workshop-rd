FROM adoptopenjdk/openjdk11-openj9:alpine-jre
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--header-footer-src=http://header-footer:8081", "--stock-src=http://stock:8083", "--eventing.brokers=kafka:9092"]