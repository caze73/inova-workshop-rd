FROM adoptopenjdk/openjdk11-openj9:alpine-jre
# Add Maven dependencies (not shaded into the artifact; Docker-cached)
ADD /composer/target/lib  /lib
ARG JAR_FILE
ARG CONF_FILE
ADD ${JAR_FILE} app.jar
ADD ${CONF_FILE} composer.conf
ENTRYPOINT ["java","-jar","/app.jar", "--config", "composer.conf"]