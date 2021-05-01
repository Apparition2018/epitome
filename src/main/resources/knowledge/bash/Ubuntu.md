# Ubuntu

---
## 命令
```bash
# 查看系统版本号
cat /etc/issue

# apt-get简介：https://www.cnblogs.com/downey-blog/p/10473893.html
# apt-get update 和 apt-get upgrade 的区别：https://www.cnblogs.com/zhaodehua/articles/11375822.html
# 更新软件源中的所有软件列表
apt-get update
# 更新软件
apt-get upgrade
# 更新系统版本
apt-get dist-upgrade

# Time Zone Database
apt-get install tzdata
    # 修改时区
    tzselect
# c/c++
apt-get isntall build-essential
apt-get install wget
apt-get isntall vim
apt-get isntall iptables
apt-get isntall net-tools
apt-get install curl
apt-get install systemctl
apt-get install daemon

# 安装 docker 三种方式
apt-get install -y docker.io
curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun
curl -sSL https://get.daocloud.io/docker | sh
# 卸载 docker
apt-get autoremove docker docker-ce docker-engine docker.io containerd runc
dpkg -l | grep docker
apt-get autoremove docker-ce-*
rm -rf /etc/systemd/system/docker.service.d
rm -rf /var/lib/docker

# 卸载
apt-get remove software
apt-get autoremove
```
---