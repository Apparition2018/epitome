# SVN

1. 安装 [TortoiseSVN](https://tortoisesvn.net/downloads.html)
    - 选上 command line client tools
2. 全局 ignore
    1. TortoiseSVN → Settings → General → Subversion 
    2. Subversion configuration file: Edit
    3. `global-ignores = *.o *.lo *.la *.al .libs *.so *.so.[0-9]* *.a *.pyc *.pyo __pycache__ .idea`
3. JetBrains 配置
    1. Settings → Version Control → Subversion
    2. Path to Subversion executable: %TortoiseSVN_HOME%\bin\svn.exe
---