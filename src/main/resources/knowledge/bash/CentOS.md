# CentOS

---
## 问题
1. [在 VMware 上安装 CentOS6.8](https://www.cnblogs.com/moranlei/p/9333184.html)
2. [VMware 没有足够内存自动启用 kdump](http://www.360doc.com/content/20/0415/23/67357512_906311206.shtml)
3. [CentOS 磁盘安装与磁盘分区方案详解](https://www.cnblogs.com/sunmoonp/p/10968359.html)
4. [CentOS6.8 联网设置](https://blog.csdn.net/Catdingwt/article/details/79585929)
---
## Partition
| 分区    | 作用              | 空间       |
|-------|-----------------|----------|
| /boot | Linux 系统启动有关的程序 | 256mb    |
| /home | 用户目录            | 5120bm   |
| swap  | 虚拟内存            | 物理内存1~2倍 |
| /     | 系统根目录           | 剩余空间     |
---
## 安装后执行命令
    su
    ip addr
    vi /etc/sysconfig/network-scripts/ifcfg-eth0; ONBOOT=yes;
    vi ~/.ssh/known_hosts
---
## [腾讯云](https://console.cloud.tencent.com/lighthouse/instance/index?rid=1)
- 公网：43.136.102.115
- 内网：10.0.8.8
---
## JDK
1. [downloads](https://www.oracle.com/java/technologies/downloads/archive/) 并安装
    ```
    rpm -qa|grep xxx    yum list installed|grep xxx
    rpm -e xxx          yum remove xxx
    wget xxx.rpm        yum search java|grep -i --color xxx     wget xxx.tar.gz
    rpm -ivh xxx.rpm    yum install -y xxx                      tar -zxvf xxx.tar.gz
    ```
2. 添加环境变量
    - `which java` → `ls -l /usr/bin/java` → `ls -l /etc/alternatives/java`
    - `vim /etc/profile` → `source /etc/profile` 或 `. /etc/profile`
        ```
        # JDK5 之后，JRE 能自动搜索目录下类文件，并且加载 dt.jar 和 tool.jar，不再需要配置 CLASSPATH
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
        4.2.3 firewall-cmd --zone=public --remove-port=8080/tcp --permanent
        4.2.4 systemctl restart firewalld
5. ${CATALINA_HOME}/bin/startup.sh
   ${CATALINA_HOME}/bin/shutdown.sh
6. 可选
    - 增加可执行权限：cd ${CATALINA_HOME} → chmod a+x -R *
    - 修改端口：vim conf/server.xml
```
---
## Maven
```
1. https://maven.apache.org/download.cgi
    wget https://dlcdn.apache.org/maven/maven-3/3.9.1/binaries/apache-maven-3.9.1-bin.tar.gz
    tar -zxvf xxx.tar.gz
    mv apache-maven-3.9.1 /usr/local/
2. 添加环境变量:
    2.1 vim /etc/profile
        export M2_HOME=/usr/local/apache-maven-3.9.1
        export PATH=$PATH:$M2_HOME/bin
    2.2 source /etc/profile
3. mvn -version
4. 常用命令
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
## [Nginx](https://cnblogs.com/bluestorm/p/4574688.html)
```
1. yum install -y gcc zlib zlib-devel pcre-devel openssl openssl-devel
2. https://nginx.org/en/download.html
    wget https://nginx.org/download/nginx-1.22.0.tar.gz
    tar -zxvf xxx.tar.gz
3. 创建用户组和用户
    3.1 groupadd nginx
    3.2 useradd -g nginx -s /sbin/nologin nginx
    3.3 passwd nginx
4. 编译和安装
    4.1 ./configure --group=nginx --user=nginx --with-pcre \
        --with-http_ssl_module --with-http_stub_status_module
        4.1.1 --prefix=PATH，指定安装目录，默认 /usr/local/nginx
    4.2 make
    4.3 make install
5. 防火墙：开放 80 端口
6. 虚拟域名配置及测试验证
    6.1 vim /usr/local/nginx/conf/nginx.conf
        # 加载 vhost/ 目录下的配置文件（方便维护），在 Server 节点前
        include vhost/*.conf;
    6.2 mkdir /usr/local/nginx/conf/vhost
    6.3 创建域名转发配置文件
        www.ljh.com.conf
        image.ljh.com.conf
        ...
7. 常用命令
    /usr/local/nginx//sbin/nginx -t             测试配置文件
    /usr/local/nginx//sbin/nginx                启动
    /usr/local/nginx//sbin/nginx -s stop        停止
    /usr/local/nginx//sbin/ngxin -s quit        退出
    /usr/local/nginx//sbin/ngxin -s reload      重启
    kill -HUP PID                               平滑重启（进程号查询：ps -ef|grep nginx）
    /usr/local/nginx//sbin/nginx -v             版本
    /usr/local/nginx//sbin/nginx -V             版本和配置选项
    /usr/local/nginx//sbin/nginx -h             帮助
8. 修改 hosts 文件，配置域名转发
    C:\Windows\System32\dirvers\etc\hosts       Windows
    /etc/hosts                                  Linux
        192.168.58.129 www.ljh.com
        192.168.58.129 image.ljh.com
        192.168.58.129 s.ljh.com
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
3. 添加环境变量
    - `vim /etc/profile` → `source /etc/profile`
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
### MySQL 双主
```
1. 主1：`vim /etc/my.cnf`，@see MySQL.md#master my.cnf
    log-bin=log-bin
    relay_log=relay-bin
2. 主2：`vim /etc/my.cnf`，只列出与 主1 不同的项
    service-id=2
3. 把主1设为主数据库，主2设为从数据库
    3.1 连接主1
        -- 创建 repl 账号，允许从 192.168.0.141 访问，密码为 repl
        grant replication slave on *.* to repl@192.168.0.141 identified by 'repl';
        flush privileges;
        -- 重置 master，初次配置时可以使用
        reset master;
        -- 查看 master 信息（File 和 Position）
        show master status;
    3.2 连接主2        
        -- 停止 slave
        stop slave;
        -- 将 192.168.0.141:3306 设为 master
        -- master_user, master_password，使用 master 创建的 repl 账号
        -- master_log_file, master_log_pos 是在 master 使用 show master status 语句查看到的 File 和 Position
        change master to master_host='192.168.0.141', master_port=3306, master_user='repl',
            master_password='repl', master_log_file='log-bin.000001', master_log_pos=154;
        -- 开启 slave
        start slave;
        -- 查看 slave 状态，Slave_IO_Running 和 Slave_SQL_Running 都为 Yes 时，表示成功
        -- 如果不成功，可查看 data 目录下 *。err 日志文件
        show slave status;
        -- 重置 slave，slave 异常时可以使用
        reset slave all;
4. 把主2设为主数据库，主1设为从数据库
```
---
## Git
```
1. yum install -y zlib-devel openssl-devel cpio expat-devel gettext-devel curl-devel perl-ExtUtils-CBuilder perl-ExtUtils-MakeMaker
2. https://github.com/git/git/releases
    wget https://github.com/git/git/archive/refs/tags/v2.31.0.tar.gz
    tar -zxvf xxx.tar.gz
3. 安装
    3.1 cd git-2.31.0
    3.2 make prefix=/usr/local all
    3.3 make prefix=/usr/local install
    3.4 git --version
4. 基础配置
    4.1 git config --global user.name 'Apparition2018'          配置用户名
    4.2 git config --global user.email '88850180@163.com'       配置邮箱
    4.3 git config --global core.autocrlf false                 忽略 Windows/Unix 换行符转换
    4.4 git config --global gui.encoding utf-8                  指定默认 gui/gitk 字符编码
        git config --global core.quotepath off                  避免中文乱码
    4.5 git config --global core.ignorecase flase               关闭忽略大小写
5. 配置 SSH keys
    5.1 ssh-keygen -t rsa -C '88850180@163.com'                 生成密钥
    5.2 ssh-agent bash 或 eval `ssh-agent`                       启动 ssh-agent
    5.3 ssh-add ~/.ssh/id_rsa                                   添加密钥到 ssh-agent
    5.4 登录 GitHub|Gitee 新建 SSH key，把公钥复制过去
        ssh-add -L 或 vim ~/.ssh/id_rsa.pub                      查看公钥
    5.5 ssh -T git@github.com                                   验证配置是否成功
```
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
## [RabbitMQ](https://www.imooc.com/video/23816)
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
    rpm -Uvh erlang-*.rpm
    yum install -y erlang
    erl
    ```   
    ```bash
    rpm -Uvh rabbitmq-server-*.noarch.rpm
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
## [MongoDB](https://www.imooc.com/video/23829)
1. download
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
    ```bash
    cd /home/lighthouse/software
    cp erlang-*.rpm rabbitmq-server-*.noarch.rpm /usr/local/rabbitmq
    ```
3. 解压和移动：`cd /home/software`
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

