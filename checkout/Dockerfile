FROM openjdk:17 
ENV DB_URL=jdbc:mariadb://db-checkout:3306/checkout EUREKA_URL=http://my-running-app:8761/eureka/ HOST_NAME=checkout-client
EXPOSE 7002
COPY target/checkout-0.0.1-SNAPSHOT.jar checkout-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","checkout-0.0.1-SNAPSHOT.jar"]