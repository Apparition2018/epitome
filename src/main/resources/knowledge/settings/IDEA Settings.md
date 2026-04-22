# [IDEA Settings](https://www.jetbrains.com/help/idea/settings-preferences-dialog.html)
- j: JetBrains
- n: New Project
- o: Optional
---
### 修改 .IntelliJIdea 位置
    1 %IDEA_HOME%\bin\idea.properties
        idea.config.path=D:/JetBrains/.IntelliJIdea/config
        idea.system.path=D:/JetBrains/.IntelliJIdea/system
        idea.plugins.path=${idea.config.path}/plugins
        idea.log.path=${idea.system.path}/log
    2 退出 IDEA 并删除原 IntelliJIdea 位置
        2.1 删除 C:\Users\Administrator\AppData\Local\JetBrains\IntelliJIdea
        2.2 删除 C:\Users\Administrator\AppData\Roaming\JetBrains\IntelliJIdea
### 自定义 VM Options
    1 ...\vmoptions\idea.vmoptions | Help → Edit Custom VM Options…
        -Xms1024m
        -Xmx2048m
        -XX:ReservedCodeCacheSize=1024m
        -Drebel.base=D:\JetBrains\.IntelliJIdea\config\plugins\.jrebel
---
## Backup and Sync (-j)

---
## Appearance & Behavior
### Appearance （-j)
    1 UI Options → Main menu: Merge with Main Toolbar
---
## Build, Execution, Deployment
### Build Tools
    1 Maven
        1.1 User settings file: D:\dev\.maven\settings.xml
    2 Gradle (-n)
        2.1 Gradle user home: D:\dev\.gradle
        2.2 Build and run using: IntelliJ IDEA
        2.3 Run tests using: IntelliJ IDEA
### Compiler
    1 Compliler
        1.1 Build project automatically 勾选
        1.2 Build Process → Shared VM options:
            -javaagent:D:\dev\.maven\repository\org\projectlombok\lombok\1.18.42\lombok-1.18.42.jar
    2 Annotation Processors
        2.1 Enable annotation processing 勾选
    3 Settings → Advanced Settings → Compiler
        3.1 Allow auto-make to start even if developed application is currently running 勾选
### Deployment (-j)
    1 Deployment
        1.1 + → SFTP → New server name：43.136.102.115 → SSH configuration
            Host: 43.136.102.115
            Username: root
            Authentication type: Password
            password: Cesc123!
        1.2 Root path: /
        1.3 Advanced → Encoding for client-server communication: UTF-8
        1.4 Mappings
            Local path: D:\Liang\git\epitome\OpenCloudOS
            Deployment path: /home/lighthouse/sync_dir
    2 菜单栏 Tools → Deployment → Sync With Local…
---
## Editor
### General (-j)
    1 General → On Save
        1.1 Keep trailing spaces on caet line 取消勾选
        1.2 Remove trailing blank lines at the end of saved files 勾选
        1.3 Ensure every saved files ends with a line 勾选
    2 Auto Import (-no) → Java
        2.1 Add unambiguous imports on the fly 勾选
        2.2 Optimize imports on the fly 勾选
    3 Editor Tabs
        3.1 Show tabs in: Multiple rows
### Font (-j)
    1 Size: 10.0
    2 Typography Settings → Fallback font: Microsoft YaHei
### Code Style (-j)
    # https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml
    1 Java → JavaDoc
        1.1 Other → Do not wrap one line comments 勾选
    2 Java → Code Generation → Comment Code
        HTML/JavaScript/XML → Code Generation → Comments
        Style Sheets → CSS/Less/Sass/SCSS → Other → Comments
        2.1 Line comment at first column 取消勾选
        2.2 Add a space at line comment start 勾选
            2.2.1 Enforce on reformat 勾选
        2.3 Block comment at first column 取消勾选
        2.4 Add spaces around block comments 勾选
    3 Style Sheets → CSS/Less/SCSS → Other
        3.1 Keep single-line blocks 勾选
    4 JavaScript → Set from… → JavaScript Standard Style
    5 HTML → Set from… → JavaScript
    6 Markdown → Blank Lines → 全部设为0
### Inspections
    1 Java
        1.1 Naming conventions → Class → Class naming convention → Highlighting in editor: Weak Warning
        1.2 Probable bugs → Result of method call ignored → Highlighting in editor: Weak Warning
    2 JavaScript and TypeScript → Code quality tools → ESLint 勾选
### File and Code Templates
    1 Files
    2 AnnotationType | Class | Enum | Interface | Record
        /**
         * ${NAME}
         *
         * @author ${USER}
         * @since ${DATE} ${TIME}
         */
