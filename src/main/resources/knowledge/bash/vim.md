# [vim](https://www.vim.org)
- [Linux vi/vim | 菜鸟教程](https://www.runoob.com/linux/linux-vim.html)
- [Vim documentation: help](https://vimhelp.org/)
    - [Vim documentation: usr_toc](https://vimhelp.org/usr_toc.txt.html)
---
## General subjects
### intro：Vim 概述
- [vim-modes](https://vimhelp.org/intro.txt.html#vim-modes-intro)
    - [Normal mode](#Normal-mode)：Command mode，启动编辑器时默认模式
        - 从 other mode 进入：`Esc`，`Ctrl + [`，`Ctrl + C`
        - 从 Command-line mode 输入数字再\<Enter>进入：`num<Enter>`
    - Visual mode
        - 从 Normal mode 进入 Character-wise Visual mode：`v`
        - 从 Normal mode 进入 Line-wise Visual mode：`V`
        - 从 Normal mode 进入 Block-wise Visual mode：`g Ctrl+H`
        - 从 Select mode 进入：`Ctrl + G`
    - Select mode
        - 从 Normal mode 进入 Character-wise Select mode：`gh`
        - 从 Normal mode 进入 Line-wise Select mode：`gH`
        - 从 Normal mode 进入 Block-wise Visual mode：`Ctrl + v`
        - 从 Visual mode 进入：`Ctrl + G`
    - Insert mode
        - 从 Normal mode 进入：`i`，`a`，`o`，`O` …
        - 从 Visual mode 删除选中并进入：`c`，`s`
        - 从 Block-wise Visual mode 删除选中并进入：`C`
        - 从 Select mode 删除选中并插入字符并进入：输入任何可打印字符
    - Command-line mode：Cmdline mode
        - 从 Normal/Visual mode 进入：`:`，`/`，`?`
        - 从 Normal mode 进入 Command-line Window：`q:`
        - 从 Normal mode 进入 Search History Window：`q/`，`q?`
        - 从 Insert mode 进入 Expression Mode：`Ctrl+R =`
---
## Basic editing
### starting：启动 Vim，Vim 命令参数，初始化
- [vim-arguments](https://vimhelp.org/starting.txt.html#vim-arguments)
```
+[num]                              光标将定位在第 num 行；缺少 num 将定位在最后一行
```
---
## modes
### Normal-mode
```
[num]gg                             跳转到第 num 行，缺少 num 跳到首行
[num]G                              跳转到第 num 行，缺少 num 跳到末行
/                                   向下搜索关键字，n 下一个
?                                   向上搜索关键字，n 上一个
x                                   删除当前光标所在处的字符
```
### 输入模式
```
HOME / END                          跳转到行首/行尾
Page Up / Page Down                 上/下翻页
Insert                              切换光标为输入/替换模式，光标将变成竖线/下划线
ESC                                 退出输入模式，切换到命令模式
```
### 底线命令模式
```
q                                   退出程序
q!                                  不保存强制退出
w                                   保存文件
wq                                  存储后退出
set nu                              显示行号
```
---
