# IDEA Settings
- -g: global
- -o: optional
- -u: unknown
---
## 修改 .IntelliJIdea 位置 (-o)
    1. %IDEA_HOME%\bin\idea.properties
        idea.config.path=D:/JetBrains/.IntelliJIdea/config
        idea.system.path=D:/JetBrains/.IntelliJIdea/system
        idea.plugins.path=${idea.config.path}/plugins
        idea.log.path=${idea.system.path}/log
    2. 退出 IDEA 并删除原 IntelliJIdea 位置
        2.1 删除 C:\Users\Administrator\AppData\Local\JetBrains\IntelliJIdea
        2.2 删除 C:\Users\Administrator\AppData\Roaming\JetBrains\IntelliJIdea
---
## 自定义 VM Options (-o)
    1. %IDEA_HOME%\bin\idea64.exe.vmoptions
        -Xms1024m
        -Xmx2048m
        -Dfile.encoding=UTF-8
        -Drebel.base=D:\JetBrains\.IntelliJIdea\config\plugins\.jrebel
---
## Sync Settings
    1. Settings → Settings Sync
    2. Enable Settings Sync…
---
## 安装 IDE Eval Reset (-o)
    1. Settings → Plguins → 设置图标 → Manage Plugin Repositories…
        1.1 添加 https://plugins.zhile.io
        1.2 Marketplace → 搜索并安装 IDE Eval Reset
    2. Help → Eval Reset
        2.1 Auto reset before per restart 勾选
---
## Build Tools (-g)
    1. Settings → Build, Execution, Deployment → Build Tools → Maven
    2. Maven
        2.1 User settings file: D:\dev\.maven\settings.xml
    3. Gradle D:\dev\.gradle
---
## Trailing Spaces
    1. Settings → Editor → General
    2. Virtual Space
        2.1 Allow caret placement: After the end of line 取消勾选
    3. On Save
        3.1 Remove trailing spaces on: Modified Lines 勾选
        3.2 Keep trailing spaces on caret line 勾选
---
## Auto Import
    1. Settings → Editor → General → Auto Import → Java
    2. Add unambiguous imports on the fly 勾选
    3. Optimeze imports on the fly 勾选
---
## Code Folding
    1. Settings → Editor → General → Code Folding
    2. Fold by default: → Genral → Imports 取消勾选
---
## Editor Tabs (-o)
    1. Settings → Editor → General → Editor Tabs
    2. Show tabs in: one row, and if tabs don't fit: Squeeze tabs
---
## Font (-o)
    1. Settings → Editor → Font
    2. Typography Settings → Fallback font: SimHei
---
## Code Style (-g)
    1. Settings → Editor → Code Style
    2. Java → JavaDoc
        2.1 Other → Do not wrap one line comments 勾选
    3. Java → Code Generation → Comment Code
        3.1 Line comment at first column 取消勾选
        3.2 Add a space at line comment start 勾选
        3.3 Add spaces around block comments 勾选
    4. HTML/JavaScript/XML → Code Generation → Comments
        4.1 Line comment at first column 取消勾选
        4.2 Add spaces around block comments 勾选
    5. Style Sheets → CSS/Less/Sass/SCSS → Other → Comments
        5.1 Line comment at first column 取消勾选
        5.2 Add a space at line comment start 勾选
        5.3 Add spaces around block comments 勾选
    6. Markdown → Tabs and Indents 全部设为0
---
## Inspections (-g)
    1. Settings → Editor → Inspections
    2. 搜索 serialVersionUID
        2.1 'serialVersionUID' field not declared 'private static final long' 勾选
        2.2 Serializable class without 'serialVersionUID' 勾选
    3. Java → Naming conventions → Class → class naming convention 取消勾选
---
## File and Code Templates (-g)
    1. Settings → Editor → File and Code Templates
    2. Files
    3. Class | Interface | Enum | Record | AnnotationType
        /**
         * ${NAME}
         *
         * @author ${USER}
         * @since ${DATE} ${TIME}
         */
---
## File Encodings (-g)
    1. Settings → Editor → File Encodings
    2. Project Encoding: UTF-8
    3. Default encoding for properties files: UTF-8
