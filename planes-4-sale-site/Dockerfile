FROM openjdk:8-jre

ADD target/planes-4-sale-site-1.0-SNAPSHOT.jar /server.jar
ADD src/main/resources/app-config.yml /app-config.yml
ADD docker/wait-for-it.sh /wait-for-it.sh

CMD /wait-for-it.sh db:3306 --timeout=10 && java -jar /server.jar server /app-config.yml