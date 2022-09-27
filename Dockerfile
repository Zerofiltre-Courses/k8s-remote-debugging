FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/example-jdwp.jar

WORKDIR /app

COPY ${JAR_FILE} example-jdwp.jar

CMD ["java","-jar","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","example-jdwp.jar"]