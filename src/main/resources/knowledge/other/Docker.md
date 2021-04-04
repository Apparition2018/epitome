# Docker

---
## 参考网站
1. [Docker Hub](https://hub.docker.com)
2. [在centos和redhat上安装docker](http://www.imooc.com/article/16448) 
3. [如何在Windows上更改Docker的默认安装路径？](https://www.zhihu.com/question/359332823/answer/923520420)
4. [Windows Docker 安装 | 菜鸟教程](https://www.runoob.com/docker/windows-docker-install.html)
---
## 问题
1. [更改Docker for windows的Hyper-v虚拟机的位置](https://blog.csdn.net/chuweisan2257/article/details/100906248)
2. [从零开始的Docker Desktop使用,Docker快速上手](https://xunmi.blog.csdn.net/article/details/108641842)
3. [window10 下 docker 启动 redis](https://blog.csdn.net/qq_34670974/article/details/94051251)
4. [Windows下docker安装mysql并挂载数据](https://blog.csdn.net/pall_scall/article/details/112154454)
5. [windows10 docker -v 映射问题](https://www.80shihua.com/archives/2589)
---
## Docker Desktop 安装
1. 安装前创建目录链接
```bash
mklink /j "C:\Program Files\Docker" "D:\Docker"
mklink /j "C:\ProgramData\DockerDesktop" "D:\Docker\DockerDesktop"
```
2. 下载地址：https://hub.docker.com/editions/community/docker-ce-desktop-windows
---
## 常用命令
```bash
docker version
```
---
## 
1. Redis
```bash
docker run -d --name redis -p 6379:6379 [-v D:/Docker/Redis/data:/data] redis [--requirepass "Password" redis-server --appendonly yes]
```
2. MySQL
```bash
docker run -d --name mysql -p 3306:3306 [-v D:/Docker/MySQL/my.cnf:/etc/mysql/my.cnf -v D:/Docker/MySQL/data:/var/lib/mysql] -e MYSQL_ROOT_PASSWORD=root mysql
docker exec -it mysql mysql -uroot -proot
     create user ljh@172.17.0.1 identified by '123456';                         创建用户
     grant all privileges on `ry-vue`.* to ljh@172.17.0.1 with grant option;    授权
     flush privileges;
```
--- 