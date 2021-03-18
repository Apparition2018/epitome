# Docker

---
## 参考网站
1. [在centos和redhat上安装docker](http://www.imooc.com/article/16448) 
2. [Windows Docker 安装 | 菜鸟教程](https://www.runoob.com/docker/windows-docker-install.html)
3. [从零开始的Docker Desktop使用,Docker快速上手](https://xunmi.blog.csdn.net/article/details/108641842)
4. [window10 下 docker 启动 redis](https://blog.csdn.net/qq_34670974/article/details/94051251)
5. [Windows下的Docker配置MySQL修改默认密码](https://blog.csdn.net/TinaCSDN/article/details/90023343)
6. [windows10 docker -v 映射问题](https://www.80shihua.com/archives/2589)
---
##
1. Redis
```
docker run -d --name redis -p 6379:6379 [-v D:/Docker/redis-data:/data] redis [--requirepass "Password"]  
```
2. MySQL
```
docker run -d --name mysql -p 3306:3306 [-v D:/Docker/MySQL/my.cnf:/etc/mysql/my.cnf -v D:/Docker/MySQL/data:/var/lib/mysql] -e MYSQL_ROOT_PASSWORD=root mysql  
docker exec -it mysql mysql -uroot -proot
```
--- 