# IDEA Settings
- e: every time
- g: global
- o: optional
- u: unknown
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
        -Drebel.base=D:\JetBrains\.IntelliJIdea\config\plugins\.jrebel
---
## Sync Settings
    1. File → Manage IDE Settings
    2. Sync Settings to JetBrains Account...
    3. IDE Settings Sync → Sync Plugins Silently 勾选
---
## 安装 IDE Eval Reset (-o)
    1. Settings → Plguins → 设置图标 → Manage Plugin Repositories...
        1.1 添加 https://plugins.zhile.io
        1.2 Marketplace → 搜索并安装 IDE Eval Reset
    2. Help → Eval Reset
        2.1 Auto reset before per restart 勾选
---
## Maven (-g|-e)
    1. Settings → Build, Execution, Deployment → Build Tools → Maven
    2. User settings file: D:\dev\.maven\settings.xml
---
## Project/Solution
    1. Settings → Appearance & Behavior → System Settings
    2. Project/Solution
        2.1 Reopen projects on startup 勾选
        2.2 Open project in: New window
        2.3 Default project directory: D:\Liang\git
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
    2. Show tabs in one row 取消勾选
---
## Font (-e)
    1. Settings → Editor → Font
    2. Typography Settings → Fallback font: SimHei
---
## Comment Code
    1. Settings → Editor → Code Style
    2. Java → Code Generation → Comment Code
        2.1 Line comment at first column 取消勾选
        2.2 Add a space at line comment start 勾选
        2.3 Add spaces around block comments 勾选
    3. XML → Code Generation → Comments
        3.1 Line comment at first column 取消勾选
        3.2 Add spaces around block comments 勾选
---
## Inspections (-g|-e)
    1. Settings → Editor → Inspections
    2. 搜索 serialVersionUID
        2.1 'serialVersionUID' field not declared 'private static final long' 勾选
        2.2 Serializable class without 'serialVersionUID' 勾选
    3. Java → Naming conventions → Class → class naming convention 取消勾选 
---
## File and Code Templates (-g|-e)
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
        2.6 Change → Java → Declaration, Statement 勾选
---
## Use eclipse compiler (-g|-e)
    1. Settings → Build, Execution, Deployment
    2. Build Tools → Maven → Importing
        # 2.1 Detect compiler automatically 取消勾选
    3. Compiler
        3.1 Shared build process VM options: 
            -javaagent:D:\dev\.maven\repository\org\projectlombok\lombok\1.18.24\lombok-1.18.24.jar
        3.2 Java Compiler
            3.2.1 Use compiler: Eclipse
        3.3 Annotation Processors
            3.3.1 Enable annotation processing 勾选
---
## JavaScript version
    1. Settings → Languages & Frameworks → JavaScript
    2. JavaScript language version: ECMAScript 6+
---
## [ESLint](https://www.jetbrains.com/help/idea/eslint.html) (-u)
    1. npm install --g eslint
    2. npm install standard --global
    3. Settings → Languages & Frameworks → JavaScript → Code Quality Tools → ESLint
        3.1 Manual ESLint configuration 勾选
        3.2 ESLint package: ~\AppData\Roaming\npm\node_modules\standard
        3.3 Run eslint --fix on save 勾选
---
## [Prettier](https://www.jetbrains.com/help/idea/prettier.html) (-u)
    1. npm install --global prettier
    2. Settings → Languages & Frameworks → JavaScript → Prettier
        2.1 Prettier package: ~\AppData\Roaming\npm\node_modules\prettier
        2.1 On Save 勾选
---
## Actions on Save (-e)
    1. Settings → Tools → Actions on Save
    2. Reformat code 勾选
    3. Optimize imports 勾选
---
## Terminal (-g|-e)
    1. Settings → Tools → Terminal
    2. Shell path: D:\Git\bin\bash.exe
---
## [Jrebel & XRebel](https://javajgs.com/archives/218383)
    1. 下载 ReverseProxy：https://github.com/ilanyu/ReverseProxy/releases/tag/v1.4
    2. Generate GUIDs online：https://www.guidgen.com/
---
## [Project Structure...](https://www.jianshu.com/p/39b2206999e7)
### Modules
    1. Dependencies → + → 2 Library...
    2. Tomcat
### Facets
    1. Web
    2. Deployment Descriptors 配置 web.xml
    3. Web Resource Directories 配置 webapp
### Artifacts
    1. + Web Application: Exploded ▶ From Modules...
    2. Output directory
    3. 把 Available Elements 中需要用到的 jar Put into /WEB-INF/lib，特别是 javax.servlet-api
---
## Error running 'XxxApp': Command line is too long
    1. .idea/workspace.xml
    2. 在 <component name="PropertiesComponent" /> 里添加 
        <property name="dynamic.classpath" value="true" /> 或 "dynamic.classpath": "true"
---
## 一个窗口同时打开两个项目
    1. Project Structure → Modules → + → Import Module
---
