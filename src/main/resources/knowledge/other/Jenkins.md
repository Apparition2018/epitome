# Jenkins
- 开源
- 常用于自动化测试、持续集成
---
## 参考网站
1. [用Jenkins自动化搭建测试环境-慕课网](https://www.imooc.com/learn/1008)
2. [Jenkins](https://www.jenkins.io)
3. [Jenkins 中文网](https://www.jenkins.io/zh/)
---
## 安装
1. [Download](https://www.jenkins.io/download/)
    - [Past Releases](https://get.jenkins.io/war-stable/)
    - `java -jar jenkins.war --ajp13Port=-1 --httpPort=8081`
    - [Docker](../docker/Docker.md#安装软件)
2. localhost:8081
3. 安装推荐的插件
4. 创建管理员账号 (admin/123456)
---
## 管理插件
- Dashboard → 系统管理 → 系统配置 → 管理插件
- Available plugins
- 搜索 Rebuilder 并勾选
- 搜索 Safe Restart 并勾选
- Install without restart
---
## 管理用户 & 全局安全配置
1. 新建用户
    - Dashboard → 系统管理 → 安全 → 管理用户
    - Create User → user01/123456 → 新建用户
2. 全局安全配置
    - Dashboard → 系统管理 → 安全 → 全局安全配置
    - 授权策略 → 安全矩阵
        - Add user… → admin → Grant all permissions to admin (在右边) → 保存
        - Add user… → user01 → Grant all permissions to admin (在右边) → 取消勾选 Administer - 保存
---
## [Docker](https://blog.csdn.net/qq_41318914/article/details/124494776)
1. `docker run -itd --name ubuntu -p 22:22 --privileged ubuntu`
    - `docker exec -it ubuntu bash`
2. `passwd root`
3. `unminimize`，`apt update`，`apt upgrade`
4. `apt install -y openssh-client openssh-server vim`
    1. `vim /etc/ssh/sshd_config`：`PermitRootLogin yes`
    2. 启动/重启 sshd：`/etc/init.d/ssh start` 等同于 `service start ssh`
5. `ssh root@127.0.0.1 -p 22`
6. IDEA 配置 Deployment
    1. Tools → Deployment → Configuration
    2. &divide; → SFTP → New server name: test → SSH configuration
        ```
        Host: 127.0.0.1
        Username: root
        Authenication type: Password
        ```
    3. Mappings → Local Path / Deployment path
    4. Tools → Deployment → Sync With Local…
7. `apt install -y openjdk-17-jdk`
8. `apt install -y git`，@see CentOS.md#Git
9. @see CentOS.md#Maven
10. @see CentOS.md#Tomcat
---
## 新建节点
- 系统管理 → 管理节点 →  New Node → Create
    1. 远程工作目录：/root/.jenkins
    2. 启动方式：Launch agent via SSH
        - 主机：127.0.0.1
        - Credentials → 添加 → root/root
    3. 保存
---