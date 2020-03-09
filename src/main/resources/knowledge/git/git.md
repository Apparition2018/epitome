# [Git](https://git-scm.com/) 
---
## 参考网站
1. [Git - Book](https://git-scm.com/book/zh/v2)
2. [Git 教程 | 菜鸟教程](https://www.runoob.com/git/git-tutorial.html)
3. [Git 教程 | 易百教程](https://www.yiibai.com/git)
4. [Git 教程 - 廖雪峰](https://www.liaoxuefeng.com/wiki/896043488029600)
5. [Git 一些比较常用的命令](https://blog.csdn.net/weixin_37292229/article/details/71080573)
6. [github/gitignore](https://github.com/github/gitignore)
---
## 问题
1. [Git 忽略提交 .gitignore](https://www.cnblogs.com/youyoui/p/8337147.html)
2. [Git 和 Github 绑定ssh key](https://blog.csdn.net/smiple9102/article/details/79254577)
3. [解决 git ERROR: Permission to XXX.git denied to user](https://www.cnblogs.com/chevin/p/9236674.html)
4. [解决github 打开、拉取、推送速度慢的问题](https://blog.csdn.net/natahew/article/details/81387885)
---
## Git 常用命令速查表
![Git 常用命令速查表](https://img-blog.csdn.net/20171126101742109?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd2VpeGluXzM3MjkyMjI5/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

---
## [config](https://git-scm.com/docs/git-config#Documentation/git-config)
>```
>-l                 --list                  显示所有配置
>                   --global                全局配置
>```

>```
>user.name                                  用户名称
>user.email                                 用户邮件
>core.editor                                编辑器
>core.excludesFile                          .ignore 文件地址
>core.autocrlf                              提交或检出转换 lf 和 crlf
>core.safecrlf                              提交混合换行符提示
>alias                                      别名
>```
---
## 创建版本库
```
init                                        创建一个空的 Git 存储库或重新初始化一个现有的存储库
clone                                       克隆远程版本库
```
---
## 修改和提交
```
status                                      显示状态
diff                                        显示提交、提交和工作树等之间的更改
add                                         将文件内容添加到索引(将修改添加到暂存区)
mv                                          移动或重命名文件，目录或符号链接
rm                                          从工作区和索引中删除文件
    --cached <file>                         把文件从索引库中删除
commit                                      将更改记录提交到存储库
    -m <msg>                                备注
    --amend                                 修改最后一次的提交
stash                                       将更改储藏在脏工作目录中
    list/show/apply/drop/pop                列出/显示/恢复/删除/恢复并删除储藏的工作
```
---
## 查看提交历史
```
log                                         显示提交日志
    -p <file>                               显示指定文件的提交日志
    --pretty=oneline                        只显示版本号和备注
reflog                                      ???
blame                                       显示文件的每一行最后修改的版本和作者
```
---
## 撤销
```
reset                                       将当前 HEAD 复位到指定状态  ???
chekcout                                    切换分支或恢复工作树文件
    -- <paths>                              恢复工作树文件
revert <commit>                             撤销指定的提交
```
---
## 分支与标签
```
branch                                      列出，创建或删除分支
    <new_branch>                            创建分支
    -d <branch_name>                        删除分支
tag                                         创建、列表、删除或验证用 GPG 签名的 Tag 对象
    <tag_name>                              基于最新提交创建标签
    -d <tag_name>                           删除标签
checkout                                    切换分支或恢复工作树文件
    <branhc|tag>                            切换分支或标签
    -b <new_branch>                         创建并切换分支
```
---
## 合并与衍合
```
merge                                       将两个或两个以上的开发历史合并在一起
    --no-ff -m <msg> <branch>               禁用 fast forward (禁用后能看出来曾经做过合并)，会生成一个新的 commit (所以需要加上-m)
rebase <branch>                             在另一个分支基础之上重新应用，用于把一个分支的修改合并到当前分支
```
---
## 远程操作
```
remote                                      管理一组跟踪的存储库
    -v                                      列出已经存在的远程分支的详细信息
    add <remote> <url>                      添加远程版本库
    show <remote>                           显示指定远程版本库信息
    remove <remote>                         删除指定远程版本库
fetch <remote>                              从远程版本库获取代码
pull <remote> <branch>                      下载代码或快速合并
push                                        将本地分支的更新，推送到远程主机
    <remote> <branch|tag>                   推送分支或标签
    <remote> :<branch|tag>                  删除远程分支或标签
    <remote> --tags                         推送所有标签
    -u                                      指定默认主机，以后就可以不加任何参数，直接使用 git push
```
---
## git-bash shortcut
```
Ctrl + Ins          Copy                    复制
Shift + Ins         Paste                   粘贴
Alt + F3            Search                  搜索
Alt + F8            Reset                   清屏
Alt + F10           Default Size            还原屏幕
Alt + F11           Full Screen             全屏幕
Alt + F12           Flip Screen             翻转屏幕
```