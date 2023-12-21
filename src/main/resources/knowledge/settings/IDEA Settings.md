# [IDEA Settings](https://www.jetbrains.com/help/idea/settings-preferences-dialog.html)
- -j: JetBrains
- -n: New
- -e: Every Time
---
## 修改 .IntelliJIdea 位置
    1. %IDEA_HOME%\bin\idea.properties
        idea.config.path=D:/JetBrains/.IntelliJIdea/config
        idea.system.path=D:/JetBrains/.IntelliJIdea/system
        idea.plugins.path=${idea.config.path}/plugins
        idea.log.path=${idea.system.path}/log
    2. 退出 IDEA 并删除原 IntelliJIdea 位置
        2.1 删除 C:\Users\Administrator\AppData\Local\JetBrains\IntelliJIdea
        2.2 删除 C:\Users\Administrator\AppData\Roaming\JetBrains\IntelliJIdea
---
## 自定义 VM Options
    1. D:\JetBrains\jetbra\vmoptions\idea.vmoptions
        -Xms1024m
        -Xmx2048m
        -Dfile.encoding=UTF-8
        -Drebel.base=D:\JetBrains\.IntelliJIdea\config\plugins\.jrebel
---
## Manage IDE Settings (-j)
    1. Settings Sync → Enable Settings Sync…
---
## Build Tools (-n)
    1. Settings → Build, Execution, Deployment → Build Tools
    2. Maven
        2.1 User settings file: D:\dev\.maven\settings.xml
    3. Gradle
        3.1 Gradle user home: D:\dev\.gradle
        3.2 Build and run using: IntelliJ IDEA
        3.3 Run tests using: IntelliJ IDEA
---
## Virtual Space & On Save (-j)
    1. Settings → Editor → General
    2. Virtual Space
        2.1 Allow caret placement: After the end of line 取消勾选
    3. On Save
        3.1 Remove trailing spaces on: Modified Lines 勾选
        3.2 Keep trailing spaces on caret line 勾选
        3.3 Ensure every saved files ends with a line break 勾选
---
## Auto Import
    1. Settings → Editor → General → Auto Import → Java
    2. Add unambiguous imports on the fly 勾选
    3. Optimize imports on the fly 勾选
---
## Code Folding (-j)
    1. Settings → Editor → General → Code Folding
    2. Fold by default: → General → Imports 取消勾选
---
## Editor Tabs
    1. Settings → Editor → General → Editor Tabs
    2. Show tabs in: one row, and if tabs don't fit: Squeeze tabs
---
## Font
    1. Settings → Editor → Font
    2. Typography Settings → Fallback font: Microsoft YaHei
---
## Code Style (-jn)
    # https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml
    1. Settings → Editor → Code Style
    2. Java → JavaDoc
        2.1 Other → Do not wrap one line comments 勾选
    3. Java → Code Generation → Comment Code
       HTML/JavaScript/XML → Code Generation → Comments
       Style Sheets → CSS/Less/Sass/SCSS → Other → Comments
        3.1 Line comment at first column 取消勾选
        3.2 Add a space at line comment start 勾选
        3.3 Add spaces around block comments 勾选
    4. Style Sheets → CSS/Less/SCSS → Other
        4.1 Keep single-line blocks 勾选
    5. JavaScript → Set from… → JavaScript Standard Style
    6. HTML → Other
        6.1 Set from… → JavaScript
        6.2 Wrap text 取消勾选
    7. Markdown → Blank Lines → 全部设为0
---
## Inspections (-n)
    1. Settings → Editor → Inspections
    2. 搜索 serialVersionUID
        2.1 'serialVersionUID' field not declared 'private static final long' 勾选
        2.2 Serializable class without 'serialVersionUID' 勾选
    3. Java → Naming conventions → Class → class naming convention 取消勾选
---
## File and Code Templates (-n)
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
## File Encodings (-jn)
    1. Settings → Editor → File Encodings
    2. Project Encoding: UTF-8
    3. Default encoding for properties files: UTF-8
