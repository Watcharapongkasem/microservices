FROM openjdk:17 
ENV DB_URL=jdbc:mariadb://auth-user_db-auth.1.vl248cpuc48i6k2ndpskiou10:3306/microservice
EXPOSE 6000
COPY target/auth-demo-0.0.1-SNAPSHOT.jar auth-demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","auth-demo-0.0.1-SNAPSHOT.jar"]