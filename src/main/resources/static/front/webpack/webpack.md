# webpack

---
## 参考网站
1. [webpack](https://webpack.docschina.org/)
---
## 概念
1. 入口：entry，指示 webpack 应该使用哪个模块，来作为构建其内部 依赖图(dependency graph) 的开始
    ```javascript
    module.exports = {
        entry: './path/to/my/entry/file.js'
    };
    ```
2. 输出：output，告诉 webpack 在哪里输出它所创建的 bundle，以及如何命名这些文件
    ```javascript
    const path = require('path');
    
    module.exports = {
        output: {
            path: path.resolve(__dirname, 'dist'),
            filename: 'my-first-webpack.bundle.js'
        }
    };
    ```
3. loader：webpack 只能理解 JavaScript 和 JSON 文件。loader 让 webpack 能够去处理其他类型的文件，并将它们转换为有效模块，以供应用程序使用，以及被添加到依赖图中
    ```javascript
    const path = require('path');
    
    module.exports = {
        module: {
            rules: [
                { test: /\.txt$/, use: 'raw-loader' }
            ]
        }
    };
    ```
4. 插件：plugins，用于执行范围更广的任务。包括：打包优化，资源管理，注入环境变量
    ```javascript
    const HtmlWebpackPlugin = require('html-webpack-plugin'); // 通过 npm 安装
    const webpack = require('static/front/webpack/1-asset-management/webpack'); // 用于访问内置插件
    
    module.exports = {
       plugins: [
            new HtmlWebpackPlugin({template: './src/index.html'})
       ]
    };
    ```
5. 模式：mode，通过选择 development, production 或 none 之中的一个，来设置 mode 参数，你可以启用 webpack 内置在相应环境下的优化。其默认值为 production
    ```javascript
    module.exports = {
        mode: 'production'
    };
    ```
---
## 起步
```
1. mkdir webpack && cd webpack
2. npm init -y
3. npm install webpack webpack-cli --save-dev
4. npm install --save lodash
5. npm install --save-dev style-loader css-loader file-loader csv-loader xml-loader
```
---
## 目录结构
```
src     源代码，用于书写和编辑的代码
dist    分发代码，构建过程产生的代码最小化和优化后的 输出(output) 目录，最终将在浏览器中加载
```
---
## 常用命令
```
npx webpack [--config webpack.config.js]                    默认使用 webpack.config.js
```
---