# DOS (Disk Operating System)
```
help                                    显示原生命令

    /?                                  显示帮助信息

call                                    从批处理程序调用另一个批处理程序

choice                                  允许用户从选择列表选择一个项目并返回所选项目的索引

cls                                     清除屏幕

copy                                    将一份或多份文件复制到另一个位置
XCOPY                                   复制文件和目录树

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

set                                     显示、设置或删除 cmd.exe 环境变量
    /a expression                       指定等号右边的字符串为被评估的数字表达式                    ?

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