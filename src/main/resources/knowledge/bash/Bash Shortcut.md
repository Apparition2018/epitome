# Bash Shortcut
Bash 默认使用 Emacs 的快捷键，可以通过 set -o vi 让它使用 vim 的快捷键
### [GNU Readline](https://en.wikipedia.org/wiki/GNU_Readline)
### [Bash 快捷键大全](https://www.runoob.com/w3cnote/bash-shortcut.html)
### [Linux bash 常用快捷键](https://www.cnblogs.com/dabaodb/p/10148929.html)
---
>## 光标
>```
>Ctrl + B               ←                       前移至一个字符
>Ctrl + F               →                       后移至一个字符
>Alt + B                                        前移至一个单词
>Alt + F                                        后移至一个单词
>Ctrl + A               Home                    跳转至行首
>Ctrl + E               End                     跳转至行尾
>Ctrl + X , X                                   行首/当前位置光标跳转
>```
---
>## 编辑                                          
>```
>Ctrl + H               Backspace               删除光标前一个字符
>Ctrl + D               Delete                  删除光标后一个字符
>Alt + Backspace                                删除光标左侧单词，并复制到剪贴板
>Ctrl + W                                       删除光标左侧单词，并复制到剪贴板
>Alt + D                                        删除光标右侧单词，并复制到剪贴板
>Ctrl + U                                       删除光标左侧所有字符，并复制到剪贴板
>Ctrl + K                                       删除光标右侧所有字符，并复制到剪贴板
>Ctrl + Y                                       粘帖剪贴板内容
>Alt + Y                                        轮询到删除环，并复制新的顶端文本
>Alt + R                                        撤销本t行所有操作，并恢复到初始状态
>Ctrl + Shift + C       Ctrl + Insert           复制 (鼠标左键拖拽)
>Ctrl + Shift + V       Shift + Insert          粘贴 (鼠标中键)
>Alt + <                                        粘贴历史纪录中的第一行
>Alt + >                                        粘贴历史纪录中的最后一行
>```
---
>## 操作
>```
>Ctrl + S                                       停止回显(XOFF)
>Ctrl + Q                                       打开回显(XON)
>Ctrl + C                                       中止任务
>Ctrl + Z                                       挂起任务，输入 bg 后台执行，输入 fg 恢复执行
>Ctrl + O                                       执行命令，并重新显示该命令
>Ctrl + /                                       撤销
>Ctrl + _                                       撤销
>Ctrl + L               command: clear          清屏
>Ctrl + D               command: exit           关闭当前 shell
>Shift + PageUp                                 向上翻页
>Shift + PageDown                               向下翻页
>```
---
>## 输入
>```
>Ctrl + I               Tab                     水平制表符
>Ctrl + K                                       垂直制表符
>Ctrl + M               Enter                   回车
>Ctrl + J                                       新行
>Ctrl + L                                       换页
>Ctrl + D                                       "EOF"
>Ctrl + V                                       特殊字符 ???
>Alt + (n)                                      重复输入 n 次字符
>```
---                                           
>## 历史
>```
>Ctrl + P               ↑                       上一条命令
>Ctrl + N               ↓                       下一条命令
>Ctrl + R                                       搜索命令，多次按返回下一个匹配项
>Ctrl + G                                       推出搜索命令；响铃 ???
>Alt + .                                        粘帖上一次命令最后的单词
>```
---
>## 大小写
>```
>Alt + C                                        从光标处开始，将右边一个单词首字母更改为大写
>Alt + U                                        从光标处开始，将右边一个单词更改为大写
>Alt + L                                        从光标处开始，将右边一个单词更改为小写
>```
---
>## 交换位置                                         
>```
>Ctrl + T                                       交换字符位置
>Alt + T                                        交换单词位置
>```
---
>## Bang(!) 命令
>```
>!!                                             执行上一条命令
>!(±n)                                          执行 history 中编号为 n 的命令，-n 表示前 n 条
>!xxx                                           执行以 xxx 开头的最近一条命令
>!?xxx[?]                                       执行匹配 xxx 的最近一条命令 ???
>!xxx:p                                         打印以 xxx 开头的最近一条命令
>!$                                             上一条命令的最后的一个参数
>!*                                             上一条命令的所有参数
>!#                                             重复输入目前已输入的内容 
>^xxx                                           替换上一条命令的 xxx 为 ''
>^xxx^yyy                                       替换上一条命令的 xxx 为 yyy
>^xxx^yyy^                                      替换上一条命令所有的 xxx 为 yyy ???
>```