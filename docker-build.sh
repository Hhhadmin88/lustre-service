#!/usr/bin/env bash

mvn clean package -Dmaven.test.skip=true -U

docker build -t lustre-service .
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin registry.cn-hangzhou.aliyuncs.com
docker images
docker tag lustre-service registry.cn-hangzhou.aliyuncs.com/lustre/lustre-service:$VERSION
docker push registry.cn-hangzhou.aliyuncs.com/lustre/lustre-service:$VERSION
