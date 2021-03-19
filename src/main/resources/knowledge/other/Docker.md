# Docker

---
## 参考网站
1. [在centos和redhat上安装docker](http://www.imooc.com/article/16448) 
2. [Windows Docker 安装 | 菜鸟教程](https://www.runoob.com/docker/windows-docker-install.html)
3. [从零开始的Docker Desktop使用,Docker快速上手](https://xunmi.blog.csdn.net/article/details/108641842)
4. [window10 下 docker 启动 redis](https://blog.csdn.net/qq_34670974/article/details/94051251)
5. [Windows下docker安装mysql并挂载数据](https://blog.csdn.net/pall_scall/article/details/112154454)
6. [windows10 docker -v 映射问题](https://www.80shihua.com/archives/2589)
---
##
1. Redis
```
docker run -d --name redis -p 6379:6379 [-v D:/Docker/Redis/data:/data] redis [--requirepass "Password" redis-server --appendonly yes]  
```
2. MySQL
```
docker run -d --name mysql -p 3306:3306 [-v D:/Docker/MySQL/my.cnf:/etc/mysql/my.cnf -v D:/Docker/MySQL/data:/var/lib/mysql] -e MYSQL_ROOT_PASSWORD=root mysql  
docker exec -it mysql mysql -uroot -proot
     create user ljh@172.17.0.1 identified by '123456';                         创建用户
     grant all privileges on `ry-vue`.* to ljh@172.17.0.1 with grant option;    授权
     flush privileges;
```
--- 