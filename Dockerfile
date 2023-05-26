FROM openjdk:11
EXPOSE 8081
ADD target/my-own-jenkins.jar my-own-jenkins.jar
ENTRYPOINT ["java","-jar","/my-own-jenkins.jar"]