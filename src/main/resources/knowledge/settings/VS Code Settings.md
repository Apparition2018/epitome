# VS Code Settings

---
## Reference
1. [VS Code 用法大全](https://space.bilibili.com/337242418/channel/collectiondetail?sid=1815914)
2. [VS Code + ESLint + Prettier + Git Hooks](https://zhuanlan.zhihu.com/p/444925446)
3. [Syntax Highlighting](https://babeljs.io/docs/en/editors/#visual-studio-code)
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
  "files.autoSave": "afterDelay",
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
  // editor formatter
  "[xml]": {
    "editor.defaultFormatter": "redhat.vscode-xml"
  },
  "[java]": {
    "editor.defaultFormatter": "redhat.java"
  },
  // remote
  "remote.SSH.remotePlatform": {
    "43.136.102.115": "linux"
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
  // eslint
  "editor.codeActionsOnSave": {
    "source.fixAll.eslint": true
  },
  "eslint.validate": ["javascript"],
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
  // icon theme
  "workbench.iconTheme": "vscode-icons",
  // project manager
  "projectManager.git.baseFolders": [
    "D:\\Liang\\git"
  ],
  // tabnine
  "tabnine.experimentalAutoImports": true,
  // code-runner
  "code-runner.executorMap": {
    "javascript": "node",
    "java": "cd $dir && javac -encoding utf8 $fileName && java -Dfile.encoding=UTF-8 $fileNameWithoutExt",
    "c": "cd $dir && gcc $fileName -o $fileNameWithoutExt && $dir$fileNameWithoutExt",
    "zig": "zig run",
    "cpp": "cd $dir && g++ $fileName -o $fileNameWithoutExt && $dir$fileNameWithoutExt",
    "objective-c": "cd $dir && gcc -framework Cocoa $fileName -o $fileNameWithoutExt && $dir$fileNameWithoutExt",
    "php": "php",
    "python": "python -u",
    "perl": "perl",
    "perl6": "perl6",
    "ruby": "ruby",
    "go": "go run",
    "lua": "lua",
    "groovy": "groovy",
    "powershell": "powershell -ExecutionPolicy ByPass -File",
    "bat": "cmd /c",
    "shellscript": "bash",
    "fsharp": "fsi",
    "csharp": "scriptcs",
    "vbscript": "cscript //Nologo",
    "typescript": "ts-node",
    "coffeescript": "coffee",
    "scala": "scala",
    "swift": "swift",
    "julia": "julia",
    "crystal": "crystal",
    "ocaml": "ocaml",
    "r": "Rscript",
    "applescript": "osascript",
    "clojure": "lein exec",
    "haxe": "haxe --cwd $dirWithoutTrailingSlash --run $fileNameWithoutExt",
    "rust": "cd $dir && rustc $fileName && $dir$fileNameWithoutExt",
    "racket": "racket",
    "scheme": "csi -script",
    "ahk": "autohotkey",
    "autoit": "autoit3",
    "dart": "dart",
    "pascal": "cd $dir && fpc $fileName && $dir$fileNameWithoutExt",
    "d": "cd $dir && dmd $fileName && $dir$fileNameWithoutExt",
    "haskell": "runghc",
    "nim": "nim compile --verbosity:0 --hints:off --run",
    "lisp": "sbcl --script",
    "kit": "kitc --run",
    "v": "v run",
    "sass": "sass --style expanded",
    "scss": "scss --style expanded",
    "less": "cd $dir && lessc $fileName $fileNameWithoutExt.css",
    "FortranFreeForm": "cd $dir && gfortran $fileName -o $fileNameWithoutExt && $dir$fileNameWithoutExt",
    "fortran-modern": "cd $dir && gfortran $fileName -o $fileNameWithoutExt && $dir$fileNameWithoutExt",
    "fortran_fixed-form": "cd $dir && gfortran $fileName -o $fileNameWithoutExt && $dir$fileNameWithoutExt",
    "fortran": "cd $dir && gfortran $fileName -o $fileNameWithoutExt && $dir$fileNameWithoutExt",
    "sml": "cd $dir && sml $fileName",
    "mojo": "mojo run"
  },
}
```
---
