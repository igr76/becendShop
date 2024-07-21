FROM openjdk:11
ADD /target/test-0.0.1-SNAPSHOT.jar test.jar
ENTRYPOINT "java", "-jar","test.jar"