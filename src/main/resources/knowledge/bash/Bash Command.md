# Bash abbr
---
## 参考网站
1. [Linux 命令大全](https://www.runoob.com/linux/linux-command-manual.html)
2. [Linux 命令大全教程](https://www.yiibai.com/linux)
3. [Linux 常见命令缩写](https://www.cnblogs.com/xiaochina/p/6432903.html)
---
## 问题
1. [Linux 下如何查看系统和内核版本](https://www.cnblogs.com/lcword/p/5680731.html)
2. [Linux 命令之 ssh 基本用法](https://blog.csdn.net/gaoxilong526/article/details/79757285)
3. [Linux 下命令行 curl 的 10 种常见用法示例](https://www.cnblogs.com/zxqblogrecord/p/8900219.html)
---
## 文件管理
```
cat         concatenate                     连锁                                ???
cp          copy                            复制文件或目录

scp         secure copy                     Linux 之间复制文件或目录
    -r      --recursive                     递归复制
    
mv          move files                      更改文件或目录名字或位置
chmod       change mode                     更改文件或目录访问权限
chgrp       change group                    更改文件或目录群组
chown       change owner                    更改文件件拥有者
touch                                       修改文件或目录的时间属性

rm          remove                          删除文件或目录
    -f      --force                         不存在的文件也不给出提示
    -i      --interactive                   交互式删除
    -r      --recursive                     递归删除
    
ln          link                            链接                                ???
```
---
## 文档编辑
```
tail                                        查看文件内容
grep        global regular expression print 查找文件里符合条件的字符串
```
---
## 磁盘管理
```
cd          change directory                切换目录
ls          list                            显示目录下内容
pwd         print work directory            显示当前目录
df          disk free                       显示磁盘可用空间
du          disk usage                      显示目录或文件所占空间
mkdir       make directory                  创建目录
rmdir       remove directory                删除目录
umount      unmount                         卸载文件系统
```
---
## 磁盘维护
```
mkfs        make file system                在特定的分区上建立 linux 文件系统   ???
```
---
## 系统管理
```
su          switch user                     切换用户
ps          process status                  显示进程状态
uname       unix name                       显示系统信息
logout                                      退出系统
```
---
## 系统设置
```
clear                                       清楚屏幕
rpm         redhat package manager          红帽子打包管理器
lsmod       list modules                    显示已载入模块
insmod      install modules                 载入模块                            ???
```
---
## 系统任务
```
fg          foreground                      将后台中的任务调至前台继续运行
bg          background                      将一个在后台暂停的命令，变成继续执行
```
---
## 其它
```
man         manual                          手册                                ???
```