# [vim](https://www.vim.org)
- [Linux vi/vim | 菜鸟教程](https://www.runoob.com/linux/linux-vim.html)
- [Vim documentation: help](https://vimhelp.org/)
    - [Vim documentation: usr_toc](https://vimhelp.org/usr_toc.txt.html)
---
## General subjects
### intro：Vim 概述
- [vim-modes](https://vimhelp.org/intro.txt.html#vim-modes-intro)
    - Normal mode：Command mode，启动编辑器时默认模式
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
## General subjects
### intro：Vim 概述；帮助文件的符号与标记
- [notation](https://vimhelp.org/intro.txt.html#notation)
```
[count]                                 在命令前输入一个数字，使其执行多次
```
### helphelp：关于使用帮助文件
- [online-help](https://vimhelp.org/helphelp.txt.html#online-help)
```
:he[elp]                                显示帮助文件
```
---
## Basic editing
### starting：启动 Vim，Vim 命令参数，初始化
- [vim-arguments](https://vimhelp.org/starting.txt.html#vim-arguments)
```
+[num]                                  光标将定位在第 num 行；缺少 num 将定位在最后一行
```
### editing：编辑和写入文件
- [writing](https://vimhelp.org/editing.txt.html#writing)
```
:w[rite] [++opt]                        将整个缓冲区内容写入当前文件
```
- [write-quit](https://vimhelp.org/editing.txt.html#write-quit)
```
:q[uit]                                 退出当前窗口，如果是最后一个窗口则退出
:q[uit]!                                强制退出当前窗口，如果是最后一个窗口则退出
:wq [++opt]                             写入当前文件并关闭窗口，如果是最后一个窗口则退出；对于只读文件可使用 :wq!
:qa[ll]!                                离开，缓冲区的任何更改都将丢失
```
### motion：移动指令
- [up-down-motions](https://vimhelp.org/motion.txt.html#up-down-motions)
```
G                                       跳转到第 count 行，缺少 count 跳到末行
gg                                      跳转到第 count 行，缺少 count 跳到首行
```
### change：删除和替换文本
- [deleting](https://vimhelp.org/change.txt.html#deleting)
```
["x]<Del>   or  ["x]x                   删除 count 个字符，并存入寄存器 x(a-z)
["x]dd                                  删除 count 行内容，并存入寄存器 x(a-z)
```
- [copy-move](https://vimhelp.org/change.txt.html#copy-move)
```
["x]p                                   粘贴 count 次寄存器 x(a-z) 或最近一次删除或复制的内容
```
### undo：撤消和重做
- [undo-commands](https://vimhelp.org/undo.txt.html#undo-commands)
```
<Undo>      or  u                       撤销 count 次更改
CTRL-R                                  重做 count 已撤消的更改
```
---
## Advanced editing
### options：选项描述
- [set-option](https://vimhelp.org/options.txt.html#set-option)
```
:se[t] {option}                         开关选项
```
- [option-summary](https://vimhelp.org/options.txt.html#option-summary)
```
'number' 'nu'                           在每一行的前面打印行号                     :set nu
```
### pattern：正则表达式模式和搜索命
- [search-commands](https://vimhelp.org/pattern.txt.html#search-commands)
```
/{pattern}[/]<CR>                       向前（光标往文件末尾）正则搜索
?{pattern}[?]<CR>                       向后（光标往文件开头）正则搜索
n                                       重复上一次 / 或 ? 执行的搜索 count 次
```
---
