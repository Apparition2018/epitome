# IDEA Settings

---
## 修改 .IntelliJIdea 位置
    1. %IDEA_HOME%\bin\idea.properties
        idea.config.path=%JetBrains%/.IntelliJIdea/config
        idea.system.path=%JetBrains%/.IntelliJIdea/system
        idea.plugins.path=${idea.config.path}/plugins
        idea.log.path=${idea.system.path}/log
    2. 退出 IDEA 并删除原 IntelliJIdea 位置
        2.1 删除 C:\Users\Administrator\AppData\Local\JetBrains\IntelliJIdea
        2.2 删除 C:\Users\Administrator\AppData\Roaming\JetBrains\IntelliJIdea
---
## 安装 IDE Eval Reset
    1. Settings → Plguins → 设置图标 → Manage Plugin Repositories...
        1.1 添加 https://plugins.zhile.io
        1.2 Marketplace → 搜索并安装 IDE Eval Reset
    2. Help → Eval Reset
        2.1 Auto reset before per restart 勾选
---
## 自定义 VM Options
    1. %IDEA_HOME%\bin\idea64.exe.vmoptions
        -Xms1024m
        -Xmx2048m
        -Drebel.base=%JetBrains%\.IntelliJIdea\config\plugins\.jrebel
---
## Sync Settings
    1. File → Sync Settings to JetBrains Account...
    2. File → Manage IDE Settings → IDE Settings Sync → Sync Plugins Silently
---
## [Maven](https://developer.aliyun.com/mvn/guide) (-g)
    1. Settings → Build, Execution, Deployment → Build Tools → Maven
    2. User settings file: D:\dev\apache-maven-3.8.4\conf\settings.xml
    ```xml
    <localRepository>D:\dev\.maven\repository</localRepository>
    <mirror>
        <id>aliyunmaven</id>
        <mirrorOf>*</mirrorOf>
        <name>阿里云公共仓库</name>
        <url>https://maven.aliyun.com/repository/public</url>
    </mirror>
    ```
    3. Use settings from .mvn/maven.config 取消勾选
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
        3.2 Keep trailing spaces on caret line 取消勾选
---
## Code Folding
    1. Settings → Editor → General → Code Folding
    2. Fold by default: → Genral
    3. Imports 取消勾选
---
## Font
    1. Settings → Editor → Font
    2. Typography Settings → Fallback font: SimHei
---
## File and Code Templates (-g)
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
## serialVersionUID (-g)
    1. Settings → Editor → Inspections
    2. 搜索 serialVersionUID
        2.1 'serialVersionUID' field not declared 'private static final long' 勾选
        2.2 Serializable class without 'serialVersionUID' 勾选
---
## TODO
    1. Settings → Editor → TODO
    2. Patterns → +
        2.1 Pattern: \btodo-ljh\b.*
        2.2 Use color scheme TODO default colors 取消勾选
        2.3 Bold 勾选
        2.4 Foreground #CC0033
    3. Settings → Editor → Live Templates
    4. user → +
        4.1 Abbreviation: toduljh
        4.2 Description: TODO-LJH
        4.3 Template text: // TODO-LJH: $date$ $todo$
        4.4 Edit variables
            4.3.1 date → Expression:date()
        4.5 Change → Java → Statement 勾选
---
## Use eclipse compiler (-g)
    1. Settings → Build, Execution, Deployment → Compiler → Java Compiler    
        1.1 Use compiler: Eclipse
    2. Settings → Build, Execution, Deployment → Build Tools → Maven → Importing    
        2.1 Detect compiler automatically 取消勾选
    3. Settings → Build, Execution, Deployment → Compiler
        3.1 Shared build process VM options: -javaagent:%LOMBOK_HOME%/lombok.jar
---
## Enable annotation processing (-g)
    1. Settings → Build, Execution, Deployment → Compiler → Annotation Processors
    2. Enable annotation processing 勾选
---
## HTML do not wrap (-g)
    1. Settings → Editor → Code Style → HTML
    2. Other
    3. Wrap attributes: Do not wrap
---
## ESLint: Missing space before function parentheses (-g)
    1. Settings → Editor → Code Style → JavaScript
    2. Spaces
    3. Before parenthese
    4. Function declaration parentheses 勾选
---
## JavaScript version (-g)
    1. Settings → Languages & Frameworks → JavaScript
    2. JavaScript language version: ECMAScript 6+
---
## Terminal (-g)
    1. Settings → Tools → Terminal
    2. Shell path: %GIT_HOME%\bin\bash.exe
---
## [Project Structure...](https://www.jianshu.com/p/39b2206999e7)
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
## Error running 'XxxApp': Command line is too long
    1. .idea/workspace.xml
    2. 在 <component name="PropertiesComponent"></component> 里添加
        2.1 <property name="dynamic.classpath" value="true" />
---