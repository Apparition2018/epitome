# VS Code Settings

---
## extensions-dir
    1. 找到 vscode 快捷方式位置
    2. 右键 → 属性 → 目标
    3. "D:\Microsoft VS Code\Code.exe" --extensions-dir "D:\dev\.vscode\extensions"
---
## Settings Sync
    1. File → Preferences → Turn on Settings Sync...
    2. Sign in & Turn on → GitHub
---
## Extensions
1. [Marketplace](https://marketplace.visualstudio.com/search?target=VSCode&category=All%20categories&sortBy=Installs)
---
## settings.json
```json5
{
  "files.autoSave": "afterDelay",
  "files.associations": {
    "*.wxml": "html",
    "*.wxs": "javascript",
    "*.wxss": "css"
  },
  // explorer
  "explorer.confirmDragAndDrop": false,
  "explorer.confirmDelete": false,
  // git
  "git.path": "D:/Git/cmd/git.exe",
  // emmet
  "emmet.includeLanguages": {
    "vue-html": "html",
    "javascript": "javascriptreact"
  },
  "emmet.triggerExpansionOnTab": true,
  "emmet.variables": {
    "lang": "zh-CN"
  },
  // Terminal
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
  // java
  "java.configuration.runtimes": [
    {
      "name": "JavaSE-1.8",
      "path": "D:\\Java\\jdk1.8.0_321",
      "default": true
    }
  ],
  // maven
  "java.configuration.maven.globalSettings": "D:\\dev\\.maven\\settings.xml",
  "java.configuration.maven.userSettings": "D:\\dev\\.maven\\settings.xml",
  // lombok
  "java.jdt.ls.vmargs": "-XX:+UseParallelGC -XX:GCTimeRatio=4 -XX:AdaptiveSizePolicyWeight=90 -Dsun.zip.disableMemoryMapping=true -Xmx1G -Xms100m -javaagent:\"D:\\dev\\.vscode\\extensions\\gabrielbb.vscode-lombok-1.0.1\\server\\lombok.jar\"",
  // appworks
  "appworks.materialSources": [],
  // prettier: https://prettier.io/docs/en/
  "editor.formatOnSave": true,
  "editor.defaultFormatter": "esbenp.prettier-vscode",
  "[html]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },
  "[javascript]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },
  "[typescript]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },
  "[javascriptreact]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },
  "[typescriptreact]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },
  "[less]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },
  "[css]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },
  "[json]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },
  "[jsonc]": {
    "editor.defaultFormatter": "esbenp.prettier-vscode"
  },
  // eslint
  "editor.codeActionsOnSave": {
    "source.fixAll.eslint": true
  },
  "eslint.validate": ["javascript"]
}
```
---
