# Docker
![Docker](https://img.mukewang.com/608d64810001120319201080-500-284.jpg)

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
2. 下载地址：https://www.docker.com/products/docker-desktop
    或：https://hub.docker.com/editions/community/docker-ce-desktop-windows
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
## [常用命令](https://docs.docker.com/engine/reference/commandline/docker/)
```
service docker start
service docker status

docker version [OPTIONS]                                        显示版本信息
docker images [OPTIONS] [REPOSITORY[:TAG]]                      列出 iamges
docker pull [OPTIONS] NAME[:TAG|@DIGEST]                        拉取 image 或 repository
docker run [OPTIONS] IMAGE [COMMAND] [ARG...]                   创建新的 container 并运行 command
    -d                                                          后台运行
    -v                                                          绑定 volume
    -p                                                          指定端口映射
    -P                                                          暴露容器所有端口到宿主随机端口
    --restart=always                                            总是启动
    --privileged                                                扩展权限
docker ps [OPTIONS]                                             列出 containers
docker exec [OPTIONS] CONTAINER COMMAND [ARG...]                在 container 中运行 command
    -it IMAGE /bin/bash
docker start|stop|restart [OPTIONS] CONTAINER [CONTAINER...]    启动|停止|重启 containers
docker rm [OPTIONS] CONTAINER [CONTAINER...]                    移除 containers
docker rmi [OPTIONS] IMAGE [IMAGE...]                           移除 images
    -a
docker inspect [OPTIONS] NAME|ID [NAME|ID...]                   返回 Docker 对象的 low-level 信息
docker kill [OPTIONS] CONTAINER [CONTAINER...]                  杀掉 containers
docker build [OPTIONS] PATH | URL | -                           从 Dockerfile 构建 image
    -t                                                          命名 'name:tag'
docker commit                                                   从 container 创建 image
```
---
## Docker 网络
![Docker 网络](https://img.mukewang.com/608d6a4c0001e15019201080-500-284.jpg)
- 网络类型：1-Bridge 2-Host 3-None
- 端口映射
## Dockerfile
0. 下载一个 [JPress war 包](http://www.jpress.io/club/post/116)
1. 编写 Dockerfile 文件
```
from tomcat
MAINTAINER ljh xxx@163.com
COPY jpress-v3.3.0.war /usr/local/tomcat/webapps
```
2. docker build -t jpress .
3. docker run -d -p 8888:8080 --name jpress jpress
4. localhost:8888/jpress-v3.3.0
---
## 安装软件
1. [Redis](https://blog.csdn.net/qq_34670974/article/details/94051251)
```bash
docker run -d --name redis -p 6379:6379 --restart=always
[-v D:/Docker/Redis/data:/data:rw]
[-v D:/Docker/Redis/conf/redis.conf:/etc/redis/redis.conf:ro]
redis redis-server /etc/redis/redis.conf

docker exec -it redis redis-cli
```
2. [MySQL](https://blog.csdn.net/pall_scall/article/details/112154454)
```bash
docker run -d --name mysql -p 3306:3306 --privileged
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
docker run -d --name nginx -p 80:80 --restart=always
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
docker run -d --name rabbitmq -p 4369:4369 -p 5671:5671 -p 5672:5672 -p 15672:15672 -p 1883:1883 -p 8883:8883 rabbitmq:management-alpine

docker exec -it rabbitmq bash
    rabbitmq-plugins list
    rabbitmq-plugins enable rabbitmq_management
        http://localhost:15672       Username:guest      Password:guest
    rabbitmq-plugins enable rabbitmq_mqtt
```
7. [MinIO](https://www.jianshu.com/p/52dbc679094a)
```bash
docker run -d --name minio -p 9000:9000 --restart=always
[-v D:\Docker\MinIO:/data]
[-v D:\Docker\MinIO\config:/root/.minio]
-e MINIO_ACCESS_KEY=minio
-e MINIO_SECRET_KEY=minio123
minio/minio server /data
```
8. Ubuntu
```bash
docker run -itd --name ubuntu --privileged ubuntu:latest
```
--- 