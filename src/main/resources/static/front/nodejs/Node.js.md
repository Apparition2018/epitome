# Node.js

---
## Reference
1. [Node.js -- JavaScript 标准参考教程](https://javascript.ruanyifeng.com/nodejs/basic.html)
2. [Node.js - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1022910821149312/1023025235359040)
---
## 安装 nodejs
1. [下载并安装 nodejs](https://nodejs.org/en/)
2. .npmrc
    1. `npm config set cache "D:\nodejs\node_cache"`
    2. `npm config set prefix "D:\nodejs\node_global"`，并添加到系统环境变量 Path
    3. `npm config set registry https://registry.npm.taobao.org`
3. `npm install -g yarn`
4. `npm install -g cnpm`
5. yarnrc
    1. `yarn config set cache-folder "D:\nodejs\yarn_cache"`
    2. `yarn config set global-folder "D:\nodejs\yarn_global"`
    3. `yarn config set registry https://registry.npm.taobao.org`
6. cnpmrc
    4. `cnpm config set cache "D:\nodejs\node_cache"`
    5. `cnpm config set prefix "D:\nodejs\node_global"`
    6. `cnpm config set registry https://registry.npm.taobao.org`
7. `cnpm i -g @vue/cli`
---
## [npm](https://docs.npmjs.com/)
### [npm CLI Commands](https://docs.npmjs.com/cli/v8/commands)
| commands   | aliases           | synopsis                                                                                                                              | description        |
|:-----------|:------------------|:--------------------------------------------------------------------------------------------------------------------------------------|:-------------------|
| config     | c                 | npm config set key=value [key=value...]<br/>npm config get [key ...]<br/>npm config delete key [key ...]<br/>npm config list [--json] | 管理 npm 配置文件        |
| init       | create            | npm init [--force&#124;-f&#124;--yes&#124;-y&#124;--scope]<br/>npm init <@scope><br/>npm init [<@scope>/]&lt;name&gt;                 | 创建 package.json 文件 |
| install    | i, add            | npm install [<@scope>/]&lt;pkg&gt;[@&lt;tag&gt;]<br/>npm install [<@scope>/]&lt;pkg&gt;[@&lt;version range&gt;]                       | 安装包                |
| update     | up, upgrade       | npm update [&lt;pkg&gt;...]                                                                                                           | 更新包                |
| uninstall  | un, remove, r, rm | npm uninstall [<@scope>/]&lt;pkg&gt;...                                                                                               | 卸载包                |
| ls         | list              | npm ls [[<@scope>/]&lt;pkg&gt; ...]<br/><br/>npm list -g --depth=0                                                                    | 列出已安装的包            |
| view       | info, show, v     | npm view [<@scope>/]&lt;pkg&gt;[@&lt;version&gt;] [&lt;field&gt;[.subfield]...]                                                       | 查看包信息              |
| run-script | run               | npm run-script &lt;command&gt; [-- &lt;args&gt;]                                                                                      | 运行脚本               |
## [package.json](https://docs.npmjs.com/cli/v8/configuring-npm/package-json)

---
## npm vs [yarn](https://yarn.bootcss.com/docs/cli/)
| npm                                | yarn                        |
|:-----------------------------------|:----------------------------|
| npm init                           | yarn create ?               |
| npm install                        | yarn [install]              |
| npm install &lt;pkg&gt;            | yarn add &lt;pkg&gt;        |
| npm install &lt;pkg&gt; --save-dev | yarn add &lt;pkg&gt; --dev  |
| npm install &lt;pkg&gt; -g         | yarn global add &lt;pkg&gt; |
| npm update &lt;pkg&gt;             | yarn upgrade &lt;pkg&gt;    |
| npm uninstall &lt;pkg&gt;          | yarn remove &lt;pkg&gt;     |
| npm run &lt;command&gt;            | yarn run &lt;command&gt;    |
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
