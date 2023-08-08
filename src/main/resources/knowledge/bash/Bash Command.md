# Bash Command

---
## 参考网站
1. [Linux 命令大全 | 菜鸟教程](https://www.runoob.com/linux/linux-command-manual.html)
2. [Linux 命令大全 | 易百教程](https://www.yiibai.com/linux)
3. [Linux man pages online](https://linux.die.net/man/)
4. [Linux man pages online](https://man7.org/linux/man-pages/)
5. [CSE 390 Bash Command Reference](https://courses.cs.washington.edu/courses/cse390a/14au/bash.html)
6. [Linux Command Reference](https://personales.unican.es/corcuerp/Linux/commands/Bash%20Command%20Reference.html)
## 问题
1. [Linux 常见命令缩写](https://www.cnblogs.com/h2mm/p/6691309.html)
2. [Linux 下命令行 curl 的 10 种常见用法示例](https://www.cnblogs.com/zxqblogrecord/p/8900219.html)
3. [Linux 日志常用查看技巧](https://blog.csdn.net/zw235345721/article/details/70792195)
---
## 其它
    --help                                                              显示帮助信息
    |                                                                   管道命令
---
## 基础 shell (basic shell)
    clear                                                               清除终端所有输出文本
    exit / logout                                                       退出 shell
    alias, unalias                                                      给一个命令起一个别名
    history                                                             显示历史输入命令
---
## 目录 (directories)
    ls                              list                                列出目录内容
        -l                                                              long listing 格式
    pwd                             print work directory                打印当前工作目录
    cd                              change directory                    更改工作目录
    mkdir                           make directory                      创建目录
        -p, --parents                                                   创建目录，如需要创建父目录
    rmdir                           remove directory                    删除空目录
        --ignore-fail-on-non-empty                                      非空时忽略失败
---
## 文件操作 (file operations)
    cp                              copy                                复制文件或目录
    mv                              move                                移动文件或目录
    rm                              remove                              删除文件或目录
        -r, -R, --recursive                                             递归删除
        -f, --force                                                     忽略不存在的文件，不提示
        -i                          interactive                         交互式删除
    touch                                                               创建文件和更改文件的访问和修改时间
---
## 文件审查 (file examination)
    cat                             concatenate                         连接并打印文件内容
        /etc/alternatives/                                              http://www.manongjc.com/detail/17-aeorawzcehpuatd.html
        /etc/group                                                      用户组
        /etc/hostname                                                   主机名
        /etc/hosts                                                      IP 和主机名映射
        /etc/init.d/                                                    /etc/rc.d/init.d 软链接，https://blog.csdn.net/liaowenxiong/article/details/117083906
        /etc/localtime                                                  本地时间
        /etc/passwd                                                     用户，https://www.linuxprobe.com/explain-etc-passwd.html
        /etc/profile                                                    环境变量
        /etc/sysconfig/iptables                                         防火墙
        /etc/sysconfig/network-scripts/                                 网络脚本
        /etc/selinux/                                                   强制访问控制系统
        /etc/timezone                                                   时区
    more / less                                                         打印文件内容，一次一页
    head / tail                                                         打印文件的前/后十行内容
        -n                                                              打印文件的前/后n行内容
    wc                              word count
        -w                                                              打印字数
        -c                                                              打印字节数
        -m                                                              打印字符数
        -l                                                              打印行数
    du                              disk usage                          递归目录文件的磁盘使用情况
    diff                            difference                          打印两个文件的差异
---
## 文件权限 (file permissions)
    chmod                           change modes                        更改文件模式/属性/权限，https://mp.weixin.qq.com/s/2OyR1GlQLQcQkbMVQbIRjg
        -R                                                              递归处理
        u                           user                                文件所有者
        g                           group                               文件所有者所在组
        o                           others                              所有其他用户
        a                           all                                 所有用户，相当于 ugo
        +                                                               增加权限
        -                                                               去除权限
        =
        r                           read                                可读
        w                           write                               可写
        x                           execute                             可执行
        0                           ---             000                 无
        1                           --x             001                 可执行
        2                           -w-             010                 可写
        3                           -wx             011                 可写+可执行
        4                           r--             100                 可读
        5                           r-x             101                 可读+可执行
        6                           rw-             110                 可读+可写
        7                           rwx             110                 可读+可写+可执行
    chown                           change ownership                    更改文件所有权
    chgrp                           change group                        更改组所有权
    umask                           user's mask                         指定建立文件时预设的权限掩码
---
## 搜索和排序 (searching and sorting)
    grep PATTERN [FILE]             global regular expression print     查找与给定 PATTERN 匹配的行
        -i, --ignore-case                                               不区分大小写
        -n, --line-number                                               显示行号
        --color                                                         红色字体
        -A <num>                                                        在匹配行之后再打印 num 行
    sort                                                                输出排序后的文件内容
    uniq                            unique                              删除重复的行
    find                                                                在给定目录中查找文件
        -iname                                                          文件名，忽略大小写
    xargs                           extended arguments                  给命令传递参数的一个过滤器，也是组合多个命令的一个工具
    locate                                                              按名称查找文件
    which                                                               显示命令或文件的完整路径
---
## 压缩 (compression)
    tar                             tape archive                        存档/解存档
        -z, --gzip                                                      通过 gzip 过滤存档
        -c, --create                                                    创建新的存档
        -C, --directory=DIR                                             更改目录 DIR
        -x, --extract, --get                                            从存档中提取文件
        -v, --verbose                                                   显示执行过程
        -f, --file=ARCHIVE                                              使用归档文件或设备 ARCHIVE
    gzip / gunzip                                                       压缩/解压缩
---
## 系统信息 (system information
    date                            date '+%Y-%m-%d %H:%M:%S'           打印或设置系统日期时间
    cal                                                                 显示日历
    uname                           unix name                           打印系统信息
        -a, --all                                                       打印全部信息
        -n, --nodename                                                  打印主机名
        -m, --machine                                                   打印处理器架构
        -r, --kernel-release                                            打印内核版本
        -s, --kernel-name                                               打印内核名称
    time                                                                测量命令或程序运行时间
---
## 进程管理 (process management)
    ps                              process status                      显示进程状态
    ps -ef                                                              System V 风格
            -e                                                          所有进程
            -f                                                          full-format listing
    ps aux                                                              BSD-style
            u                                                           user-oriented 格式
    top                                                                 显示当前内核管理的任务
        -p                                                              指定进程 ID
    lsof                            list open files
        -i                                                              Internet address 匹配
        -P                                                              禁止端口号转换为端口名
        -n                                                              禁止 IP 转换为 host names 
    kill                                                                杀死进程
        -1, -HUP                                                        重新加载进程
        -9, -KILL                                                       强制杀死进程
        -15, -TERM                                                      正常退出进程
    killall                                                             按名称杀死进程
    &                                                                   当 & 放在命令末尾时，该命令在后台运行
---
## 用户和组 (users and groups)
    whoami                                                              打印与当前有效用户 ID 关联的用户名
    passwd                          password                            更改用户密码
    groups                                                              打印用户所在组
    sudo                            super user do                       以超级用户身份执行命令
    su                              switch user                         切换用户
---
## 多用户环境 (multi-user environments)
    hostname                                                            显示或设置主机名
    w                                                                   显示当前登录用户
    write                                                               向其他登录用户发送信息，特定
    wall                                                                向其他登录用户发送信息，广播
## 网络 (network)
    ssh                             OpenSSH SSH client                  远程登录程序
    sftp                            secure file transfer program        安全文件传输程序
    scp                             secure copy                         远程文件复制程序

---
##
    nohup                           no hang up                          运行命令，忽略 hangup signals
        nohup java -jar thymeleaf-8081.jar > thymeleaf-8081.log 2>&1 &
        nohup java -jar thymeleaf-8082.jar > thymeleaf-8082.log 2>&1 &
        nohup java -jar thymeleaf-8083.jar > thymeleaf-8083.log 2>&1 &
---
## 文件系统 (Filesystem)
    file                                                                确定文件类型
    cmp                             compare                             对比两个文件
    dd                              disk dump                           转换和复制文件
    df                              disk free                           报告磁盘空间使用情况
    ln                              link                                在文件之间建立链接
        -s, -symbolic                                                   符号链接
    mount                                                               挂载文件
    umount                          unmount                             取消挂载
---
## 杂项 (Misc)
    bc                              basic calculator                    任意精度算术语言，在 linux 下通常用作计算器
    crontab                                                             定时任务
    export                                                              设置或显示环境变量
    fc
    man                             manual                              手册指南                                     
---
## 文件管理
    nl                                                                  显示文件内容，添加行号
    od                              octal dump                          以八进制显示文件内容
    cut                                                                 切割文件并显示
    tee                                                                 读取标准输入的数据，并将其内容输出成文件
    paste                                                               合并文件的列
        -r                          --recursive                         递归复制
    chattr                          change attribute                    更改文件属性
    whereis                                                             查找文件
    mc                              midnight commander                  提供一个菜单式的文件管理程序
---
## 文档编辑
    egrep                           extended grep                       查找文件里符合条件的字符串 (grep -e)
    fgrep                           fixed grep                          查找文件里符合条件的字符串 (grep -f)
    tr                              translate                           转换大小写或删除字符
    ed                              editor                              编辑文本
    joe                             joe's own editor                    编辑文本
    pico                            pine's message composition editor   编辑文本
    awk                                                                 处理文本
    sed {scrip} [input-file]        stream  editor                      使用脚本处理文本
    sed "s/xyz/XYZ/g" file_name                                         全局取代xyz为XYZ
        a                           add                                 新增
        c                           replace                             替换
        d                           delete                              删除
        i                           insert                              插入
        p                           print                               打印
        s                           substitute                          取代
    fmt                             format                              格式化文本
    col                                                                 过滤控制字符
    join                                                                将两个文件中，指定栏位内容相同的行连接起来
    split                                                               将一个文件分割成数个文件
---
## 文件传输
    lpr                             line print                          将一个或多个文件放入打印队列等待打印
    lpq                             line print queue                    查看打印队列
    lprm                                                                将一个工作由打印机贮列中移除
---
## 磁盘管理
        -a                          --all                               显示目录下内容，包括以.开头的隐藏文件
        -h                                                              以合适的单位显示文件大小
        -i                                                              显示目录下内容及其详细信息
        -l                          ll                                  除文件名称外，亦将文件型态、权限、拥有者、文件大小等资讯详细列出
        -r                                                              按时间倒序显示
        -t                                                              按时间顺序显示
    tree                                                                以树状图显示目录内容
    quota                                                               显示磁盘已使用的空间与限制
    stat                                                                显示 inode 内容
        -p                          --parents                           创建多级目录
---
## 磁盘维护
    mkfs                            make file system                    建立 Linux 文件系统
    mke2fs                                                              建立 ext2 文件系统
    mkswap                                                              设置交换区
    swapon                                                              激活 Linux 系统中的交换空间
    fdformat                                                            对指定的软碟机装置进行低阶格式化
    fsck                            file system check                   检查与修复 Linux 档案系统
    badblocks                                                           检查磁盘装置中损坏的区块
---
## 网络通信
    ping                            packet internet grouper             像网络主机发送 ICMP ECHO_REQUEST
    # net-tools vs iproute2：https://www.cnblogs.com/liyuanhong/p/15960954.html
    ifconfig --help                 ip help
    ifconfig                        ip link
    ifconfig -a                     ip addr show
    route add                       ip route add
    route del                       ip route del
    route -n                        ip route show
    netstat -r                      ip route
    netstat -i                      ip -s link
    netstat -g                      ip maddr
    netstat                         ss
    netstat                                                             显示网络信息和统计信息
        -t, --tcp                                                       显示 TCP 传输协议的连线状况
        -u, --udp                                                       显示 UDP 传输协议的连线状况
        -n, --numeric                                                   直接使用 IP 地址，而不通过域名服务器
        -l, --listening                                                 显示监控中的服务器的 Socket
        -p, --program                                                   显示正在使用 Socket 的程序识别码和程序名称
    wget                                                                非交互网络下载器
    telnet                          telecommunications network          远程连接
    tty                             teletypewriter                      打印连接到标准输入的终端的文件名
---
## 系统管理
    rsh                             remote shell                        登入远端 shell
    groupadd                                                            创建一个新的组
        -r                                                              建立系统账号
        -g                                                              指定用户所属的组
    newgrp                                                              登录到新组
    adduser                                                             新增使用者帐号或更新预设的使用者资料
    chsh                            change shell                        更改使用者 shell 设定
    ulimit                          user's limit                        控制 shell 程序的资源
    id                                                                  显示用户 ID，以及所属群组 ID
    finger                                                              显示其它用户的信息
    who                                                                 显示系统所有使用者信息
    whois                                                               查找并显示用户信息
    shutdown                                                            关闭系统
        -h                                                              关闭系统后停机
        -r                                                              关闭系统后重新开机
    reboot                                                              重启系统
---
## 系统设置
    ssh-keygen                                                          生成、管理和转换 SSH 的身份验证密钥
        -t rsa|dsa|…                                                    指定密钥类型
        -C                                                              添加注释
    ssh-agent                                                           一个程序用于保存私钥（用于公钥身份验证）
        bash                                                            启动 ssh-agent
    ssh-add                                                             将私钥添加到 ssh-agent
        -L                                                              列出公钥
    uptime                                                              显示系统运行时间或系统的平均负载
    useradd                                                             添加用户
        -d                                                              指定用户登录目录 HOME_DIR，默认 BASE_DIR + LOGIN name
        -s                                                              指定用户使用的 shell
        -g                                                              指定用户所属用户组
        -M                                                              不创建 HOME_DIR
    usermod                                                             修改用户
        -a, --append                                                    将用户添加到组，仅与 -G 一起使用
        -G, --groups                                                    组列表，用逗号分隔
    userdel                                                             删除用户
        -r                                                              删除用户登入目录以及目录中所有文件
        -f                                                              强制删除用户
    chkconfig                                                           检查和设置系统各种服务
        --add                                                           增加服务
        --del                                                           移除服务
        --list                                                          列出服务的情况
    eval                            evaluate                            读取一连串的参数，然后再依参数本身的特性来执行
    rpm                             redhat package manager              红帽子打包管理器
        -a, -all                                                        查询所有套件
        -e, --erase                                                     删除指定套件
        --nodeps                                                        不进行依赖项检查
        -i, --install                                                   安装指定的套件档
        -U, --upgrade                                                   升级指定的套件档
        -v                                                              显示指令执行过程
        -h                                                              套件安装时列出标记
        -q                                                              使用询问模式，当遇到任何问题时，会先询问用户
    yum                             yellow dog updater, modified        Shell 前端软件包管理器
        list available                                                  列出可供安装的包
        list updates                                                    列出可更新的包
        list installd                                                   列出已安装的包
        search                                                          搜索包
        install                                                         安装包
        check-update                                                    列出可更新的软件
        update                                                          更新所有或指定包
        remove                                                          删除包
        clean packages                                                  消除缓存包
        clean headers                                                   清除 header 文件
        clean all                                                       清除所有
    lsmod                           list modules                        显示已载入模块
    insmod                          install modules                     载入模块
    rmmod                           remove modules                      删除模块
---
## 备份压缩
    gzcat                                                               查看 gzip 文件
---
## 其它
    printf                          print formatted
    echo                                                                输出字符串
---
