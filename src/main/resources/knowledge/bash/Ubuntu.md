# Ubuntu

---
## 命令
```bash
# 查看系统版本号
cat /etc/issue

# 查看当前系统时间
date -R

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
wget --version
# c/c++
apt-get isntall build-essential
apt-get install wget
apt-get isntall vim
apt-get isntall iptables
apt-get isntall net-tools

# 卸载
apt-get remove software
apt-get autoremove
```
---