# Windows

---
## Reference
1. [Windows Commands | Microsoft Docs](https://docs.microsoft.com/zh-cn/windows-server/administration/windows-commands/windows-commands)
2. [Win + R 提升效率](https://blog.csdn.net/u012995964/article/details/52549778)
---
## Win + R
- 系统变量下能直接访问
1. .exe
    ```
    calc                                计算器
    charmap                             字符映射表
    chkdsk                              检查卷的文件系统和文件系统元数据是否存在逻辑和物理错误
    cleanmgr                            磁盘清理
    cmd                                 cmd
    control                             控制面板
    explorer                            资源管理器
    msconfig                            系统配置
    mstsc                               远程桌面
    netplwiz                            用户账户
    nslookup                            显示可用于诊断域名系统(DNS)基础结构的信息
    osk                                 屏幕键盘
    regedit                             注册表编辑器
    taskmgr                             任务管理器
    dcomcnfg                            组件服务
    ```
2. .cpl
    ```
    appwiz.cpl                          程序和功能
    firewall.cpl                        Windows Defender 防火墙
    hdwwiz.cpl                          设备管理器
    sysdm.cpl                           系统属性
    ```
3. .msc
    ```
    comexp.msc                          组件服务
    compmgmt.msc                        计算机管理
    devmgmt.msc                         设备管理器
    diskmgmt.msc                        磁盘管理
    perfmon.msc                         性能监视器
    services.msc                        服务
    ```
4. 环境变量
    ```
    %AppData%                           C:\Users\kingxin\AppData\Roaming
    %LocalAppData%                      C:\Users\kingxin\AppData\Local
    %ProgramData% | %AllUsersProfile%   C:\ProgramData
    %SystemDrive%                       C:\
    %SystemRoot% | %WinDir%             C:\Windows
    %UserProfile% | %HomePath%          C:\Users\kingxin
    ```
5. HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\App Paths
    ```
    chrome                              Google Chrome
    devenv                              Visual Studio 2022
    excel                               Microsoft Excel
    msedge                              Microsoft Edge
    notepad++                           Notepad++
    powershell                          PowerShell
    winword                             Microsoft Word
    xftp                                Xftp
    xshell                              XShell
    
    # 自定义
    idea                                IntelliJ IDEA
    rider                               JetBrains Rider
    vscode                              VS Code
    ```
---
## 更改默认保存位置
1. 方法一：Ctrl + S → 搜索"默认保存位置“
2. 方法二：视频/图片/文档/下载/音乐/桌面 → 右键属性 → 位置
---
