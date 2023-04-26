# CentOS

---
## 问题
1. [在 VMware 上安装 CentOS6.8](https://www.cnblogs.com/moranlei/p/9333184.html)
2. [centos磁盘安装与磁盘分区方案详解](https://www.cnblogs.com/sunmoonp/p/10968359.html)
3. [没有足够内存自动启用kdump](http://www.360doc.com/content/20/0415/23/67357512_906311206.shtml)
4. [CentOS6.8联网设置](https://blog.csdn.net/Catdingwt/article/details/79585929)
5. [SSH登录：WARNING: REMOTE HOST IDENTIFICATION HAS CHANGED!](https://blog.csdn.net/xlgen157387/article/details/52669709)
6. [CentOS6 YUM 源失效问题解决办法](https://www.cnblogs.com/binbingg/p/14082610.html)
---
## Partition
    1. /boot：   Linux 系统启动有关的程序，256mb
    2. /home：   用户目录，5120mb
    3. swap：    虚拟内存，物理内存的1~2倍
    4. /：       系统根目录，剩余空间
---
## Basic Info
    1. host: 192.168.58.129
    2. user: ljh
    3. password: 123456
---
## Sign in
    ssh ljh@192.168.58.129
    123456
---
## Common Commands
    su
    ip addr
    vi /etc/sysconfig/network-scripts/ifcfg-eth0; ONBOOT=yes;
    vi ~/.ssh/known_hosts
---
## JDK
1. [Linux 安装 JDK8 (rpm 方式)](https://www.cnblogs.com/myibm/p/9232744.html)
2. [定位 JDK 安装路径](https://www.cnblogs.com/baojun/p/10832624.html)
```
1. rpm -qa|grep XXX             yum list installed|grep XXX
   rpm -e XXX                   yum remove XXX
   wget XXX.rpm                 yum search java|grep -i --color XXX
   https://www.oracle.com/java/technologies/downloads/archive/
   rpm -ivh XXX.rpm             yum install XXX
2. 添加环境变量
    2.1 which java
    2.2 ls -l /usr/bin/java
    2.3 ls -l /etc/alternatives/java
    2.4 vim /etc/profile
        export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_281-amd64
        export PATH=$PATH:$JAVA_HOME/bin
        注：JDK5 之后，JRE 能自动搜索目录下类文件，并且加载 dt.jar 和 tool.jar，不再需要配置 CLASSPATH
    2.5 source /etc/profile 或 . /etc/profile
3. 配置多版本 JDK：https://www.imooc.com/video/18991
    3.1 vim /etc/profile
        export JAVA_8_HOME=/.../jdk1.8...
        export JAVA_17_HOME=/.../jdk-17...
        alias jdk8="export JAVA_HOME=$JAVA_8_HOME"
        alias jdk17="export JAVA_HOME=$JAVA_17_HOME"
    3.2 source /etc/profile
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
    - 修改端口：vim con/server.xml
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
1. yum -y install ftp
   yum -y install vsftpd
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
1. yum -y install gcc zlib zlib-devel pcre-devel openssl openssl-devel
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
## MySQL
- [安装 mysql-5.7](https://cnblogs.com/qcq0703/p/11186055.html)
```
1. yum list installed|grep mysql
   yum list installed|grep mariadb
   yum remove XXX
2. https://downloads.mysql.com/archives/community/
    Product Version     5.7.38
    Operating System    Linux - Generic
    OS Version          Linux - Generic(glibc 2.12)(x86,64-bit)
    wget https://downloads.mysql.com/archives/get/p/23/file/mysql-5.7.38-linux-glibc2.12-x86_64.tar.gz
    tar -zxvf xxx.tar.gz
    mv mysql-5.7... mysql-5.7                               重命名
    mv mysql-5.7 /usr/local/                                移动
3. 添加环境变量
    3.1 vim /etc/profile
        export MYSQL_HOME=/usr/local/mysql-5.7
        export PATH=$PATH:$MYSQL_HOME/bin
    3.2 source /etc/profile
4. 创建用户组和用户
    4.1 cat /etc/group|grep mysql                                       查看 mysql 组是否存在
    4.2 cat /etc/passwd|grep mysql                                      查看 mysql 用户是否存在
    4.3 groupadd mysql                                                  创建 mysql 组
    4.4 useradd -g mysql -s /sbin/nologin mysql                         创建 mysql 用户在 mysql 组下
    4.5 passwd mysql                           `                        更改 mysql 用户密码
5. /etc/my.cnf
6. 初始化
    6.1 /usr/local/mysql-5.7/bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql-5.7/ --datadir=/usr/local/mysql-5.7/data/
    6.2 记住日志最后一行的密码
    6.3 chown -R mysql:mysql /usr/local/mysql-5.7/data/                 更改 data 目录所属用户组和用户
7. 添加 mysql 服务
    7.1 cp /usr/local/mysql-5.7/support-files/mysql.server /etc/init.d/mysql
                                                                        复制服务脚本到 /etc/init.d/
                                                                        使得 Centos 6 可以使用 service mysql start|stop|restart|status 命令
                                                                        使得 Centos 7 可以使用 systemctl start|stop|restart|status|enable|disable mysql 命令
    7.2 chmod +x /etc/init.d/mysql                                      增加脚本可执行权限
    7.3 chkconfig --add mysql                                           添加 mysql 服务
    7.4 chkconfig --list mysql                                          列出 mysql 服务情况
    7.5 service mysql start | systemctl start mysql
8. 防火墙：开放 3306 端口
9. 连接 mysql：mysql -uroot -p 
    9.1 alter user root@localhsot identified by 'root';                 修改密码
    9.2 grant all privileges on *.* to root@'%' identified by 'root';   授权
    9.3 flush privileges;
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
1. yum -y install zlib-devel openssl-devel cpio expat-devel gettext-devel curl-devel perl-ExtUtils-CBuilder perl-ExtUtils-MakeMaker
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
    5.5 ssh git@github.com                                      验证配置是否成功
```
---
## Redis
```
1. yum install gcc-c++
2. https://redis.io/download/
    wget https://github.com/redis/redis/archive/refs/tags/6.2.2.tar.gz
    tar -zxvf xxx.tar.gz
3. 安装
    3.1 cd redis-6.2.2
    3.2 make
    3.4 make PREFIX=/usr/local/redis install
4. 配置
    4.1 cd redis-6.2.2
    4.2 cp redis.conf /usr/local/redis
5. 防火墙：开放 6379 端口
6. 启动
    6.1 cd /usr/local/redis
    6.2 vim redis.conf，修改 daemonize no 改成 daemonize yes，保存退出
    6.3 ./bin/redis-server ./redis.conf
7. 客户端操作：./bin/redis-cli
8. 停止：./bin/redis-cli shutdown
```
---
