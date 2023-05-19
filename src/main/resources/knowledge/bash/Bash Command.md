# Bash Command

---
## 参考网站
1. [Linux 命令大全 | 菜鸟教程](https://www.runoob.com/linux/linux-command-manual.html)
2. [Linux 命令大全 | 易百教程](https://www.yiibai.com/linux)
3. [Linux man pages online](https://linux.die.net/man/)
4. [Linux man pages online](https://man7.org/linux/man-pages/)
5. [Linux/Basic commands](https://en.wikiversity.org/wiki/Linux/Basic_commands)
---
## 问题
1. [Linux 常见命令缩写](https://www.cnblogs.com/h2mm/p/6691309.html)
2. [Linux 下命令行 curl 的 10 种常见用法示例](https://www.cnblogs.com/zxqblogrecord/p/8900219.html)
3. [Linux 日志常用查看技巧](https://blog.csdn.net/zw235345721/article/details/70792195)
---
## 其它
    --help                                                              显示帮助信息
    &                                                                   后台执行
    |                                                                   管道命令
---
## 进程管理 (Process Management)
    ps                              process status                      显示进程状态
    ps -ef                                                              System V 风格
            -e                                                          所有进程
            -f                                                          full-format listing
    ps aux                                                              BSD-style
            u                                                           user-oriented 格式
    top                                                                 显示系统摘要信息和内核管理的任务列表
        -p                                                              指定进程 ID
    lsof                            list open files
        -i                                                              Internet address 匹配
        -P                                                              禁止端口号转换为端口名
        -n                                                              禁止 IP 转换为 host names 
    kill                                                                终止进程
        -9                                                              强制杀死进程
        -15                                                             正常退出进程
---
## 文件系统 (Filesystem)
    chgrp                           change group                        更改组所有权
    chown                           change ownership                    更改文件所有权
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
    pwd                             print work directory                打印工作目录
    cd                              change directory                    更改工作目录
    ls                              list                                列出目录内容
        -l                                                              long listing 格式
    mkdir                           make directory                      创建目录
        -p, --parents                                                   创建目录，如需要创建父目录
    touch                                                               创建文件和更改文件的访问和修改时间
    file                                                                确定文件类型
    find                                                                搜索文件
        -iname                                                          文件名，忽略大小写
    cat                             concatenate                         连接并打印文件
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
    mv                              move                                移动文件
    cp                              copy                                复制文件
    cmp                             compare                             对比两个文件
    rm                              remove                              删除文件或目录
        -r, -R, --recursive                                             递归删除
        -f, --force                                                     忽略不存在的文件，不提示
        -i                          interactive                         交互式删除
    rmdir                           remove directory                    删除空目录
        --ignore-fail-on-non-empty                                      非空时忽略失败
    dd                              disk dump                           转换和复制文件
    df                              disk free                           报告磁盘空间使用情况
    du                              disk usage                          估计文件空间使用情况
    ln                              link                                在文件之间建立链接
        -s, -symbolic                                                   符号链接
    mount                                                               挂载文件
    umount                          unmount                             取消挂载
---
## 杂项 (Misc)
    bc                              basic calculator                    任意精度算术语言，在 linux 下通常用作计算器
    cal                                                                 打印日历
    date                            date '+%Y-%m-%d %H:%M:%S'           显示或设定系统的日期与时间
    crontab                                                             定时任务
    export                                                              设置或显示环境变量
    fc
    man                             manual                              手册指南                                     
---
## 文件管理
    nl                                                                  显示文件内容，添加行号
    more                                                                显示文件内容，以一页一页的形式
    head                                                                显示文件头部内容
        -n                                                              显示的行数
    tail [FILE]                                                         显示文件尾部内容
    od                              octal dump                          以八进制显示文件内容
    cut                                                                 切割文件并显示
    tee                                                                 读取标准输入的数据，并将其内容输出成文件
    paste                                                               合并文件的列
        -r                          --recursive                         递归复制
    scp                             secure copy                         Linux 之间复制文件或目录
    chattr                          change attribute                    更改文件属性
    whereis                                                             查找文件
    which                                                               查找文件
    locate                                                              查找符合条件的文件
    diff                            difference                          比较两个文件的差异
    umask                           user's mask                         指定建立文件时预设的权限掩码
    mc                              midnight commander                  提供一个菜单式的文件管理程序
---
## 文档编辑
    grep PATTERN [FILE]             global regular expression print     查找文件里符合条件的字符串
        -i, --ignore-case                                               不区分大小写
        -n, --line-number                                               显示行号
        --color                                                         红色字体
    egrep                           extended grep                       查找文件里符合条件的字符串 (grep -e)
    fgrep                           fixed grep                          查找文件里符合条件的字符串 (grep -f)
    uniq                            unique                              删除重复的行列
    tr                              translate                           转换大小写或删除字符
    sort                                                                排序文本
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
    wc                              word count                          统计字数
        -w                                                              统计字数
        -c                                                              统计字节数
        -m                                                              统计字符数
        -l                                                              统计行数
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
    hostname                                                            获取或设置主机名或 NIS 域名
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
    ssh                                                                 远程连接
    tty                             teletypewriter                      打印连接到标准输入的终端的文件名
---
## 系统管理
    rsh                             remote shell                        登入远端 shell
    groupadd                                                            创建一个新的组
        -r                                                              建立系统账号
        -g                                                              指定用户所属的组
    groups                                                              打印用户所在组
    newgrp                                                              登录到新组
    adduser                                                             新增使用者帐号或更新预设的使用者资料
    su                              switch user                         切换用户
    sudo                            super user do                       以系统管理者的身份执行指令
    chsh                            change shell                        更改使用者 shell 设定
    ulimit                          user's limit                        控制 shell 程序的资源
    whoami                                                              显示自身用户名称
    id                                                                  显示用户 ID，以及所属群组 ID
    w                                                                   显示当前用户的信息
    finger                                                              显示其它用户的信息
    who                                                                 显示系统所有使用者信息
    whois                                                               查找并显示用户信息
    uname                           unix name                           显示系统信息
        -a                          --all                               显示全部信息
        -n                          --nodename                          显示主机名称
        -m                          --machine                           显示处理器架构
        -r                          --release                           显示操作系统的发行编号
        -s                          --sysname                           显示操作系统名称
    shutdown                                                            关闭系统
        -h                                                              关闭系统后停机
        -r                                                              关闭系统后重新开机
    reboot                                                              重启系统
    logout                                                              退出系统
    exit                                                                退出目前的 shell
---
## 系统设置
    ssh-keygen                                                          生成、管理和转换 SSH 的省份验证密钥
        -t rsa|dsa|…                                                    指定密钥类型
        -C                                                              添加注释
    ssh-agent                                                           一个程序用于保存私钥（用于公钥身份验证）
        bash                                                            启动 ssh-agent
    ssh-add                                                             将私钥添加到 ssh-agent
        -L                                                              列出公钥
    uptime                                                              显示系统运行时间或系统的平均负载
    clear                                                               清除屏幕
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
    passwd                          password                            更改用户密码
    chkconfig                                                           检查和设置系统各种服务
        --add                                                           增加系统服务
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
    tar                             tape archive                        备份文件
        -z                          --gzip                              通过 gzip指令处理备份文件
        -c                          --create                            建立新的备份文件
        -C | --directory                                                解压到指定目录
        -x                          --extract                           从备份文件中还原文件
        -v                          --verbose                           显示指定执行过程
        -f                          --file                              指定备份文件
    gzip                                                                压缩文件
    gunzip                                                              解压 gzip 文件
    gzcat                                                               查看 gzip 文件
---
## 其它
    xargs                           extended arguments                  给命令传递参数的一个过滤器，也是组合多个命令的一个工具
    printf                          print formatted
    echo                                                                输出字符串
---
