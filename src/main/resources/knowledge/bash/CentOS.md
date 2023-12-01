# CentOS

---
## 问题
1. [CentOS 6.8 联网设置](https://blog.csdn.net/Catdingwt/article/details/79585929)
---
## 安装与配置
- [Download](https://www.centos.org/download/)
### VMware
- [Downloads](https://customerconnect.vmware.com/en/downloads/#all_products)：VMware Workstation
- [VMware 安装 CentOS 7](https://www.imooc.com/video/24343)
    - 网络连接：桥接模式
- [VMware 克隆环境](https://www.imooc.com/video/24347)
- [VMware 10 安装 CentOS 6.8](https://www.cnblogs.com/moranlei/p/9333184.html)
- [VMware 10 没有足够内存自动启用 kdump](http://www.360doc.com/content/20/0415/23/67357512_906311206.shtml)
### [分区](https://www.cnblogs.com/sunmoonp/p/10968359.html)
| 分区    | 作用              | 空间       |
|-------|-----------------|----------|
| /boot | Linux 系统启动有关的程序 | 256mb    |
| /home | 用户目录            | 5120bm   |
| swap  | 虚拟内存            | 物理内存1~2倍 |
| /     | 系统根目录           | 剩余空间     |
### 其它
1. 修改 [ifcfg-eth0](https://blog.csdn.net/wanghailan1818/article/details/123629708)：`vim /etc/sysconfig/network-scripts/ifcfg-eth0` → `service network restart`
    ```
    BOOTPROTO=static
    ONBOOT=yes
    IPADDR=192.168.0.9
    NETMASK=255.255.255.0
    GATEWAY=192.168.0.2
    DNS1=192.168.0.2
    HWADDR=00:0c:29:fe:82:89
    ```
2. 修改 hostname：`hostnamectl set-hostname <hostname>` → `reboot`
3. [防火墙](https://www.likecs.com/show-205138371.html) ???

    | CentOS 6                                        | CentOS 7                                                      |                   |
    |-------------------------------------------------|---------------------------------------------------------------|-------------------|
    | chkconfig iptables on\|off                      | systemctl enable\|disable firewalld                           | 开启/关闭防火墙（重启后永久生效) |
    | service iptables start\|stop                    | systemctl start\|stop firewalld                               | 开启/关闭防火墙（重启后失效)   |
    | service iptables restart\|status\|save          | systemctl restart\|status firewalld                           |                   |
    | /etc/sysconfig/iptables                         |                                                               | 防火墙文件             |
    | iptables -I INPUT -p tcp --dport 8090 -j ACCEPT | firewall-cmd --zone=public --add-port=8080/tcp --permanent    | 添加                |
    | iptables -D INPUT -p tcp --dport 8090 -j ACCEPT | firewall-cmd --zone=public --remove-port=8080/tcp --permanent | 移除                |
4. CentOS 7 设置打开 Terminal 快捷键
    1. Applications → System Tools → Settings → Devices → Keyboard
    2. \+ → Name:Terminal  Command:/usr/bin/gnome-terminal
---
## [内网互通原则](https://www.imooc.com/video/24348)
1. 关闭本地防火墙
2. 关闭 Linux 防火墙 或 开启安全组端口
3. 连接同一个 WiFi 下的网段
4. 保证同一个网段设备同处于一个网段
5. 前端 (IOS/Android/HarmonyOS/…) 不要使用 localhost
---
## JDK
1. [downloads](https://www.oracle.com/java/technologies/downloads/archive/) 并安装
    ```
    rpm -qa|grep jdk    yum list installed|grep jdk
    rpm -e jdk          yum remove jdk
    wget xxx.rpm        yum search java|grep -i --color jdk     wget xxx.tar.gz
    rpm -ivh xxx.rpm    yum install -y xxx                      tar -zxvf xxx.tar.gz
    ```
    - [Azul Zulu JDK](https://www.azul.com/downloads/?package=jdk#zulu)
2. 添加环境变量
    - `which java` → `ls -l /usr/bin/java` → `ls -l /etc/alternatives/java`
    - `vim /etc/profile` → `source /etc/profile` 或 `. /etc/profile`
        ```
        export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_*
        export PATH=$PATH:$JAVA_HOME/bin
        ```
3. [配置多版本 JDK](https://www.imooc.com/video/18991)
    - `vim /etc/profile` → `source /etc/profile`
        ```
        export JAVA_8_HOME=/.../jdk1.8...
        export JAVA_17_HOME=/.../jdk-17...
        alias jdk8="export JAVA_HOME=$JAVA_8_HOME"
        alias jdk17="export JAVA_HOME=$JAVA_17_HOME"
        ```
---
## Tomcat
- [CentOS 搭建多个 Tomcat](https://blog.csdn.net/qq_40065776/article/details/105652328)
- [Tomcat 启动慢警告：Creation of SecureRandom instance for ...](https://zhuanlan.zhihu.com/p/107078362)
```
1. https://archive.apache.org/dist/tomcat/
    wget https://dlcdn.apache.org/tomcat/tomcat-10/v10.1.8/bin/apache-tomcat-10.1.8.tar.gz
    tar -zxvf xxx.tar.gz
    mv apache-tomcat-10.1.8 /usr/local/
2. 添加环境变量
    2.1 vim /etc/profile
        export CATALINA_HOME=/usr/local/apache-tomcat-10.1.8
    2.2 source /etc/profile
    2.3 配置多个 Tomcat
        2.3.1 vim /etc/profile
              exprot CATALINA_HOME2=/user/local/apache-tomcat2-10.1.8
        2.3.2 vim ${CATALINA_HOME2}/bin/catalina.sh，在开头添加 
              export CATALINA_HOME=$CATALINA_HOME2
              export CATALINA_BASE=$CATALINA_HOME2
3. 配置 UTF-8 字符集
    3.1 vim ${CATALINA_HOME}/conf/server.xml
    3.2 在 <connector port="8080" protocol="HTTTP/1.1" 添加 URIEncoding="UTF-8"
4. 防火墙
    4.1 CentOS 6
        4.1.1 vim /etc/sysconfig/iptables
            -A INPUT -m state --state NEW -m tcp -p tcp --dport 8080 -j ACCEPT
            注：不要加在 REJECT 之后
        4.1.2 service iptables restart
    4.2 CentOS 7
        4.2.1 firewall-cmd --zone=public --list-ports
        4.2.2 firewall-cmd --zone=public --add-port=8080/tcp --permanent
        4.2.3 systemctl restart firewalld
5. ${CATALINA_HOME}/bin/startup.sh
   ${CATALINA_HOME}/bin/shutdown.sh
6. 可选
    - 增加可执行权限：cd ${CATALINA_HOME} → chmod a+x -R *
    - 修改端口：vim conf/server.xml
```
---
## Maven
1. [download](https://maven.apache.org/download.cgi) 并解压
    ```bash
    wget https://dlcdn.apache.org/maven/maven-3/3.9.3/binaries/apache-maven-3.9.3-bin.tar.gz
    tar -zxvf xxx.tar.gz
    mv apache-maven-3.9.3 /usr/local/
    ```
2. 添加环境变量：`vim /etc/profile` → `source /etc/profile`
    ```
    export M2_HOME=/usr/local/apache-maven-3.9.3
    export PATH=$PATH:$M2_HOME/bin
    ```
3. 命令
    ```
    mvn -version
    mvn clean                                       清除
    mvn compile                                     编译
    mvn package                                     打包
    mvn clean package -Dmaven.test.skip=true        跳过单元测试
    ``` 
---
## vsftpd
- Very Secure FTP Daemon，一个完全免费的、开放源代码的 ftp 服务器软件
```
1. yum install -y ftp
   yum install -y vsftpd
2. 创建虚拟用户
    mkdir /ftpfile                                      创建 ftp 文件夹
    useradd ftpuser -d /ftpfile -s /sbin/nologin        添加匿名用户 ftpuser
    chown -R ftpuser.ftpuser /ftpfile                   修改 ftp 文件夹权限
    passwd ftpuser                                      重置 ftpuser 用户密码为 123456
3. 配置
    3.1 vim /etc/vsftpd/chroot_list                     添加 ftpuser
    3.2 vim /etc/selinux/config                         修改 SELINUX=disabled
        setenforce 0                                    临时生效
    3.3 550 拒绝访问
        setsebool -P ftp_home_dir 1
        重启 linux，执行 reboot 命令
    3.4 vim /etc/vsftpd/vsftpd.conf
        http://learning.happymmall.com/vsftpdconfig/vsftpd.conf.readme.html
4. 防火墙
    4.1 vim /etc/sysconfig/iptables
        -A INPUT -p tcp --dport 61001:62000 -j ACCEPT
        -A OUTPUT -p tcp --sport 61001:62000 -j ACCEPT
        -A INPUT -p tcp --dport 20 -j ACCEPT
        -A OUTPUT -p tcp --sport 20 -j ACCEPT
        -A INPUT -p tcp --dport 21 -j ACCEPT
        -A OUTPUT -p tcp --sport 21 -j ACCEPT
    4.2 service iptables restart
5. 启动和访问
    5.1 service vsftpd restart
    5.2 浏览器访问 ftp://192.168.58.129
6. 登录服务器：ftp 192.168.58.129
7. FTP 软件：FileZilla
```
---
## [Nginx](https://www.imooc.com/video/23837)
1. [Download](https://nginx.org/en/download.html)
    ```bash
    cd /home/lighthouse/software
    wget https://nginx.org/download/nginx-1.24.0.tar.gz
    tar -zxvf xxx.tar.gz
    ```
2. 安装基础依赖：`yum install -y gcc zlib zlib-devel pcre pcre-devel openssl openssl-devel`
3. 创建用户组和用户
    ```bash
    groupadd nginx
    useradd -g nginx -s /sbin/nologin nginx
    passwd nginx
    ```
4. [配置并安装](https://nginx.org/en/docs/configure.html)：`mkdir -p /var/temp/nginx`
    ```bash
    ./configure \
    #--prefix=path，指定安装目录，默认 /usr/local/nginx
    --prefix=/usr/local/nginx \
    --http-client-body-temp-path=/var/temp/nginx/client \
    --http-proxy-temp-path=/var/temp/nginx/proxy \
    --http-fastcgi-temp-path=/var/temp/nginx/fastcgi \
    --http-uwsgi-temp-path=/var/temp/nginx/uwsgi \
    --http-scgi-temp-path=/var/temp/nginx/scgi
    --group=nginx \
    --user=nginx \
    #强制使用 pcre 库
    --with pcre
    #启用动态模块兼容性
    --with-compat
    #允许在 FreeBSD 和 Linux 上使用异步文件 I/O (AIO)
    --with-file-aio
    #允许使用线程池
    --with-threads
    #需要安装的模块
    #--with-http_addition_module
    #……
    make
    make install
    ```
    - 可参考 docker 安装 nginx 后，输入 nginx -V 查看配置参数
5. 防火墙：开放 80 端口
6. [命令](https://nginx.org/en/docs/switches.html)：`cd /usr/local/nginx/sbin`
    ```
    .nginx -h                   帮助
    .nginx -t                   测试
    .nginx                      启动
    .nginx -s stop              快速关闭
    .nginx -s quit              优雅关闭
    .nginx -s reload            重新加载配置
    .nginx -v                   版本
    .nginx -V                   版本、编译版本、配置参数
    kill -s QUIT 1628           杀死进程 /var/run/nginx.pid
    ```
---
## [MySQL 5.7](https://www.cnblogs.com/qcq0703/p/11186055.html)
1. `yum list installed|grep mysql`、`yum list installed|grep mariadb` → `yum remove xxx`
2. [downloads](https://downloads.mysql.com/archives/community/) 并解压和移动
    ```
    Product Version     5.7.41
    Operating System    Linux - Generic
    OS Version          Linux - Generic(glibc 2.12)(x86,64-bit)
    ```
    ```
    wget https://downloads.mysql.com/archives/get/p/23/file/mysql-5.7.41-linux-glibc2.12-x86_64.tar.gz
    tar -zxvf xxx.tar.gz
    mv mysql-5.7… mysql-5.7
    mv mysql-5.7 /usr/local/
    ```
3. 添加环境变量：`vim /etc/profile` → `source /etc/profile`
    ```
    export MYSQL_HOME=/usr/local/mysql-5.7
    export PATH=$PATH:$MYSQL_HOME/bin
    ```
4. 创建用户组和用户
    ```
    cat /etc/group|grep mysql                       查看 mysql 组是否存在
    cat /etc/passwd|grep mysql                      查看 mysql 用户是否存在
    groupadd mysql                                  创建 mysql 组
    useradd -g mysql -s /sbin/nologin mysql         添加 mysql 用户到 mysql 组下
    passwd mysql                           `        更改 mysql 用户密码
    ```
5. /etc/my.cnf
6. 初始化并更改 data 目录所属用户组和用户
    1. `/usr/local/mysql-5.7/bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql-5.7/ --datadir=/usr/local/mysql-5.7/data/`，记住日志最后一行的密码
    2. `chown -R mysql:mysql /usr/local/mysql-5.7/data/`
7. 添加 mysql 服务
    ```
    cp /usr/local/mysql-5.7/support-files/mysql.server /etc/init.d/mysql
    chmod +x /etc/init.d/mysql                      添加可执行权限
    chkconfig --add mysql                           添加 mysql 服务
    chkconfig --list mysql                          列出 mysql 服务情况
    chkconfig mysqld on                             开机启动
    service mysql start|stop|restart|status
    systemctl start|stop|restart|status|enable|disable mysql
    ```
8. 防火墙：开放 3306 端口
9. 连接 msql：`mysql -uroot -p`
    ```
    alter user root@localhost identified by 'root';                 -- 修改密码
    grant all privileges on *.* to root@'%' identified by 'root';   -- 授权
    flush privileges;
    ```
### [MySQL 双主](https://www.jb51.net/article/252651.htm)
1. 节点 a 配置：`vim /etc/my.cnf`，@see MySQL.md#复制
2. 节点 b 配置：`vim /etc/my.cnf`，只列出与节点 a 不同的配置
    ```
    service_id=2
    log-bin=/usr/local/mysql-b/bin_log/log-bin
    relay_log=/usr/local/mysql-b/relay_log/relay-bin
    ```
3. 把节点 a 设为 master，节点 b 设为 slave
    - 连接节点 a，创建 slave（节点 b）复制使用的账号
    ```mysql
    -- 创建账号 repl ，密码为 repl，允许任意 IP 访问，并授予 replication slave 权限
    grant replication slave on *.* to 'repl'@'%' identified by 'repl';
    flush privileges;
    -- 删除所有 binlog 文件，并重置 binlog 索引文件
    -- https://dev.mysql.com/doc/refman/5.7/en/reset-master.html
    reset master;
    -- 查看 binlog 状态信息，记下 File 和 Position
    -- https://dev.mysql.com/doc/refman/5.7/en/show-master-status.html
    show master status;
    ```
    - 连接节点 b，并设置为 slave
    ```mysql
    -- 停止复制线程：https://dev.mysql.com/doc/refman/5.7/en/stop-slave.html
    stop slave;
    -- 更改节点 b 的 master 为节点 a
    -- https://dev.mysql.com/doc/refman/5.7/en/change-master-to.html
    -- master_log_file, master_log_pos 是在节点 a 使用 show master status 语句查看到的 File 和 Position
    change master to 
        master_host='节点 a 的 ip', master_port=3306, 
        master_user='repl', master_password='repl', 
        master_log_file='log-bin.000001', master_log_pos=154;
    -- 启动复制线程：https://dev.mysql.com/doc/refman/5.7/en/start-slave.html
    start slave;
    -- 查看复制线程基本参数的状态信息：https://dev.mysql.com/doc/refman/5.7/en/show-slave-status.html
    -- 成功时：Slave_IO_Running 和 Slave_SQL_Running 都为 Yes
    -- 不成功时：可查看 data 目录下 *.err 日志文件
    show slave status;
    -- 重置 slave，slave 出现异常时使用
    -- https://dev.mysql.com/doc/refman/5.7/en/reset-slave.html
    -- reset slave all;
    ```
4. 把节点 b 设为主数据库，节点 a 设为从数据库
---
## Git
1. `yum install -y git`
2. 全局配置
    ```bash
    git config --global user.name 'Apparition2018'
    git config --global user.email '88850180@163.com'
    git config --global core.autocrlf false
    git config --global gui.encoding utf-8
    git config --global core.quotepath off
    ```
3. 配置 SSH keys
    1. 生成密钥：`ssh-keygen -t rsa -C '88850180@163.com'`
    2. 启动 ssh-agent：`ssh-agent bash` 或 eval `ssh-agent`
    3. 将私钥添加到 ssh-agent：`ssh-add ~/.ssh/id_rsa`
    4. 登录 GitHub|Gitee 新建 SSH key，把公钥复制过去
        - 查看公钥：`ssh-add -L` or `vim ~/.ssh/id_rsa.pub`
    5. 验证：`ssh -T git@github.com`
---
## [Redis](https://www.imooc.com/video/23818)
1. `yum install gcc-c++`
2. [Download](https://redis.io/download/)
    - `wget https://github.com/redis/redis/archive/7.0.11.tar.gz`
    - `tar -zxvf 7.0.11.tar.gz`
3. 安装：`cd redis-7.0.11` → `make` → `make PREFIX=/usr/local/redis install`
4. 配置文件
    ```bash
    cp redis.conf /usr/local/redis
    cd /usr/local/redis
    vim redis.conf
        requirepass 123456         
        # bind 127.0.0.1 -::1
        bind 0.0.0.0
        daemonize yes
    ```
5. 防火墙：开放 6379 端口
6. 操作
    - 启动：`./bin/redis-server ./redis.conf`
    - 客户端：`./bin/redis-cli`
---
## [RabbitMQ](https://www.imooc.com/video/23821)
1. [Downloading and Installing RabbitMQ](https://rabbitmq.com/download.html)
2. Erlang
    - [RabbitMQ 和 Erlang/OTP 兼容性矩阵](https://rabbitmq.com/which-erlang.html#compatibility-matrix)
    - [Zero-dependency Erlang from RabbitMQ](https://rabbitmq.com/install-rpm.html#install-zero-dependency-rpm)
        - [Install From PackageCloud](https://packagecloud.io/rabbitmq/erlang)
3. 复制文件到服务器
    ```bash
    mkdir /usr/local/rabbitmq
    # windows
    scp C:/Users/HP/Desktop/rabbitmq-server-3.11.15-1.el8.noarch.rpm \
        C:/Users/HP/Desktop/erlang-25.3.2-1.el8.x86_64.rpm \
        root@43.136.102.115:/usr/local/rabbitmq
    ```
4. 升级软件包并安装：`cd /usr/local/rabbitmq`
    ```bash
    rpm -Uvh erlang-25.3.2-1.el8.x86_64.rpm
    yum install -y erlang
    erl
    ```   
    ```bash
    rpm -Uvh rabbitmq-server-3.11.15-1.el8.noarch.rpm
    yum install -y socat
    yum install -y rabbitmq-server
    systemctl start rabbitmq-server
    ```
5. 防火墙：开放 5672 和 15672 端口
6. rabbitmq_management
    ```bash
    rabbitmq-plugins enable rabbitmq_management
    # 添加用户
    rabbitmqctl add_user ljh 123456
    # 设置为管理员
    rabbitmqctl set_user_tags ljh administrator
    # 授予所有权限
    rabbitmqctl set_permissions -p / ljh ".*" ".*" ".*"
    # http://43.136.102.115:15672
    # Admin → Virtual Hosts → Add a new virtual host
    ```
---
## [MongoDB](https://www.imooc.com/video/23831)
1. Download
    - [MongoDB Community Server Download](https://www.mongodb.com/try/download/community)
        ```
        Version         current
        Platform        RedHat/CentOS 8.0
        Package         tgz
        ```
    - [MongoDB Shell Download](https://www.mongodb.com/try/download/shell)
        ```
        Version
        Platform        Linux Tarball 64-bit
        Package         tgz
        ```
2. 复制文件到服务器
    ```bash
    # windows
    scp C:/Users/Administrator/Desktop/mongodb-linux-x86_64-rhel80-6.0.6.tgz \
        C:/Users/Administrator/Desktop/mongosh-1.8.2-linux-x64.tgz \
        root@43.136.102.115:/home/lighthouse/software
    ```
3. 解压和移动：`cd /home/lighthouse/software`
    ```bash
    tar -zxvf mongodb-linux-x86_64-rhel80-6.0.6.tgz
    mv mongodb-linux-x86_64-rhel80-6.0.6 /usr/local/mongodb
    tar -zxvf mongosh-1.8.2-linux-x64.tgz
    mv mongosh-1.8.2-linux-x64 /usr/local/mongosh
    ```
4. 添加环境变量：`vim /etc/profile` → `source /etc/profile` → `mongod -version`
    ```
    export MONGODB_HOME=/usr/local/mongodb
    export MONGOSH_HOME=/usr/local/mongosh
    export PATH=$PATH:$MONGODB_HOME/bin:$MONGOSH_HOME/bin
    ```
5. 创建数据和日志文件夹：`cd /usr/local/mongodb`
    - `mkdir db`
    - `mkdir logs` → `cd logs` → `touch mongod.log`
6. 修改配置文件：`vim /etc/mongod.conf`，@see [MongoDB.md](../nosql/MongoDB.md)
7. 命令
    - 启动服务：`mongod -f /etc/mongod.conf`
    - 停止服务：`mongod --shutdown --dbpath /usr/local/mongodb/db`
8. 连接 mongodb：`mongosh`
    ```
    use admin
    db.createUser({user: "root", pwd: "root", roles: ["root"]})
    db.auth("root", "root")
    ``` 
9. 防火墙：开放 27017 端口
---
## [MinIO](https://www.imooc.com/video/23862)
1. [Download](https://www.minio.org.cn/download.shtml#/linux)
    ```bash
    mkdir /usr/local/minio
    cd /usr/local/minio
    wget https://dl.minio.org.cn/server/minio/release/linux-amd64/minio
    chmod +x minio
    ```
2. 编写启动 shell：`vim start-minio.sh` → `chmod +x start-minio.sh`
    ```shell
    export MINIO_ROOT_USER=minio
    export MINIO_ROOT_PASSWORD=minio123
    # 2>&1 详解：https://blog.csdn.net/icanlove/article/details/38018169
    ./minio server --console-address 0.0.0.0:9001 /usr/local/minio/data > /usr/local/minio/minio.log 2>&1 &
    ```
3. [MinIO Console](http://localhost:9001/login)
    - Buckets → Create Bucket → Bucket Name: test → Create Bucket
    - 点击 test Bucket → Access Policy: Public
        - 右上角 Browse Bucket → Upload
---
## [Nacos](https://www.imooc.com/video/23826)
1. [Download](https://github.com/alibaba/nacos/releases)
    ```
    wget https://github.com/alibaba/nacos/releases/download/2.1.1/nacos-server-2.1.1.tar.gz
    tar -zxvf nacos-server-2.1.1.tar.gz
    mv nacos /usr/local/
    cd /usr/local/nacos
    ```
2. 启动：`sh ./bin/startup.sh -m standalone`
---
