# Git

---
## Reference
1. [Git](https://git-scm.com/)
2. [Learn Git Branching](https://learngitbranching.js.org/?locale=zh_CN)
3. [Git 教程 | 菜鸟教程](https://www.runoob.com/git/git-tutorial.html)
4. [Git 教程 | 易百教程](https://www.yiibai.com/git)
5. [Git 教程 - 廖雪峰](https://www.liaoxuefeng.com/wiki/896043488029600)
6. [Git 术语及中英文对照](https://blog.csdn.net/xufive/article/details/84647423)
---
## 问题
1. [解决 GitHub 提交次数过多 .git 文件过大的问题？](https://www.zhihu.com/question/29769130)
2. [解决 GitHub 网页上图片显示失败的问题](https://blog.csdn.net/qq_38232598/article/details/91346392)
---
## hosts
1. [IPAddress.com](https://www.ipaddress.com)
2. 查找 github.com 和 github.global.ssl.fastly.net 的 IP，并记录在 hosts 文件
3. cmd → `ipconfig /flushdns`
---
## [配置多个 git 账号](https://blog.csdn.net/qq_33254766/article/details/122941664)
1. 生成多个 SSH key
```
ssh-keygen -t rsa -C "one@gmail.com"
    Enter file in which to save the key         one_rsa
    
ssh-keygen -t rsa -C "two@gmail.com"
    Enter file in which to save the key         two_rsa
```
2. 创建 config 配置文件
```
# one(one@gmail.com)
Host github.com
HostName github.com
PreferredAuthentications publickey
IdentityFile ~/.ssh/one_rsa
User one
    
# two(two@gmail.com)
Host gitee.com
HostName gitee.com
PreferredAuthentications publickey
IdentityFile ~/.ssh/two_rsa
User two
```
3. 部署 SSH key：Settings → SSH and GPG keys
4. SSH 测试：`ssh -T git@github.com`，`ssh -T git@gitee.com`
5. 克隆项目：`git clone git@github.com:one/epitome.git`
6. 取消全局用户名和邮箱
```
git config --global --unset user.name
git config --global --unset user.email
```
8. 设置局部用户名和邮箱
```
git config user.name "one"; git config user.email "one@gmail.com"
```
---
## .gitconfig
- [gitignore Documentation](https://git-scm.com/docs/gitignore)
- [github/gitignore](https://github.com/github/gitignore)
- [Git 忽略提交 .gitignore](https://www.cnblogs.com/youyoui/p/8337147.html)
```
[user]
	name = Apparition2018
	email = 88850180@163.com
[core]
	excludesfile = D:/Liang/git/epitome/.gitignore
[http]
	sslVerify = false
[https]
	sslVerify = false
```
---
## 命令
- Git 常用命令速查表
![Git 常用命令速查表](https://img-blog.csdn.net/20171126101742109)
- Git Data Transport Commands
![Git Data Transport Commands](http://www.findme.wang/Uploads/Editor/2017-04-13/58ef783b96ebd.png)
### [设置与配置](https://git-scm.com/book/zh/v2/%E9%99%84%E5%BD%95-C%3A-Git-%E5%91%BD%E4%BB%A4-%E8%AE%BE%E7%BD%AE%E4%B8%8E%E9%85%8D%E7%BD%AE)
```
help [COMMAND|GUIDE]                            帮助
config                                          获取和设置 repository 或全局选项
    --global                                    全局配置
    -l | --list                                 列出配置文件中设置的所有变量及其值
    --unset                                     从配置文件中删除与密钥匹配的行
        user.name                               用户名称
        user.email                              用户邮件
        core.editor                             编辑器
        core.excludesFile                       .ignore 文件地址
        core.autocrlf                           提交或检出转换 lf 和 crlf
        core.safecrlf                           提交混合换行符提示
        http.postBuffer                         
        merge.tool                              合并工具
        alias                                   别名
            .co checkout
            .br branch
            .ci commit
            .st status
```
### [获取与创建项目](https://git-scm.com/book/zh/v2/%E9%99%84%E5%BD%95-C%3A-Git-%E5%91%BD%E4%BB%A4-%E8%8E%B7%E5%8F%96%E4%B8%8E%E5%88%9B%E5%BB%BA%E9%A1%B9%E7%9B%AE)
```
init                                            创建一个空的 Git repository 或重新初始化一个现有的 repository
clone <repository>                              克隆一个 repository 到一个新目录
    --depth <depth>                             创建一个浅克隆，其中历史记录被截断为指定提交次数
    -b | --branch <name>                        克隆分支
```
### [快照基础](https://git-scm.com/book/zh/v2/%E9%99%84%E5%BD%95-C%3A-Git-%E5%91%BD%E4%BB%A4-%E5%BF%AB%E7%85%A7%E5%9F%BA%E7%A1%80)
- [git commit -amend：重写 commit 历史](https://blog.csdn.net/weixin_39558754/article/details/110643585)
- [Git 修改已提交的 commit 注释](https://www.jianshu.com/p/098d85a58bf1)
- [git 撤销 push](https://blog.csdn.net/chenyiyue/article/details/79461624)
- [git 删除远程已经提交的文件](https://zhuanlan.zhihu.com/p/54032865)
```
add [<pathspec>…]                               将文件内容添加到 index
    .                                           将修改的文件，新建的文件，添加到 index
    -u                                          将修改的文件，删除的文件，添加到 index
    -A | --all                                  将修改的文件，删除的文件，新建的文件，添加到 index
stage                                           add 同义
status [<pathspec>…]                            显示 working tree 状态
commit                                          将变更记录到 repository
    -m <msg>                                    备注
    --amend                                     通过创建一个新的 commit 来替换当前分支尖端（最近）的 commit
reset                                           将当前 HEAD 复位到指定状态
    --soft                                      复位 repository                                                            git reset --soft HEAD^
    --mixed                                     复位 repository 和 index，默认                                              git reset --mixed HEAD^                    
    --hard                                      复位 repository 和 index 和 working tree                                    git reset --hard HEAD^
rm [<pathspec>…]                                从 working tree 和 index 中删除文件
    --cached                                    从 index 中删除文件
mv                                              移动或重命名文件，目录或符号链接
```
### [分支与合并](https://git-scm.com/book/zh/v2/Git-%E5%88%86%E6%94%AF-%E5%88%86%E6%94%AF%E7%AE%80%E4%BB%8B)
- [Git 拉取指定远程分支](https://www.jianshu.com/p/856ce249ed78)
- [Git 分支管理策略](https://www.ruanyifeng.com/blog/2012/07/git.html)
- [Git Flow 的正确使用姿势](https://www.jianshu.com/p/41910dc6ef29)
```
branch                                          列出，创建或删除 branch
    <branchname>                                创建 branch
    -a | --all                                  列出远程和本地所有分支
    -r | --remotes                              列出或删除（与 -d 一起使用）远程所有分支
    -d | --delete <branchname>…                 删除 branch
checkout                                        切换 branch 或 恢复 working tree 文件
    <branhc|tag>                                切换 branch 或 tag
    -b <new_branch> [<start_point>]             创建并切换 branch
merge                                           将两个或两个以上的开发历史合并在一起
    --no-ff                                     禁用 fast forward (禁用后能看出来曾经做过合并)，会生成一个新的 commit (所以可加上-m)
stash                                           将更改 stash 在 dirty working directory
    list/show/apply/drop/pop                    列出/显示/恢复/删除/恢复并删除储藏的工作
tag                                             创建、列表、删除或验证用 GPG 签名的 tag 对象
    <tagname>                                   基于最新提交创建 tag
    -d | --delete <tagname>…                    删除 tag
```
### [项目分享与更新](https://git-scm.com/book/zh/v2/%E9%99%84%E5%BD%95-C%3A-Git-%E5%91%BD%E4%BB%A4-%E9%A1%B9%E7%9B%AE%E5%88%86%E4%BA%AB%E4%B8%8E%E6%9B%B4%E6%96%B0)
```
fetch [<repository> [<refspec>…]]               从另一个 directory 下载 objects 和 refs
pull [<repository> [<refspec>…]]                从另一个 directory 或 local branch 获取并与之集成
push                                            使用 local refs 更新 remote refs，并发送相关 objects
    <repository> <refname>                      更新到远程 ref                                                               git push origin HEAD --force
    <repository> :<expect>                      删除远程 ref
    -u | --set-upstream                         下次输入同样的命令，只需输入 git push，无需输入参数
remote                                          管理一组被跟踪的 repository
    -v | --verbose                              显示 remote 的 url 在 name 之后
    show <name>…                                显示指定 name 的 remote 的信息
    add <name> <url>                            给在 url 的 repository 添加一个名为 name 的 remote
    remove | rm <name>                          删除名为 name 的 remote
```
### [检查和比较](https://git-scm.com/book/zh/v2/%E9%99%84%E5%BD%95-C%3A-Git-%E5%91%BD%E4%BB%A4-%E6%A3%80%E6%9F%A5%E4%B8%8E%E6%AF%94%E8%BE%83)
```
show [<object>…]                                显示各种类型的 objects，包括 lobs, trees, tags and commits
log                                             显示提交日志
    <file>                                      显示文件提交日志
    -p <file>                                   显示文件提交差异
    -p -2                                       显示最近两次提交差异
    --pretty=oneline                            只显示版本号和备注
diff                                            显示 commits, commit 和 working tree 等之间的更改
                                                显示 working tree 和 next commit 之间的更改
    --cached                                    显示 index 和 last commit 之间的更改
    HEDAD                                       显示 working tree 和 last commit 之间的更改
    --stat                                      显示更改统计
```
### [调式](https://git-scm.com/book/zh/v2/%E9%99%84%E5%BD%95-C%3A-Git-%E5%91%BD%E4%BB%A4-%E8%B0%83%E8%AF%95)
```
blame <file>                                    显示文件的每一行最后修改的版本和作者
```
### [补丁](https://git-scm.com/book/zh/v2/%E9%99%84%E5%BD%95-C%3A-Git-%E5%91%BD%E4%BB%A4-%E8%A1%A5%E4%B8%81)
[活用 git apply 合入 patch 补丁](https://juejin.im/post/6844903560564441101)
```
apply [<patch>…]                                将补丁文件打入文件和/或 index
rebase [<newbase> [<branch>]]                   把分叉的提交历史"整理"成一条直线的提交
revert <commit>…                                撤销指定的提交
```
### [管理](https://git-scm.com/book/zh/v2/%E9%99%84%E5%BD%95-C%3A-Git-%E5%91%BD%E4%BB%A4-%E7%AE%A1%E7%90%86)
```
gc                                              清除不必要的文件并优化本地存储库
    --prune=<date>                              删除日期之前的松散对象(默认是2周前)
    --aggressive                                更积极地优化存储库，但会花费更多的时间
reflog [<subcommand>]                           管理 reflog 信息 (包括 已删除的 commit 记录 和 reset 记录)
    expire                                      删除掉更老的 reflog 条目
    delete                                      从 reflog 中删除一个条目
    exists                                      检查一个 ref 是否有一个 reflog
```
### [底层命令](https://git-scm.com/book/zh/v2/%E9%99%84%E5%BD%95-C%3A-Git-%E5%91%BD%E4%BB%A4-%E5%BA%95%E5%B1%82%E5%91%BD%E4%BB%A4)
```
rev-list [<<commit>…>]                          以反时间顺序列出提交对象
    --objects
    --all
verify-pack <pack>.idx …                        验证已打包的 Git 归档文件
    -v|--verbose
```
---
## git-bash shortcuts
```
Ctrl + Ins              Copy                    复制
Shift + Ins             Paste                   粘贴
Alt + F3                Search                  搜索
Alt + F8                Reset                   清屏
Alt + F10               Default Size            还原屏幕
Alt + F11               Full Screen             全屏幕
Alt + F12               Flip Screen             翻转屏幕
```
