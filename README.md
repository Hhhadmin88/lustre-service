<h1 align="center"><a href="https://github.com/wangming2674/lustre-service" target="_blank">Lustre</a></h1>

>这是一款基于springboot 2.1.8.RELEASE，Vue.js的前后端分离的家政服务管理系统。

<p align="center">
<a href="https://travis-ci.org/wangming2674/lustre-service"><img alt="Travis CI" src="https://api.travis-ci.org/wangming2674/lustre-service.svg?branch=master"/></a>
<a href="#"><img alt="VERSION" src="https://img.shields.io/badge/version-v1.0.0-brightgreen"/></a>
<a href="#"><img alt="JDK" src="https://img.shields.io/badge/JDK-1.8-yellow.svg?style=flat-square"/></a>
<a href="https://github.com/wangming2674/lustre-service/blob/master/LICENSE"><img alt="LICENSE" src="https://img.shields.io/github/license/wangming2674/lustre-service"/></a>
<a href="#"><img alt="STARS" src="https://img.shields.io/github/stars/wangming2674/lustre-service"/></a>
<a href="#"><img alt="FORKS" src="https://img.shields.io/github/forks/wangming2674/lustre-service"/></a>
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
>希望大家可以利用此项目更好的学习springboot、Vue.js、DevOps等相关技术栈。丰富前后端分离架构经验，更好地了解前后端分离项目的交互运作。
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
#### 前端:(开发中...如果有擅长Vue.js的小伙伴儿想加入，可以通过邮箱或提issue联系我)

  

## 🔍 快速上手
### 使用前须知(`必读`)：
1. 本项目由于使用SpringDataJpa作为持久层orm框架，所以并没有建表sql，项目目录中自然也就没有sql脚本文件，所以请不要奇怪没有sql文件项目该如何启动了。
2. 默认配置使用的数据库为h2。h2是一款内存数据库，轻盈便利，适合轻量级项目使用，且方便测试。
可通过配置文件修改使用的数据库，后面会有详细介绍。
3. 为了方便排查问题，lustre把日志和h2的数据库文件，默认输出到"~/.lustre"文件夹下，如果是windows一般情况在"C:\Users\admin"的位置下。如果没有，你也可以在C盘下直接搜索".lustre"即可找到。
如果是linux则在root目录下，由于在linux下，以'.'开头的文件夹默认为隐藏文件夹，所以如果你使用了shell工具，请设置显示隐藏文件。当然你可以直接选择使用命令'cd ~/.lustre'命令直接进入目录。
4. 由于默认使用的是内存数据库，使用者无法直接查看数据库内相关内容，如果想查看数据库内容，请将application.yml文件内配置修改如下。然后访问'ip:8090/h2-console'即可。默认username:admin,password:123456。这些都可以通过yml配置文件得知。
```yaml
h2:
   console:
    settings:
      #允许通过web查看
      web-allow-others: true
    path: /h2-console
    #启用web查看
    enabled: true
```
5. 启用MySQL数据库，你需要将application.yml中H2 database的配置注释，放开MySQL配置。然后创建名称为：lustre的数据库，字符集utf8mb4，排序规则utf8mb4_general_ci。如果你是想在开发环境下启动lustre，请直接通过在application.yml指定配置文件，然后配置application.yml的MySQL数据库信息。
```yaml
#指定配置文件为dev-application.yml，默认为application.yml
spring:
  profiles:
    active: dev
```

### 环境要求
在linux服务器部署时，推荐以下配置。
* CentOS 7.x
* 512 MB 以上内存

### 在Linux服务器上部署
#### 1.使用jar包的方式部署
在部署前，请确保服务器的软件包的版本是最新的。 
```shell script
sudo yum update -y
```
安装 Java 运行环境。
> 如果你已经安装了Java运行环境，请忽略这一步。
```shell script
# 安装 OpenJDK
sudo yum install java-1.8.0-openjdk -y

# 检测是否安装成功
java -version
```
下载lustre的jar包，目前最新版本。
>1.0.0-SNAPSHOT

