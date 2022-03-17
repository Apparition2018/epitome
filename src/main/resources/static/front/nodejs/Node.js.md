# Node.js

---
## 参考网站
1. [Node.js -- JavaScript 标准参考教程](https://javascript.ruanyifeng.com/nodejs/basic.html)
2. [Node.js - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1022910821149312/1023025235359040)
---
## 安装 nodejs
1. [下载并安装 nodejs](https://nodejs.org/en/)
2. 创建 `D:\nodejs\node_cache` 目录，并执行 `npm config set cache "D:\nodejs\node_cache"`
3. `npm config set prefix "D:\nodejs"`
4. `npm config set registry=https://registry.npm.taobao.org`
5. `npm i -g yarn`
6. `yarn config set global-folder "D:\nodejs\yarn_global"`
7. `yarn config set cache-folder "D:\nodejs\yarn_cache"`
8. `yarn conifg set registry https://registry.npm.taobao.org`
9. `yarn global add @vue/cli`
---
## [npm](https://docs.npmjs.com/)
### [npm CLI Commands](https://docs.npmjs.com/cli/v8/commands)
```
npm -v
npm config list [--json]                                    显示所有配置
npm root [-g]                                               打印[全局]有效的 node_modules
npm install|i [-g] <pkg>                                    安装包及其所依赖的包
npm uninstall|un [-g] <pkg>...                              卸载包，完全删除 npm 安装的所有 
npm list -g --depth=0
npm info <pkg>
npm init [-y|-f]                                            设置新的或现有的 npm 包
npm run-script <command> [-- <args>]                        运行脚本
```
### [package.json](https://docs.npmjs.com/cli/v8/configuring-npm/package-json)

---
## npm vs [yarn](https://yarn.bootcss.com/docs/cli/)
| npm                          | yarn                        |
|:-----------------------------|:----------------------------|
| npm install (npm i)          | yarn install (yarn)         |
| npm i --save &lt;pkg&gt;     | yarn add &lt;pkg&gt;        |
| npm i --save-dev &lt;pkg&gt; | yarn add &lt;pkg&gt; --dev  |
| npm i -g &lt;pkg&gt;         | yarn global add &lt;pkg&gt; |
| npm update --save            | yarn upgrade                |
| npm uninstall &lt;pkg&gt;    | yarn remove &lt;pkg&gt;     |
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
    // 4: four                                      // 命令行参数 3
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
---