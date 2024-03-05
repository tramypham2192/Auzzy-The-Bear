FROM maven:3.8-openjdk-18 AS build
ENV HOME=/usr/src/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
RUN mvn verify --fail-never
ADD . $HOME
RUN mvn clean -Dmaven.test.skip=true package

FROM openjdk:18

COPY src/main/resources/application.properties /
COPY --from=build /usr/src/app/target/AuzzyTheBear-0.0.1-SNAPSHOT.jar /usr/app/AuzzyTheBear-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.config.location=/application.properties","-jar","/usr/app/AuzzyTheBear-0.0.1-SNAPSHOT.jar"]
