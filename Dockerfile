FROM openjdk:8-jre-alpine
MAINTAINER giovanni <giovanni.dintrono>
EXPOSE 8080 5005
COPY ./build/libs/demoPersistence-0.0.1-SNAPSHOT.jar /usr/app/
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "demoPersistence-0.0.1-SNAPSHOT.jar"]
