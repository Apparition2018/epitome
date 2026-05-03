# [Ubuntu](https://ubuntu.com/server/docs)

---
## systemd vs SysV init
| Function               | SysV init                   | systemd                                                                      |
|------------------------|-----------------------------|------------------------------------------------------------------------------|
| Start service          | `service <name> start`      | `systemctl start <name>`                                                     |
| Stop service           | `service <name> stop`       | `systemctl stop <name>`                                                      |
| Restart service        | `service <name> restart`    | `systemctl restart <name>`                                                   |
| Reload config          | `service <name> reload`     | `systemctl reload <name>`                                                    |
| Check status           | `service <name> status`     | `systemctl status <name>`                                                    |
| Enable at boot         | `chkconfig <name> on`       | `systemctl enable <name>`                                                    |
| Disable at boot        | `chkconfig <name> off`      | `systemctl disable <name>`                                                   |
| Check if enabled       | `chkconfig --list <name>`   | `systemctl is-enabled <name>`                                                |
| List all services      | `chkconfig --list`          | `systemctl list-unit-files --type=service`                                   |
| View logs              | `tail -f /var/log/messages` | `journalctl -u <name>`                                                       |
| Follow logs live       | `tail -f /var/log/syslog`   | `journalctl -f`                                                              |
| Power off              | `shutdown -h now`           | `systemctl poweroff`                                                         |
| Reboot                 | `reboot`                    | `systemctl reboot`                                                           |

---
## 更改软件源配置
```shell
# ≤ 23.10
cd /etc/apt
sudo cp sources.list sources.list.bak
sudo vim sources.list
# ≥ 24.04
cd /etc/apt/sources.list.d
sudo cp ubuntu.sources ubuntu.sources.bak
sudo vim ubuntu.sources
    https://mirrors.tuna.tsinghua.edu.cn/help/ubuntu/

apt update
```
---
## [Package Management](https://ubuntu.com/server/docs/package-management)

---
## [apt](https://manpages.ubuntu.com/manpages/xenial/man8/apt.8.html)
- [options](https://manpages.ubuntu.com/manpages/xenial/man8/apt-get.8.html#options)
    - `-h`：帮助
    - `-y`：所有提示默认选择 yes
    - `-q`：Quite，安静
    - `-s`：simulate，模拟
```
unminimize                                      解除系统最小化
apt update                                      更新软件源中的所有软件列表
apt upgrade                                     更新软件
apt list                                        列出所有包
    --installed                                 已安装
    --upgradeable                               可升级
    --all-versions                              所有可用版本
apt search <keyword>                            查找软件
apt show <package_name>                         显示安装包的信息

apt install -y man-db
    man
apt install -y tzdata                           Time Zone Databas
    tzselect
apt install -y build-essential                  GNU 调试器、g++/GNU 编译器集合，和更多编译程序所需的工具和库
apt install -y openssh-client
apt install -y openssh-server
apt install -y wget
apt install -y vim
apt install -y iptables
apt install -y firewalld                        防火墙，智能化的 iptables 管理服务器
apt install -y ufw                              防火墙，轻量化配置 iptables
    ufw enable/disable/status
apt install -y net-tools                        网络工具
    ifconfig
    netstat
apt install -y curl
apt install -y [--reinstall] systemd            一个 init 系统和服务管理器，取代 SysV init
    systemctl
apt install -y openjdk-8-jdk
apt install -y mysql-server-8.0
apt install -y git

apt remove <package_name>                       卸载，保留配置文件
apt purge <package_name>                        卸载，清除配置文件
apt autoremove                                  删除为满足其它包而自动安装的且现在不再需要的软件包
```
---
## MySQL
```
# 查看运行状态
sudo systemctl status mysql     # 查看状态
sudo systemctl start mysql      # 启动
sudo systemctl stop mysql       # 停止
sudo systemctl restart mysql    # 重启
sudo systemctl enable mysql     # 开机自启

# 初始安全配置
sudo mysql_secure_installation
y2ynyy

# 登录 MySQL
mysql

# 配置远程访问
# 查看 root 使用的认证插件
SELECT user, host, plugin FROM mysql.user WHERE user='root';
# 修改 root 密码和认证方式（允许密码登录）
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'Cesc123!';
FLUSH PRIVILEGES;
# 创建允许远程连接的 root 用户
CREATE USER 'root'@'%' IDENTIFIED BY 'Cesc123!';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

# 修改绑定地址（远程访问需要）
sudo vim /etc/mysql/mysql.conf.d/mysqld.cnf
bind-address = 0.0.0.0

# 重启
systemctl restart mysql

# 防火墙放行 3306 端口
sudo ufw allow 3306/tcp
sudo ufw reload
```
---
## [dpkg](http://manpages.ubuntu.com/manpages/jammy/man1/dpkg.1.html)

---
