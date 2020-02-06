#!/usr/bin/env bash

mvn clean package -Dmaven.test.skip=true -U

docker build -t homemaking-service .

docker push registry.cn-hangzhou.aliyuncs.com/homemaking/homemaking-service