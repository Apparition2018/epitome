# Docker
- 快速、一致地交付应用程序
- 响应式部署和扩展
- 在同一硬件上运行更多工作负载
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
## [Docker 架构](https://docs.docker.com/get-started/overview/#docker-architecture)
![architecture](https://docs.docker.com/assets/images/architecture.svg)

|                   |                                                                                                                                                                                              |
|-------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Docker daemon     | Docker daemon (dockerd) 侦听 Docker API 请求并管理 Docker 对象。daemon 还可以与其他 daemon 通信来管理 Docker 服务                                                                                                   |
| Docker client     | Docker client (docker) 是许多 Docker 用户与 Docker 交互的主要方式。当您使用诸如 docker-run 之类的命令时，client 会将这些命令发送给 dockerd，后者会执行这些命令。Docker client 可以与多个 daemon 进行通信                                             |
| Docker Desktop    | Docker Desktop 是一款适用于 Mac、Windows 或 Linux 环境的易于安装的应用程序，使您能够构建和共享容器化应用程序和微服务。Docker Desktop 包括 Docker daemon、Docker client、Docker-Compose、Docker Content Trust、Kubernetes 和 Credential Helper |
| Docker registries | Docker 注册表存储 Docker 映像。Docker Hub 是一个任何人都可以使用的公共注册表，默认情况下 Docker 会在 DockerHub 上查找映像                                                                                                          |
| Docker objects    | 当您使用 Docker 时，您正在创建和使用图像、容器、网络、卷、插件和其他对象                                                                                                                                                     |
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
>2. docker inspect \<CONTAINER ID>|grep Id，查看 container Id
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
sudo yum install /path/to/package.rpm
# 2.3 升级 Docker Engine，下载新的 package 文件
sudo yum -y upgrade /path/to/package.rpm
# 3 在测试和开发环境下，使用自动化的便利脚本安装
curl -fsSL https://get.docker.com -o get-docker.sh
# 了解调用脚本时将运行哪些步骤
sudo sh ./get-docker.sh --dry-run
sudo sh get-docker.sh

# 启动 Docker
sudo systemctl start docker

# 卸载 Docker Engine
sudo yum remove docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin docker-ce-rootless-extras
sudo rm -rf /var/lib/docker
sudo rm -rf /var/lib/containerd
```
### [安装后步骤](https://docs.docker.com/engine/install/linux-postinstall/)
- 以非 root 用户身份管理 Docker
```bash
# 创建 docker 组
sudo groupadd docker
# 将用户添加到 docker 组，然后注销重新登录（虚拟机需要重启）
sudo usermod -aG docker $USER
# 激活对组的更改
newgrp docker
# 验证不是用 sudo 的情况下运行 docker 命令
docker run hello-world
```
- 配置开机启动
```bash
sudo systemctl enable/disable docker.service
sudo systemctl enable/disable containerd.service
```
- log rotation
```bash
vim /etc/docker/daemon.json
# https://docs.docker.com/engine/reference/commandline/dockerd/#daemon-configuration-file
{
  "registry-mirrors": [
    // 腾讯云 Docker 镜像
    "https://mirror.ccs.tencentyun.com"
  ],
  // json-file：默认日志驱动，捕获所有标准输出（和标准错误），并使用 JSON 格式写入文件
  // https://docs.docker.com/config/containers/logging/json-file/
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "10m",
    "max-file": "3"
  }
}

# 使更改对新创建的 containers 生效（已存在的不生效）
sudo systemctl daemon-reload
sudo systemctl restart docker

