# [VS Code Shortcuts](https://code.visualstudio.com/shortcuts/keyboard-shortcuts-windows.pdf)

---
## System
```
?                                                                               列出当前可执行的动作
F1 | Ctrl + Shift + P       Show All Commands                                   显示所有命令
Ctrl + E                    … Go to File…                                       转到文件…
Ctrl + P                    … Go to File…                                       转到文件…
Ctrl + G                    : Go to Line/Column…                                转到行/列…
Ctrl + T                    # Go to Symbol in Workspace…                        转到工区中的符号…
Ctrl + Shift + O            @ Go to Symbol in Editor…                           转到编辑器中的符号…
Ctrl + K Ctrl + Q           Go to Last Edit Location                            转到上一编辑位置
Ctrl + Y                    Redo                                                恢复
Ctrl + Shift + N            New Window                                          新建窗口
```
- editorTextFocus
```
F7                          Go to Next Symbol Highlight                         跳转到下一个突出显示的符号
Shift + F7                  Go to Previous Symbol Highlight                     跳转到上一个突出显示的符号
Ctrl + [                    Outdent Line                                        行减少缩进
Ctrl + ]                    Indent Line                                         行缩进
Ctrl + Shift + [            Fold                                                折叠
Ctrl + Shift + ]            Unfold                                              展开
Ctrl + F2                   Change All Occurrences                              更改所有匹配项
Ctrl + Alt + ↑/↓            Add Cursor Above/Below                              在上面/下面添加光标
Ctrl + K Ctrl + F           Format Selection                                    格式化选定内容
Ctrl + Shift + \            Go to Bracket                                       转到括号
Shift + Alt + I             Add Cursors to Line Ends                            在行尾添加光标
Shift + Alt + F             Format Document                                     格式化文档
Shift + Alt + ←/→           Shrink/Expand Selection                             收起/展开选择
Shift + Alt + ↑/↓           Copy Line Up/Down                                   复制行
```
- editorFocus
```
F3                          Find Next                                           查找下一个
Shift + F3                  Find Previous                                       查找上一个
F8                          Go to Next Problem in Files                         转到文件中的下一个问题
Alt + F8                    Go to Next Problem                                  转到下一个问题
Shift + Alt + F8            Go to Previous Problem                              转到上一个问题
Ctrl + D                    Add Selection To Next Find Match                    将下一个查找匹配项添加到选择
Ctrl + Shift + L            select All Occurrences of Find Match                选择所有找到的查找匹配项
```
- textInputFocus
```
Ctrl + L                    Expand Line Selection                               展开行选择
Ctrl + U                    Cursor Undo                                         光标撤销
Ctrl + Delete               deleteWordRight                                     删除右侧字符
Ctrl + Shift + K            Delete Line                                         删除行
Ctrl + Shift + Alt + Arrow  cursorColumnSelect
```
- textCompareEditor
```
Alt + F3                    Show Next Change                                    显示下一个更改
Shift + Alt + F3            Show Previous Change                                显示上一个更改
Alt + F5                    Go to Next Change                                   转到下一个更改
Shift + Alt + F5            Go to Previous Change                               转到上一个更改
```
- editorHasCodeActionsProvider
```
Ctrl + .                    quickFix                                            快速修复
Ctrl + Shift + R            Refactor…                                           重构
```
- editorHasDefinitionProvider
```
F12 | Ctrl + F12            Go to Definition                                    转到定义
Alt + F12                   Peek Definition                                     速览定义
```
- workbench.action
```
Alt + 1-9                   openEditorAtIndex1-9                                聚焦编辑器
Ctrl + 1-8                  focusXXXEditorGroup                                 聚焦编辑器组
Ctrl + ,                    openSettings                                        打开设置
Ctrl + Q                    quickOpenNavigateNextInViewPicker
Ctrl + Shift + Q            quickOpenNavigatePreviousInViewPicker
Ctrl + Shift + C            openNativeConsole                                   打开新的外部终端
Ctrl + K P                  files.copyPathOfActiveFile                          复制当前文件路径
Ctrl + K R                  files.revealActiveFileInWindows                     在资源管理器中显示当前文件
```
- other
```
F2                          Rename Symbol               editorHasRenameProvider 重命名符号
Ctrl + I | Ctrl + Space     Trigger Suggest     editorHasCompletionItemProvider 建议
Ctrl + K M                  Change Language Mode                                更改语言模式
```
### View
```
Ctrl + B                    Toggle Primary Side Bar Visibility                  切换主侧栏可见性
Ctrl + Alt + B              Toggle Secondary Side Bar Visibility                切换辅助侧栏可见性
Ctrl + J                    Toggle Panel Visibility                             切换面板可见性
Ctrl + `                    Toggle Terminal                                     切换终端
Ctrl + Shift + M            Toggle Problems                                     切换问题
Ctrl + Shift + U            Toggle Output                                       切换输出
Ctrl + Shift + Y            Toggle Debug Console                                切换调试控制台
Alt + Z                     Toggle Word Wrap                                    切换自动换行
Ctrl + K Ctrl + M           Toggle Maximize Editor Group                        切换最大化编辑器组

