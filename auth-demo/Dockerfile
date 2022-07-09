FROM openjdk:17 
ENV DB_URL=jdbc:mariadb://db-auth:3306/microservice EUREKA_URL=http://my-running-app:8761/eureka/ HOST_NAME=auth-client
EXPOSE 6000
COPY target/auth-demo-0.0.1-SNAPSHOT.jar auth-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","auth-demo-0.0.1-SNAPSHOT.jar"]