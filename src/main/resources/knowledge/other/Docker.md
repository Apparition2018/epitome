# Docker

---
## 参考网站
1. [Docker Hub](https://hub.docker.com)
2. [在centos和redhat上安装docker](http://www.imooc.com/article/16448) 
3. [如何在Windows上更改Docker的默认安装路径？](https://www.zhihu.com/question/359332823/answer/923520420)
4. [Windows Docker 安装 | 菜鸟教程](https://www.runoob.com/docker/windows-docker-install.html)
---
## 问题
1. [更改Docker for windows的Hyper-v虚拟机的位置](https://blog.csdn.net/chuweisan2257/article/details/100906248)
2. [从零开始的Docker Desktop使用,Docker快速上手](https://xunmi.blog.csdn.net/article/details/108641842)
3. [windows10 docker -v 映射问题](https://www.80shihua.com/archives/2589)
---
## Docker Desktop 安装
1. 安装前创建目录链接
```bash
mklink /j "C:\Program Files\Docker" "D:\Docker"
mklink /j "C:\ProgramData\DockerDesktop" "D:\Docker\DockerDesktop"
```
2. 下载地址：https://hub.docker.com/editions/community/docker-ce-desktop-windows
3. 设置可挂载目录：Settings → Resources → FILE SHARING
4. 设置 Docker Engine：Settings → Docker Engine
```
  "registry-mirrors": [
    "https://registry.docker-cn.com",
    "http://hub-mirror.c.163.com",
    "https://docker.mirrors.ustc.edu.cn"
  ],
```
---
## [常用命令](https://www.runoob.com/docker/docker-command-manual.html)
```
docker version                      显示版本
docker images                       列出 iamges
docker pull                         拉取 image 或 repository
docker rm                           移除 containers
docker rmi                          移除 images
docker run                          创建新的 container 并运行命令；run 接 iamge，start 接 container
docker start|stop|restart           启动|停止|重启 containers
docker ps                           列出 containers
    -a
docker kill                         杀掉 containers
docker exec                         在执行的 container 中执行命令
    -it ... bash
docker commit                       从 container 创建 image
```
---
## 
1. [Redis](https://blog.csdn.net/qq_34670974/article/details/94051251)
```bash
docker run -d --name redis -p 6379:6379
[-v D:/Docker/Redis/data:/data]
redis [--requirepass "Password" redis-server --appendonly yes]
```
2. [MySQL](https://blog.csdn.net/pall_scall/article/details/112154454)
```bash
docker run -d --name mysql -p 3306:3306
[-v D:/Docker/MySQL/my.cnf:/etc/mysql/my.cnf -v D:/Docker/MySQL/data:/var/lib/mysql]
-e MYSQL_ROOT_PASSWORD=root mysql

docker exec -it mysql mysql -uroot -proot
     create user ljh@172.17.0.1 identified by '123456';                         创建用户
     grant all privileges on `ry-vue`.* to ljh@172.17.0.1 with grant option;    授权
     flush privileges;
```
3. [Tomcat](https://www.cnblogs.com/liyiran/p/12544715.html)
```bash
docker run -d --name tomcat -p 8080:8080 
[-v D:\Docker\Tomcat\webapps:/usr/local/tomcat/webapps]
tomcat:9.0.45

docker run -d --name tomcat -p 8080:8080 -v D:\Docker\Tomcat\webapps:/usr/local/tomcat/webapps tomcat:9.0.45
```
4. [Nginx](https://blog.csdn.net/goodboy31985/article/details/106676475/)
```bash
docker run -d --name nginx -p 80:80
[-v D:\Docker\Nginx\conf\nginx.conf:/etc/nginx/nginx.conf]
[-v D:\Docker\nginx\conf\conf.d:/etc/nginx/conf.d]
[-v D:\Docker\Nginx\log:/var/log/nginx]
[-v D:\Docker\Nginx\html:/usr/share/nginx/html]
nginx
```
5. Zookeeper
```bash
docker run -d --name zookeeper -p 2181:2181 zookeeper
```
6. [RabbitMQ](https://www.cnblogs.com/feily/p/14207897.html)
```bash
docker run -d --name rabbitmq -p 4369:4369 -p 5671:5671 -p 5672:5672 -p 15672:15672 -p 1883:1883 -p 8883:8883 rabbitmq

docker exec -it rabbitmq bash
    rabbitmq-plugins list
    rabbitmq-plugins enable rabbitmq_management
        localhost:15672       Username:guest      Password:guest
    rabbitmq-plugins enable rabbitmq_mqtt
```
7. [Ubuntu]
```bash
docker run -itd --name ubuntu ubuntu:20.04
```
--- 