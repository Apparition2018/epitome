# Chocolatey
- 适用于 Windows 的包管理器
---
## Reference
1. [Chocolatey - The package manager for Windows](https://chocolatey.org/)
2. [软件包管理工具 CHOCOLATEY](https://www.bilibili.com/video/BV1DV4y1j71j/?vd_source=3e8925b2b7b3d7129d2a71e05ac6db6b)
---
## [Install](https://docs.chocolatey.org/en-us/choco/setup#install-with-powershell.exe)
1. 以管理员身份运行 Windows PowerShell
2. 运行命令：`Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))`
3. 等待命令完成后键入：`choco`
---
## [Uninstall](https://docs.chocolatey.org/en-us/choco/uninstallation)
1. [删除文件夹](https://docs.chocolatey.org/en-us/choco/uninstallation#script)：C:\ProgramData\chocolatey or $env:ChocolateyInstall
2. [删除环境变量](https://docs.chocolatey.org/en-us/choco/uninstallation#environment-variables)
    - ChocolateyInstall
    - ChocolateyToolsLocation
    - ChocolateyLastPathUpdate
    - PATH (will need updated to remove)
---
## [Search Packages](https://community.chocolatey.org/packages)

---
## [Commands](https://docs.chocolatey.org/en-us/choco/commands/)
```
choco list <filter>                                     列出本地 packages
choco install <pkg|packages.config> [<pkg2> <pkgN>]     安装 packages，可以指定 packages.config
    choco install chocolateygui -y                      安装 Chocolatey GUI
choco export                                            将所有当前安装的 packages 导出到文件中，可以使用 choco install packages.config 重新安装这些 packages
choco uninstall <pkg|all> [pkg2 pkgN]                   卸载 packages
choco upgrade <pkg|all> [<pkg2> <pkgN>]                 升级 packages
choco search <filter>                                   在本地或远程搜索 packages
```
---