# 기본 이미지로 openjdk 11의 slim 버전을 사용
FROM openjdk:11-jdk-slim-stretch

# ./target/lonua-0.0.1-SNAPSHOT.jar 파일을 컨테이너 내부로 복사
COPY ./target/lonua-0.0.1-SNAPSHOT.jar lonua-0.0.1-SNAPSHOT.jar

# 컨테이너가 시작될 때 "java -jar lonua-0.0.1-SNAPSHOT.jar 명령어 실행
CMD ["java", "-jar", "/lonua-0.0.1-SNAPSHOT.jar"]