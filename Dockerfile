# 1단계: 빌드 스테이지
FROM openjdk:21-jdk-slim AS build

RUN apt-get update 
RUN apt-get install -y git

WORKDIR /app

RUN git clone -b dev https://github.com/KTB-FarmMate/FramMate-API-Server
WORKDIR /app/FramMate-API-Server

RUN chmod +x gradlew
RUN ./gradlew clean build -x test

# 2단계: 실행 스테이지
FROM openjdk:21-jdk-slim

# 포트 명시적 선언
EXPOSE 8080

# 환경 변수 정의
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
ENV MYSQL_HOST=${MYSQL_HOST}
ENV MYSQL_PORT=${MYSQL_PORT}
ENV DB_NAME=${DB_NAME}
ENV MYSQL_USERNAME=${MYSQL_USERNAME}
ENV MYSQL_PASSWORD=${MYSQL_PASSWORD}

WORKDIR /app
COPY --from=build /app/FramMate-API-Server/build/libs/farmmate-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
