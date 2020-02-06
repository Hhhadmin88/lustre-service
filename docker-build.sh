#!/usr/bin/env bash

mvn clean package -Dmaven.test.skip=true -U

docker build -t homemaking-service .

docker login --username=javawangming registry.cn-hangzhou.aliyuncs.com
docker images
docker tag [homemaking-service] registry.cn-hangzhou.aliyuncs.com/homemaking/homemaking-service:[1.0.0]
docker push registry.cn-hangzhou.aliyuncs.com/homemaking/homemaking-service:[1.0.0]