FROM openjdk:8-jre

ADD target/acceptance-tests-as-monitors-1.0-SNAPSHOT.jar /server.jar
ADD src/main/resources/app-config-deployed.yml /app-config-deployed.yml

CMD java -jar /server.jar server /app-config-deployed.yml