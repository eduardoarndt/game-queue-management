FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /webflux
COPY pom.xml /webflux

COPY app/ /webflux/app
COPY core/ /webflux/core
COPY data/ /webflux/data 
COPY presenter/ /webflux/presenter

RUN mvn -f /webflux/pom.xml clean package -Dmaven.test.skip=true


FROM openjdk:11-jre-slim
COPY --from=build /webflux/app/target/app-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