# 使用 `--log-driver` 为 docker create 或 docker run 的 container 设置日志驱动
docker run --log-driver json-file --log-opt max-size=10m alpine echo hello world
```
---
## [使用](https://docs.docker.com/get-started/)
1. [容器化应用程序](https://docs.docker.com/get-started/02_our_app/)
    1. Clone repository：`cd /home/lighthouse/git` → `git clone https://github.com/docker/getting-started-app.git`
    2. Create Dockerfile：`cd /getting-started-app` → `vim Dockerfile`
        ```
        # syntax=docker/dockerfile:1
        
        FROM node:18-alpine
        WORKDIR /app
        COPY package.json yarn.lock ./
        RUN yarn install --production
        COPY . .
        CMD ["node", "src/index.js"]
        EXPOSE 3000
        ```
    3. Create .dockerignore：`vim .dockerignore` → `node_modules`
    4. Build image：`docker build -t getting-started .`
    5. Run container：`docker run -dp 127.0.0.1:3000:3000 getting-started` → http://localhost:3000
2. [分享应用程序](https://docs.docker.com/get-started/04_sharing_app/)
    1. 创建 repository
        1. 登录 [Docker Hub](https://hub.docker.com/)
        2. 点击 Create Repository
        3. 填写 Repository Name，Visibility 选择 Public
        4. 创建
    2. 推送 image
        1. 登录 Docker Hub：`docker login -u apparition2018`
        2. 指定 image 名字：`docker tag getting-started apparition2018/getting-started`
        3. 推送 image：`docker push apparition2018/getting-started`
3. [多容器应用程序](https://docs.docker.com/get-started/07_multi_container/)
    1. 创建 network：`docker network create todo-app`
    2. 启动 MySQL 容器并将其连接到网络
        ```bash
        docker run -d \
        --network todo-app --network-alias mysql \
        -v todo-mysql-data:/var/lib/mysql \
        -e MYSQL_ROOT_PASSWORD=secret \
        -e MYSQL_DATABASE=todos \
        mysql:8.0
        ```
    3. 启动容器连接 MySQL并将其连接到同一网络
        ```bash
        docker run -dp 3000:3000 \
        -w /app -v "$(pwd):/app" \
        --network todo-app \
        -e MYSQL_HOST=mysql \
        -e MYSQL_USER=root \
        -e MYSQL_PASSWORD=secret \
        -e MYSQL_DB=todos \
        node:18-alpine \
        sh -c "yarn install && yarn run dev"
        ```
4. [使用 Docker Compose](https://docs.docker.com/get-started/08_using_compose/)
    - 一款用于帮助和定义多容器应用程序的工具
    1. 创建 docker-compose.yml
        ```yaml
        # Docker Compose 会自动创建一个 network (getting-stated-app_default)
        services:
          app:
            image: node:18-alpine
            command: sh -c "yarn install && yarn run dev"
            ports:
              - "3000:3000"
          working_dir: /app
          volumes:
            - ./:/app
          environment:
            MYSQL_HOST: mysql8
            MYSQL_USER: root
            MYSQL_PASSWORD: secret
            MYSQL_DB: todos
          
          mysql8:
            image: mysql:8.0
            volumes:
              - todo-mysql-data:/var/lib/mysql
            environment:
              MYSQL_ROOT_PASSWORD: secret
              MYSQL_DATABASE: todos
        
        # 使用 docker run 时，会自动创建 named volume
        # 使用 Compose 时，则需要在 top-level volumes: 定义 volume
        volumes:
          todo-mysql-data:
        ```
    2. 启动应用程序：`docker-compose up -d` → http://localhost:3000
5. [资源清理](https://blog.csdn.net/xixihahalelehehe/article/details/106594576)
    - 移除 untagged images：`docker rmi $(docker images -qf dangling=true)`
    - 移除 untagged volume：`docker volume rm $(docker volume ls -qf dangling=true)`
    - 移除 untagged network：`docker network rm $(docker network ls -qf dangling=true)`
---
## [Java 指南](https://docs.docker.com/language/java/)
1. [Build image](https://docs.docker.com/language/java/build-images/)
    1. Clone repository：`cd /home/lighthouse/git` → `git clone https://github.com/spring-projects/spring-petclinic.git`
    2. Create Dockerfile：`cd /spring-petclinic` → `vim Dockerfile`
        ```
        # syntax=docker/dockerfile:1
        
        FROM eclipse-temurin:17-jdk-focal
        WORKDIR /app
        COPY .mvn/ .mvn
        COPY mvnw pom.xml ./
        RUN ./mvnw dependency:resolve
        COPY src ./src
        CMD ["./mvnw", "spring-boot:run"]
        ```
    3. Create .dockerignore file：`vim .dockerignore` → `target`
    4. Build image：`docker build --tag java-docker .`
    5. Run container：`docker run --rm -d -p 8080:8080 --name springboot-server java-docker`
        - `curl --request GET --url http://localhost:8080/actuator/health --header 'content-type: application/json'`
2. [Develop app](https://docs.docker.com/language/java/develop/)
    1. Run a database in a container
        1. create volumes and network
            ```bash
            docker volume create mysql_data
            docker volume create mysql_config
            docker network create mysqlnet
            ```
        2. run MySQL
            ```bash
            docker run -it --rm -d \
            -v mysql_data:/var/lib/mysql -v mysql_config:/etc/mysql/conf.d \
            --network mysqlnet --name mysqlserver \
            -e MYSQL_USER=petclinic -e MYSQL_PASSWORD=petclinic \
            -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=petclinic \
            -p 3306:3306 mysql:8.0
            ```
        3. H2 数据库切换到 MySQL：`vim Dockerfile` → `CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=mysql"]`
        4. Build image：`docker build --tag java-docker .`
        5. Run container
            ```bash
            docker run --rm -d \
            --name springboot-server --network mysqlnet \
            -e MYSQL_URL=jdbc:mysql://mysqlserver/petclinic \
            -p 8080:8080 java-docker
            ```
            - `curl --request GET --url http://localhost:8080/vets --header 'content-type: application/json'`
    2. Multi-stage Dockerfile：`vim Dockerfile`
        ```
        # syntax=docker/dockerfile:1
        
        FROM eclipse-temurin:17-jdk-focal as base
        WORKDIR /app
        COPY .mvn/ .mvn
        COPY mvnw pom.xml ./
        RUN ./mvnw dependency:resolve
        COPY src ./src
        
        FROM base as test
        RUN ["./mvnw", "test"]
        
        FROM base as development
        CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=mysql", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]
        
        FROM base as build
        RUN ./mvnw package
        
        FROM eclipse-temurin:17-jre-jammy as production
        EXPOSE 8080
        COPY --from=build /app/target/spring-petclinic-*.jar /spring-petclinic.jar
        CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/spring-petclinic.jar"]
        ```
    3. Use Compose：`vim docker-compose.dev.yml` → `docker-compose -f docker-compose.dev.yml up -d --build`
        → `curl --request GET --url http://localhost:8080/vets --header 'content-type: application/json'`
        ```yaml
        version: '3.8'
        services:
          petclinic:
            build:
              context: .
              target: development
            ports:
              - "8000:8000"
              - "8080:8080"
            environment:
              - SERVER_PORT=8080
              - MYSQL_URL=jdbc:mysql://mysqlserver/petclinic
            volumes:
              - ./:/app
            depends_on:
              - mysqlserver
        
          mysqlserver:
            image: mysql:8.0
            ports:
              - "3306:3306"
            environment:
              - MYSQL_ROOT_PASSWORD=
              - MYSQL_ALLOW_EMPTY_PASSWORD=true
              - MYSQL_USER=petclinic
              - MYSQL_PASSWORD=petclinic
              - MYSQL_DATABASE=petclinic
            volumes:
              - mysql_data:/var/lib/mysql
              - mysql_config:/etc/mysql/conf.d
        volumes:
          mysql_data:
          mysql_config:
        ```
    4. Connect a Debugger：IDEA → Edit Configurations… → Remote JVM Debug → Port: 8000
3. [Run tests](https://docs.docker.com/language/java/run-tests/)
    1. start the container and run tests：`docker run -it --rm --name springboot-test java-docker ./mvnw test`
    2. build image and run test build stage：`docker build -t java-docker --target test .`
4. [Configure CI/CD](https://docs.docker.com/language/java/configure-ci-cd/)
    - [Workflow syntax for GitHub Actions](https://docs.github.com/en/actions/using-workflows/workflow-syntax-for-github-actions)
5. [Deploy app](https://docs.docker.com/language/java/deploy/)
---
## [Dockerfile](https://docs.docker.com/engine/reference/builder/)
1. [解析器指令 (Parser directives)](https://docs.docker.com/engine/reference/builder/#parser-directives)
    - 可选的，影响 Dockerfile 中后续行的处理方式
    - 一旦处理了注释、空行、构建器指令，就不再查找解析器指令。因此必须位于 Dockerfile 顶部 
    - 不区分大小写。解析器指令后留一个空行。不支持换行符
    - 支持的指令
        1. [syntax](https://docs.docker.com/build/dockerfile/frontend/)：仅在使用 BuildKit backend 时可用，在使用 classic builder backend 时被忽略
            - `# syntax=docker/dockerfile:1`，建议使用 docker/dockerfile:1，它总是指向版本1语法的最新版本
        2. escape：`# escape=\ (backtick)`，设置用于对字符进行转义的字符。默认为 \
2. [环境替换 (Environment replacement)](https://docs.docker.com/engine/reference/builder/#environment-replacement)
    - 使用 ENV 声明 
    - 表示：①$variable_name；②${variable_name}
    - ${variable:-word}：如设置了 variable，则结果是该值，否则为 word
    - ${variable:+word}：如设置了 variable，则结果是 word，否则为空字符串
3. Instruction
    - Build Stage
    ```
    FROM                    基于某个 image 初始化一个新的 build stage，并为后续指令设置 base iamge
        FROM scratch        最小 image
    ARG                     设置变量
    ADD                     复制文件；自动解压 tar
    COPY                    复制文件
    RUN                     执行命令，docker build 时执行；两种格式：①shell ②exec
    ```
    - Run Stage
    ```
    VOLUME                  VOLUME
    ENTRYPOINT              执行命令，docker run 时执行；两种格式：①shell ②exec
                                不会被 docker run 后的参数覆盖；
    CMD                     执行命令，docker run 时执行；三种格式：①shell ②exec ③作为 ENTRYPOINT 的参数；
                                可以有多个 CMD 指令，但只有最后一个生效，且会被 docker run 后的参数覆盖；
    EXPOSE                  端口
    ```
    - Both Stage
    ```
    ENV                     设置变量
    WORKDIR                 工作目录
    ```
    - 只有 `RUN`、`COPY`、`ADD` 才创建 layers，其它指令创建临时中间 images，不会增加 build 大小
    - [CMD vs ENTRYPOINT](https://docs.docker.com/engine/reference/builder/#understand-how-cmd-and-entrypoint-interact)
4. [Dockerfile examples](https://docs.docker.com/engine/reference/builder/#dockerfile-examples)
---
## [Docker Build](https://docs.docker.com/build/)
### [Architecture](https://docs.docker.com/build/architecture/)
![build-high-level-arch](https://docs.docker.com/build/images/build-high-level-arch.png)
1. Buildx：一个 CLI 工具。在较新版本中，调用 Docker build 默认使用 Buildx
2. Builders：一个用于描述 BuildKit backend 实例的术语。Builder nodes 可以是 containers、虚拟机或物理机
3. BuildKit：BuildKit or buildkitd 是执行构建工作负载的 daemon
### Building images
1. [Build context](https://docs.docker.com/build/building/context/)：传递给 build 命令的参数
    ```
    docker build [OPTIONS] PATH | URL | -
                           ^^^^^^^^^^^^^^
    ```
    1. Filesystem contexts：文件夹、压缩包、远程 Git 仓库
    2. [Text file contexts](https://docs.docker.com/build/building/context/#text-files)：[-](https://docs.docker.com/build/building/context/#pipes)
2. [Multi-stage builds](https://docs.docker.com/build/building/multi-stage/)
    1. Use：使用多个 FROM 语句。①可以使用不同的 base；②begins a new stage
    2. Name build stages：①从 0 的整数；②`AS <name>`
    3. build at a specific build stage：`--target <name>`
3. [Builders](https://docs.docker.com/build/builders/)
    1. Default Builder：Docker Engine 自动创建的 builder。使用 docker driver
    2. [Manage builders](https://docs.docker.com/build/builders/manage/)
4. [Drivers](https://docs.docker.com/build/drivers/)

    | Feature                  |              docker               |       docker-container        |           kubernetes            |           remote           |
    |--------------------------|:---------------------------------:|:-----------------------------:|:-------------------------------:|:--------------------------:|
    | explanation              | 使用绑定到 Docker daemon 中的 BuildKit 库 | 使用 Docker 创建一个专用的 BuildKit 容器 | 在 Kubernetes 集群中创建 BuildKit pod | 直接连接到手动管理的 BuildKit daemon |
    | Automatically load image |                 √                 |                               |                                 |                            |
    | Cache export             |           	Inline only            |               √               |                √                |             √              |
    | Tarball output           |                                   |               √               |                √                |             √              |
    | Multi-arch images        |                                   |               √               |                √                |             √              |
    | BuildKit configuration   |                                   |               √               |                √                |     Managed externally     |
5. [BuildKit](https://docs.docker.com/build/buildkit/)：Docker Desktop 和 Docker Engine v23.0 以上版本的默认 builder
    - enable BuildKit
        1. BuildKit environment variable：`DOCKER_BUILDKIT=1 docker build .`
        2. /etc/docker/daemon.json
            ```json
            {
              "features": {
                "buildkit": true
              }
            }
            ```
    - [configure](https://docs.docker.com/build/buildkit/configure/)
    - [legacy builder vs BuildKit](https://docs.docker.com/build/building/multi-stage/#differences-between-legacy-builder-and-buildkit)
6. [Continuous integration](https://docs.docker.com/build/ci/)
---
## [Docker Compose](https://docs.docker.com/compose/)
- @see Docker.md#使用 4.使用 Docker Compose
### [安装场景](https://docs.docker.com/compose/install/#installation-scenarios)
1. [安装 Docker Desktop](https://docs.docker.com/desktop/install/linux-install/)：Docker Desktop 包含 Docker Compose、Docker Engine、Docker CLI
2. 安装 Compose plugin
    1. [使用 Docker's repository](https://docs.docker.com/compose/install/linux/#install-using-the-repository)
    2. [手动安装](https://docs.docker.com/compose/install/linux/#install-the-plugin-manually) 
3. [安装 Compose standalone](https://docs.docker.com/compose/install/other/)
### [docker compose CLI](https://docs.docker.com/compose/reference/)
```
    -f, --file
    -p, --project-name
docker compose build [OPTIONS] [SERVICE...]                     构建或重构 services
docker compose config [OPTIONS] [SERVICE...]                    以规范格式 parse/resove/reder compose 文件
docker compose down [OPTIONS] [SERVICES]                        停止和移除 containers, networks
docker compose exec [OPTIONS] SERVICE COMMAND [ARGS...]         在正在运行的 container 中执行命令
docker compose logs [OPTIONS] [SERVICE...]                      查看 containers 输出
    -f, --follow
docker compose ps [OPTIONS] [SERVICE...]                        列出 containers
docker compose rm [OPTIONS] [SERVICE...]                        移除停止的 containers
docker compose stop [OPTIONS] [SERVICE...]                      停止 services
docker compose up [OPTIONS] [SERVICE...]                        创建并启动 containers
```
---
## [Docker CLI](https://docs.docker.com/engine/reference/commandline/docker/)
- Docker
```
docker version [OPTIONS]                                        显示 Docker 版本信息
docker info [OPTIONS]                                           显示 Docker system-wide 信息
docker inspect [OPTIONS] NAME|ID [NAME|ID...]                   显示 Docker 对象的 low-level 信息
    # 显示所有 container IP
    docker inspect --format='{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)
    # 显示 container 日志地址
    docker inspect --format '{{.LogPath}}' f9d2bd079dbc
docker login                                                    登录 registry
docker logout                                                   注销 registry 
```
- image
```
docker images [OPTIONS] [REPOSITORY[:TAG]]                      列出 iamges
docker search [OPTIONS] TERM                                    在 Docker Hub 搜索 images
docker image pull [OPTIONS] NAME[:TAG|@DIGEST]                  从 registry 下载 image
docker image push [OPTIONS] NAME[:TAG|@DIGEST]                  将 image 上载 registry
docker rmi [OPTIONS] IMAGE [IMAGE...]                           移除 images
docker image rm [OPTIONS] IMAGE [IMAGE...]                      移除 images
docker image build [OPTIONS] PATH | URL | -                     从 Dockerfile build image
    -t, --tag                                                   名字和标签，name:tag 格式
    --target                                                    设置要生成的目标生成阶段
    --output                                                    自定义生成输出
    --platform                                                  设置平台，如果服务器支持多平台
docker image tag SOURCE_IMAGE[:TAG] TARGET_IMAGE[:TAG]          创建 tag
docker image history [OPTIONS] IMAGE                            显示 image 历史记录                               
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
    --mount                                                     将文件系统挂载到容器
        -v vs --mount:  https://docs.docker.com/storage/volumes/#choose-the--v-or---mount-flag
                        https://docs.docker.com/storage/bind-mounts/#choose-the--v-or---mount-flag
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
    --network                                                   将 container 连接到 network
    --network-alias		                                        为 container 添加 network-scoped 别名
    --ip                                                        IPv4 地址
    --ip6		                                                IPv6 地址
docker ps [OPTIONS]                                             列出 containers
docker container ls [OPTIONS]                                   列出 containers
docker container start [OPTIONS] CONTAINER [CONTAINER...]       启动 containers
docker container stop [OPTIONS] CONTAINER [CONTAINER...]        停止 containers
docker container restart [OPTIONS] CONTAINER [CONTAINER...]     重启 containers
docker container rm [OPTIONS] CONTAINER [CONTAINER...]          移除 containers
docker container kill [OPTIONS] CONTAINER [CONTAINER...]        杀掉 containers
docker container exec [OPTIONS] CONTAINER COMMAND [ARG...]      在运行的 container 中执行命令
    -it CONTAINER bash
docker cp [OPTIONS] SRC_PATH|- CONTAINER:DEST_PATH              在 container 和本地文件系统之间复制文件或文件夹
docker container cp [OPTIONS] CONTAINER:SRC_PATH DEST_PATH|-    在 container 和本地文件系统之间复制文件或文件夹
docker container commit [OPTIONS] CONTAINER [REPOSITORY[:TAG]]  根据 container 的更改创建 image
    -a, --author                                                作者
    -m, --mesage                                                提交消息
docker container logs [OPTIONS] CONTAINER                       获取 container 日志
docker container port CONTAINER [PRIVATE_PORT[/PROTO]]          列出 container 的端口映射或特定映射
docker container inspect [OPTIONS] CONTAINER [CONTAINER...]     显示 containers 详细信息
docker container export [OPTIONS] CONTAINER                     将 container 的文件系统导出为 tar 存档
docker import [OPTIONS] file|URL|- [REPOSITORY[:TAG]]           从 tarball 导入内容以创建文件系统 image 
```
- volume
```
docker volume create [OPTIONS] [VOLUME]                         创建 volume
docker volume inspect [OPTIONS] VOLUME [VOLUME...]              显示 volumes 的详细信息
docker volume ls [OPTIONS]                                      列出 volumes
docker volume rm [OPTIONS] VOLUME [VOLUME...]                   移除 volumes
```
- network
```
docker network create [OPTIONS] NETWORK                         创建 network
    -- subnet                                                   代表网段的 CIDR 格式的子网
docker network prune [OPTIONS]                                  移除所有未使用的网络
```
- 常用 Options
```
    -a, --all                                                   显示所有
    -f, --filter                                                根据提供的条件过滤输出
    --format                                                    使用自定义模板设置输出格式
    -q, --quiet                                                 仅显示 IDs
```
---
## [MySQL](https://hub.docker.com/_/mysql) / [MariaDB](https://hub.docker.com/_/mariadb)
- 配置文件读取顺序：`mysql --verbose --help|grep -A 1 'Default options'`
```bash
mkdir -p /home/lighthouse/docker_data/mysql/{data,conf,log,files}
cd /home/lighthouse/docker_data/mysql
```
```bash
docker run -d --name mysql -p 3306:3306 --privileged --restart=unless-stopped \
-v $PWD/conf:/etc/mysql/conf.d \
-v $PWD/data:/var/lib/mysql \
-v $PWD/files:/var/lib/mysql-files \
-v $PWD/log:/var/log/mysql \
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
## [MySQL 主从](https://www.bilibili.com/video/BV1gr4y1U7CY/?p=41)
- @see CentOS.md#双主
```bash
mkdir -p /home/lighthouse/docker_data/mysql-ms/master/{data,conf,log,files}
mkdir -p /home/lighthouse/docker_data/mysql-ms/slave/{data,conf,log,files}
cd /home/lighthouse/docker_data
```
```bash
docker run -d --name mysql-master -p 3307:3306 \
-v $PWD/mysql-ms/master/conf:/etc/mysql/conf.d \
-v $PWD/mysql-ms/master/data:/var/lib/mysql \
-v $PWD/mysql-ms/master/files:/var/lib/mysql-files \
-v $PWD/mysql-ms/master/log:/var/log/mysql \
-e MYSQL_ROOT_PASSWORD=root \
mysql:5.7

docker run -d --name mysql-slave -p 3308:3306 \
-v $PWD/mysql-ms/slave/conf:/etc/mysql/conf.d \
-v $PWD/mysql-ms/slave/data:/var/lib/mysql \
-v $PWD/mysql-ms/slave/files:/var/lib/mysql-files \
-v $PWD/mysql-ms/slave/log:/var/log/mysql \
-e MYSQL_ROOT_PASSWORD=root \
mysql:5.7
```
```
vim mysql-ms/master/conf/master.cnf
[mysqld]
server_id=101
log-bin=log-bin
binlog-ignore-db=mysql
binlog-ignore-db=sys
binlog-ignore-db=information_schema
binlog-ignore-db=performance_schema
binlog_cache_size=1M
binlog_format=MIXED

vim mysql-ms/slave/conf/slave.cnf
[mysqld]
server_id=102
binlog-ignore-db=mysql
binlog-ignore-db=sys
binlog-ignore-db=information_schema
binlog-ignore-db=performance_schema
log-bin=log-bin
binlog_cache_size=1M
binlog_format=MIXED
expire_logs_days=7
log_slave_updates=1
relay_log=relay-bin
read_only=1
```
```mysql
-- 节点 a：创建 slave（节点 b）复制使用的账号
CREATE USER 'slave'@'%' IDENTIFIED BY '123456';
GRANT REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'slave'@'%';
-- 查看 binlog 状态信息，记下 File 和 Position
SHOW MASTER STATUS;
```
```mysql
-- 节点 b：更改节点 b 的 master 为节点 a
STOP SLAVE;
CHANGE MASTER TO 
    MASTER_HOST='10.0.8.8', MASTER_PORT=3307,
    MASTER_USER='slave', MASTER_PASSWORD='123456',
    MASTER_LOG_FILE='log-bin.000001', MASTER_LOG_POS=154;
START SLAVE;
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
## [Redis 主从](https://hub.docker.com/_/redis)
- [Docker 部署 Redis](https://blog.csdn.net/qq_41316955/article/details/108381923)
- [redis.conf](https://redis.io/docs/manual/config/) 选择对应版本
    - redis.conf
    ```
    requirepass 123456
    bind 0.0.0.0
    ```
    - redis-replica.conf
    ```
    requirepass 123456
    bind 0.0.0.0
    replicaof 43.136.102.115 6379
    masterauth 123456
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
## [Redis 集群（3主3从）](https://www.bilibili.com/video/BV1gr4y1U7CY/?p=45)
```bash
# 创建 node1~node6 文件夹
mkdir -p /home/lighthouse/docker_data/redis-cluster/node1
cd /home/lighthouse/docker_data/redis-cluster

# 端口 6381~6386，则总线端口默认 16381~16386
docker run -d --name redis-node1 --net host \
-v $PWD/node1:/data \
redis --cluster-enabled yes --appendonly yes --port 6381
```
- 创建 Redis 集群
```bash
docker exec -it redis-node1 bash

# 使用 create 命令，--cluster-replicas 1 表示为每一个 master 对应一个 replica
redis-cli --cluster create \
43.136.102.115:6381 43.136.102.115:6382 43.136.102.115:6383 \
43.136.102.115:6384 43.136.102.115:6385 43.136.102.115:6386 \
--cluster-replicas 1

# -c 表示集群方式连接
redis-cli -c -p 6387
```
- [扩容](https://www.bilibili.com/video/BV1gr4y1U7CY?p=52)
```bash
# 添加节点 6387
redis-cli --cluster add-node 43.136.102.115:6387 43.136.102.115:6381
# 检查集群，发现 6387 0 slots | 0 slaves，表示没有槽位，没有 slave
redis-cli --cluster check 43.136.102.115:6381
# 槽位迁移，分配 6387 槽位
redis-cli --cluster reshard 43.136.102.115:6381
  #How many slots do you want to move?  4096
  #What is the receiving node ID?       <6387_node_id>
  #Source node #1:                      all
# 添加节点 6388 为 6387 的 slave
redis-cli --cluster add-node 43.136.102.115:6388 43.136.102.115:6387 \
--cluster-slave --cluster-master-id <6387_node_id>
```
- [缩容](https://www.bilibili.com/video/BV1gr4y1U7CY?p=54)
```bash
# 删除节点 6388
redis-cli --cluster del-node 43.136.102.115:6388 <6388_node_id>
# 槽位迁移，清除 6387 槽位
redis-cli --cluster reshard 43.136.102.115:6381
  #How many slots do you want to move?  4096
  #What is the receiving node ID ?      <6381_node_id>
  #Source node #1:                      <6387_node_id>
  #Source node #2:                      done
# 删除节点 6387
redis-cli --cluster del-node 43.136.102.115:6387 <6387_node_id>
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
    apt update
    apt install -y openssh-client openssh-server vim net-tools
    vim /etc/ssh/sshd_config
        PermitRootLogin yes
    /etc/init.d/ssh start
ssh root@127.0.0.1
docker commit -m="my ubuntu" -a="ljh" 996aa42add87 ljh/ubuntu:1.0

### 推送 image 到阿里云 ###
# 阿里云仓库管理：https://cr.console.aliyun.com/cn-guangzhou/instance/dashboard → 创建命名空间 → 创建镜像仓库
docker login --username=Apparition2018 registry.cn-guangzhou.aliyuncs.com
docker tag 656f4856adaa registry.cn-guangzhou.aliyuncs.com/apparition2018/ubuntu:1.0
docker push registry.cn-guangzhou.aliyuncs.com/apparition2018/ubuntu:1.0

### 推送 image 到私有仓库 ###
docker run -d --name registry -p 5000:5000 -v /home/lighthouse/docker_data/registry:/var/lib/registry --privileged registry
docker tag 2548adfcf618 43.136.102.115:5000/ubuntu:1.0
vim /etc/docker/daemon.json
    { "insecure-registries": ["43.136.102.115:5000"] }
docker push 43.136.102.115:5000/ubuntu:1.0
# 查看仓库有哪些镜像
curl -XGET http://43.136.102.115:5000/v2/_catalog
```
---
