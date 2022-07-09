FROM openjdk:17 
ENV DB_URL=jdbc:mariadb://db-product:3306/product EUREKA_URL=http://my-running-app:8761/eureka HOST_NAME=product-client 
EXPOSE 7000
COPY target/product-0.0.1-SNAPSHOT.jar product-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","product-0.0.1-SNAPSHOT.jar"]