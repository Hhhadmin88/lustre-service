FROM hub.c.163.com/library/java:8-alpine

MAINTAINER Evan Wang wangmingmis@163.com

ADD target/*.jar homemaking-service.jar

EXPOSE 8090

ENTRYPOINT ["java", "-jar", "/homemaking-service"]