---
## TODO
    1. Settings → Editor → TODO → Patterns
        1.1 Patterns → +
        1.2 Pattern: \btodo-ljh\b.*
        1.3 Use color scheme TODO default colors 取消勾选
        1.4 Bold 勾选, Italic 取消勾选
        1.5 Foreground #CC0033
    2. Settings → Editor → Live Templates
        2.1 user → +
        2.2 Abbreviation: todoljh
        2.3 Description: TODO-LJH
        2.4 Template text: // TODO-LJH: $date$ $todo$
        2.5 Edit variables: Name:date → Expression:date()
        2.6 Define/Change
            2.6.1 Java → Declaration, Statement 勾选
            2.6.2 JavaScript and TypeScript → Statement → Other 勾选
---
## Compiler (-g)
    1. Settings → Build, Execution, Deployment
    2. Build Tools → Maven → Importing
        2.1 Detect compiler automatically 取消勾选 ???
    3. Compiler
        3.1 Shared build process VM options: 
            -javaagent:D:\dev\.maven\repository\org\projectlombok\lombok\1.18.26\lombok-1.18.26.jar
        3.2 Java Compiler
            3.2.1 Use compiler: Eclipse
        3.3 Annotation Processors
            3.3.1 Enable annotation processing 勾选
---
## Actions on Save (-g)
    1. Settings → Tools → Actions on Save
    2. Reformat code 勾选
    3. Optimize imports 勾选
---
## Terminal (-g)
    1. Settings → Tools → Terminal
    2. Shell path: D:\Git\bin\bash.exe
---
## JRebel & XRebel
    1. 下载 ReverseProxy：https://github.com/ilanyu/ReverseProxy/releases/tag/v1.4
    2. Generate GUIDs online：https://www.guidgen.com/
    3. http://127.0.0.1:8888/{guid}
    4. Settings → JRebel & XRebel → Work offline
    5. View → Tool Windows → JRebel，勾选模块
    6. 修改代码后，Ctrl + Shift + F9
---
## [Project Structure…](https://www.jianshu.com/p/39b2206999e7)
### Modules
    1. Dependencies → + → 2 Library…
    2. Tomcat
### Facets
    1. Web
    2. Deployment Descriptors 配置 web.xml
    3. Web Resource Directories 配置 webapp
### Artifacts
    1. + Web Application: Exploded ▶ From Modules…
    2. Output directory
    3. 把 Available Elements 中需要用到的 jar Put into /WEB-INF/lib，特别是 javax.servlet-api
---
## npm
### [ESLint](https://www.jetbrains.com/help/idea/eslint.html) (-u)
    1. npm install --g eslint
    2. npm install standard --global
    3. Settings → Languages & Frameworks → JavaScript → Code Quality Tools → ESLint
        3.1 Manual ESLint configuration 勾选
        3.2 ESLint package: ~\AppData\Roaming\npm\node_modules\standard
        3.3 Run eslint --fix on save 勾选
### [Prettier](https://www.jetbrains.com/help/idea/prettier.html) (-u)
    1. npm install --global prettier
    2. Settings → Languages & Frameworks → JavaScript → Prettier
        2.1 Prettier package: ~\AppData\Roaming\npm\node_modules\prettier
        2.1 On Save 勾选
---
## Tools
### Deployment
    1. Tools → Deployment → Configuration
    2. + → SFTP → New server name：test → SSH configuration
        Host: 43.136.102.115
        Username: root
        Authenication type: Password
        password: Cesc123!
    3. Mappings
        Local path: D:\OpenCloudOS
        Deployment path: /home
    4. Tools → Deployment → Sync With Local…
---
## Other
### Error running 'XxxApp': Command line is too long
    1. .idea/workspace.xml
    2. 在 <component name="PropertiesComponent" /> 里添加
        <property name="dynamic.classpath" value="true" /> 或 "dynamic.classpath": "true"
### 运行 main 方法或 @Test 避免编译整个项目
    1. Edit Configurations… → Edit configuration templates…
    2. Applicatoin/JUnit → Modify options
        2.1 Java → Do not build before run 勾选
        2.2 Before Lauch → Add before launch task 勾选 → Build, no error check
### 一个窗口同时打开两个项目
    1. Project Structure → Modules → + → Import Module
---
