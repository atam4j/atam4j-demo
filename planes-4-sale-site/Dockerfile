FROM openjdk:8-jre

ADD target/planes-4-sale-site-1.0-SNAPSHOT.jar /server.jar
ADD src/main/resources/app-config.yml /app-config.yml

CMD java -jar /server.jar server /app-config.yml