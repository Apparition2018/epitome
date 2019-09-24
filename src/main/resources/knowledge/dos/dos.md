# DOS (Disk Operating System)
```
help                                    显示原生命令

    /?                                  显示帮助信息

cls                                     清除屏幕

findstr                                 在文件中寻找字符串

ipconfig                                显示绑定到 TCP/IP 的每个适配器的 IP 地址、子网掩码和默认网关

netstat                                 显示协议统计信息和当前 TCP/IP 网络连接
    /a                                  显示所有连接和侦听端口
    /n                                  以数字形式显示地址和端口号
    /o                                  显示拥有的与每个连接关联的进程 ID
    /p proto                            显示 proto 指定的协议的连接
    /s                                  显示每个协议的统计信息
    /v                                  显示正在进行的工作 ???

tasklist                                显示在本地或远程机器上当前运行的进程列表

taskkill                                按照进程 ID (PID) 或映像名称终止任务
    /f                                  指定强制终止进程
    /im     imagename                   指定要终止的进程的映像名称
    /pid    processid                   指定要终止的进程的 PID
    /t                                  终止指定的进程和由它启用的子进程

shutdown
    /s                                  关闭计算机
    /t xxx                              设置 xxx 秒后关闭计算机
    /a                                  取消关闭计算机
    /r                                  关闭并重启计算机
    /h                                  休眠
```