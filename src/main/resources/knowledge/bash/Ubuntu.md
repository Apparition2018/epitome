# Ubuntu

--- 
## Reference
1. [Introduction | Ubuntu](https://ubuntu.com/server/docs)
---
## 更改软件源配置
```shell
cd /etc/apt/
sudo mv sources.list sources.list.def
sudo vim sources.list
    https://mirrors.tuna.tsinghua.edu.cn/help/ubuntu/
apt update
```
---
## [Package Management](https://ubuntu.com/server/docs/package-management)
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
apt isntall -y build-essential                  GNU 调试器、g++/GNU 编译器集合，和更多编译程序所需的工具和库
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
apt isntall -y [--reinstall] systemd            一个系统和服务管理器，用于引导用户空间和管理用户进程的 init 系统，可代替 System V 与 BSD 风格等 init 程序
    systemctl
apt install -y openjdk-8-jdk
apt install -y mysql-server-8.0
apt install -y git

apt remove <package_name>                       卸载，保留配置文件
apt purge <package_name>                        卸载，清除配置文件
apt autoremove                                  删除为满足其它包而自动安装的且现在不再需要的软件包
```
---
## [dpkg](http://manpages.ubuntu.com/manpages/jammy/man1/dpkg.1.html)

---