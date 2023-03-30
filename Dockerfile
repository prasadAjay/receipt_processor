FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/*jar
COPY ${JAR_FILE} ReceiptProcessor-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ReceiptProcessor-0.0.1-SNAPSHOT.jar"]