# IDEA Settings
---
## 同步设置
    1. File
    2. Sync Settings to JetBrains Account...
    3. File
    4. IDE Settings Sync
    5. Sync Plugins Silently
---
## 字体
    1. Settings → Editor → Font
    2. Enable ligatures (启用字体连写) 勾选
    3. Typography Settings → Fallback font: SimHei
---
## 文件和代码模板
    1. Settings → Editor → File and Code Templates
    2. Files
    3. Class | Interface | Enum | Record | AnnotationType
        /**
         * ${NAME}
         *
         * @author ${USER}
         * created on ${DATE} ${TIME}
         */
---
## 去除尾行空格s
    1. Settings → Editor → General
    2. Virtual Space
        2.1 Allow caret placement: After the end of line 取消勾选
    3. On Save
        3.1 Remove trailing spaces on: Modified Lines 取消勾选
        3.2 Keep trailing spaces on caret line 取消勾选
---
## serialVersionUID
    1. Settings → Editor → Inspections
    2. 搜索并勾选 Serializable class without 'serialVersionUID'，Severity：Weaking Warning
    3. 搜索并勾选 'serialVersionUID' field not declared 'private static final long'
---
## 加快编译速度
>### 调整 JVM 参数
>   ```
>   1. Help → Edit Custom VM Options... | idea64.exe.vmoptions
>   2. 设置 -Xms 和 -Xmx
>   ```
>### Use compiler Eclipse
>   ```
>   1. Settings → Build, Execution, Deployment → Compiler → Java Compiler
>   2. Use compiler: Eclipse
>   3. Settings → Build, Execution, Deployment → Build Tools → Maven → Importing
>   4. Detect compiler automatically 取消勾选
>   ```
---
## HTML 代码不换行
    1. Settings → Editor → Code Style → HTML
    2. Other
    3. Hard wrap at: 240
    4. Wrap attributes: Do not wrap
---
## ESLint: Missing space before function parentheses
    1. Settings → Editor → Code Style → JavaScript
    2. Spaces
    3. Before parenthese
    4. Function declaration parentheses 勾选
---
## 更换编译器
    1. Settings → Build, Execution, Deployment → Compiler → Java Compiler
    2. Use compiler: Eclipse
---
## 启用注解处理
    1. Settings → Build, Execution, Deployment → Compiler → Annotation Processors
    2. Enable annotation processing 勾选
---
## JavaScript version
    1. Settings → Languages & Frameworks → JavaScript
    2. JavaScript language version: ECMAScript 6
---
## Terminal
    1. Settings → Tools → Terminal
    2. Shell path: %GIT_HOME%\bin\bash.exe
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