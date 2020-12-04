# My CentOS

---
## 问题
1. [在 VMware 上安装 CentOS6.8](https://www.cnblogs.com/moranlei/p/9333184.html)
2. [centos磁盘安装与磁盘分区方案详解](https://www.cnblogs.com/sunmoonp/p/10968359.html)
3. [CentOS6.8联网设置](https://blog.csdn.net/Catdingwt/article/details/79585929)
4. [SSH登录：WARNING: REMOTE HOST IDENTIFICATION HAS CHANGED!](https://blog.csdn.net/xlgen157387/article/details/52669709)
5. [YumRepo Error: All mirror URLs are not using问题解决](http://blog.sina.com.cn/s/blog_541a3cf10101h245.html)
6. [Linux环境下字符串替换](https://blog.csdn.net/ymaini/article/details/80724806)
---
## Partition
    1. /boot：Linux 系统启动有关的程序，256mb
    2. /home：用户目录，5120mb
    3. swap：虚拟内存，物理内存的1~2倍?
    4. /：系统根目录，剩余空间
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
    su                       切换用户
    ip addr
    vim; %s/xyz/XYZ/g        vim 模式下替换所有字符串
---
## install
>### jdk
>1. yum list installed|grep java 或 rpm -qa|grep java，查看自带的 java
>2. yum remove XXX (上面命令的结果)，删除
>3. yum search java -i --color jdk
---