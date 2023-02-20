FROM openjdk:17
EXPOSE 8888
ARG JAR_FILE=target/SpringJwt-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
RUN docker pull mysql:latest
RUN docker run -d -p 3307:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=test mysql:latest