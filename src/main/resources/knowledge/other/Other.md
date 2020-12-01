# 其它

---
## 杂
1. [版本号命名规则](https://mp.weixin.qq.com/s/ZoUG9h1TndW2QpnPyGeIQA)
---
## [端口被占用](https://jingyan.baidu.com/article/3c48dd34491d47e10be358b8.html)
1. 打开 cmd.exe
2. netstat -ano
3. netstat -ano|findstr 8080，根据指定端口字符串查找，记下 PID
4. tasklist|findstr 9776，根据指定 PID 字符串查找任务，记下任务名
5. taskkill /f /t /im WXWork.exe，或 taskkill /pid 333412 /t
---
## [Maven 环境变量](http://www.xitongcheng.com/jiaocheng/dnrj_article_44449.html)
1. WIN+R 输入 sysdm.cpl → 高级 → 环境变量
2. 在系统变量新建 MAVEN_HOME：D:\tts9\apache-maven-3.3.9
3. 在系统变量找到 Path，增加 %MAVEN_HOME%\bin;
4. 在 cmd 输入 mvn -v
---
## Tomcat 启动中文乱码
1. 打开 D:\tts9\apache-tomcat-9.0.40\conf\logging.properties
2. 修改 java.util.logging.ConsoleHandler.encoding = GBK
---
## 开源镜像站
1. [阿里巴巴开源镜像站](https://developer.aliyun.com/mirror/)
2. [腾讯软件源](https://mirrors.cloud.tencent.com)
3. [网易开源镜像站](https://mirrors.163.com/)
---
## Redis 下载，启动，可视化工具
1. [Releases/redis](https://github.com/microsoftarchive/redis/releases)
2. 点击最新版本
3. 点击 Redis-x64-x.y.z.zip 下载
4. 启动：
    1. 双击 Redis 目录下 redis-server.exe
    2. 启动 cmd cd 到 Redis 目录，输入命令 redis-server.exe redis.window.conf
5. [Redis Desktop Manager](https://www.jianshu.com/p/ccc3ebe29f7b)
## 服务
1. WIN+R 输入 services.msc
---