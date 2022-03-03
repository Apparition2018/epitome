# Visual Studio Code

---
## Settings JSON
```json
{
  "git.path": "D:\\Git\\cmd\\git.exe",
  "emmet.includeLanguages": {
    "vue-html": "html",
    "javascript": "javascriptreact",
    "plaintext": "jade"
  }
}
```
---
## [Tasks](https://code.visualstudio.com/docs/editor/tasks)
```json
{
    "version": "2.0.0",
    "tasks": [
        {
            "label": "Run HTML file with Chrome",
            "type": "process"
            "command": "Chrome",
            "args": ["${file}"],
            "windows": {
                "command": "E:/Chrome/App/chrome.exe"
            },
            "group": "build",
            "presentation": {
                "reveal": "never"
            },
            "problemMatcher": "$msCompile"
        }
    ]
}
```
---