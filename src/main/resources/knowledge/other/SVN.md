# SVN
    1. 安装 [TortoiseSVN](https://tortoisesvn.net/downloads.html)
        - 选上 command line client tools
    2. 全局 ignore
        2.1 TortoiseSVN → Settings → General → Subversion 
        2.2 Subversion configuration file: Edit
        2.3 `global-ignores = *.o *.lo *.la *.al .libs *.so *.so.[0-9]* *.a *.pyc *.pyo __pycache__ .idea logs`
    3. JetBrains 配置
        3.1 Settings → Version Control → Subversion
        3.2 Path to Subversion executable: %TortoiseSVN_HOME%\bin\svn.exe
---
## IDEA
    1. Settings → Version Control → Subversion
    2. Path to Subversion executable: D:\TortoiseSVN\bin\svn.exe
---
## Rider
    1. Extensions: SVN
    2. 
---