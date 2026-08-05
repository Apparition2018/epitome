# [Ubuntu](https://ubuntu.com/server/docs)

---
## VMware Workstation
- [Downloads](https://support.broadcom.com/group/ecx/free-downloads)：VMware Workstation
- [系统镜像](../other/Programming.md)
- 创建新的虚拟机
    1. 自定义 → 下一步
    2. 稍后安装操作系统 → 下一步
    3. Linux → Ubuntu 64 位 → 下一步
    4. Ubuntu_20260704 → **\Ubuntu → 下一步
    5. 2 → 2 → 下一步 → 4096 → 下一步
    6. 使用网络地址转换 → 下一步
    7. LSI Logic → 下一步 → SCSI → 下一步
    8. 创建新虚拟磁盘 → 下一步
    9. 20 → 将虚拟磁盘拆分成多个文件 → 下一步
    10. Ubuntu_20260704.vmdk → 下一步
- 右键虚拟机 → 设置
    - 硬件 → CD/DVD：启动时连接 → 使用 ISO 映像文件
    - 选项 → 高级：为启用了 Hyper-V 的主机禁用侧通道缓解
- [VMware 克隆环境](https://www.imooc.com/video/24347)
---
## systemd vs SysV init

| Package       | SysV init                        | systemd                                                |
|---------------|----------------------------------|--------------------------------------------------------|
| Firewall      | `iptables` + `iptables-services` | `firewalld`(CentOS) / `ufw`(Ubuntu)                    |
| Network       | `network`                        | `NetworkManager`(Desktop) + `systemd-networkd`(Server) |
| Network Tools | `net-tools`                      | `iproute2`                                             |
| Scheduling    | `cron`                           | `cron` / `systemd-timers`                              |
| Hostname      | `hostname` + `/etc/hostname`     | `hostnamectl`                                          |

| Command           | SysV init                   | systemd                                    |
|-------------------|-----------------------------|--------------------------------------------|
| Start service     | `service <name> start`      | `systemctl start <name>`                   |
| Stop service      | `service <name> stop`       | `systemctl stop <name>`                    |
| Restart service   | `service <name> restart`    | `systemctl restart <name>`                 |
| Reload config     | `service <name> reload`     | `systemctl reload <name>`                  |
| Check status      | `service <name> status`     | `systemctl status <name>`                  |
| Enable at boot    | `chkconfig <name> on`       | `systemctl enable <name>`                  |
| Disable at boot   | `chkconfig <name> off`      | `systemctl disable <name>`                 |
| Check if enabled  | `chkconfig --list <name>`   | `systemctl is-enabled <name>`              |
| List all services | `chkconfig --list`          | `systemctl list-unit-files --type=service` |
| View logs         | `tail -f /var/log/messages` | `journalctl -u <name>`                     |
| Follow logs live  | `tail -f /var/log/syslog`   | `journalctl -f`                            |
| Power off         | `shutdown -h now`           | `systemctl poweroff`                       |
| Reboot            | `reboot`                    | `systemctl reboot`                         |
---
## 更改软件源配置
```shell
# ≤ 23.10
cd /etc/apt
sudo cp sources.list sources.list.bak
sudo vi sources.list
# ≥ 24.04
cd /etc/apt/sources.list.d
sudo cp ubuntu.sources ubuntu.sources.bak
sudo vi ubuntu.sources
    https://mirrors.tuna.tsinghua.edu.cn/help/ubuntu/

apt update
```
---
## 其它设置
- 修改密码
```bash
nano /etc/pam.d/common-pasword
    password        requisite                       pam_pwquality.so retry=3 minlen=6 dictcheck=0
passwd
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
```bash
unminimize                                          # 解除系统最小化
apt update                                          # 更新软件源中的所有软件列表
apt upgrade                                         # 更新软件
apt list                                            # 列出所有包
    --installed                                     # 已安装
    --upgradeable                                   # 可升级
    --all-versions                                  # 所有可用版本
apt search <keyword>                                # 查找软件
apt show <package_name>                             # 显示安装包的信息

# Ubuntu 默认已安装
apt install -y [--reinstall] systemd                # 一个 init 系统和服务管理器，取代 SysV init
apt install -y man-db                               # man 手册页的工具包
    man
apt install -y tzdata                               # Time Zone Databas
    tzselect

apt install -y wget
apt install -y iptables
apt install -y ufw                                  # 防火墙，轻量化配置 iptables
    ufw enable/disable/status/reload
apt install -y build-essential                      # GNU 调试器、g++/GNU 编译器集合，和更多编译程序所需的工具和库
apt install -y openssh-client
apt install -y openssh-server
apt install -y curl
apt install -y git
apt install -y openjdk-8-jdk
apt install -y open-vm-tools open-vm-tools-desktop  #  VMware 官方开源的虚拟机增强工具，用于替代传统的闭源 VMware Tools

apt remove <package_name>                           # 卸载，保留配置文件
apt purge <package_name>                            # 卸载，清除配置文件
apt autoremove                                      # 删除为满足其它包而自动安装的且现在不再需要的软件包
```
---
## MySQL
1. `apt install -y mysql-server-8.0`
2. 安全初始化 `mysql_secure_installation` → y2ynyy
3. 连接 msql `mysql`
    ```sql
    # 修改 root 密码和认证插件。mysql_native_password（5.7）、caching_sha2_password（8+）
    ALTER USER 'root'@'localhost' IDENTIFIED WITH caching_sha2_password BY 'Cesc123!';
    # 创建允许远程连接的 root 用户，并授权
    CREATE USER 'root'@'%' IDENTIFIED BY 'Cesc123!';
    GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
    FLUSH PRIVILEGES;
    # 查看用户使用的认证插件
    SELECT user, host, plugin FROM mysql.user where user = 'root';
    ```
4. 监听所有 ip（允许远程访问）
    - `vi /etc/mysql/mysql.conf.d/mysqld.cnf` → `bind-address = 0.0.0.0`
    - `systemctl restart mysql`
    - 注：Docker MySQL 的 bind-address 默认就是 0.0.0.0
5. 防火墙：放行 3306 端口：`ufw allow 3306/tcp` → `ufw reload`
    - `ufw allow 3306/tcp`
---
## [dpkg](http://manpages.ubuntu.com/manpages/jammy/man1/dpkg.1.html)

---
