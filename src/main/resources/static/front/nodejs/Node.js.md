# Node.js

---
## Reference
1. [Node.js -- JavaScript 标准参考教程](https://javascript.ruanyifeng.com/nodejs/basic.html)
2. [Node.js - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1022910821149312/1023025235359040)
---
## nvm-windows 管理 Node.js
1. [下载并安装 nvm-setup.exe](https://github.com/coreybutler/nvm-windows/releases)
    - NVM for Windows: D:\nvm4w\nvm
    - symlink: D:\nvm4w\nodejs
    - 勾选 Node.js LTS releases
    - 勾选 NVM For Windows releases
2. 常用命令：
    ```
    nvm install <version>                                   安装 Node.js
        nvm install lts                                     安装 LTS 版本
    nvm alias default <version>                             设置默认版本
    nvm use <version>                                       切换版本
    nvm list / ls                                           查查看已安装版本
    nvm uninstall <version>                                 卸载版本
    ```
---
## [npm](https://docs.npmjs.com/)
### [npm CLI Commands](https://docs.npmjs.com/cli/v8/commands)
| commands   | aliases           | synopsis                                                                                                                                                                                   | description        |
|:-----------|:------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:-------------------|
| config     | c                 | `npm config set <key>=<value> [<key>=<value> ...]`<br/>`npm config get [<key> [<key> ...]]`<br/>`npm config delete <key> [<key> ...]`<br/>`npm config list [--json]`<br/>`npm config edit` | 管理 npm 配置文件        |
| init       | create            | `npm init <package-spec>`<br/>`npm init <@scope>`                                                                                                                                          | 创建 package.json 文件 |
| install    | i, add            | `npm install [<package-spec> ...]`                                                                                                                                                         | 安装包                |
| update     | up, upgrade       | `npm update [<pkg>...]`                                                                                                                                                                    | 更新包                |
| uninstall  | un, remove, r, rm | `npm uninstall [<@scope>/]<pkg>...`                                                                                                                                                        | 卸载包                |
| ls         | list              | `npm ls <package-spec>`                                                                                                                                                                    | 列出已安装的包            |
| view       | info, show, v     | `npm view [<package-spec>] [<field>[.subfield]...]`                                                                                                                                        | 查看包信息              |
| run-script | run               | `npm run-script <command> [-- <args>]`                                                                                                                                                     | 运行脚本               |
## [package.json](https://docs.npmjs.com/cli/v8/configuring-npm/package-json)

---
## npm vs [yarn](https://yarn.bootcss.com/docs/cli/)
| npm                            | yarn                    |
|:-------------------------------|:------------------------|
| `npm init`                     | `yarn create ?`         |
| `npm install`                  | `yarn [install]`        |
| `npm install <pkg>`            | `yarn add <pkg>`        |
| `npm install <pkg> --save-dev` | `yarn add <pkg> --dev`  |
| `npm install <pkg> -g`         | `yarn global add <pkg>` |
| `npm update <pkg>`             | `yarn upgrade <pkg>`    |
| `npm uninstall <pkg>`          | `yarn remove <pkg>`     |
| `npm run <command>`            | `yarn run <command>`    |
---
## [Globals](https://nodejs.org/dist/latest-v16.x/docs/api/globals.html)
1. __dirname：当前模块的目录名
2. __filename：当前模块的文件名
---
## [Process](https://nodejs.org/dist/latest-v16.x/docs/api/process.html)
1. process.env：返回一个包含用户环境的对象
2. process.arg：返回一个数组，其中包含启动 Node.js 进程时传递的命令行参数
    ```javascript
    import {argv} from 'process';

    argv.forEach((v, i) => {
        console.log(`${i}: ${v}`);
    })

    // output:
    // 0: /usr/local/bin/node                       // process.execPath
    // 1: /Users/mjr/work/node/process-args.js      // 正在执行的 JavaScript 文件的路径
    // 2: one                                       // 命令行参数 1
    // 3: two=three                                 // 命令行参数 2
    // 4: four
    ```
3. process.execPath：返回启动 Node.js 进程的可执行文件的绝对路径名
---
## [Path](https://nodejs.org/dist/latest-v16.x/docs/api/path.html)
1. path.join([...paths])：正确使用当前系统的路径分隔符连接路径
    ```javascript
    path.join('a', 'b', 'c');               // a\b\c
    ```
2. path.resolve([...paths])：将一系列路径或路径段解析为绝对路径
    ```javascript
    path.resolve('/a', 'b', 'c');           // \a\b\c
    path.resolve('/a', '/b', 'c');          // \b\c
    path.resolve('/a', 'b', './c');         // \a\b\c
    ```
