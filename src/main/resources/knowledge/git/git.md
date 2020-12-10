# [Git](https://git-scm.com/)

---
## 参考网站
1. [Git](https://git-scm.com/)
2. [Git 教程 | 菜鸟教程](https://www.runoob.com/git/git-tutorial.html)
3. [Git 教程 | 易百教程](https://www.yiibai.com/git)
4. [Git 教程 - 廖雪峰](https://www.liaoxuefeng.com/wiki/896043488029600)
5. [Git 一些比较常用的命令](https://blog.csdn.net/weixin_37292229/article/details/71080573)
6. [Git 术语及中英文对照](https://blog.csdn.net/xufive/article/details/84647423)
7. [github/gitignore](https://github.com/github/gitignore)
---
## 问题
1. [Git 忽略提交 .gitignore](https://www.cnblogs.com/youyoui/p/8337147.html)
2. [Git 和 Github 绑定ssh key](https://blog.csdn.net/smiple9102/article/details/79254577)
3. [解决 git ERROR: Permission to XXX.git denied to user](https://www.cnblogs.com/chevin/p/9236674.html)
4. [解决github 打开、拉取、推送速度慢的问题](https://blog.csdn.net/natahew/article/details/81387885)
5. [如何解决 GitHub 提交次数过多 .git 文件过大的问题？ - 知乎](https://www.zhihu.com/question/29769130)
---
## Git 常用命令速查表
![Git 常用命令速查表](https://img-blog.csdn.net/20171126101742109)

---
## Git Data Transport Commands
![Git Data Transport Commands](https://i.stack.imgur.com/caci5.png)

---
## 命令
>### 设置与配置
>```
>help [COMMAND|GUIDE]                           帮助
>config                                         获取和设置 repository 或全局选项
>   -l | --list                                 列出所有变量及其值
>   --global                                    全局配置
>       user.name                               用户名称
>       user.email                              用户邮件
>       core.editor                             编辑器
>       core.excludesFile                       .ignore 文件地址
>       core.autocrlf                           提交或检出转换 lf 和 crlf
>       core.safecrlf                           提交混合换行符提示
>       merge.tool                              合并工具
>       alias                                   别名
>           .co checkout
>           .br branch
>           .ci commit
>           .st status
>```
>## 获取与创建项目
>```
>init                                           创建一个空的 Git repository 或重新初始化一个现有的 repository
>clone <repository>                             克隆一个 repository 到一个新目录
>   --depth <depth>                             创建一个浅克隆，其中历史记录被截断为指定提交次数
>   -b <name> | --branch <name>                 分支
>```
>## 基本快照
>```
>add [<pathspec>…]                              将文件内容添加到 index
>   .                                           将修改的文件，新建的文件，添加到 index
>   -u                                          将修改的文件，删除的文件，添加到 index
>   -A | --all                                  将修改的文件，删除的文件，新建的文件，添加到 index
>status [<pathspec>…]                           显示 working tree 状态
>commit                                         将变更记录到 repository
>    -m <msg>                                   备注
>    --amend                                    通过创建一个 new commit 来替换 the tip of the current branch
>reset                                          将当前 HEAD 复位到指定状态
>   --soft                                      复位 repository
>   --mixed                                     复位 repository 和 index
>   --hard                                      复位 repository 和 index 和 working tree
>rm [<pathspec>…]                               从 working tree 和 index 中删除文件
>    --cached                                   从 index 中删除文件
>mv                                             移动或重命名文件，目录或符号链接
>```
>## [分支与合并](https://git-scm.com/book/zh/v2/Git-%E5%88%86%E6%94%AF-%E5%88%86%E6%94%AF%E7%AE%80%E4%BB%8B)
>```
>branch                                         列出，创建或删除 branch
>   <branchname>                                创建 branch
>   -d | --delete <branchname>…                 删除 branch
>checkout                                       切换 branch 或 恢复 working tree 文件
>   <branhc|tag>                                切换 branch 或 tag
>   -b <new_branch>                             创建并切换 branch
>merge                                          将两个或两个以上的开发历史合并在一起
>   --no-ff                                     禁用 fast forward (禁用后能看出来曾经做过合并)，会生成一个新的 commit (所以可加上-m)
>stash                                          将更改 stash 在 dirty working directory
>   list/show/apply/drop/pop                    列出/显示/恢复/删除/恢复并删除储藏的工作
>tag                                            创建、列表、删除或验证用 GPG 签名的 tag 对象
>   <tagname>                                   基于最新提交创建 tag
>   -d | --delete <tagname>…                    删除 tag
>```
>## 项目分享与更新
>```
>fetch [<repository> [<refspec>…]]              从另一个 directory 下载 objects 和 refs
>pull [<repository> [<refspec>…]]               从另一个 directory 或 local branch 获取并与之集成
>push                                           使用 local refs 更新 remote refs，并发送相关 objects
>   <repository> <refname>                      更新到远程 ref
>   <repository> :<expect>                      删除远程 ref
>   -u | --set-upstream                         下次输入同样的命令，只需输入 git push，无需输入参数
>remote                                         管理一组被跟踪的 repository
>   -v | --verbose                              显示 remote 的 url 在 name 之后
>   show <name>…                                显示指定 name 的 remote 的信息
>   add <name> <url>                            给在 url 的 repository 添加一个名为 name 的 remote
>   remove | rm <name>                          删除名为 name 的 remote
>```
>## 检查和比较
>```
>show [<object>…]                               显示各种类型的 objects，包括 lobs, trees, tags and commits
>log                                            显示提交日志
>   <file>                                      显示文件提交日志
>   -p <file>                                   显示文件提交差异
>   -p -2                                       显示最近两次提交差异
>   --pretty=oneline                            只显示版本号和备注
>diff                                           显示 commits, commit 和 working tree 等之间的更改
>                                               显示 working tree 和 next commit 之间的更改
>   --cached                                    显示 index 和 last commit 之间的更改
>   HEDAD                                       显示 working tree 和 last commit 之间的更改
>   --stat                                      显示更改统计
>```
>## 补丁
>[活用 git apply 合入 patch 补丁](https://juejin.im/post/6844903560564441101)
>```
>apply [<patch>…]                               将补丁文件打入文件和/或 index
>rebase [<newbase> [<branch>]]                  把分叉的提交历史"整理"成一条直线的提交
>revert <commit>…                               撤销指定的提交
>```
>## 调式
>```
>blame <file>                                   显示文件的每一行最后修改的版本和作者
>```
>## 管理  
>```
>gc                                             清除不必要的文件并优化本地存储库
>   --prune=<date>                              删除日期之前的松散对象(默认是2周前)
>   --aggressive                                更积极地优化存储库，但会花费更多的时间
>reflog [<subcommand>]                          管理 reflog 信息 (包括 已删除的 commit 记录 和 reset 记录)
>   expire                                      删除掉更老的 reflog 条目
>   delete                                      从 reflog 中删除一个条目
>   exists                                      检查一个 ref 是否有一个 reflog
>```
>## 管道命令
>```
>rev-list [<<commit>…>]                         以反时间顺序列出提交对象
>   --objects
>   --all
>verify-pack <pack>.idx …                       验证已打包的 Git 归档文件
>   -v|--verbose
>```
---
## git-bash shortcut
```
Ctrl + Ins              Copy                    复制
Shift + Ins             Paste                   粘贴
Alt + F3                Search                  搜索
Alt + F8                Reset                   清屏
Alt + F10               Default Size            还原屏幕
Alt + F11               Full Screen             全屏幕
Alt + F12               Flip Screen             翻转屏幕
```