# IDEA Settings
---
## 同步设置
    1. File
    2. Sync Settings to JetBrains Account...
    3. File
    4. IDE Settings Sync
    5. Sync Plugins Silently
---
## 文件和代码模板
    1. Settings → Editor → File and Code Templates
    2. Files
    3. Class / Interface / Enum / AnnotationType
    
    /**
     * ${NAME}
     *
     * @author ${USER}
     * created on ${DATE} ${TIME}
     */
---
## 去除尾行空格
    1. Settings → Editor → General
    2. Virtual Space
        2.1 Allow placement of caret after end of line 取消勾选
    3. Other
        2.1 Strip trailing spaces on Save: Modified Lines
        2.2 Always keep trailing spaces on caret line 取消勾选
---
## HTML 代码不换行
    1. Settings → Editor → Code Style → HTML
    2. Other
    3. Hard wrap at: 240
    4. Wrap attributes: Do not wrap
---
## Terminal
    1. Settings → Tools → Terminal
    2. Shell path
---
## JavaScript version
    1. Settings → Languages & Frameworks → JavaScript
    2. JavaScript language version
---
## [Project Settings](https://www.jianshu.com/p/39b2206999e7)
>### Facets
>   1. Project Structure → Facets
>   2. Web
>   3. Deployment Descriptors 配置 web.xml
>   4. Web Resource Directories 配置 webapp
>### Artifacts
>   1. Project Structure → Artifacts
>   2. \+ Web Application: Exploded ▶ From Modules...
>   3. Output directory: xxx\target
>   4. 把 Available Elements 中需要用到的 jar Put into /WEB-INF/lib，特别是 javax.servlet-api
---