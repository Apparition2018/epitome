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
>1. wget xxx.tar.gz
>2. tar -zxvf xxx.tar.gz
>3. 添加环境变量
>   3.1 vim /etc/profile
>       - export CATALINA_HOME=/home/ljh/software/apache-tomcat-9.0.41
>   3.2 source /etc/profile
>4. 配置 UTF-8 字符集
>   4.1 vim ${CATALINA_HOME}/conf/server.xml
>   4.2 在 <connector port="8080" 最后添加 URIEncoding="UTF-8"/>
>5. ${CATALINA_HOME}/bin/startup.sh
>6. ${CATALINA_HOME}/bin/shutdown.sh
>```
>### Maven
>```
>1. wget xxx.tar.gz
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
>   3.1 vim /etc/vsftpd/chroot_list                    添加 ftpuser 
>   3.2 vim /etc/selinux/config                        修改 SELINUX=disabled
>       setenforce 0                                    临时生效
>   3.3 550 拒绝访问
>       setsebool -P ftp_home_dir 1
>       重启 linux，执行 reboot 命令
>   4.4 vim /etc/vsftpd/vsftpd.conf                    http://learning.happymmall.com/vsftpdconfig/vsftpd.conf.readme.html
>4. 防火墙配置
>   4.1 vim /etc/sysconfig/iptables
>       -A INPUT -p TCP --dport 61001:62000 -j ACCEPT
>       -A OUTPUT -p TCP --sport 61001:62000 -j ACCEPT
>       -A INPUT -p TCP --dport 20 -j ACCEPT
>       -A OUTPUT -p TCP --sport 20 -j ACCEPT
>       -A INPUT -p TCP --dport 21 -j ACCEPT
>       -A OUTPUT -p TCP --sport 21 -j ACCEPT
>   4.2 service iptables restart
>5. 启动和访问
>   5.1 service vsftpd restart
>   5.2 浏览器访问 ftp://192.168.58.129                           
>6. 登录服务器：ftp 192.168.58.129
>7. FTP 软件：filezilla
>```
>### Nginx
>- Nginx 是一款轻量级 web 服务器，也是一款反向代理服务器；特点有高稳定，高性能，资源占用少，功能丰富，模块化结构，支持热部署
>```
>1. yum -y install gcc zlib zlib-devel pcre-devel openssl openssl-devel
>2. wget xxx.tar.gz
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
>8. 修改 hosts 文件，配置域名转发
>   C:\Windows\System32\dirvers\etc\hosts   Windows
>   /etc/hosts                              Linux
>       192.168.58.129 www.ljh.com
>       192.168.58.129 image.ljh.com
>       192.168.58.129 s.ljh.com
>```
---