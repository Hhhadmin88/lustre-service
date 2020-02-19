<h1 align="center"><a href="https://github.com/wangming2674/lustre-service" target="_blank">Lustre</a></h1>

> 这是一款基于springboot 2.1.8.RELEASE，Vue.js的前后端分离的家政服务管理系统。

<p align="center">
<a href="https://travis-ci.org/wangming2674/lustre-service"><img alt="Travis CI" src="https://api.travis-ci.org/wangming2674/lustre-service.svg?branch=master"/></a>
<a href="#"><img alt="VERSION" src="https://img.shields.io/badge/version-v1.0.0-brightgreen"/></a>
<a href="#"><img alt="JDK" src="https://img.shields.io/badge/JDK-1.8-yellow.svg?style=flat-square"/></a>
<a href="#"><img alt="LICENSE" src="https://img.shields.io/github/license/wangming2674/lustre-service"/></a>
<a href="#"><img alt="STARS" src="https://img.shields.io/github/stars/wangming2674/lustre-service"/></a>
<a href="#"><img alt="ISSUE" src="https://img.shields.io/github/issues/wangming2674/lustre-service"/></a>
<a href="#"><img alt="SWAGGER" src="https://img.shields.io/badge/swagger-available-brightgreen"/></a>
</p>

  

## 💡 简介

<b>Lustre</b> ( [ˈlʌstər], n.光泽 光辉)。
* 采用前后端分离架构，前端Vue.js,后端SpringBoot，各司其职。
* 前后端通过restful API进行交互。
* 无需手写SQL，使用Spring-Data-Jpa作为持久层orm框架。
* 整合docker实现一键部署，无需多余配置。
* 使用travis完成自动构建、测试、推送镜像实现高度自动化。
* 开箱即用，没有复杂的业务，是学习拓展的不二之选。

  
  
## ⚡ 目的
>随着互联网行业的发展，开发技术也在不断更新，各式各样的技术体系层出不穷。作为一个开发人员，我们会不断的进行新技术的学习。但如果每次只是单纯的学习，没有实际整合应用的话，就无法验证有些技术的场景和实际可行性。我们需要一个项目将日常中所用和所学整合起来，以便后续有需要可以直接上手或迁移。
>
>我们会遇到一个很现实的问题，就是如果有些知识学了，不常用，慢慢的就会忘记，但如果每次忘记了再去从头复习，则会十分耗费时间，为什么不把它记录下来呢？
>
>如果我们所学所用都被整合到了一个项目当中，即使有些内容不常用，甚至忘记，也可以通过实际项目温故而知新，所以这就是开发Lustre的意义。
>
>这个项目没有复杂的业务为的是学习拓展更加方便。但“麻雀虽小，五脏俱全”，以真正的企业级项目作为标准，最终开发而成。
>
>希望大家可以利用此项目更好的学习springboot、Vue.js、DevOps等相关技术栈。丰富前后端分离架构经验，更好的了解前后端分离项目的交互运作。
>
>可能将来会有一天去开发一个真正的面向用户的企业级开源项目，到时希望通过Lustre学到东西的你可以一起来参与。😉 

  

## ✨  相关技术
#### 后端：
* spring-boot
* spring-boot-actuator
* undertow
* swagger
* MySQL
* H2 DataBase
* spring-data-jpa
* lombok
* docker
* travis CI
* JUC 并发包
* lambda
* 流式、链式编程
* Optional
* ...
#### 前端:(待总结)

  

## 🔧 快速上手
#### 使用前须知：
1. 本项目由于使用SpringDataJpa作为持久层orm框架，所以并没有建表sql，项目目录中自然也就没有sql脚本文件，所以请不要奇怪没有sql文件项目该如何启动了。
2. 默认配置使用的数据库为h2。h2是一款内存数据库，轻盈便利，适合轻量级项目使用，且方便测试。
可通过配置文件修改使用的数据库，后面会有详细介绍。
3. 为了方便排查问题，lustre把日志和h2的数据库文件，默认输出到"~/.lustre"文件夹下，如果是windows一般情况在"C:\Users\admin"的位置下。如果没有，你也可以在C盘下直接搜索".lustre"即可找到。
如果是linux则在root目录下，由于在linux下，以'.'开头的文件夹默认为隐藏文件夹，所以如果你使用了shell工具，请设置显示隐藏文件。

 ####  开始使用：
 ...后续待总结
