# Node.js

---
## 参考网站
1. [安装 nodejs](https://www.cnblogs.com/jianguo221/p/11487532.html)
2. [Node.js -- JavaScript 标准参考教程](https://javascript.ruanyifeng.com/nodejs/basic.html)
3. [Node.js - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1022910821149312/1023025235359040)
---
## npm
- [npm 常用命令详解](https://www.cnblogs.com/PeunZhang/p/5553574.html#npm-install)
- [run-script | npm 中文文档](https://www.npmjs.cn/cli/run-script/)
- [package.json | npm 中文文档](https://www.npmjs.cn/files/package.json/)
>### 命令
>```
>npm -v
>npm config set registry=https://registry.npm.taobao.org
>npm config set prefix "D:\nodejs\node_global"
>npm config set cache "D:\nodejs\node_cache"
>npm config list
>npm root -g
>npm i -g npm
>npm uni -g npm
>npm install -g yarn --registry=https://registry.npm.taobao.org
>npm install -g cnpm --registry=https://registry.npm.taobao.org
>npm uninstall -g cnpm
>npm info yarn
>npm list -g --depth=0
>npm init -y | -f
>npm run-script <command> [--silent] [-- <args>...]                  运行包脚本
>```
---
### yarn
1. [CLI 简介 | Yarn 中文文档](https://yarn.bootcss.com/docs/cli/)
2. [package-lock.json 和 yarn.lock](https://segmentfault.com/a/1190000017075256)
>### 命令
>```
>yarn conifg set registry https://registry.npm.taobao.org -g
>yarn config set global-folder "E:\nodejs\yarn_global"
>yarn config set cache-folder "E:\nodejs\yarn_cache"
>yarn config list
>yarn global add @vue/cli
>yarn global remove @vue/cli
>yarn install
>yarn run serve
>```
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