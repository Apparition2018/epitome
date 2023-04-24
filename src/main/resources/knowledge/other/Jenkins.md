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
2. `passwd root`
3. `apt update`，`apt upgrade`
4. `apt install -y openssh-client openssh-server vim systemctl`
    - `vim /etc/ssh/sshd_config`：`PermitRootLogin yes`
    - `/etc/init.d/ssh start|restart`，启动/重启 sshd 服务程序
    - `ps -e|grep ssh`，查看 sshd 服务程序是否启动
5. `ssh root@127.0.0.1 -p 22`
6. idea → Tools → Deployment → Configuration
    - &divide; → SFTP → New server name: test → SSH configuration
        ```
        Host: 127.0.0.1
        Username: root
        Authenication type: Password
        ```
    - Mappings → Local Path / Deployment path
    - Tools → Deployment → Sync With Local…
---