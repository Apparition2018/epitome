# Linux

---
## 参考网站
1. [Linux vi/vim | 菜鸟教程](https://www.runoob.com/linux/linux-vim.html)
2. [linux yum 命令 | 菜鸟教程](https://www.runoob.com/linux/linux-yum.html)
---
## 问题
1. [详解Linux中/etc/passwd文件](https://www.linuxprobe.com/explain-etc-passwd.html)
2. [命令详解 ./configure、make、make install](https://www.cnblogs.com/tinywan/p/7230039.html)
---
## vim
>### 命令模式
>```
>i                        切换到输入模式，以输入字符
>x                        删除当前光标所在处的字符
>:                        切换到底线命令模式，以在最低一行输入命令
>GG / Shift + G           跳转到首行/尾行
>/                        向下搜索关键字，n 下一个
>?                        向上搜索关键字，n 上一个
>```
>### 输入模式
>```
>HOME / END               跳转到行首/行尾
>Page Up / Page Down      上/下翻页
>Insert                   切换光标为输入/替换模式，光标将变成竖线/下划线
>ESC                      退出输入模式，切换到命令模式
>```
>### 底线命令模式
>```
>q                        退出程序
>q!                       不保存强制退出
>w                        保存文件
>wq                       存储后退出
>set nu                   显示行号
>```
---
## [apt-get](https://www.cnblogs.com/downey-blog/p/10473893.html)
- [apt-get update 和 apt-get upgrade 的区别](https://www.cnblogs.com/zhaodehua/articles/11375822.html)
- 用法：
    - apt-get [options] command
    - apt-get [options] install | remove pkg1 [pkg2...]
    - apt-get [options] source pgk1 [pkg2...]
```
apt-get update                                  更新软件源中的所有软件列表
apt-get upgrade                                 更新软件
apt-get dist-upgrade                            更新系统版本

apt-get install -y tzdata                       Time Zone Database
    tzselect                                    修改时区
apt-get isntall -y build-essential              c/c++
apt-get install -y openssh-server
apt-get install -y wget
apt-get install -y vim
apt-get install -y iptables
apt-get install -y net-tools
apt-get install -y curl
apt-get install -y systemctl
apt-get install -y daemon
apt-get install -y openjdk-8-jdk

apt-get remove pkg                              卸载
apt-get autoremove                              自动卸载
```
---
## yum
- yum [options] [command] [packages ...]
```
options:
    -h          help                            帮助
    -y          yes                             当安装过程提示选择，默认全部选 yes
    -q                                          不显示安装的过程
command:
    list                                        列出所有可安装的软件
    search <package_name>                       查找指定软件包
    install <package_name>                      安装指定软件
    check-update                                列出所有可更新的软件
    update                                      更新所有软件
    update <package_name>                       更新指定软件
    remove <package_name>                       删除指定软件
    clean
        packages                                清除缓存目录下的软件包
        headers                                 清除缓存目录下的 headers
        oldheaders                              清除缓存目录下的旧 headers
        all                                     清除缓存目录下的软件包和旧 headers
    makecache
```
---