F6                          Focus Next Part                                     聚焦下一部分（编辑器→面板→状态栏→活动栏→主侧栏）
Shift + F6                  Focus Previous Part                                 聚焦前一部分
Ctrl + K Ctrl + Arrow       Focus Editor Group                                  聚焦上/下/左/右编辑组
Ctrl + 0                    Focus into Primary Side Bar                         聚焦主侧栏
Ctrl + 1                    Focus First Editor Group                            聚焦第一个编辑器组
Alt + 0                     Open Last Editor in Group                           打开组中最后一个编辑器
Ctrl + PgUp/PgDn            Open Previous/Next Editor                           打开上/下一个编辑器
Ctrl + Tab                  Quick Open Previous Recently Used Editor in Group   快速打开组中上一个最近使用过的编辑器

Ctrl + Shift + D            Show Run and Debug                                  显示运行和调试
Ctrl + Shift + E            Show Explorer                                       显示资源管理器
Ctrl + Shift + F            Show Search                                         显示搜索
Ctrl + Shift + G            Show Source Control                                 显示源代码管理
Ctrl + Shift + X            Show Extensions                                     显示扩展

Ctrl + Alt + ←/→            Move Editor into Previous/Next Group                将编辑器移动到上/下一组
Ctrl + Shift + PgUp/PgDn    Move Editor Left/Right                              向左/右移动编辑器

Ctrl + K Enter              Keep Editor                                         保留编辑器                  ???
Ctrl + \                    Split Editor                                        拆分编辑器
Ctrl + W                    Close Editor                                        关闭编辑器
Ctrl + K Ctrl + W           Close All Editors                                   关闭所有编辑器
Ctrl + K Ctrl + Shift + W   Close All Editors Groups                            关闭所有编辑器组
Ctrl + Shift + W            Close Window                                        关闭窗口
Ctrl + Shift + T            Reopen Closed Editor                                重新打开已关闭的编辑器
Ctrl + K Shift + Enter      Pin/Unpin Editor                                    固定/解除固定 编辑器

