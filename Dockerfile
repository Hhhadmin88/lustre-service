FROM adoptopenjdk/openjdk8-openj9

MAINTAINER Evan Wang wangmingmis@163.com

ARG PORT=8090
ARG TIME_ZONE=Asia/Shanghai
ENV TZ=${TIME_ZONE}
ENV JAVA_OPTS="-Xms256m -Xmx256m"

ADD target/*.jar lustre-service.jar

EXPOSE ${PORT}

ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -server -jar lustre-service.jar