---
## TODO
    1. Settings → Editor → TODO → Patterns
        1.1 Patterns → +
        1.2 Pattern: \btodo\(ljh\).*
        1.3 Use color scheme TODO default colors 取消勾选
        1.4 Bold 勾选, Italic 取消勾选
        1.5 Foreground #CC0033
    2. Settings → Editor → Live Templates
        2.1 user → +
        2.2 Abbreviation: todoljh
        2.3 Description: TODO(LJH)
        2.4 Template text: // TODO(LJH): $date$ $todo$
        2.5 Edit variables: Name:date → Expression:date()
        2.6 Define/Change
            2.6.1 Java → Declaration, Statement 勾选
            2.6.2 JavaScript and TypeScript → Statement → Other 勾选
---
## Version Control (-jn)
    1. Settings → Version Control
        1.1 Git → Path to Git executable: D:\Git\cmd\git.exe
        1.2 Subversion → Path to Subversion executable: D:\TortoiseSVN\bin\svn.exe
---
## Compiler (-n)
    1. Settings → Build, Execution, Deployment
        1.1 Compiler
            1.1.1 Build project automatically 勾选
            1.1.2 Shared build process VM options:
                -javaagent:D:\dev\.maven\repository\org\projectlombok\lombok\1.18.30\lombok-1.18.30.jar
            1.1.3 Java Compiler → Use compiler: Eclipse
            1.1.4 Annotation Processors → Enable annotation processing 勾选
    2. Settings → Advanced Settings → Compiler
        2.1 Allow auto-make to start even if developed application is currently running 勾选
---
## Deployment (-jn)
    1. Settings → Build, Execution, Deployment → Deployment
        1.1 + → SFTP → New server name：43.1356.102.115 → SSH configuration
            Host: 43.136.102.115
            Username: root
            Authentication type: Password
            password: Cesc123!
        1.2 Root path: /
        1.3 Advanced → Encoding for client-server communication: UTF-8
        1.4 Mappings
            Local path: D:\Liang\git\epitome\OpenCloudOS8.6
            Deployment path: /home/lighthouse/sync_dir
    2. Tools → Deployment → Sync With Local…
---
## Actions on Save (-n)
    1. Settings → Tools → Actions on Save
    2. Reformat code 勾选
    3. Optimize imports 勾选
---
## Terminal (-jn)
    1. Settings → Tools → Terminal
    2. Shell path: D:\Git\bin\bash.exe
---
## JRebel & XRebel
    1. 下载 ReverseProxy：https://github.com/ilanyu/ReverseProxy/releases
    2. Generate GUIDs online：https://www.guidgen.com/
    3. http://127.0.0.1:8888/{guid}
    4. Settings → JRebel & XRebel → Work offline
    5. View → Tool Windows → JRebel → 勾选模块自动生成 rebel.xml → <classpath/> 指向编译输出路径
    6. 修改代码后，Ctrl + Shift + F9
---
## JavaScript (-jn)
### [ESLint](https://www.jetbrains.com/help/idea/eslint.html)
    1. npm init @eslint/config
    2. npm i --save-dev eslint eslint-config-standard eslint-config-standard-jsx
        eslint-plugin-promise eslint-plugin-import eslint-plugin-n
        eslint-plugin-html eslint-plugin-jquery eslint-plugin-vue eslint-plugin-react
    3. Settings → Languages & Frameworks → JavaScript → Code Quality Tools → ESLint
        3.1 Manual ESLint configuration 勾选
        3.2 ESLint package: D:\Liang\git\epitome\node_modules\eslint
        3.3 Run for files: **/*.{js,ts,jsx,tsx,html,vue}
        3.4 Run eslint --fix on save 勾选
### [Prettier](https://www.jetbrains.com/help/idea/prettier.html)
    1. npm i --save-dev --save-exact prettier
    2. Settings → Languages & Frameworks → JavaScript → Prettier
        2.1 Manual prettier configuration 勾选
        2.2 Prettier package: D:\Liang\git\epitome\node_modules\prettier
        2.3 Run on 'Reformat Code' action 勾选
        2.4 Run for files: **/*.{html,css,less,scss,sass}
> - [JavaScript Standard Style](https://standardjs.com/)
> - [Google Style Guides | styleguide](https://github.com/google/styleguide)
> - [Google developer documentation style guide](https://developers.google.com/style)
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
### 全局搜索排除 out 和 log 文件夹内容
    1. Project Structure → Modules → Sources
    2. 把 out 和 log 文件夹设为 Excluded Folders
### 一个窗口同时打开两个项目
    1. Project Structure → Modules → + → Import Module
---
