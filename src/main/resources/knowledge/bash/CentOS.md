# My CentOS

---
## 问题
1. [在 VMware 上安装 CentOS6.8](https://www.cnblogs.com/moranlei/p/9333184.html)
2. [centos磁盘安装与磁盘分区方案详解](https://www.cnblogs.com/sunmoonp/p/10968359.html)
3. [CentOS6.8联网设置](https://blog.csdn.net/Catdingwt/article/details/79585929)
4. [SSH登录：WARNING: REMOTE HOST IDENTIFICATION HAS CHANGED!](https://blog.csdn.net/xlgen157387/article/details/52669709)
5. [CentOS6 YUM 源失效问题解决办法](https://www.cnblogs.com/binbingg/p/14082610.html)
6. [Centos7配置JAVA_HOME](https://www.cnblogs.com/baojun/p/10832624.html)
---
## Partition
    1. /boot：   Linux 系统启动有关的程序，256mb
    2. /home：   用户目录，5120mb
    3. swap：    虚拟内存，物理内存的1~2倍
    4. /：       系统根目录，剩余空间
---
## Basic Info
    1. host: 192.168.58.128
    2. user: ljh
    3. password: 123456
---
## Sign in
    ssh ljh@192.168.58.128
    123456
## common commands
    su
    ip addr
    vi /etc/sysconfig/network-scripts/ifcfg-eth0; ONBOOT=yes;
    vi ~/.ssh/known_hosts
---
## install
>### jdk
>1. yum list installed|grep java 或 rpm -qa|grep java，查看自带的 java
>2. yum remove XXX (上面命令的结果)，删除
>3. yum search java | grep -i --color jdk，查看可安装 java
>4. yum install java-1.8.0-openjdk java-1.8.0-openjdk-devel
>5. 添加环境变量
>   1. which java
>   2. ls -l /usr/bin/java
>   3. ls -l /etc/alternatives/java
>   4. vi /etc/profile
>       - export JAVA_HOME=/usr/lib/jvm/jre-1.8.0-openjdk.x86_64
---