### File Encodings (-j)
    1 Global Encoding: UTF-8
    2 Project Encoding: UTF-8
    3 Default encoding for properties files: UTF-8
    4 Transparaent native-to-ascii conversion 勾选
### TODO
    1 TODO
        1.1 Patterns → +
        1.2 Pattern: \btodo\(ljh\).*
        1.3 Use color scheme TODO default colors 取消勾选
        1.4 Bold 勾选, Italic 取消勾选
        1.5 Foreground #CC0033
    2 Settings → Editor → Live Templates
        2.1 user → +
        2.2 Abbreviation: todoljh
        2.3 Description: TODO(LJH)
        2.4 Template text: // TODO(LJH): $date$ $todo$
        2.5 Edit variables: Name:date → Expression:date()
        2.6 Define/Change
            2.6.1 Java → Declaration, Statement 勾选
            2.6.2 JavaScript/TypeScript → Statement → Other 勾选
---
## Version Control (-j)
    1 Git → Path to Git executable: D:\Git\cmd\git.exe
    2 Subversion → Path to Subversion executable: D:\TortoiseSVN\bin\svn.exe
---
## Tools
### Actions on Save (-jo)
    1 Reformat code 勾选
    2 Optimize imports 勾选
### Terminal (-jo)
    1 Shell path: D:\Git\bin\bash.exe
---
## Languages & Frameworks
### JavaScript (-jn)
#### [ESLint](https://www.jetbrains.com/help/idea/eslint.html)
    1 npm init @eslint/config
    2 npm i --save-dev eslint eslint-config-standard eslint-config-standard-jsx \
        eslint-plugin-promise eslint-plugin-import eslint-plugin-n \
        eslint-plugin-html eslint-plugin-jquery eslint-plugin-vue eslint-plugin-react
    3 Code Quality Tools → ESLint
        3.1 Manual ESLint configuration 勾选
            ESLint package: D:\Liang\git\epitome\node_modules\eslint
        3.2 Run eslint --fix on save 勾选（主动格式化 触发 Prettier，再主动保存触发 eslint --fix，修复 Prettier 无法实现的 space-before-function-paren）
#### [Prettier](https://www.jetbrains.com/help/idea/prettier.html)
    1 npm i --save-dev --save-exact prettier
    2 Prettier
        2.1 Manual prettier configuration 勾选
            Prettier package: D:\Liang\git\epitome\node_modules\prettier
        2.2 Run on 'Reformat Code' action 勾选
        2.3 Run for files: **/*.{js,ts,jsx,tsx,cjs,cts,mjs,mts,vue,astro,css,scss,html,json}
> - [JavaScript Standard Style](https://standardjs.com/)
> - [Google Style Guides | styleguide](https://github.com/google/styleguide)
> - [Google developer documentation style guide](https://developers.google.com/style)
---
## JRebel & XRebel
    1 下载 ReverseProxy：https://github.com/ilanyu/ReverseProxy/releases
        - 后备：https://github.com/yu-xiaoyao/jrebel-license-active-server/releases
    2 Generate GUIDs online：https://www.guidgen.com/
    3 http://127.0.0.1:8888/{guid}
    4 JRebel & XRebel → Work offline
    5 View → Tool Windows → JRebel → 勾选模块自动生成 rebel.xml → <classpath/> 指向编译输出路径
    6 修改代码后，Ctrl + Shift + F9
---
## [Project Structure…](https://www.jianshu.com/p/39b2206999e7)
### Modules
    1 Dependencies → + → 2 Library…
    2 Tomcat
### Facets
    1 Web
    2 Deployment Descriptors 配置 web.xml
    3 Web Resource Directories 配置 web
### Artifacts
    1 + Web Application: Exploded ▶ From Modules…
    2 Output directory
    3 把 Available Elements 中需要用到的 jar Put into /WEB-INF/lib，特别是 javax.servlet-api
---
## Other
### Error running 'XxxApp': Command line is too long
    1 .idea/workspace.xml
    2 在 <component name="PropertiesComponent" /> 里添加
        <property name="dynamic.classpath" value="true" /> 或 "dynamic.classpath": "true"
### 运行 main 方法或 @Test 避免编译整个项目
    1 Edit Configurations… → Edit configuration templates…
    2 Applicatoin/JUnit → Modify options
        2.1 Java → Do not build before run 勾选
        2.2 Before Lauch → Add before launch task 勾选 → Build, no error check
### 全局搜索排除 out 和 log 文件夹内容
    1 Project Structure → Modules → Sources
    2 把 out 和 log 文件夹设为 Excluded Folders
### 一个窗口同时打开两个项目
    1 Project Structure → Modules → + → Import Module
---