```shell script
# 使用wget命令
# 例如最新版本为：1.0.0-SNAPSHOT
# 则命令应该为:wget http://lustre.evanwang.top/snapshot/lustre-1.0.0-SNAPSHOT.jar
wget http://lustre.evanwang.top/snapshot/lustre-{{version}}.jar
```

验证是否能启动成功，如果看到如下日志即证明项目可以启动成功。
com.evan.lustre.LustreApplication        : Started LustreApplication in 20.7 seconds (JVM running for 21.633)
```shell script
#具体版本号请根据下载的jar进行更改。
java -jar lustre-{{version}}.jar
```

正式启动项目
```shell script
#使用nhoup命令后台启动项目
nhoup java -jar lustre-{{version}}.jar
```

#### 2.使用docker的方式部署
> 假设你已经具备一定的liunx基础，且学会了使用docker，请按照以下步骤操作。如果已经安装完docker，请忽略安装步骤，从拉取镜像开始。

安装必要依赖
```shell script
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
```
添加软件源信息
```shell script
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```
更新 yum 缓存
```shell script
sudo yum makecache fast
```
安装 Docker
```shell script
sudo yum install docker-ce docker-ce-cli containerd.io
```
启动 Docker 后台服务
```shell script
sudo systemctl start docker
```
镜像加速
```shell script
# 新建 daemon.json 文件
sudo vim /etc/docker/daemon.json
```
将下面的配置复制进去即可：
```shell script
{
  "registry-mirrors": ["https://w553g21w.mirror.aliyuncs.com"]
}
```
重新加载docker配置
```shell script
sudo systemctl daemon-reload
sudo systemctl restart docker
```

拉取lustre镜像
```shell script
#例如版本号为：1.0.0-SNAPSHOT，则命令应该为
#sudo docker pull registry.cn-hangzhou.aliyuncs.com/lustre/lustre-service:1.0.0-SNAPSHOT
#如果不指定版本号，则默认为latest
sudo docker pull registry.cn-hangzhou.aliyuncs.com/lustre/lustre-service:[镜像版本号]
```
重命名镜像
```shell script
docker tag registry.cn-hangzhou.aliyuncs.com/lustre/lustre-service:[镜像版本号]  lustre-service
```

使用docker启动lustre
```shell script
docker run --rm -it -d --name lustre -p 8090:8090  -v ~/.lustre:/root/.lustre lustre-service
```
验证是否启动成功
```shell script
#首先查看lustre镜像是否启动成功
docker ps 
#进一步查看log验证是否启动成功 看到如下日志证明启动成功
#com.evan.lustre.LustreApplication : Started LustreApplication in 18.087 seconds (JVM running for 19.364)
cat ~/.lustre/logs/log
```

  

## 📔 进阶了解和使用
看到这里，首先恭喜你部署完成，下面开始关于lustre的进一步使用，感谢你的耐心。
由于lustre是个主要为学习开发而生的项目，所以一般用ip和端口号访问即可满足需求，这里就暂不介绍配置域名了。后续我会在我的博客中写一篇关于部署后配置域名的文章供大家参考。
>地址：待补充
### 熟悉接口
当我们要使用一个项目进行开发时，首当其冲的是要了解它的交互方式和接口。
lustre基于前后端分离的架构，后端为前端提供RestfulAPI，前后端通过json进行数据传递。

项目中整合了<b>swagger</b>，当项目启动成功后，可以通过访问了解项目的接口信息：
> http://localhost:8090/swagger-ui.html

<b>关于swagger的更多资料，请访问</b>
> https://swagger.io/


### 示例 json
#### Request:
当登录成功后会返回token作为登录凭证，每次请求需要在header中附加token和用户名用来验证登录状态和登录身份。
> 登录和注册无需在header中附带User-Account和User-Authorization。


> url: http://localhost:8090/api/evaluation/create
> type: post
> header :
>{
>  Content-Type:application/json
>  User-Account:12345678
>  User-Authorization:55151e8146134a18be4e47b8379e929b
>}

