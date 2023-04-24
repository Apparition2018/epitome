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
## [apt](https://www.runoob.com/linux/linux-comm-apt.html)
- 语法： `apt [options] [command] [package ...]`
    - options：`-h`帮助, `-y`当安装过程提示选择，默认全部选 yes, `-q`不显示安装的过程
```
apt list                                        列出所有包
    --installed                                 列出所有已安装的包
    --all-versions                              列出所有已安装的包的版本信息
apt show <package_name>                         显示安装包的信息
apt search <keyword>                            查找软件
apt update                                      更新软件源中的所有软件列表
apt full-upgrade                                更新软件
apt upgrade                                     更新软件

apt install -y procps
    ps
apt install -y tzdata                           Time Zone Database
    tzselect                                    修改时区
apt install -y ufw                              防火墙
    ufw enable/disable/status
apt isntall -y build-essential                  c/c++
apt install -y openssh-client
apt install -y openssh-server
apt install -y wget
apt install -y vim
apt install -y iptables
apt install -y net-tools
apt install -y curl
apt install -y systemctl
apt install -y daemon
apt install -y openjdk-8-jdk
apt install -y openjdk-17-jdk

apt remove <package_name>                       卸载，保留配置
apt autoremove                                  自动卸载
apt purge <package_name>                        卸载
```
---
## [dpkg](http://manpages.ubuntu.com/manpages/jammy/man1/dpkg.1.html)

---