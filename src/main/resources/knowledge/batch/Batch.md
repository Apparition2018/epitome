# Batch
---
## 参考网站
1. [批处理教程™](https://www.yiibai.com/batch_script)
2. [DOS命令学习手册_w3cschool](https://www.w3cschool.cn/dosmlxxsc1/)
3. [批处理入门手册_w3cschool](https://www.w3cschool.cn/pclrmsc/)
4. [批处理之家](http://www.bathome.net/)
---
## DOS (Disk Operating System)
```
help                                    显示原生命令
    /?                                  显示帮助信息，help commnad 相当于 command /?

call                                    从批处理程序调用另一个批处理程序

choice                                  允许用户从选择列表选择一个项目并返回所选项目的索引

cls                                     清除屏幕

color                                   设置默认的控制台前景和背景颜色

copy                                    将一份或多份文件复制到另一个位置
xcopy                                   复制文件和目录树

del                                     删除一个或多个文件
    /p                                  删除每一个文件之前提示确认
    /f                                  强制删除只读文件
    /s                                  删除所有子目录中的指定的文件
    /q                                  安静模式。删除全局通配符时，不要求确认

dir                                     显示目录中的文件和子目录列表

echo                                    显示消息，或者启用或关闭命令回显
    [on | off]                          命令回显

find                                    在文件中搜索字符串
    /v                                  只打印不包含匹配的行
    /c                                  仅显示包含字符串的行                                          ?
    /n                                  在匹配的每行前打印行数
    /i                                  指定搜索不分大小写

findstr                                 在文件中寻找字符串

for                                     对一组文件中的每一个文件执行某个特定命令

format                                  格式化磁盘以供 Windows 使用
    /q                                  执行快速格式化

GOTO label                              定向到批处理程序中带标签的行

if [not] ERRORLEVEL number              如果最后运行的程序返回一个等于或大于指定数字的退出代码，指定条件为 true
if [not] string1=string2                如果指定的文字字符串匹配，指定条件为 true
if [not] EXIST filename                 如果指定的文件名存在，指定条件为 true

ipconfig                                显示绑定到 TCP/IP 的每个适配器的 IP 地址、子网掩码和默认网关

md                                      创建目录

mkllink                                 创建符号链接
    /D                                  创建目录符号链接。默认为文件符号链接
    /J                                  创建目录链接

mode                                    配置系统设备

more                                    逐屏显示输出

netstat                                 显示协议统计信息和当前 TCP/IP 网络连接
    /a                                  显示所有连接和侦听端口
    /n                                  以数字形式显示地址和端口号
    /o                                  显示拥有的与每个连接关联的进程 ID
    /p proto                            显示 proto 指定的协议的连接
    /s                                  显示每个协议的统计信息
    /v                                  显示正在进行的工作 ???

pause                                   暂停批处理程序，并显示："请按任意键继续..."

rem                                     在批处理文件或 CONFIG.SYS 里加上注解或说明

ren                                     重命名文件

sc                                      显示或配置服务<后台处理>
    create                              在注册表和服务数据库中创建服务项                
    delete                              从注册表删除服务项                              

set                                     显示、设置或删除 cmd.exe 环境变量
    /a expression                       指定等号右边的字符串为被评估的数字表达式

setlocal                                开始批处理文件中环境改动的本地化操作

shift                                   更改批处理文件中可替换参数的位置

start                                   启动单独的窗口以运行指定的程序或命令

shutdown
    /s                                  关闭计算机
    /t xxx                              将关闭前的超时时间设置为 xxx 秒
    /a                                  中止系统关闭
    /r                                  完全关闭并重启计算机
    /h                                  休眠本地计算机

tasklist                                显示在本地或远程机器上当前运行的进程列表

taskkill                                按照进程 ID (PID) 或映像名称终止任务
    /f                                  指定强制终止进程
    /im     imagename                   指定要终止的进程的映像名称
    /pid    processid                   指定要终止的进程的 PID
    /t                                  终止指定的进程和由它启用的子进程

tree                                    以图形显示驱动器或路径的文件夹结构
    /f                                  显示每个文件夹中文件的名称

type                                    显示文本文件的内容
```
---
## [符号](https://www.cnblogs.com/liangxiaofeng/p/5028727.html)
    @                                   使命令也适用于自己
    ^                                   特殊符号的前导字符
    :                                   标签
    ::                                  注释
    <                                   将其后面的文件的内容作为其前面命令的输入
    >                                   输出重定向命令，清空原文件
    >>                                  输出重定向命令，追加
    &                                   顺序执行多条命令
    &&                                  顺序执行多条命令，当遇到执行出错的命令后不执行后面的命令
    ||                                  顺序执行多条命令，当遇到执行正确的命令后不执行后面的命令
    |                                   管道命令，把前一命令的输出当后一命令的输入
    %v                                  命令行参数
    %ev%                                环境变量
---
## 控制结构

---

## 字符串
>### [截取](https://www.jb51.net/article/52744.htm)
>```
>    @echo off
>    set info=abcde
>    echo %info:~0,-2%
>    
>    结果：abc
>```
---
## 其它
    setlocal enabledelayedexpansion         延迟变量：https://www.jb51.net/article/29323.htm
    errorlevel                              上一条命令的返回值：https://blog.csdn.net/qq_33811402/article/details/51774287
---
## 例子
>### [%~dp0](https://www.cnblogs.com/cnpirate/p/5282324.html) 和 复制
>```
>    @echo off
>    set BASE_DIR=%~dp0
>    set desk=%userprofile%\Desktop
>    copy %BASE_DIR%\Batch.md %desk%
>    echo Please press any key to delete... && pause>nul
>    del %desk%\Batch.md
>```
>### call 和 >
>```
>    @echo off
>    call other.bat 999
>    echo Please press any key to delete... && pause>nul
>    del %userprofile%\Desktop\arg.txt
>    
>    --- other.bat ---
>    SET /A arg=%1
>    echo %arg% > %userprofile%\Desktop\arg.txt
>```
---