FROM openjdk:17 
ENV DB_URL=jdbc:mariadb://db-order:3306/order EUREKA_URL=http://my-running-app:8761/eureka/ HOST_NAME=order-client
EXPOSE 7001
COPY target/order-0.0.1-SNAPSHOT.jar order-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","order-0.0.1-SNAPSHOT.jar"]