Ctrl + NumPad0              Reset Zoom                                          重置缩放
Ctrl + =/NumPad_Add         Zoom In                                             放大
Ctrl + -/NumPad_Subtract    Zoom Out                                            缩小
```
## Search
```
Ctrl + Shift + H            Replace in Files                                    全局替换
F4                          Focus Next Search Result                            聚焦下一搜索结果
Shift + F4                  Focus Previous Search Result                        聚焦上一搜索结果
```
## Search Editor
```
Alt + C                     Toggle Match Case                                   切换匹配大小写
Alt + L                     Toggle Context Lines                                切换上下文行
Alt + R                     Toggle Use Regular Expression                       切换使用正则表达式
Alt + W                     Toggle Match Whole Word                             切换全字匹配
Ctrl + Shift + L            Select All Matches                                  选择所有匹配项
Ctrl + Shift + R            Search Again                                        再次搜索
Alt + Enter                 Open Results in Editor                              在编辑器中打开结果
```
### Debug
```
F5                          Start Debugging                                     开始调试
F5                          Continue                                            继续
Ctrl + F5                   Start Without Debugging                             开始执行（不调试）
Shift + F5                  Stop                                                停止
Shift + F5                  Disconnect                                          断开连接
Ctrl + Shift + F5           Restart                                             重启
F6                          Pause                                               暂停
F9                          toggleBreakpoint                                    切换断点
Shift + F9                  toggleInlineBreakpoint                              内联断点
F10                         Step Over                                           逐过程
F11                         Step Into                                           单步调试
Ctrl + F11                  Step Into Target                                    单步执行目标
Shift + F11                 Step Out                                            单步跳出
Ctrl + K Ctrl + I           Show Hover                                          显示悬停
Ctrl + PgUp/PgDn            Focus Previous/Next Terminal Group                  聚焦上/下一个调试控制台
```
### File
```
Shift + Alt + C             Copy Path of Active File                            复制活动文件的路径
Shift + Alt + R             Reveal in File Explorer                             在文件资源管理器中显示
Ctrl + N                    New Untitled Text File                              新建无标题文本文件
Ctrl + O                    Open File…                                          打开…
Ctrl + K O                  Open Active File in New Window                      在新窗口中打开活动文件
Ctrl + R                    Open Recent…                                        打开最近的…
Ctrl + K Ctrl + Shift + S   Save without Formatting                             保存但不格式化
Ctrl + K C                  Compare Active File with Clipboard                  比较活动文件与剪贴板
Ctrl + K D                  Compare Active File with Saved                      比较活动文件与保存的文件
Ctrl + K Ctrl + P           Show All Editors By Appearance                      按外观显示所有编辑器
```
---
### Preferences
```
Ctrl + K Ctrl + S           Open Keyboard Shortcuts                             打开键盘快捷方式
Ctrl + K Ctrl + T           Color Theme                                         颜色主题
```
### Terminal
```
Ctrl + Shift + `            Create New Terminal                                 创建新的终端
Ctrl + Shift + O            Open Detected Link…                                 打开检测到的链接…
Ctrl + ↑/↓                  Scroll To Previous/Next Command                     滚动到上/下一条命令
Ctrl + PgUp/PgDn            Focus Previous/Next Terminal Group                  聚焦上/下一终端组
Ctrl + Shift + \            Focus Terminal Tabs View                            聚焦终端选项卡视图
Alt + Z                     Toggle Size to Content Width                        将大小切换到内容宽度
```
### Explorer
```
Ctrl + K E                  Focus on Open Editors View                          聚焦在打开的编辑器视图上
```
### Workspaces
```
Ctrl + K F                  Close Workspace                                     关闭工作区
```
### Tasks
```
Ctrl + Shift + B            Run Build Task                                      运行生成任务
```
---
## JavaScript Debugger
```
F10                         Start Debugging and Stop on Entry                   开始调试并停在输入时停止
F11                         Start Debugging and Stop on Entry                   开始调试并停在输入时停止
```
---
## Markdown
```
Ctrl + Shift + V            Open Preview                                        打开预览
Ctrl + K V                  Open Preview to the Side                            打开侧边预览
```
---
## Extensions
```
Ctrl + Alt + N              Run Code                                            Code Runner
Alt + B                     Open In Default Browser                             open in browser
Alt + L Alt + O             Open with Live Server                               Live Server
Ctrl + Alt + K              Toggle                                              Bookmarks
Ctrl + Alt + J              Jump to Previous                                    Bookmarks
Ctrl + Alt + L              Jump to Next                                        Bookmarks
Ctrl + Alt + L              Display Log Message                                 Turbo Console Log
Shift + Alt + C             Comment All Log Message                             Turbo Console Log
Shift + Alt + U             Uncomment All Log Message                           Turbo Console Log
Shift + Alt + D             Delete All Log Message                              Turbo Console Log
```
---
