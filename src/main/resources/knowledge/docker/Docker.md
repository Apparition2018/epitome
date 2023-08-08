# Docker

---
## Reference
1. [Docker Documentation](https://docs.docker.com)
2. [RuoYi 应用容器部署](http://doc.ruoyi.vip/ruoyi-cloud/cloud/dokcer.html#%E5%BA%94%E7%94%A8%E5%AE%B9%E5%99%A8%E9%83%A8%E7%BD%B2)
3. [搭建和使用 Docker-腾讯云](https://cloud.tencent.com/document/product/213/46000)
## 课程
1. [尚硅谷Docker实战教程](https://www.bilibili.com/video/BV1gr4y1U7CY/)
2. [第一个docker化的java应用-慕课网](https://www.imooc.com/learn/824)
3. [Docker入门教程-慕课网](https://www.imooc.com/learn/867)
4. [Docker 容器简介 - Training | Microsoft Learn](https://learn.microsoft.com/zh-cn/training/modules/intro-to-docker-containers/)
5. [使用 Docker 生成容器化 Web 应用程序 - Training | Microsoft Learn](https://learn.microsoft.com/zh-cn/training/modules/intro-to-containers/)
---
## Docker vs Others
1. [Docker 与 VM - 应用程序部署技术之间的区别 - AWS](https://aws.amazon.com/cn/compare/the-difference-between-docker-vm/)
    ![Docker vs VM](https://geekflare.com/wp-content/uploads/2019/09/traditional-vs-new-gen.png)
2. [Kubernetes 与 Docker — 容器技术之间的区别 — AWS](https://aws.amazon.com/cn/compare/the-difference-between-kubernetes-and-docker/)
---
## [Docker Desktop](https://docs.docker.com/desktop/)
- [Docker Desktop for Linux vs Docker Engine](https://docs.docker.com/desktop/faqs/linuxfaqs/#what-is-the-difference-between-docker-desktop-for-linux-and-docker-engine)
### [Windows 安装 Docker Desktop](https://docs.docker.com/desktop/install/windows-install/)
1. [安装前创建目录链接](https://www.zhihu.com/question/359332823/answer/923520420)
    - 以管理员身份运行 CMD
    ```bash
    mklink /j "C:\Program Files\Docker" "D:\Docker\Docker"
    # 此路径不能是 symlink 了
    mklink /j "C:\ProgramData\DockerDesktop" "D:\Docker\DockerDesktop"
    mklink /j "C:\Users\Administrator\AppData\Local\Docker" "D:\Docker\Local"
    mklink /j "C:\Users\Administrator\AppData\Roaming\Docker" "D:\Docker\Roaming\Docker"
    mklink /j "C:\Users\Administrator\AppData\Roaming\Docker Desktop" "D:\Docker\Roaming\Docker Desktop"
    ```
2. 下载地址：https://www.docker.com/products/docker-desktop
   或：https://hub.docker.com/editions/community/docker-ce-desktop-windows
3. 启用 Windows 功能：①Hyper-V；②WSL（适用于 Linux 的 Windows 子系统）
4. 下载并安装 [WSL2](https://docs.microsoft.com/zh-cn/windows/wsl/install-manual#step-4---download-the-linux-kernel-update-package)
5. [镜像加速器](https://cr.console.aliyun.com/cn-qingdao/instances/mirrors)：Settings → Docker Engine
    ```
    ,
     "registry-mirrors": [
       "https://docker-cn.com",
       "https://hub-mirror.c.163.com",
       "https://docker.mirrors.ustc.edu.cn",
       "https://vdm14qsf.mirror.aliyuncs.com"
     ]
    ```
6. < Win 10 build 18362.1040：Settings → Resources → FILE SHARING
7. ≥ Win 10 build 18362.1040：配置 [.wslconfig](https://docs.microsoft.com/zh-cn/windows/wsl/wsl-config#configure-global-options-with-wslconfig)
    ```
    [wsl2]
    memory=2GB
    swap=4GB
    localhostForwarding=true
    ```
>### 增加端口映射
>1. docker ps -a → 记下 CONTAINER ID
>2. docker inspect \<CONTAINER ID>|grep Id，查看容器的 Id
>3. Win + E → \\wsl.localhost\docker-desktop-data\data\docker\containers\Id
>4. 修改 hostconfig.json
>   ```
>   "PortBindings": {
>       80/tcp": [
>           {
>               "HostIp": "",
>               "HostPort": "8088"
>           }
>       ]
>   },
>   ```
>5. 修改 config.v2.json
>   ```
>   "ExposedPorts": {
>       "8080/tcp": {}
>   },
>   "Ports": {
>       "8080/tcp": [
>           {
>               "HostIp": "0.0.0.0",
>               "HostPort": "8088"
>           }
>       ]
>   },
>   ```
### [Linux 安装 Docker Desktop](https://docs.docker.com/desktop/install/linux-install/)

---
## [Docker Engine](https://docs.docker.com/engine/)
### [安装 Docker Engine](https://docs.docker.com/engine/install/)
```bash
# 卸载旧版本（存储在/var/lib/docker/中的 images、container、volumes、networks 不会自动删除）
sudo yum remove docker docker-client docker-client-latest docker-common \
docker-latest docker-latest-logrotate docker-logrotate docker-engine

# 安装 Docker Engine 三种方式
# 1 设置 Docker's repositories 并从中进行安装，以便于安装和升级任务（推荐）
# 1.1 设置 repository
sudo yum install -y yum-utils
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
# 1.2 安装 Docker Engine、container 和 Docker Compose
sudo yum install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
# 2 下载 RPM package 并手动安装，适用于无法访问互联网的情况
# 2.1 https://download.docker.com/linux/centos/，选择 CentOS 版本，x86_64/stable/Packages/，下载要装的 rpm 文件
# 2.2 安装 Docker Engine，将下面路径更改为下载的 Docker package 路径
sudo yum install /**/***.rpm
# 2.3 升级 Docker Engine，下载新的 package 文件
sudo yum -y upgrade /**/***.rpm
# 3 在测试和开发环境下，使用自动化的便利脚本安装
curl -fsSL https://get.docker.com -o get-docker.sh
# 了解调用脚本时将运行哪些步骤
sudo sh ./get-docker.sh --dry-run
sudo sh get-docker.sh

# 卸载 Docker Engine
sudo yum remove docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin docker-ce-rootless-extras
sudo rm -rf /var/lib/docker
sudo rm -rf /var/lib/containerd
```
### [安装后步骤](https://docs.docker.com/engine/install/linux-postinstall/)
```bash
# 创建 docker 组
sudo groupadd docker
# 将用户添加到 docker 组，然后注销重新登录
sudo usermod aGdocker $USER
# 登录到新组
newgrp docker
# 验证不是用 sudo 的情况下运行 docker 命令
docker run hello-world
# 开机启动
sudo systemctl enable docker.service
sudo systemctl enable containerd.service
# 取消开机启动
sudo systemctl disable docker.service
sudo systemctl disable containerd.service
```
### [daemon.json](https://docs.docker.com/engine/reference/commandline/dockerd/#daemon-configuration-file)
1. `vim /etc/docker/daemon.json`
    ```json5
    {
      "registry-mirrors": [
        // 腾讯云 Docker 镜像
        "https://mirror.ccs.tencentyun.com"
      ],
      // json-file driver: https://docs.docker.com/config/containers/logging/json-file/
      "log-driver": "json-file",
      "log-opts": {
        "max-size": "10m",
        "max-file": "3"
      }
    }
    ```
2. `sudo systemctl restart docker`，使更改生效。现有 containers 不使用新 json-file driver
3. 使用 `--log-driver` 为 docker create 或 docker run 的 container 设置日志驱动
    - `docker run --log-driver json-file --log-opt max-size=10m alpine echo hello world`
---
## [Docker Compose](https://docs.docker.com/compose/)
- [Sample apps with Compose](https://docs.docker.com/compose/samples-for-compose/)
- [环境变量](https://docs.docker.com/compose/environment-variables/)
### [安装场景](https://docs.docker.com/compose/install/)
1. [安装 Docker Desktop](https://docs.docker.com/desktop/install/linux-install/)，Docker Desktop 包含 Docker Compose、Docker Engine、Docker CLI
2. 安装 Compose plugin
    1. [Docker's repository](https://docs.docker.com/compose/install/linux/#install-using-the-repository)
    2. [手动安装](https://docs.docker.com/compose/install/linux/#install-the-plugin-manually) 
3. [安装 Compose standalone](https://docs.docker.com/compose/install/other/)
### [基本步骤](https://docs.docker.com/compose/gettingstarted/)
1. 定义应用程序依赖项
2. 创建 Dockerfile
3. [在 Compose 文件中定义服务](https://docs.docker.com/compose/compose-file/)
    ```
    build                           构建时的配置选项，可直接指定一个文件夹
    image                           指定镜像
    networks                        所属网路
    depends_on                      服务之间的依赖关系
    ```
### [docker-compose CLI](https://docs.docker.com/compose/reference/)
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
---
## [基本命令](https://docs.docker.com/engine/reference/commandline/docker/)
- Docker
```
docker version [OPTIONS]                                        显示 Docker 版本信息
docker info [OPTIONS]                                           显示 Docker system-wide 信息
docker inspect [OPTIONS] NAME|ID [NAME|ID...]                   显示 Docker 对象的 low-level 信息
    # 显示所有 container IP
    docker inspect --format='{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)
    # 显示 container 日志地址
    docker inspect --format '{{.LogPath}}' c951678a44fc
docker login                                                    登录 registry
docker logout                                                   注销 registry 
```
- image
```
docker images [OPTIONS] [REPOSITORY[:TAG]]                      列出 iamges
docker search [OPTIONS] TERM                                    在 Docker Hub 搜索 images
docker pull [OPTIONS] NAME[:TAG|@DIGEST]                        从 registry 下载 image
docker push [OPTIONS] NAME[:TAG]                                将 image 上载 registry
docker rmi [OPTIONS] IMAGE [IMAGE...]                           移除 images
docker build [OPTIONS] PATH | URL | -                           从 Dockerfile 构建 image
    -t, --tag                                                   名字和标签，name:tag 格式
docker tag SOURCE_IMAGE[:TAG] TARGET_IMAGE[:TAG]                创建 tag
```
- container
```
docker container create [OPTIONS] IMAGE [COMMAND] [ARG...]      创建新 container
docker container update [OPTIONS] CONTAINER [CONTAINER...]      更新 container 配置
docker container run [OPTIONS] IMAGE [COMMAND] [ARG...]         从 image 创建并运行新 container
    -i, --interactive                                           即使没有 attached，也要保持 STDIN 打开
    -t, --tty                                                   分配一个伪 TTY
    -d, --detach                                                在后台运行 container 并打印 ID
    -v, --volume                                                挂载 volume，$PWD 当前目录
    --volumes-from                                              从指定 container 挂载 volumes
    -p, --publish                                               将 container 的端口发布到主机
    -e, --env                                                   设置环境变量
    --restart                                                   container 退出时要应用的自动重启策略
        no                                                      默认，不自动重启
        on-failure[:max-retries]                                退出状态非0则自动重启，即遇到错误时自动重启
        unless-stopped                                          总是自动重启，除非状态为 Exited
        always                                                  总是自动重启
    --privileged                                                扩展权限，获得完整的 container 功能
    --rm                                                        退出时自动移除 container
    --link                                                      将 link 添加到另一个 container
    --net, --network                                            将 container 连接到网络
    --ip                                                        IPv4 地址
    --ip6		                                                IPv6 地址
docker ps [OPTIONS]                                             列出 containers
docker container ls [OPTIONS]                                   列出 containers
    -a, --all                                                   显示所有 running containers
docker container start [OPTIONS] CONTAINER [CONTAINER...]       启动 containers
docker container stop [OPTIONS] CONTAINER [CONTAINER...]        停止 containers
docker container restart [OPTIONS] CONTAINER [CONTAINER...]     重启 containers
docker container rm [OPTIONS] CONTAINER [CONTAINER...]          移除 containers
docker container kill [OPTIONS] CONTAINER [CONTAINER...]        杀掉 containers
docker container exec [OPTIONS] CONTAINER COMMAND [ARG...]      在运行的 container 中执行命令
    -it CONTAINER bash
docker container cp [OPTIONS] CONTAINER:SRC_PATH DEST_PATH|-    在 container 和本地文件系统之间复制文件或文件夹
docker container commit [OPTIONS] CONTAINER [REPOSITORY[:TAG]]  根据 container 的更改创建 image
    -m                                                          提交消息
docker container logs [OPTIONS] CONTAINER                       获取 container 日志
docker container port CONTAINER [PRIVATE_PORT[/PROTO]]          列出 container 的端口映射或特定映射
docker container inspect [OPTIONS] CONTAINER [CONTAINER...]     显示 containers 详细信息
```
- network
```
docker network create [OPTIONS] NETWORK                         创建网络
    -- subnet                                                   代表网段的 CIDR 格式的子网
```
---
## [Dockerfile](https://docs.docker.com/engine/reference/builder/)
1. Commands
    ```
    FROM                                    初始化一个新的 build stage，并为后续指令设置 base iamge
    RUN                                     执行命令，docker build 时执行
    CMD                                     执行命令，docker run 时执行
    ENTRYPOINT                              执行命令，不会被 docker run 的参数指定的指令所覆盖，而且参数会传送给指定的程序
    ADD                                     添加文件，gzip 和 bzip2 会自动解压
    COPY                                    复制文件
    ENV                                     设置环境变量
    ARG                                     设置环境变量，仅在 Dockerfile 内有效
        docker build --build-arg
    MAINTAINER                              维护者
    USER                                    用户
    VOLUME                                  VOLUME
    WORKDIR                                 工作目录
    EXPOSE                                  端口
    ```
2. Dockerfile Demo
    1. [Containerize an application](https://docs.docker.com/get-started/02_our_app/)
        - 克隆项目：`cd /home/lighthouse/git` → `git clone https://github.com/docker/getting-started-app.git`
        - 创建 Dockerfile 文件：`cd /getting-started-app` → `touch Dockerfile`
            ```
            # syntax=docker/dockerfile:1
               
            FROM node:18-alpine
            WORKDIR /app
            COPY . .
            RUN yarn install --production
            CMD ["node", "src/index.js"]
            EXPOSE 3000
            ```
        - 构建容器镜像：`docker build -t getting-started .`
        - 启动容器：`docker run -dp 127.0.0.1:3000:3000 getting-started` → http://localhost:3000
    2. [Best practices for writing Dockerfiles](https://docs.docker.com/develop/develop-images/dockerfile_best-practices/)
---
## [MySQL](https://hub.docker.com/_/mysql)
- [Windows 下 docker 安装 mysql 并挂载数据](https://blog.csdn.net/pall_scall/article/details/112154454)
- 配置文件读取顺序命令：`mysql --verbose --help|grep -A 1 'Default options'`
```bash
mkdir -p /home/lighthouse/docker_data/mysql/{data,conf,log,files}
cd /home/lighthouse/docker_data/mysql
```
```bash
docker run -d --name mysql -p 3306:3306 --privileged --restart=unless-stopped \
-v $PWD/data:/var/lib/mysql \
-v $PWD/conf:/etc/mysql/conf.d \
-v $PWD/log:/var/log/mysql \
-v $PWD/files:/var/lib/mysql-files \
-e MYSQL_ROOT_PASSWORD=root \
mysql \
--character-set-server=utf8mb4 \
--collation-server=utf8mb4_unicode_ci
```
```bash
docker exec -it mysql mysql -uroot -proot
create user root@'%' identified by 'root';
grant all privileges on *.* to root@'%' with grant option;
flush privileges;

alter user root@'%' identified by 'Cesc123!' password expire never;
alter user root@'localhost' identified by 'Cesc123!';
```
---
## [MariaDB](https://hub.docker.com/_/mariadb)
```bash
mkdir -p /home/lighthouse/docker_data/mariadb/{conf,logs,data}
cd /home/lighthouse/docker_data/mariadb
# 从容器复制配置文件
docker run -d --name mariadb --privileged -e MYSQL_ROOT_PASSWORD=root mariadb
docker cp mariadb:/etc/mysql/. $PWD/conf
docker stop mariadb
docker rm mariadb
```
```bash
docker run -d --name mariadb -p 3307:3306 --privileged --restart=unless-stopped \
-v $PWD/conf:/etc/mysql \
-v $PWD/data:/var/lib/mysql \
-v $PWD/logs:/var/log/mysql \
-e MYSQL_ROOT_PASSWORD=root \
mariadb \
--default_authentication_plugin=mysql_native_password \
--character-set-server=utf8mb4 \
--collation-server=utf8mb4_general_ci
```
---
## [SQL Server](https://docs.microsoft.com/zh-cn/sql/linux/quickstart-install-connect-docker)
```bash
docker run -d --name mssql \
-e "ACCEPT_EULA=Y" \
-e "SA_PASSWORD=Cesc123456!" \
-p 1433:1433 \
mcr.microsoft.com/mssql/server:2019-latest
```
---
## Oracle
```bash
docker run -d --name oracle_11g -p 1521:1521 registry.cn-hangzhou.aliyuncs.com/helowin/oracle_11g
```
---
## [InfluxDB](https://hub.docker.com/_/influxdb)
- [InfluxDBException](https://community.influxdata.com/t/getting-started-with-influxdb-docker-401-unauthorized/16989/3)
- [influx v1 auth](https://docs.influxdata.com/influxdb/v2.0/reference/cli/influx/v1/auth/)
```bash
docker run -d --name influxdb -p 8086:8086 \
-e DOCKER_INFLUXDB_INIT_USERNAME=admin \
-e DOCKER_INFLUXDB_INIT_PASSWORD=12345678 \
-e DOCKER_INFLUXDB_INIT_ORG=my-org \
-e DOCKER_INFLUXDB_INIT_BUCKET=my-bucket \
influxdb
```
```bash
docker exec -it influxdb bash
cd /usr/local/bin
influx setup
influx bucket list                                    # 记下 ID
influx v1 auth create --read-bucket 303f1c88eaa4473a --write-bucket 303f1c88eaa4473a --username admin
influx v1 dbrp create --bucket-id 303f1c88eaa4473a --db test --rp autogen --default
```
---
## [Redis](https://hub.docker.com/_/redis)
- [Docker 部署 Redis](https://blog.csdn.net/qq_41316955/article/details/108381923)
- [redis.conf](https://redis.io/docs/manual/config/) 选择对应版本
    ```
    requirepass 123456
    # bind 127.0.0.1 -::1
    bind 0.0.0.0
    ```
```bash
mkdir -p /home/lighthouse/docker_data/redis/data
cd /home/lighthouse/docker_data/redis
# 将 redis.conf scp 到当前目录

docker network create --subnet=172.10.0.0/16 redis_net

docker run -d --name redis-master --restart=unless-stopped \
--net redis_net --ip 172.10.0.2 -p 6379:6379 \
-v $PWD/data:/data:rw \
-v $PWD/redis.conf:/etc/redis/redis.conf:ro \
redis redis-server /etc/redis/redis.conf

# replica 不挂载 data 目录，否则 replica 不知该挂载数据还是从 master 复制数据
docker run -d --name redis-replica \
--net redis_net --ip 172.10.0.3 -p 6380:6379 \
-v $PWD/redis-replica.conf:/etc/redis/redis.conf:ro \
redis redis-server /etc/redis/redis.conf
```
```bash
docker exec -it redis-master redis-cli
auth 123456
info replication

docker exec -it redis-replica redis-cli
auth 123456
info replication
```
---
## [MongoDB](https://hub.docker.com/_/mongo)
- [MongoDB 用户角色配置](https://www.cnblogs.com/out-of-memory/p/6810411.html)
```bash
mkdir -p /home/lighthouse/docker_data/mongodb
cd /home/lighthouse/docker_data/mongodb
# 从容器复制 mongod.conf
docker run -d --name mongo mongo:4.4.21
docker cp mongo:/etc/mongod.conf.orig $PWD/mongod.conf
    storage:
      dbPath: /data/db
    net:
      bindIp: 0.0.0.0
docker stop mongo
docker rm mongo
```
```bash
docker run -d --name mongo -p 27017:27017 \
-v $PWD/mongod.conf:/data/configdb/mongod.conf \
mongo:4.4.21 -f /data/configdb/mongod.conf
```
```bash
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
---
## [Tomcat](https://hub.docker.com/_/tomcat)
- [Docker 安装 tomcat 并挂载目录](https://www.cnblogs.com/liyiran/p/12544715.html)
```bash
docker run -d --name tomcat -p 8080:8080 \
-v D:/Docker/Data/Tomcat/webapps:/usr/local/tomcat/webapps \
tomcat
```
---
## [Nginx](https://hub.docker.com/_/nginx)
- [Docker 安装 Nginx](https://blog.csdn.net/BThinker/article/details/123507820)
```bash
mkdir -p /home/lighthouse/docker_data/nginx/{conf,logs,share}
cd /home/lighthouse/docker_data/nginx
# 从容器复制配置文件
docker run -d --name nginx nginx
docker cp nginx:/etc/nginx/. $PWD/conf
docker cp nginx:/var/run/nginx.pid $PWD/
docker stop nginx
docker rm nginx
```
```bash
docker run -d --name nginx -p 80:80 -p 8080:8080 --restart=unless-stopped \
-v $PWD/conf:/etc/nginx \
-v $PWD/logs:/var/log/nginx \
-v $PWD/share:/usr/share/nginx \
-v $PWD/nginx.pid:/var/run/nginx.pid \
nginx
```
---
## [Zookeeper](https://hub.docker.com/_/zookeeper)
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
---
## [RabbitMQ](https://hub.docker.com/_/rabbitmq)
- [Win10 Docker 安装 RabbitMQ](https://www.cnblogs.com/feily/p/14207897.html)
- [Networking and RabbitMQ](https://www.rabbitmq.com/networking.html)
    ```
    4369            EPMD (Erlang Port Mapper Daemon)
    25672           Erlang distribution
    5672, 5671      AMQP 0-9-1 and AMQP 1.0 clients without and with TLS
    15672, 15671    HTTP API clients, management UI and rabbitmqadmin, without and with TLS
    1883, 8883      MQTT clients without and with TLS
    ```
```bash
docker run -d --name rabbitmq \
-p 4369:4369 -p 25672:25672 \
-p 5672:5672 -p 5671:5671 \
-p 15672:15672 -p 15671:15671 \
-p 1883:1883 -p 8883:8883 \
rabbitmq:management
```
```bash
docker exec -it rabbitmq bash
    rabbitmq-plugins list
    rabbitmq-plugins enable rabbitmq_management
        # Console：http://43.136.102.115:15672/  guest/guest
    rabbitmq-plugins enable rabbitmq_mqtt
```
---
## [RocketMQ](https://hub.docker.com/r/rocketmqinc/rocketmq)
- [docker-compose 部署 rocketmq](https://blog.csdn.net/oschina_41731918/article/details/123115102)
- @see [docker-compose.yml](compose/rocketmq/docker-compose.yml)
```bash
docker compose up -d

http://localhost:8180
``` 
---
## [MinIO](https://hub.docker.com/r/minio/minio)
- [Download MinIO](https://www.minio.org.cn/download.shtml#/docker)
- [MinIO Object Storage for Container](https://min.io/docs/minio/container/index.html)
```bash
mkdir -p /home/lighthouse/docker_data/minio/{data,config}
cd /home/lighthouse/docker_data/minio

docker run -d --name minio -p 9000:9000 -p 9001:9001 \
-v $PWD/data:/data \
-v $PWD/config:/root/.minio \
-e MINIO_ROOT_USER=minio \
-e MINIO_ROOT_PASSWORD=minio123 \
minio/minio server /data --console-address ":9001"

# Console：http://43.136.102.115:9001/login  minio/minio123
```
---
## [Nacos](https://hub.docker.com/r/nacos/nacos-server)
- [Docker 部署 Nacos](https://www.cnblogs.com/serendipity-fzx/articles/15400618.html)
- [Nacos 内存参数调优](https://cloud.tencent.com/developer/article/1913455)
```bash
mkdir -p /home/lighthouse/docker_data/nacos/{conf,logs}
cd /home/lighthouse/docker_data/nacos
# 从容器复制 conf 和 logs 文件夹
docker run -d --name nacos -e MODE=standalone \
-e JVM_XMS=64m \
-e JVM_XMX=64m \
-e JVM_XMN=16m \
-e JVM_MS=8m \
-e JVM_MMS=8m \
nacos/nacos-server
docker cp nacos:/home/nacos/conf/. $PWD/conf
docker cp nacos:/home/nacos/logs/. $PWD/logs
docker stop nacos
docker rm nacos
```
```bash
docker run -d --name nacos -p 8848:8848 -p 9848:9848 -p 9849:9849 \
-e MODE=standalone \
-e SPRING_DATASOURCE_PLATFORM=mysql \
-e MYSQL_SERVICE_HOST=43.136.102.115 \
-e MYSQL_SERVICE_USER=root \
-e MYSQL_SERVICE_PASSWORD=Cesc123! \
-e MYSQL_SERVICE_DB_NAME=nacos \
-e MYSQL_SERVICE_DB_PARAM="characterEncoding=utf8&connectTimeout=10000&socketTimeout=30000&autoReconnect=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true" \
-e JVM_XMS=64m \
-e JVM_XMX=64m \
-e JVM_XMN=16m \
-e JVM_MS=8m \
-e JVM_MMS=8m \
-v $PWD/conf:/home/nacos/conf \
-v $PWD/logs:/home/nacos/logs \
nacos/nacos-server

# Console：http://43.136.102.115:8848/nacos/index.html nacos/nacos
```
---
## [Sentinel](https://hub.docker.com/r/bladex/sentinel-dashboard)
```bash
docker run -d --name sentinel -p 8858:8858 bladex/sentinel-dashboard
```
---
## [Jenkins](https://hub.docker.com/r/jenkins/jenkins)
- [Docker 快速安装 Jenkins 完美教程](https://www.cnblogs.com/fuzongle/p/12834080.html)
```bash
[docker network create --subnet=172.11.0.0/16 jenkins_net]

docker run -d --name jenkins -p 8081:8080 -p 50000:50000 --restart=unless-stopped \
[--net jenkins_net --ip 172.11.0.2 \]
-v D:/Docker/Data/Jenkins:/var/jenkins_home \
[-v /etc/localtime:/etc/localtime \]
jenkins/jenkins:latest-jdk8

# 进入 jenkins 工作目录，打开 hudson.model.UpdateCenter.xml，
# 把 https://updates.jenkins.io/update-center.json 改成 https:mirrors.tuna.tsinghua.edu.cn/jenkins/updates/update-center.json
# http://localhost:8081
```
---
## [Ubuntu](https://hub.docker.com/_/ubuntu)
```bash
docker run -itd --name ubuntu -p 22:22 -p 8088:8080 [--net jenkins_net --ip 172.11.0.3 ]--privileged ubuntu
```
```bash
docker exec -it ubuntu bash
passwd root
unminimize
apt update
apt upgrade
apt install -y openssh-client openssh-server vim
    vim /etc/ssh/sshd_config
        PermitRootLogin yes
    /etc/init.d/ssh start
ssh root@127.0.0.1
```
---
