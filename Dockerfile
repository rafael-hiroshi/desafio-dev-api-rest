FROM adoptopenjdk/openjdk11:alpine-jre
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=api.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080/tcp
