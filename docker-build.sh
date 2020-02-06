#!/usr/bin/env bash

mvn clean package -Dmaven.test.skip=true -U

docker build -t homemaking-service .
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin registry.cn-hangzhou.aliyuncs.com
docker images
docker tag homemaking-service registry.cn-hangzhou.aliyuncs.com/homemaking/homemaking-service:1.0.0-SNAPSHOT
docker push registry.cn-hangzhou.aliyuncs.com/homemaking/homemaking-service:1.0.0-SNAPSHOT
