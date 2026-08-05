# webpack

---
## 参考网站
1. [指南 | webpack5 中文文档](https://webpack.docschina.org/guides/)
2. [指南 | webpack4 中文文档](https://v4.webpack.docschina.org/guides/)
3. [环境变量 .env 文件](https://juejin.cn/post/6844904153890684935)
4. [webpack-dev-server 配置 host 0.0.0.0 作用](https://juejin.cn/post/6844904019039617038)
---
## 概念
- 本质上，webpack 是一个用于现代 JavaScript 应用程序的 静态模块打包工具
1. [Entry](https://webpack.docschina.org/concepts/entry-points/) ：入口，指示 webpack 应该使用哪个模块，来作为构建其内部依赖关系图(dependency graph)的开始
    ```javascript
    module.exports = {
        // 默认 entry: './src/index.js';,
        entry: './path/to/my/entry/file.js',
    };
    ```
2. [Output](https://webpack.docschina.org/concepts/output/) ：输出，告诉 webpack 在哪里(默认./dist)输出它所创建的 bundle，以及如何命名这些文件
    ```javascript
    const path = require('path');
    
    module.exports = {
        output: {
            path: path.resolve(__dirname, 'dist'),
            filename: 'my-first-webpack.bundle.js',
        }
    };
    ```
3. [Loaders](https://webpack.docschina.org/concepts/loaders/) ：webpack 只能理解 JavaScript 和 JSON 文件。Loaders 让 webpack 能够去处理其他类型的文件，并将它们转换为可以被应用程序使用的，以及添加到依赖关系图中
    ```javascript
    const path = require('path');
    
    module.exports = {
        module: {
            // test：哪些文件会被转换；use：使用哪个 Loder 转换
            rules: [{ test: /\.txt$/, use: 'raw-loader' }],
        }
    };
    ```
4. [Plugins](https://webpack.docschina.org/concepts/plugins/) ：插件，用于执行更广泛的任务。包括：打包优化，资源管理，注入环境变量
    ```javascript
    const HtmlWebpackPlugin = require('html-webpack-plugin'); // 通过 npm 安装
    const webpack = require('static/front/webpack/1-asset-management/webpack'); // 用于访问内置插件
    
    module.exports = {
       plugins: [new HtmlWebpackPlugin({ template: './src/index.html' })],
    };
    ```
5. [Mode](https://webpack.docschina.org/concepts/#mode) ：模式，通过设置 mode 参数，development(默认)、production 或 none，来启用 webpack 内置的相应环境的优化
    ```javascript
    module.exports = {
    mode: 'production',
    };
    ```
---
## 起步
```
1. mkdir webpack && cd webpack

2. npm init -y

3. npm install --save-dev webpack webpack-cli

4. npm install lodash

// 加载各类文件
5. npm install --save-dev style-loader css-loader file-loader csv-loader xml-loader

// 生成 html 文件
6. npm install --save-dev html-webpack-plugin

// 清理插件
7. npm install --save-dev clean-webpack-plugin

// 快速开发应用程序，可以实时重新加载(live reloading)
8. npm install --save-dev webpack-dev-server

// 合并配置文件
9. npm install --save-dev webpack-merge
```
---
## 目录结构
```
src     源代码，用于书写和编辑的代码
dist    分发代码，构建过程产生的代码最小化和优化后的 输出(output) 目录，最终将在浏览器中加载
```
---
## 常用命令 & npm scripts
```
npx webpack [--config webpack.config.js]                    默认使用 webpack.config.js
npx webpack serve

"build": "webpack"
"build2": "webpack --config webpack.prod.js"
"start": "webpack serve"
"dev": "webpack serve --config webpack.dev.js",
```
---
## tree shaking
- 移除 JavaScript 上下文中的未引用代码(dead-code)
1. 使用 ES2015 模块语法（即 import 和 export）
2. 确保没有 compiler 将 ES2015 模块语法转换为 CommonJS 模块
3. 在项目 package.json 文件中，添加一个 "sideEffects" 属性
4. 通过将 mode 选项设置为 production，启用 minification(代码压缩) 和 tree shaking
---
## development VS production
1. development
```
1. 强大的 source map
2. live reloading（实时重新加载）
3. hot module replacement（热模块替换）
```
2. production
```
1. 压缩 bundle
2. 更轻量的 source map
3. 资源优化
```
---
## 代码分离
1. 入口起点：使用 entry 配置手动地分离代码
```
entry: {
    index: './src/index.js',
    another: './src/another-module.js'
}
```
2. 防止重复：使用 SplitChunksPlugin 去重和分离 chunk
```
optimization: {
    splitChunks: {
        chunks: 'all'
    }
}
```
3. 动态导入：通过模块中的内联函数调用来分离代码
```
```
---