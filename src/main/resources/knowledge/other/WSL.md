# WSL

---
## Reference
1. [WSL | Microsoft Docs](https://docs.microsoft.com/zh-cn/windows/wsl/)
---
## [WSL 1 vs WSL 2](https://docs.microsoft.com/zh-cn/windows/wsl/compare-versions)
| 功能                                | WSL 1 | WSL 2 |
|:----------------------------------|:------|:------|
| Windows 和 Linux 之间的集成             | ✓     | ✓     |
| 启动时间短                             | ✓     | ✓     |
| 与传统虚拟机相比，占用资源量少                   | ✓     | ✓     |
| 可以与当前版本的 VMware 和 VirtualBox 一起运行 | ✓     | ✓     |
| 托管 VM                             | ✕     | ✓     |
| 完整的 Linux 内核                      | ✕     | ✓     |
| 完全的系统调用兼容性                        | ✕     | ✓     |
| 跨 OS 文件系统的性能                      | ✓     | ✕     |
---
## [Basic commands](https://docs.microsoft.com/zh-cn/windows/wsl/basic-commands)
```
wsl --install                                               安装
wsl --install --distribution <Distribution Name>            安装特定的 Linux 发行版
wsl --list --online         wsl -l -o                       列出可用的 Linux 发行版
wsl --list --verbose        wsl -l -v                       列出已安装的 Linux 发行版
wsl --set-version <distribution name> <versionNumber>       将 WSL 版本设置为 1 或 2
wsl --set-default-version <Version>                         设置默认 WSL 版本
wsl --set-default <Distribution Name>                       设置默认 Linux 发行版
wsl ~                                                       将目录更改为主页
wsl --distribution <Distribution Name> --user <User Name>   通过 PowerShell 或 CMD 运行特定的 Linux 发行版
wsl --update                                                更新 WSL
wsl --status                                                检查 WSL 状态
wsl --user <Username>       wsl -u <Username>               以特定用户的身份运行
wsl --shutdown                                              关闭
wsl --terminate <Distribution Name>                         Terminate
wsl --export <Distribution Name> <FileName>                 将发行版导出到 TAR 文件
wsl --import <Distribution Name> <InstallLocation> <FileName> 导入新发行版
wsl --unregister <DistributionName>                         注销或卸载 Linux 发行版
```
---
## [安装 Ubuntu 并更改位置](https://www.bilibili.com/read/cv10280220)
```
1. [最佳安装实践](https://docs.microsoft.com/zh-cn/windows/wsl/setup/environment)
2. Microsoft Store 安装 Ubuntu
3. wsl -l -v
4. wsl --export Ubuntu e:\Ubuntu.tar
5. wsl --import Ubuntu e:\Ubuntu e:\Ubuntu.tar --version 2
6. ubuntu config --default-user ljh
7. del e:\Ubuntu.tar
```
---
## 基本使用
1. 更新和升级包：定期执行 `sudo apt update && sudo apt upgrade`
2. 在 Windows 文件资源管理器中查看当前目录：`explorer.exe .`
---