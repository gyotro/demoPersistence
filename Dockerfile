FROM openjdk:8-jre-alpine
MAINTAINER giovanni <giovanni.dintrono>
EXPOSE 8080
COPY ./build/libs/demoPersistence-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "demoPersistence-0.0.1-SNAPSHOT.jar"]