# VS Code Settings

---
## [配置 user-data-dir 和 extensions-dir](https://stackoverflow.com/a/66281688/19598136)
    1. 找到 vscode 快捷方式位置
    2. 右键 → 属性 → 目标
    3. "D:\Microsoft VS Code\Code.exe" --user-data-dir "D:\Microsoft VS Code\data" --extensions-dir "D:\Microsoft VS Code\extensions"
    4. 删除 C:\Users\HP\AppData\Roaming\Code 和 C:\Users\HP\.vscode\extensions
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
