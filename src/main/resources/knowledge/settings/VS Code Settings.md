# VS Code Settings

---
## [配置 user-data-dir 和 extensions-dir](https://stackoverflow.com/a/66281688/19598136)
- 以管理员身份运行 CMD
```bash
mklink /j "C:\Users\Administrator\.vscode\extensions" "D:\Microsoft VS Code\extensions"
mklink /j "C:\Users\Administrator\AppData\Roaming\Code" "D:\Microsoft VS Code\Code"
```
---
## Settings Sync
    1. File → Preferences → Turn on Settings Sync...
    2. Sign in & Turn on → GitHub
---
## settings.json
```json5
{
  // icon theme
  "workbench.iconTheme": "vscode-icons",
  // files
  "files.autoSave": "afterDelay",
  "files.autoGuessEncoding": true,
  "files.associations": {
    "*.wxml": "html",
    "*.wxs": "javascript",
    "*.wxss": "css"
  },
  // git
  "git.path": "D:/Git/cmd/git.exe",
  "git.autofetch": true,
  "search.useGlobalIgnoreFiles": true,
  // terminal
  "terminal.integrated.profiles.windows": {
    "PowerShell": {
      "source": "PowerShell",
      "icon": "terminal-powershell"
    },
    "Command Prompt": {
      "path": [
        "${env:windir}\\Sysnative\\cmd.exe",
        "${env:windir}\\System32\\cmd.exe"
      ],
      "args": [],
      "icon": "terminal-cmd"
    },
    "Git-Bash": {
      "path": "D:\\Git\\bin\\bash.exe",
      "args": []
    },
    "Windows PowerShell": {
      "path": "C:\\WINDOWS\\System32\\WindowsPowerShell\\v1.0\\powershell.exe"
    }
  },
  "terminal.integrated.defaultProfile.windows": "Git-Bash",
  "terminal.integrated.cursorBlinking": true,
  "terminal.integrated.cursorStyle": "line",
  // editor
  "editor.guides.bracketPairs": true,
  "editor.wordWrap": "on",
  "editor.mouseWheelZoom": true,
  "editor.snippetSuggestions": "top",
  "editor.acceptSuggestionOnEnter": "off",
  // eslint
  "editor.codeActionsOnSave": {
    "source.fixAll.eslint": "explicit"
  },
  "eslint.validate": ["javascript"],
  // prettier: https://prettier.io/docs/en/
  "editor.formatOnSave": true,
  "editor.defaultFormatter": "esbenp.prettier-vscode",
  // editor formatter
  "[java]": {
    "editor.defaultFormatter": "redhat.java"
  },
  // emmet
  "emmet.includeLanguages": {
    "vue-html": "html",
    "javascript": "javascriptreact"
  },
  "emmet.triggerExpansionOnTab": true,
  "emmet.variables": {
    "lang": "zh-CN"
  },
  // debug
  "debug.showBreakpointsInOverviewRuler": true,
  // project manager
  "projectManager.git.baseFolders": ["D:\\Liang\\git"],
  // colorize
  "colorize.colorized_variables": ["CSS"],
  "colorize.languages": ["javascript"],
  // java
  "java.configuration.runtimes": [
    {
      "name": "JavaSE-1.8",
      "path": "D:\\Java\\jdk-1.8"
    },
    {
      "name": "JavaSE-17",
      "path": "D:\\Java\\jdk-17",
      "default": true
    }
  ],
  "java.configuration.updateBuildConfiguration": "automatic",
  "java.configuration.maven.globalSettings": "D:\\dev\\.maven\\settings.xml",
  "java.configuration.maven.userSettings": "D:\\dev\\.maven\\settings.xml",
  "java.compile.nullAnalysis.mode": "automatic",
  "java.format.settings.url": "https://raw.githubusercontent.com/google/styleguide/gh-pages/intellij-java-google-style.xml",
  "java.format.settings.profile": "GoogleStyle",
  "java.jdt.ls.vmargs": "-Xmx2G -Xms1G -javaagent:D:\\dev\\.maven\\repository\\org\\projectlombok\\lombok\\1.18.30\\lombok-1.18.30.jar",
  // remote
  "remote.SSH.remotePlatform": {
    "43.136.102.115": "linux"
  },
  // gitlens
  "gitlens.views.commitDetails.files.layout": "tree",
  // rsp-ui
  "rsp-ui.enableStartServerOnActivation": [
    {
      "id": "redhat.vscode-community-server-connector",
      "name": "Community Server Connector",
      "startOnActivation": true
    }
  ],
  // tabnine
  "tabnine.experimentalAutoImports": true,
  // code-runner
  "code-runner.executorMap": {
    "java": "cd $dir && javac -encoding utf8 $fileName && java -Dfile.encoding=UTF-8 $fileNameWithoutExt"
  }
}
```
---
## [Tasks](https://code.visualstudio.com/docs/editor/tasks)
- [通过任务使用 Chrome 打开 HTML](https://blog.csdn.net/u010510187/article/details/96635089)
    - Ctrl + Shift + P → Configure Task → 使用模板创建 task.json 文件 → Others
    - 打开任意 HTML 文件 → Ctrl + Shift + B
```json5
{
  // See https://go.microsoft.com/fwlink/?LinkId=733558
  // for the documentation about the tasks.json format
  "version": "2.0.0",
  "tasks": [
    {
      "label": "Run HTML file with Chrome",
      // 任务类型：shell/process
      "type": "process",
      "command": "chrome",
      "args": [
        // 预定义变量：https://code.visualstudio.com/docs/editor/variables-reference
        // 当前打开文件
        "${file}"
      ],
      "windows": {
        "command": "C:/Program Files/Google/Chrome/Application/chrome.exe"
      },
      "group": "build",
      // 任务输出
      "presentation": {
        // 控制是否将集成终端面板移到前面：always/never/silent
        "reveal": "never"
      }
    }
  ]
}
```
---