这里以发布评价为例，展示json格式及其内容。

请求json:
```json
{
  "taskId": 1,
  "employerId": 5,
  "employeeId": 1,
  "content": "很不错的清洁师傅，态度好，工作细心。",
  "score": 10
}
```

#### Response:
为了方便前端处理请求和异常信息，这里我们使用了统一异常处理，并将响应封装成固定格式json。

> code:状态码
> message:请求状态信息 (ok或错误信息)
> data:响应主体内容
> success: 请求成功表示(成功为true，失败为false)

这里以查看留言为例，展示json格式及其内容。

响应json:
```json
{
    "code": 200,
    "message": "OK",
    "data": {
        "id": 1,
        "senderId": 1,
        "senderName": "李明",
        "receiverId": 3,
        "receiverName": "珍妮",
        "content": "厨房有顽固污渍，麻烦帮忙重点清理一下",
        "createTime": "2020-01-12 14:12:30",
        "updateTime": "2020-01-17 02:07:11"
    },
    "success": true
}
```
#### 接口文件:
这里虽然整合了swagger，并且了解swagger的小伙伴都知道，简单的说swagger就是为了方便开发写作查看接口而生。
但是，我仍然打算为小伙伴们提供接口文件，这样就省去了你们去测试摸索接口的过程，可以一目了然的了解项目。我认为，学习的时间应该花在刀刃上，而不是对于工具的无用摸索。
了解postman的小伙伴儿都知道，postman可以将测试过的接口信息以文件的形式导出。然后分享给其他人他就可以导入你分享的文件，从而使用测试接口。

获取接口文件：
>地址：待补充

如何使用postman导入并使用，请查看我的博文：
>地址：待补充

  

## 🔧 开发及辅助工具
* 开发IDE:<b>IDEA 2019.3.3 Ultimate</b> 可以选择不用这么新的版本。
* 构建工具:<b>Maven 3.6.2</b>  这个就不用多介绍了，想必大家比我更了解。
* 持续集成工具:<b>Travis</b> 用来持续集成、自动构建和自动执行测试，类似jenkins。
* Devops:<b>docker</b> 当前火热的容器化技术。travis整合docker实现项目高度自动化。
> 如果你想简单的了解docker，我推荐:
> 官网:https://www.docker.com/
> 慕课网课程:https://www.imooc.com/learn/824


### 关于持续集成工具Travis
<b>了解lustre的构建详情: https://travis-ci.org/wangming2674/lustre-service</b>

我为什么不选择Jenkins呢？
因为lustre是个开源项目，且相对简易，用不到<b>jenkins</b>很多强大的功能。且Travis对开源项目永久免费，且更加简单易上手。

> 关于Travis的更多资料，请访问：
> 针对开源项目: https://travis-ci.org/
> 针对非开源项目: https://travis-ci.com/ 

  

## 😄 感谢
首先，感谢大家的使用与支持。
如果你从lustre学到了一些知识，麻烦您帮忙点一个star。
因为这就是对我写代码和为小伙伴们解答问题最好的鼓励了。

无论是多厉害的大牛，总是从不会到会，从小白到独当一面。
项目还有很多的不足之处，欢迎大家指出，包括我自己也是在不断学习的途中，不断的在开发的道路上一步步前进着。

我的博客中也有不少关于开发内容的文章，另外关于项目中一些踩过的坑，博客中也有相关介绍。

如果你渴望更进一步，请访问我的博客或我的个人网站：
>https://evanwang.blog.csdn.net
>http://evanwang.top

  
  
## 📣 招募
另外我在寻觅着那些喜欢技术，热爱并且想着能做一款属于自己的产品的有志青年。

如果你也有这样的想法， 无论你有强大的技术，或者很好的想法，都可以联系我，甚至你只有一颗赤诚的心。

且lustre的前端也在开发当中，如果你有兴趣，同样可以联系我。

请在验证信息中标明来意，例如github招募。

<b>mail: wangmingmis@163.com</b>

<b>QQ: 925076243</b>


