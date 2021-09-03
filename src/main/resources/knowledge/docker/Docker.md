# Docker
![Docker](https://img.mukewang.com/608d64810001120319201080-500-284.jpg)

---
## 参考网站
1. [Docker Hub](https://hub.docker.com)
2. [第一个docker化的java应用](https://www.imooc.com/learn/824)
3. [Docker 入门](https://www.imooc.com/learn/867)
4. [在centos和redhat上安装docker](http://www.imooc.com/article/16448) 
5. [如何在Windows上更改Docker的默认安装路径？](https://www.zhihu.com/question/359332823/answer/923520420)
6. [Windows Docker 安装 | 菜鸟教程](https://www.runoob.com/docker/windows-docker-install.html)
---
## 问题
1. [更改Docker for windows的Hyper-v虚拟机的位置](https://blog.csdn.net/chuweisan2257/article/details/100906248)
2. [从零开始的Docker Desktop使用,Docker快速上手](https://xunmi.blog.csdn.net/article/details/108641842)
3. [windows10 docker -v 映射问题](https://www.80shihua.com/archives/2589)
---
## VM vs DOcker
![VM vs Docker](https://img3.mukewang.com/608d8eeb0001298319201080-500-284.jpg)

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
## Linux 安装 Docker
```bash
# 安装 docker 三种方式
apt-get install -y docker.io
curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun
curl -sSL https://get.daocloud.io/docker | sh
# 安装 docker-compose：https://docs.docker.com/compose/install/
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
# 卸载 docker
apt-get autoremove docker docker-ce docker-engine docker.io containerd runc
dpkg -l | grep docker
apt-get autoremove docker-ce-*
rm -rf /etc/systemd/system/docker.service.d
rm -rf /var/lib/docker
```
---
## [常用命令](https://docs.docker.com/engine/reference/commandline/docker/)
```
service docker start
service docker status

docker version [OPTIONS]                                        显示版本信息
docker info [OPTIONS]                                           显示系统信息
docker images [OPTIONS] [REPOSITORY[:TAG]]                      列出 iamges
docker search [OPTIONS] TERM                                    在 Docker Hub 搜索 images
docker pull [OPTIONS] NAME[:TAG|@DIGEST]                        拉取 image 或 repository
docker run [OPTIONS] IMAGE [COMMAND] [ARG...]                   创建新的 container 并运行 command
    -d                                                          后台运行
    -v                                                          绑定挂载 volume，$PWD 当前目录
    --volumes-from                                              从指定 container 挂载 volumes
    -p                                                          指定端口映射
    -P                                                          暴露容器所有端口到宿主随机端口
    --restart=always                                            总是启动
    --privileged                                                扩展权限
    --rm                                                        当容器退出时自动删除
    --link                                                      连接其它容器
    --net                                                       将容器连接到网络
docker ps [OPTIONS]                                             列出 containers
docker exec [OPTIONS] CONTAINER COMMAND [ARG...]                在 container 中运行 command
    -it IMAGE /bin/bash
docker start|stop|restart [OPTIONS] CONTAINER [CONTAINER...]    启动|停止|重启 containers
docker create [OPTIONS] IMAGE [COMMAND] [ARG...]                创建 container
docker container                                                管理 containers
    update [OPTIONS] CONTAINER [CONTAINER...]                   修改一个或多个 containers 的配置
docker rm [OPTIONS] CONTAINER [CONTAINER...]                    移除 containers
docker rmi [OPTIONS] IMAGE [IMAGE...]                           移除 images
    -a
docker inspect [OPTIONS] NAME|ID [NAME|ID...]                   返回 Docker 对象的 low-level 信息
docker kill [OPTIONS] CONTAINER [CONTAINER...]                  杀掉 containers
docker cp [OPTIONS] CONTAINER:SRC_PATH DEST_PATH|-              在 container 和本地文件系统之间 复制文件或文件夹
docker commit [OPTIONS] CONTAINER [REPOSITORY[:TAG]]            根据 container 的更改创建 image
    -m                                                          提交消息
docker push [OPTIONS] NAME[:TAG]                                将 image 或 repository 推到 registry
docker tag SOURCE_IMAGE[:TAG] TARGET_IMAGE[:TAG]                创建与 SOURCE_IMAGE 关联的 TARGET_IMAGE[:TAG]
docker login [OPTIONS] [SERVER]                                 登录
docker network                                                  管理网络
    create                                                      创建网络
docker build [OPTIONS] PATH | URL | -                           从 Dockerfile 构建 image
    -t                                                          命名 'name:tag'
```
---
## Docker 网络
![Docker 网络](https://img.mukewang.com/608d6a4c0001e15019201080-500-284.jpg)
- 网络类型：1-Bridge 2-Host 3-None
- 端口映射
---
## [Dockerfile](https://www.runoob.com/docker/docker-dockerfile.html)
>### 语法
>```
>FROM                                               基于 image
>RUN                                                执行命令，docker build 时运行
>   shell 格式：RUN ./test.php dev offine
>   exec 格式：RUN ["./test.php", "dev", "offine"]
>CMD                                                执行命令，docker run 时运行
>ENTRYPOINT                                         执行命令，不会被 docker run 的参数指定的指令所覆盖，而且参数会传送给指定的程序
>ADD                                                添加文件，gzip 和 bzip2 会自动解压
>COPY                                               复制文件
>ENV                                                设置环境变量
>ARG                                                设置环境变量，仅在 Dockerfile 内有效
>   docker build --build-arg
>MAINTAINER                                         维护者
>USER                                               用户
>VOLUME                                             VOLUME
>WORKDIR                                            工作目录
>EXPOSE                                             端口
>```
>### 镜像分层
>![镜像分层](https://img2.mukewang.com/608d9d330001dd2819201080-500-284.jpg)
>### Demo
>1. 编写 Dockerfile 文件
>```
>FROM ubuntu:latest
>MAINTAINER ljh
>RUN sed -i 's/archive.ubuntu.com/mirrors.ustc.edu.cn/g' /etc/apt/sources.list
>RUN apt-get update
>RUN apt-get install -y nginx
>COPY index.html /var/www/html
>ENTRYPOINT ["/usr/sbin/nginx", "-g", "daemon off;"]
>EXPOSE 80
>```
>2. docker build -t hello_docker .
>3. docker run hello_docker
>4. localhost:80
---
## [Docker Compose](https://docs.docker.com/compose/) 
- [docker安装Ghost博客](https://www.cnblogs.com/xbblogs/p/9531069.html)
1. Dockerfile 定义应用程序环境
2. [docker-compose.yml](https://docs.docker.com/compose/compose-file/compose-file-v3/) 定义构成应用程序的服务
    ```
    build                           构建时的配置选项，可直接指定一个文件夹
    image                           指定镜像
    networks                        所属网路
    depends_on                      服务之间的依赖关系
    ```
3. [docker-compose CLI](https://docs.docker.com/compose/reference/)
    ```
    build                           构建或重构 services
    up -d                           创建并启动 containers
    ps                              列出 containers
    logs                            查看 containers output
    stop                            停止 services
    rm                              删除已停止的 containers
    ```
---
## 安装软件
1. [MySQL](https://blog.csdn.net/pall_scall/article/details/112154454)
```bash
docker run -d --name mysql -p 3306:3306 --privileged --restart=always
[-v D:/Docker/MySQL/my.cnf:/etc/mysql/my.cnf -v D:/Docker/MySQL/data:/var/lib/mysql]
-e MYSQL_ROOT_PASSWORD=root mysql

docker exec -it mysql mysql -uroot -proot
     create user ljh@172.17.0.1 identified by '123456';
     grant all privileges on `ry-vue`.* to ljh@172.17.0.1 with grant option;
     flush privileges;
```
2. [Redis](https://blog.csdn.net/qq_34670974/article/details/94051251)
```bash
docker run -d --name redis -p 6379:6379 --restart=always
[-v D:/Docker/Redis/data:/data:rw]
[-v D:/Docker/Redis/conf/redis.conf:/etc/redis/redis.conf:ro]
redis [redis-server /etc/redis/redis.conf]

docker exec -it redis redis-cli
```
3. [MongoDB](https://www.cnblogs.com/yunquan/p/11174265.html)
```bash
docker run -itd --name mongo -p 27017:27017 mongo [--auth]

docker exec -it mongo mongo admin
# 创建用户和密码
db.createUser({user: 'admin', pwd:'123', roles: [{role: "userAdminAnyDatabase", db: "admin"}]})
# 验证
db.auth("admin", "123") 
```
4. [Tomcat](https://www.cnblogs.com/liyiran/p/12544715.html)
```bash
docker run -d --name tomcat -p 8080:8080 
[-v D:\Docker\Tomcat\webapps:/usr/local/tomcat/webapps]
tomcat:9.0.45

docker run -d --name tomcat -p 8080:8080 -v D:\Docker\Tomcat\webapps:/usr/local/tomcat/webapps tomcat:9.0.45
```
5. [Nginx](https://blog.csdn.net/goodboy31985/articleb/details/106676475/)
```bash
docker run -d --name nginx -p 80:80 --restart=always
[-v D:\Docker\Nginx\conf\nginx.conf:/etc/nginx/nginx.conf]
[-v D:\Docker\nginx\conf\conf.d:/etc/nginx/conf.d]
[-v D:\Docker\Nginx\log:/var/log/nginx]
[-v D:\Docker\Nginx\html:/usr/share/nginx/html]
nginx
```
6. [Zookeeper](https://www.cnblogs.com/idea360/p/12405113.html)
- @see docker/compose/zookeeper/docker-compose-zookeeper-cluster.yml
```bash
docker network create docker_net
docker-compose -f docker-compose-zookeeper-cluster.yml up -d

docker exec -it zoo1 bash
./bin/zkServer.sh status                          Mode: follower

docker exec -it zoo2 bash
./bin/zkServer.sh status                          Mode: follower

docker exec -it zoo3 bash
./bin/zkServer.sh status                          Mode: leader
echo srvr | nc localhost 2181

docker run -it --rm --name ZookeeperCluster --link zoo1 --link zoo2 --link zoo3 --net docker_net zookeeper zkCli.sh -server zoo1:2181,zoo2:2181,zoo3:2181
```
7. [RabbitMQ](https://www.cnblogs.com/feily/p/14207897.html)
```bash
docker run -d --name rabbitmq -p 4369:4369 -p 5671:5671 -p 5672:5672 -p 15672:15672 -p 1883:1883 -p 8883:8883 rabbitmq:management-alpine

docker exec -it rabbitmq bash
    rabbitmq-plugins list
    rabbitmq-plugins enable rabbitmq_management
        http://localhost:15672       Username:guest      Password:guest
    rabbitmq-plugins enable rabbitmq_mqtt
```
8. [MinIO](https://www.jianshu.com/p/52dbc679094a)
```bash
docker run -d --name minio -p 9000:9000 --restart=always
[-v D:\Docker\MinIO:/data]
[-v D:\Docker\MinIO\config:/root/.minio]
-e MINIO_ACCESS_KEY=minio
-e MINIO_SECRET_KEY=minio123
minio server /data
```
9. [Jenkins](https://www.cnblogs.com/fuzongle/p/12834080.html)
```bash
docker run -d --name jenkins -p 8080:8080 -p 50000:50000 
[-v D:\Docker\Jenkins:/var/jenkins_home]
[-v /etc/localtime:/ect/localtime]
jenkins
```
10. Ubuntu
```bash
docker run -itd --name ubuntu --privileged ubuntu
```
11. [InfluxDB](https://registry.hub.docker.com/_/influxdb)
```bash
docker run -d --name influxdb -p 8086:8086 influxdb

docker exec -it influxdb bash
```
--- 