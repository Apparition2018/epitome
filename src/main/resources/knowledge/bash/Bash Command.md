# Bash abbr

---
## 参考网站
1. [Linux 命令大全 | 菜鸟教程](https://www.runoob.com/linux/linux-command-manual.html)
2. [Linux 命令教程 | 易百教程](https://www.yiibai.com/linux)
3. [Linux 常见命令缩写](https://www.cnblogs.com/h2mm/p/6691309.html)
4. [Linux 常用 bash 命令](https://www.cnblogs.com/shiyou00/p/10369321.html)
---
## 问题
1. [Linux 下如何查看系统和内核版本](https://www.cnblogs.com/lcword/p/5680731.html)
2. [Linux 命令之 ssh 基本用法](https://blog.csdn.net/gaoxilong526/article/details/79757285)
3. [Linux 下命令行 curl 的 10 种常见用法示例](https://www.cnblogs.com/zxqblogrecord/p/8900219.html)
---
## 其它
    --help                                          显示帮助信息
    &                                               后台执行
    |                                               管道命令
---
## 文件管理
    cat         concatenate                         显示文件内容
    nl                                              显示文件内容，添加行号
    more                                            显示文件内容，以一页一页的形式
    head                                            显示文件头部内容
        -n                                          显示的行数
    tail                                            显示文件尾部内容
    od          octal dump                          以八进制显示文件内容
    cut                                             切割文件并显示
    tee                                             读取标准输入的数据，并将其内容输出成文件
    paste                                           合并文件的列
    cp          copy                                复制文件或目录
        -r      --recursive                         递归复制
    scp         secure copy                         Linux 之间复制文件或目录
    mv          move                                更改文件或目录名字或位置
    chmod       change mode                         更改文件或目录访问权限
        -R                                          递归的方式逐个变更               
    chgrp       change group                        更改文件或目录群组
    chown       change owner                        更改文件拥有者
    chattr      change attribute                    更改文件属性
    touch                                           更改文件或目录的时间属性
    rm          remove                              删除文件或目录
        -f      --force                             不存在的文件也不给出提示
        -i      --interactive                       交互式删除
        -r      --recursive                         递归删除
    find                                            查找文件
    whereis                                         查找文件
    which                                           查找文件
    locate                                          查找符合条件的文件
    diff        difference                          比较两个文件的差异
    cmp         compare                             比较两个文件是否有差异
    umask       user's mask                         指定建立文件时预设的权限掩码
    ln          link                                链接
    mc          midnight commander                  提供一个菜单式的文件管理程序
---
## 文档编辑
    grep        global regular expression print     查找文件里符合条件的字符串
        -i                                          不区分大小写
        -n                                          显示行号
        --color                                     红色字体
    egrep       extended grep                       查找文件里符合条件的字符串 (grep -e)
    fgrep       fixed grep                          查找文件里符合条件的字符串 (grep -f)
    uniq        unique                              删除重复的行列
    tr          translate                           转换大小写或删除字符
    sort                                            排序文本
    ed          editor                              编辑文本
    joe         joe's own editor                    编辑文本
    pico        pine's message composition editor   编辑文本
    awk                                             处理文本
    sed         stream  editor                      使用脚本处理文本
        a       add                                 新增
        c       replace                             替换
        d       delete                              删除
        i       insert                              插入
        p       print                               打印
        s       substitute                          取代                                      sed "s/xyz/XYZ/g" file_name
    fmt         format                              格式化文本
    col                                             过滤控制字符
    join                                            将两个文件中，指定栏位内容相同的行连接起来
    split                                           将一个文件分割成数个文件
    wc          word count                          统计字数
        -w                                          统计字数
        -c                                          统计字节数
        -m                                          统计字符数
        -l                                          统计行数
---
## 文件传输
    lpr         line print                          将一个或多个文件放入打印队列等待打印
    lpq         line print queue                    查看打印队列
    lprm                                            将一个工作由打印机贮列中移除
---
## 磁盘管理
    cd          change directory                    切换目录
    ls          list                                显示目录下内容
        -a      --all                               显示目录下内容，包括以 . 开头的隐藏文件
        -i                                          显示目录下内容及其详细信息
    pwd         print work directory                显示当前目录
    tree                                            以树状图显示目录内容
    quota                                           显示磁盘已使用的空间与限制
    df          disk free                           显示磁盘使用情况
    du          disk usage                          显示目录或文件所占空间
    stat                                            显示 inode 内容
    mkdir       make directory                      创建目录
        -p      --parents                           创建多级目录
    rmdir       remove directory                    删除目录
    mount                                           挂载 Linux 系统外的文件
    umount      unmount                             卸除文件系统
---
## 磁盘维护
    mkfs        make file system                    建立 Linux 文件系统
    mke2fs                                          建立 ext2 文件系统
    mkswap                                          设置交换区
    swapon                                          激活 Linux 系统中的交换空间
    fdformat                                        对指定的软碟机装置进行低阶格式化
    fsck        file system check                   检查与修复 Linux 档案系统
    badblocks                                       检查磁盘装置中损坏的区块
    dd          disk dump                           读取、转换并输出数据
---
## 网络通信
    ping        packet internet grouper             检测主机
    ip                                              显示或设置网络设备，比 ipconfig 功能强大
    ipconfig                                        显示或设置网络设备
    netstat                                         显示网络状态
    dig                                             显示域的 DNS 信息
    wget                                            下载文件
    tcpdump                                         倾倒网络传输数据
    ssh                                             远程连接
    talnet      terminal over network                登入远端主机
    wall        writer all                          将讯息传给每一个 mesg 设定为 yes 的上线使用者
    tty         teletypewriter                      显示终端机连接标准输入设备的文件名称
---
## 系统管理
    rsh         remote shell                        登入远端 shell
    su          switch user                         切换用户
    chsh        change shell                        更改使用者 shell 设定
    sudo        super user do                       以系统管理者的身份执行指令
    ulimit      user's limit                        控制 shell 程序的资源
    top                                             显示进程的动态
    ps          process status                      显示进程状态
    kill                                            杀死进程
    whoami                                          显示自身用户名称
    id                                              显示用户 ID，以及所属群组 ID
    w                                               显示当前用户的信息
    finger                                          显示其它用户的信息
    who                                             显示系统所有使用者信息
    whois                                           查找并显示用户信息
    uname       unix name                           显示系统信息
        -a      --all                               显示全部信息
        -n      --nodename                          显示主机名称
        -m      --machine                           显示处理器架构
        -s      --sysname                           显示操作系统名称
    shutdown                                        关闭系统
        -h                                          关闭系统后停机
        -r                                          关闭系统后重新开机
    reboot                                          重启系统
    logout                                          退出系统
---
## 系统设置
    ssh-keygen                                      创建 SSH Key
        -t                                          指定要创建密钥类型 (RSA|DSA)
        -C                                          添加备注
    date        date '+%Y-%m-%d %H:%M:%S'           显示或设定系统的日期与时间
    cal                                             显示公历日历
    uptime                                          显示系统运行时间或系统的平均负载
    export                                          设置或显示环境变量
    clear                                           清除屏幕
    useradd                                         添加用户
    usermod                                         修改用户
    userdel                                         删除用户
    passwd      password                            更改密码
    eval        evaluate                            读取一连串的参数，然后再依参数本身的特性来执行
    rpm         redhat package manager              红帽子打包管理器
        -a                                          查询所有套件
        -e                                          删除指定套件
        --nodeps                                    不验证套件档的相互关联性
        -i                                          安装指定的套件档
        -h                                          套件安装时列出标记
        -v                                          显示指令执行过程
        -q                                          使用询问模式，当遇到任何问题时，会先询问用户
    yum         yellow dog updater, modified        前端软件包管理器
    lsmod       list modules                        显示已载入模块
    insmod      install modules                     载入模块
    rmmod       remove modules                      删除模块
---
## 备份压缩
    tar         tape archive                        备份文件
        -z      --gzip                              通过 gzip指令处理备份文件
        -c      --create                            建立新的备份文件
        -x      --extract                           从备份文件中还原文件
        -f      --file                              指定备份文件
    gzip                                            压缩文件
    gunzip                                          解压 gzip 文件
    gzcat                                           查看 gzip 文件
---
## 系统任务
    fg          foreground                          将后台中的任务调至前台继续运行
    bg          background                          将一个在后台暂停的命令，变成继续执行
---
## 其它
    xargs       extended arguments                  给命令传递参数的一个过滤器，也是组合多个命令的一个工具
    man         manual                              手册
    bc          basic calculator                    计算器
    printf      print formatted
    echo                                            输出字符串
---