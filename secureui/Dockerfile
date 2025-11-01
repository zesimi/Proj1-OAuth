FROM amazoncorretto:17-alpine-jdk
LABEL maintainer="posmbg00@gmail.com"
COPY target/secureui-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]