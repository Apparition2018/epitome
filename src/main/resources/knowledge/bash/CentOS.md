# My CentOS

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
## Common Commands
    su
    ip addr
    vi /etc/sysconfig/network-scripts/ifcfg-eth0; ONBOOT=yes;
    vi ~/.ssh/known_hosts
---
## Install
>### JDK
>1. [linux安装jdk1.8(rpm方式)](https://www.cnblogs.com/myibm/p/9232744.html)
>2. [Centos以RPM方式安装java](ibear.me/2018/11/08/445)
>3. [Centos7配置JAVA_HOME](https://www.cnblogs.com/baojun/p/10832624.html)
>```
>1. rpm -qa|grep XXX            yum list installed|grep XXX             查看已安装软件
>2. rpm -e XXX                  yum remove XXX                          删除软件
>3. wget XXX.rpm                yum search java|grep -i --color XXX     查看可安装软件
>4. rpm -ivh XXX                yum install XXX                         安装软件
>5. 添加环境变量
>   5.1 which java
>   5.2 ls -l /usr/bin/java
>   5.3 ls -l /etc/alternatives/java
>   5.4 vim /etc/profile
>       - export JAVA_HOME=/usr/java/jdk1.8.0_281-amd64
>       - export CLASSPATH=.:$JAVE_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
>       - export PATH=$PATH:$JAVA_HOME/bin
>   5.5 source /etc/profile
>```
>### Tomcat
>- [在虚拟机下的tomcat，通过本地的浏览器进行访问](https://jingyan.baidu.com/article/d169e18621a4a1436611d8d3.html)
>```
>1. wget https://downloads.apache.org/tomcat/tomcat-9/v9.0.44/bin/apache-tomcat-9.0.44.tar.gz
>   - https://tomcat.apache.org/download-90.cgi
>2. tar -zxvf xxx.tar.gz
>3. 添加环境变量
>   3.1 vim /etc/profile
>       - export CATALINA_HOME=/home/ljh/software/apache-tomcat-9.0.41
>   3.2 source /etc/profile
>4. 配置 UTF-8 字符集
>   4.1 vim ${CATALINA_HOME}/conf/server.xml
>   4.2 在 <connector port="8080" 最后添加 URIEncoding="UTF-8"/>
>5. 防火墙配置
>   5.1 vim /etc/sysconfig/iptables
>       -A INPUT -m state --state NEW -m tcp -p tcp --dport 8080 -j ACCEPT
>   5.2 service iptables restart
>6. ${CATALINA_HOME}/bin/startup.sh
>   ${CATALINA_HOME}/bin/shutdown.sh
>```
>### Maven
>```
>1. wget https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
>   - https://maven.apache.org/download.cgi
>2. tar -zxvf xxx.tar.gz
>3. 添加环境变量
>   3.1 vim /etc/profile
>       - export MAVEN_HOME=/home/ljh/software/apache-maven-3.6.3
>       - export PATH=$PATH:$MAVEN_HOME/bin
>   3.2 source /etc/profile
>4. mvn -version
>5. 常用命令
>   mvn clean                                       清除
>   mvn compile                                     编译
>   mvn package                                     打包
>   mvn clean package -Dmaven.test.skip=true        跳过单元测试
>```
>### vsftpd
>- Very Secure FTP Daemon，一个完全免费的、开放源代码的 ftp 服务器软件
>```
>1. yum -y install ftp
>   yum -y install vsftpd
>2. 创建虚拟用户
>   mkdir /ftpfile                                      创建 ftp 文件夹
>   useradd ftpuser -d /ftpfile -s /sbin/nologin        添加匿名用户 ftpuser
>   chown -R ftpuser.ftpuser /ftpfile                   修改 ftp 文件夹权限
>   passwd ftpuser                                      重置 ftpuser 用户密码为 123456
>3. 配置
>   3.1 vim /etc/vsftpd/chroot_list                     添加 ftpuser
>   3.2 vim /etc/selinux/config                         修改 SELINUX=disabled
>       setenforce 0                                    临时生效
>   3.3 550 拒绝访问
>       setsebool -P ftp_home_dir 1
>       重启 linux，执行 reboot 命令
>   4.4 vim /etc/vsftpd/vsftpd.conf                    http://learning.happymmall.com/vsftpdconfig/vsftpd.conf.readme.html
>4. 防火墙配置
>   4.1 vim /etc/sysconfig/iptables
>       -A INPUT -p tcp --dport 61001:62000 -j ACCEPT
>       -A OUTPUT -p tcp --sport 61001:62000 -j ACCEPT
>       -A INPUT -p tcp --dport 20 -j ACCEPT
>       -A OUTPUT -p tcp --sport 20 -j ACCEPT
>       -A INPUT -p tcp --dport 21 -j ACCEPT
>       -A OUTPUT -p tcp --sport 21 -j ACCEPT
>   4.2 service iptables restart
>5. 启动和访问
>   5.1 service vsftpd restart
>   5.2 浏览器访问 ftp://192.168.58.129
>6. 登录服务器：ftp 192.168.58.129
>7. FTP 软件：FileZilla
>```
>### Nginx
>- Nginx 是一款轻量级 web 服务器，也是一款反向代理服务器；特点有高稳定，高性能，资源占用少，功能丰富，模块化结构，支持热部署
>```
>1. yum -y install gcc zlib zlib-devel pcre-devel openssl openssl-devel
>2. wget http://nginx.org/download/nginx-1.18.0.tar.gz
>   - http://nginx.org/en/download.html
>3. tar -zxvf xxx.tar.gz
>4. 安装
>   4.1 ./configure
>       ①可指定安装目录，--prefix=/home/ljh/nginx
>       ②默认安装位置，/usr/local/nginx
>   4.2 make
>   4.3 make install
>5. 防火墙设置
>   5.1 vim /etc/sysconfig/iptables
>       -A INPUT -m state --state NEW -m tcp -p tcp --dport 80 -j ACCEPT
>   5.2 service iptables restart
>6. 虚拟域名配置及测试验证
>   6.1 vim /usr/local/nginx/conf/nginx.conf
>       # 加载 vhost/ 目录下的配置文件（方便维护），在 Server 节点前
>       include vhost/*.conf;
>   6.2 mkdir /usr/local/nginx/conf/vhost
>   6.3 创建域名转发配置文件
>       www.ljh.com.conf
>       image.ljh.com.conf
>       ...
>7. 常用命令
>   ./sbin/nginx -t                         测试配置文件
>   ./sbin/nginx                            启动
>   ./sbin/nginx -s stop                    停止
>   ./sbin/ngxin -s quit                    停止
>   ./sbin/ngxin -s reload                  重启
>   kill -HUP PID                           平滑重启（进程号查询：ps -ef|grep nginx）
>   ./sbin/nginx -v                         版本
>   ./sbin/nginx -V                         版本和配置选项
>   ./sbin/nginx -h                         帮助
>8. 修改 hosts 文件，配置域名转发
>   C:\Windows\System32\dirvers\etc\hosts   Windows
>   /etc/hosts                              Linux
>       192.168.58.129 www.ljh.com
>       192.168.58.129 image.ljh.com
>       192.168.58.129 s.ljh.com
>```
>### MySQL
>- [Access denied for user 'root'@'192.168.1.xxx' (using password: YES)](https://blog.csdn.net/qq_34885405/article/details/93041509)
>```
>1. yum list installed|grep mysql       查看已安装 mysql
>2. yum remove mysql-libs.x86_64        删除 msyql
>3. yum -y install mysql-server         安装 mysql （yum 只能安装 mysql-5.1.73）
>4. 字符集配置
>   4.1 vim /etc/my.cnf
>   4.2 [mysqld] 节点下添加
>       default-character-set=utf8  [5.1]
>       character-set-server=utf8   [5.5+]
>5. 自启动配置
>   5.1 chkconfig mysqld on
>   5.2 chkconfig --list mysqld （如果2-5位启用on状态即为OK）
>6. 防火墙设置
>   6.1 vim /etc/sysconfig/iptables
>       -A INPUT -m tcp -p tcp --dport 3306 -j ACCEPT
>   6.2 service iptables restart
>7. MySQL 配置
>   7.0 mysql -u root
>   7.1 查看目前 mysql 用户
>       select user, host, password from mysql.user;
>   7.2 修改 root 密码
>       set password for root@localhost=password('yourpassword');
>   7.3 删除匿名用户
>       select user, host from mysql.user where user='';
>       delete from mysql.user where user='';
>       flush privileges;
>   7.4 新增 mysql 用户
>       insert into mysql.user(Host, User, Password) values("localhost", "yourusername", passowrd("yourpassword"));
>       flush privileges;
>   7.5 创建数据库
>       create database `mmall` default character set utf8 collate utf8_general_ci;
>   7.6 查看权限（\G 格式化查询）
>       select * from mysql.user \G;
>   7.7 授权
>       grant all privileges on mmall.* to yourusername@localhost identified by 'yourpassword' with grant option;
>       grant all privileges on mmall.* to yourusername@'%' identified by 'yourpassword';
>       show grants for yourusername@localhost;
>8. 常用命令
>   service mysqld start|stop|restart
>```
>### Git
>```
>1. yum -y install zlib-devel openssl-devel cpio expat-devel gettext-devel curl-devel perl-ExtUtils-CBuilder perl-ExtUtils-MakeMaker
>2. wget https://github.com/git/git/archive/refs/tags/v2.31.0.tar.gz
>   - https://github.com/git/git/releases
>3. tar -zxvf xxx.tar.gz
>4. 安装
>   4.1 cd git-2.31.0
>   4.2 make prefix=/usr/local all
>   4.3 make prefix=/usr/local install
>   4.4 git --version
>5. 基础配置
>   5.1 配置用户名
>       git config --global user.name 'Apparition2018'
>   5.2 配置邮箱
>       git config --global user.email '88850180@163.com'
>   5.3 忽略 Windows/Unix 换行符转换
>       git config --global core.autocrlf false
>   5.4 编码相关配置
>       git config --global gui.encoding utf-8
>       git config --global core.quotepath off
>   5.5 关闭忽略大小写
>       git config --global core.ignorecase flase
>6. 配置 ssh key pair
>   6.1 ssh-keygen -t rsa -C '88850180@163.com'
>   6.2 一路回车，生成 ssh key pair
>   6.3 ssh-add ~/.ssh/id_rsa
>       如果出现 Could not open a connection to your authentication agent，先执行 eval `ssh-agent`
>   6.4 cat ~/.ssh/id_rsa.pub
>   6.5 登录码云设置 SSH 公钥
>```
>### Redis
>```
>1. yum install gcc-c++
>2. wget https://github.com/redis/redis/archive/refs/tags/6.2.2.tar.gz
>3. tar -zxvf xxx.tar.gz
>4. 安装
>   4.1 cd redis-6.2.2
>   4.2 make
>   4.4 make PREFIX=/usr/local/redis install
>5. 配置
>   5.1 cd redis-6.2.2
>   5.2 cp redis.conf /usr/local/redis
>6. 防火墙
>   6.1 vim /etc/sysconfig/iptables
>       -A INPUT -m state --state NEW -m tcp -p tcp --dport 6379 -j ACCEPT
>   6.2 service iptables restart
>7. 启动
>   7.1 cd /usr/local/redis
>   7.2 vim redis.conf，修改 daemonize no 改成 daemonize yes，保存退出
>   7.3 ./bin/redis-server ./redis.conf
>8. 客户端操作：./bin/redis-cli
>9. 停止：./bin/redis-cli shutdown
>```
---