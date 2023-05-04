# Docker

---
## Reference
1. [Docker overview](https://docs.docker.com/get-started/overview/)
2. [Docker - Learn | Microsoft Docs](https://docs.microsoft.com/zh-CN/learn/modules/intro-to-docker-containers/)
3. [Windows 安装 | 菜鸟教程](https://www.runoob.com/docker/windows-docker-install.html)
4. [Docker Desktop 快速上手](https://xunmi.blog.csdn.net/article/details/108641842)
---
## 课程
1. [第一个docker化的java应用-慕课网](https://www.imooc.com/learn/824)
2. [Docker入门教程-慕课网](https://www.imooc.com/learn/867)
---
## VM vs DOcker
![VM vs Docker](https://img3.mukewang.com/608d8eeb0001298319201080-500-284.jpg)

---
## Docker Desktop
### 安装
1. [安装前创建目录链接](https://www.zhihu.com/question/359332823/answer/923520420)
- 以管理员身份运行 CMD
```bash
mklink /j "C:\Program Files\Docker" "D:\Docker\Docker"
mklink /j "C:\ProgramData\DockerDesktop" "D:\Docker\DockerDesktop"
mklink /j "C:\Users\Administrator\AppData\Local\Docker" "D:\Docker\Local"
mklink /j "C:\Users\Administrator\AppData\Roaming\Docker" "D:\Docker\Roaming\Docker"
mklink /j "C:\Users\Administrator\AppData\Roaming\Docker Desktop" "D:\Docker\Roaming\Docker Desktop"
```
2. 下载地址：https://www.docker.com/products/docker-desktop
   或：https://hub.docker.com/editions/community/docker-ce-desktop-windows
3. 下载并安装 [WSL2](https://docs.microsoft.com/zh-cn/windows/wsl/install-manual#step-4---download-the-linux-kernel-update-package)
4. Settings → Docker Engine
```
"registry-mirrors": [
  "https://docker-cn.com",
  "http://hub-mirror.c.163.com",
  "https://docker.mirrors.ustc.edu.cn"
],
```
5. < Win 10 build 18362.1040：Settings → Resources → FILE SHARING
6. ≥ Win 10 build 18362.1040：配置 [.wslconfig](https://docs.microsoft.com/zh-cn/windows/wsl/wsl-config#configure-global-options-with-wslconfig)
```
[wsl2]
memory=2GB
swap=4GB
localhostForwarding=true
```
### 增加端口映射
1. docker ps -a → 记下 CONTAINER ID
2. docker inspect \<CONTAINER ID>|grep Id，查看容器的 Id
3. Win + E → \\wsl.localhost\docker-desktop-data\data\docker\containers\Id
4. 修改 hostconfig.json
```
"PortBindings": {
	"8080/tcp": [
		{
			"HostIp": "",
			"HostPort": "8088"
		}
	]
},
```
5. 修改 config.v2.json
```
"ExposedPorts": {
	"8080/tcp": {}
},
"Ports": {
	"8080/tcp": [
		{
			"HostIp": "0.0.0.0",
			"HostPort": "8088"
		}
	]
},
```
---
## [Linux 安装 Docker](https://docs.docker.com/desktop/install/linux-install/)
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
    --restart                                                   总是启动
    --privileged                                                扩展权限
    --rm                                                        当容器退出时自动删除
    --link                                                      连接其它容器
    --net                                                       将容器连接到网络
docker ps [OPTIONS]                                             列出 containers
docker exec [OPTIONS] CONTAINER COMMAND [ARG...]                在 container 中运行 command
    -it CONTAINER bash
docker start|stop|restart [OPTIONS] CONTAINER [CONTAINER...]    启动|停止|重启 containers
docker create [OPTIONS] IMAGE [COMMAND] [ARG...]                创建 container
docker container                                                管理 containers
    update [OPTIONS] CONTAINER [CONTAINER...]                   修改一个或多个 containers 的配置
docker rm [OPTIONS] CONTAINER [CONTAINER...]                    移除 containers
docker rmi [OPTIONS] IMAGE [IMAGE...]                           移除 images
docker port CONTAINER [PRIVATE_PORT[/PROTO]]                    列出容器的端口映射或特定映射
docker inspect [OPTIONS] NAME|ID [NAME|ID...]                   返回 Docker 对象的 low-level 信息
    # 显示所有容器 IP
    docker inspect --format='{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)
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
## [Docker 网络](https://docs.docker.com/network/)
![Docker 网络](https://img.mukewang.com/608d6a4c0001e15019201080-500-284.jpg)

---
## [Dockerfile](https://docs.docker.com/engine/reference/builder/)
1. Commands
```
FROM                                               初始化构建并为后续指令设置基本 image
RUN                                                执行命令，docker build 时运行
    shell 格式    RUN ./test.php dev offine
    exec 格式     RUN ["./test.php", "dev", "offine"]
CMD                                                执行命令，docker run 时运行
ENTRYPOINT                                         执行命令，不会被 docker run 的参数指定的指令所覆盖，而且参数会传送给指定的程序
ADD                                                添加文件，gzip 和 bzip2 会自动解压
COPY                                               复制文件
ENV                                                设置环境变量
ARG                                                设置环境变量，仅在 Dockerfile 内有效
    docker build --build-arg
MAINTAINER                                         维护者
USER                                               用户
VOLUME                                             VOLUME
WORKDIR                                            工作目录
EXPOSE                                             端口
```
2. 镜像分层：Dockerfile 中的每一行都产生一个新层
   ![镜像分层](https://img2.mukewang.com/608d9d330001dd2819201080-500-284.jpg)
3. Dockerfile Demo
    1. 编写 Dockerfile 文件
    ```
    FROM ubuntu:latest
    MAINTAINER ljh
    RUN sed -i 's/archive.ubuntu.com/mirrors.ustc.edu.cn/g' /etc/apt/sources.list
    RUN apt-get update
    RUN apt-get install -y nginx
    COPY index.html /var/www/html
    ENTRYPOINT ["/usr/sbin/nginx", "-g", "daemon off;"]
    EXPOSE 80
    ```
    2. `docker build -t hello_docker`
    3. `docker run hello_docker`
    4. localhost:80
---
## [Docker Compose](https://docs.docker.com/compose/)
1. [基本步骤](https://docs.docker.com/compose/gettingstarted/)
    1. 定义应用程序依赖项
    2. 创建 Dockerfile
    3. [在 Compose 文件中定义服务](https://docs.docker.com/compose/compose-file/)
    ```
    build                           构建时的配置选项，可直接指定一个文件夹
    image                           指定镜像
    networks                        所属网路
    depends_on                      服务之间的依赖关系
    ```
2. [docker-compose CLI](https://docs.docker.com/compose/reference/)
```
build                               构建或重构 services
config                              验证并查看 Compose 文件
download                            停止和删除 containers, networks, images, and volumes
exec                                在正在运行的 container 中执行命令
logs                                查看 containers 输出
ps                                  列出 containers
rm                                  移除停止的 containers
stop                                停止 services
up -d                               创建并启动 containers
```
3. [Sample apps with Compose](https://docs.docker.com/compose/samples-for-compose/)
4. [环境变量](https://docs.docker.com/compose/environment-variables/)
---
## 安装软件
1. [MySQL](https://hub.docker.com/_/mysql)
- [Windows 下 docker 安装 mysql 并挂载数据](https://blog.csdn.net/pall_scall/article/details/112154454)
```bash
docker run -d --name mysql -p 3306:3306 mysql

# 复制一份 my.cnf
docker cp mysql:/etc/my.cnf my.cnf

docker run -d --name mysql -p 3306:3306 --privileged --restart=unless-stopped \
-v D:/Docker/Data/MySQL/my.cnf:/etc/mysql/my.cnf \
-v D:/Docker/Data/MySQL/data:/var/lib/mysql \
-v D:/Docker/Data/MySQL/files:/var/lib/mysql-files \
-e MYSQL_ROOT_PASSWORD=root \
mysql

docker exec -it mysql mysql -uroot -proot
     create user ljh@172.17.0.1 identified by '123456';
     grant all privileges on `ry-vue`.* to ljh@172.17.0.1 with grant option;
     flush privileges;
```
2. [SQL Server](https://docs.microsoft.com/zh-cn/sql/linux/quickstart-install-connect-docker)
```
docker pull mcr.microsoft.com/mssql/server:2019-latest

docker run -d --name mssql \
-e "ACCEPT_EULA=Y" \
-e "SA_PASSWORD=Cesc123456!" \
-p 1433:1433 \
mcr.microsoft.com/mssql/server:2019-latest
```
3. [InfluxDB](https://hub.docker.com/_/influxdb)
- [InfluxDBException](https://community.influxdata.com/t/getting-started-with-influxdb-docker-401-unauthorized/16989/3)
- [influx v1 auth](https://docs.influxdata.com/influxdb/v2.0/reference/cli/influx/v1/auth/)
```bash
docker run -d --name influxdb -p 8086:8086 \
-e DOCKER_INFLUXDB_INIT_USERNAME=admin \
-e DOCKER_INFLUXDB_INIT_PASSWORD=12345678 \
-e DOCKER_INFLUXDB_INIT_ORG=my-org \
-e DOCKER_INFLUXDB_INIT_BUCKET=my-bucket \
influxdb

docker exec -it influxdb bash
cd /usr/local/bin
influx setup
influx bucket list                                    # 记下 ID
influx v1 auth create --read-bucket 303f1c88eaa4473a --write-bucket 303f1c88eaa4473a --username admin
influx v1 dbrp create --bucket-id 303f1c88eaa4473a --db test --rp autogen --default
```
4. [Redis](https://hub.docker.com/_/redis)
- [Docker 部署 Redis](https://blog.csdn.net/qq_41316955/article/details/108381923)
- [redis.conf](https://redis.io/docs/manual/config/) 选择对应版本
    - `# bind 127.0.0.1 -::1` 或 `bind 0.0.0.0`x`x`
    - `protected-mode no`
```bash
docker network create --subnet=172.10.0.0/16 redis_net

docker run -d --name redis-master --restart=unless-stopped \
--net redis_net --ip 172.10.0.2 -p 6379:6379 \
-v D:/Docker/Data/Redis/data:/data:rw \
-v D:/Docker/Data/Redis/redis.conf:/etc/redis/redis.conf:ro \
redis redis-server ../etc/redis/redis.conf

# replica 不挂载 data 目录，否则 replica 不知该挂载数据还是从 master 复制数据
docker run -d --name redis-replica \
--net redis_net --ip 172.10.0.3 -p 6380:6379 \
-v D:/Docker/Data/Redis/redis-replica.conf:/etc/redis/redis.conf:ro \
redis redis-server ../etc/redis/redis.conf

docker exec -it redis-master redis-cli
info replication

docker exec -it redis-replica redis-cli
info replication
```
5. [MongoDB](https://hub.docker.com/_/mongo)
- [MongoDB 用户角色配置](https://www.cnblogs.com/out-of-memory/p/6810411.html)
```bash
docker run -d --name mongo mongo:4.4.18

# 复制一份 mongod.conf，并修改配置
docker cp mongo:/etc/mongod.conf.orig D:/Docker/Data/MongoDB/mongod.conf
storage:
  dbPath: /data/db
net:
  bindIp: 0.0.0.0

docker run -d --name mongo -p 27017:27017 \
-v D:/Docker/Data/MongoDB/mongod.conf:/etc/mongo/mongod.conf \
mongo:4.4.18 -f ../etc/mongo/mongod.conf

# 进入 admin 数据库
# mongo 4- 使用 mongo，mongo 5+ 使用 mongosh
docker exec -it mongo mongo admin | docker exec -it mongo mongosh admin
    # 上面一句等同于下面三句
    docker exec -it mongo bash
    mongo | mongosh
    use admin

# 创建 root 账号密码，角色 root
db.createUser({user: "root", pwd: "root", roles: ["root"]})
db.auth("root", "root")

# 创建 ljh 账号密码，角色 readWrite
use spring_data
db.createUser({user: "ljh", pwd: "123456", roles: [{role: "readWrite", db: "spring_data"}]})
exit
mongo -uljh -p123456 --authenticationDatabse=spring_data
```
6. [Tomcat](https://hub.docker.com/_/tomcat)
- [Docker 安装 tomcat 并挂载目录](https://www.cnblogs.com/liyiran/p/12544715.html)
```bash
docker run -d --name tomcat -p 8080:8080 \
-v D:/Docker/Data/Tomcat/webapps:/usr/local/tomcat/webapps \
tomcat
```
7. [Nginx](https://hub.docker.com/_/nginx)
```bash
docker run -d --name nginx -p 80:80 --restart=unless-stopped \
-v D:/Docker/Data/Nginx/conf/nginx.conf:/etc/nginx/nginx.conf \
-v D:/Docker/Data/Nginx/conf/conf.d:/etc/nginx/conf.d \
-v D:/Docker/Data/Nginx/log:/var/log/nginx \
-v D:/Docker/Data/Nginx/html:/usr/share/nginx/html \
nginx
```
8. [Zookeeper](https://hub.docker.com/_/zookeeper)
- [Docker 实战之 Zookeeper 集群](https://www.cnblogs.com/idea360/p/12405113.html)
- @see [docker-compose.yml](compose/zookeeper-cluster/docker-compose.yml)
```bash
docker network create zoo_net
docker-compose up -d

docker exec -it zoo1 bash
./bin/zkServer.sh status                          Mode: follower

docker exec -it zoo2 bash
./bin/zkServer.sh status                          Mode: follower

docker exec -it zoo3 bash
./bin/zkServer.sh status                          Mode: leader
echo srvr | nc localhost 2181

docker run -it --rm --name ZookeeperCluster --link zoo1 --link zoo2 --link zoo3 --net zoo_net zookeeper zkCli.sh -server zoo1:2181,zoo2:2181,zoo3:2181
```
- [Windows 下 docker 安装 zookeeper](https://blog.csdn.net/m0_67401055/article/details/124777613)
```bash
docker run -d --name zookeeper -p 2181:2181 --restart=unless-stopped zookeeper
```
9. [RabbitMQ](https://hub.docker.com/_/rabbitmq)
- [Win10 Docker 安装 RabbitMQ](https://www.cnblogs.com/feily/p/14207897.html)
```bash
docker run -d --name rabbitmq \
-p 4369:4369 -p 5671:5671 -p 5672:5672 -p 15672:15672 -p 1883:1883 -p 8883:8883 \
rabbitmq:management

docker exec -it rabbitmq bash
    rabbitmq-plugins list
    rabbitmq-plugins enable rabbitmq_management
        http://localhost:15672       Username:guest      Password:guest
    rabbitmq-plugins enable rabbitmq_mqtt
```
10. [RocketMQ](https://hub.docker.com/r/rocketmqinc/rocketmq)
- [docker-compose 部署 rocketmq](https://blog.csdn.net/oschina_41731918/article/details/123115102)
- @see [docker-compose.yml](compose/rocketmq/docker-compose.yml)
```bash
docker compose up -d

http://localhost:8180
``` 
11. [MinIO](https://hub.docker.com/r/minio/minio)
- [MinIO's Docker Implementation](https://docs.min.io/docs/minio-docker-quickstart-guide.html)
```bash
docker run -d --name minio -p 9000:9000 -p 9001:9001 \
-v D:/Docker/Data/MinIO:/data \
-v D:/Docker/Data/MinIO/config:/root/.minio \
-e MINIO_ROOT_USER=minio \
-e MINIO_ROOT_PASSWORD=minio123 \
minio/minio server /data --console-address ":9001"

http://localhost:9001/login
```
12. [Nacos](https://hub.docker.com/r/nacos/nacos-server)
- [Docker 部署 Nacos](https://www.cnblogs.com/serendipity-fzx/articles/15400618.html)
```bash
docker run -d --name nacos -p 8848:8848 -e MODE=standalone nacos/nacos-server

# RuoYi
docker run -d --name nacos -p 8848:8848 -p 9848:9848 -p 9849:9849 \
-e MODE=standalone \
-e SPRING_DATASOURCE_PLATFORM=mysql \
-e MYSQL_SERVICE_HOST=172.17.0.3 \
-e MYSQL_SERVICE_USER=root \
-e MYSQL_SERVICE_PASSWORD=root \
-e MYSQL_SERVICE_DB_NAME=ry-config \
nacos/nacos-server
```
13. [Sentinel](https://hub.docker.com/r/bladex/sentinel-dashboard)
```bash
docker run -d --name sentinel -p 8858:8858 bladex/sentinel-dashboard
```
14. [Jenkins](https://hub.docker.com/r/jenkins/jenkins)
- [Docker 快速安装 Jenkins 完美教程](https://www.cnblogs.com/fuzongle/p/12834080.html)
```bash
[docker network create --subnet=172.11.0.0/16 jenkins_net]

docker run -d --name jenkins -p 8080:8080 -p 50000:50000 --restart=on-failure \
[--net jenkins_net --ip 172.11.0.2 \]
-v D:/Docker/Data/Jenkins:/var/jenkins_home \
[-v /etc/localtime:/etc/localtime \]
jenkins/jenkins:latest-jdk11

http://localhost:8080
```
15. [Ubuntu](https://hub.docker.com/_/ubuntu)
```bash
docker run -itd --name ubuntu -p 22:22 --privileged ubuntu

docker exec -it ubuntu bash
```
--- 
