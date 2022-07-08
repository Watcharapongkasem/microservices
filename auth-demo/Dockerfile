FROM openjdk:17 
ENV DB_URL=jdbc:mariadb://db-order_mariadb:3306/microservice
EXPOSE 6000
COPY auth-demo.jar auth-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","auth-demo-0.0.1-SNAPSHOT.jar"]