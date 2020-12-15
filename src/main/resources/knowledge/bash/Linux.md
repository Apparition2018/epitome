# Linux

---
## 参考网站
1. [Linux vi/vim | 菜鸟教程](https://www.runoob.com/linux/linux-vim.html)
2. [linux yum 命令 | 菜鸟教程](https://www.runoob.com/linux/linux-yum.html)
---
## 问题
1. [详解Linux中/etc/passwd文件](https://www.linuxprobe.com/explain-etc-passwd.html)
---
## vim
>### 命令模式
>```
>i                       切换到输入模式，以输入字符
>x                       删除当前光标所在处的字符
>:                       切换到底线命令模式，以在最低一行输入命令
>```
>---
>### 输入模式
>```
>HOME / END              移动光标到行首/行尾
>Page Up / Page Down     上/下翻页
>Insert                  切换光标为输入/替换模式，光标将变成竖线/下划线
>ESC                     退出输入模式，切换到命令模式
>```
>---
>### 底线命令模式
>```
>q                       退出程序
>q!                      不保存强制退出
>w                       保存文件
>wq                      存储后退出
>set nu                  显示行号
>```
---
## yum
- [options] [command] [packages ...]
```
options:
    -h          help                                帮助
    -y          yes                                 当安装过程提示选择，默认全部选 yes
    -q                                              不显示安装的过程
command:
    list                                            列出所有可安装的软件
    search <package_name>                           查找指定软件包
    install <package_name>                          安装指定软件
    check-update                                    列出所有可更新的软件
    update                                          更新所有软件
    update <package_name>                           更新指定软件
    remove <package_name>                           删除指定软件
    clean
        packages                                    清除缓存目录下的软件包
        headers                                     清除缓存目录下的 headers
        oldheaders                                  清除缓存目录下的旧 headers
        all                                         清除缓存目录下的软件包和旧 headers
    makecache        
```
---