FROM adoptopenjdk/openjdk11

ARG JAR_FILE=build/libs/marketplace-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "coupang.jar"]

EXPOSE 8080