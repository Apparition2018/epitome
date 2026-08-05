# [WSL](https://docs.microsoft.com/zh-cn/windows/wsl/)

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
wsl --help
wsl --install --distribution|-d <Distribution>              按名称安装分发
wsl --list|-l                                               列出分发
        --online|-o                                         列出可安装分发
        --verbose|-v                                        列出分发详细信息
wsl --set-default-version <Version>                         设置默认 WSL 版本
wsl --set-version <Distribution> <Version>                  设置分发 WSL 版本
wsl --set-default|-s <Distribution>                         设置默认分发
wsl ~                                                       将目录更改为主页
wsl --distribution|-d <Distribution>                        运行指定分发
wsl --user|-u <User>                                        以指定用户运行
wsl --update                                                更新 WSL
        --rollback                                          回滚 WSL
wsl --status                                                显示 WSL 状态
wsl --shutdown                                              终止所有运行分发和 WSL
wsl --terminate|-t <Distribution>                           终止指定分发
wsl --export <Distribution> <File>                          导出分发 (TAR)
wsl --import <Distribution> <InstallLocation> <File>        导入分发 (TAR)
        --version                                           指定导入分发的 WSL 版本
wsl --unregister <Distribution>                             注销或卸载分发
```
---
## [安装 Ubuntu 并更改位置](https://www.bilibili.com/read/cv10280220)
- [最佳安装实践](https://docs.microsoft.com/zh-cn/windows/wsl/setup/environment)
```
1. Microsoft Store 安装 Ubuntu
    安装位置：%LOCALAPPDATA%\Packages\CanonicalGroupLimited
2. wsl -l -v
3. wsl --export Ubuntu e:\ubuntu.tar
4. wsl --unregister Ubuntu
5. wsl --import ubuntu e:\ubuntu e:\ubuntu.tar --version 2
6. ubuntu config --default-user ljh
7. del e:\ubuntu.tar
8. 应用和功能 → 卸载Ubuntu
9. wsl -u ljh 或 bash
```
---
## [.wslconfig](https://docs.microsoft.com/zh-cn/windows/wsl/wsl-config#wslconfig)
```
[wsl2]
memory=2GB
swap=4GB
```
---
## 基本使用
1. 更新和升级包：定期执行 `sudo apt update && sudo apt upgrade`
2. 在 Windows 文件资源管理器中查看当前目录：`explorer.exe .`
---
