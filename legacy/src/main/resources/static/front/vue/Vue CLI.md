# Vue CLI
Command-Line Interface: 命令行界面或字符用户界面

---
## 参考网站
1. [Vue CLI](https://cli.vuejs.org/zh/guide/)
2. [【Vue】教程:一、windows下vue cli3安装及搭建项目详解](https://www.jianshu.com/p/da5719804018)
3. [【Vue】教程:三、Vue Cli3项目结构优化](https://www.jianshu.com/p/37cec256e9ed)
---
## [安装](https://cli.vuejs.org/zh/guide/installation.html)
```bash
npm install -g @vue/cli
yarn global add @vue/cli

# 升级
npm update -g @vue/cli
yarn global upgrade --latest @vue/cli

# 升级 Vue CLI 相关模块(@vue/cli-plugin-*)
vue upgrade
```
---
## 基础
### [创建项目](https://cli.vuejs.org/zh/guide/creating-a-project.html)
1. `vue create [options] <app-name>`
2. `vue ui [options]`
### [插件](https://cli.vuejs.org/zh/guide/plugins-and-presets.html)
- 在现有的项目中安装插件：`vue add [options] <plugin> [pluginOptions]`
### [cli-service](https://cli.vuejs.org/zh/guide/cli-service.html)
1. 使用命令
---
## [项目结构](https://www.jianshu.com/p/90c6a5384e54)
    1. public                   公共资源目录。
                                任何放置在 public 文件夹的静态资源都会被简单的复制，而不经过 webpack。
                                你需要通过绝对路径来引用它们。
        - index.html            首页入口文件
    2. src                      源代码
        - assets                静态资源
        - components            公共组件
        - router                路由
        - store                 状态管理
        - views                 各功能模块
            - App.vue           根组件，所有组件和路由的出口。会被渲染到根目录 index.html 中显示出来。
                                可以写一些全局 CSS 样式。
            - main.js           入口文件。实例化 Vue；引入需要的插件和公共组件；全局变量等全局使用的东西。
    3. .browserslistrc          指定项目的目标浏览器范围。
                                这个值会被 @babel/preset-env 和 Autoprefixer 用来确定需要转译的 JavaScript 特性和需要添加的 CSS 浏览器前缀。
    4. .eslintrc.js             ESLint 规则
    5. babel.config.js          babel 配置
    6. package.json             依赖配置
        - scripts               npm 命令行缩写
        - dependencies          生产环境用到的依赖，如 element-ui，vue-router       (npm i)
        - devDependencies       开发环境用到的依赖，如 webpack，gulp                (npm i --save-dev)
    7. package-lock.json        保存依赖的信息（版本，下载地址）。在多人开发项目，保证下载依赖是一致的。
>#### 参考网站
>1. [index.html 什么时候加载 main.js](https://blog.csdn.net/u013605060/article/details/109601098)
>2. [browserslist 详解](https://www.jianshu.com/p/d45a31c50711)
>3. [eslintrc.js 最详细的介绍](https://segmentfault.com/a/1190000017461203)
---
## [vue.config.js](https://cli.vuejs.org/zh/config/#vue-config-js)
1. publicPath：部署应用包时的基本 URL
2. outputDir：当运行 vue-cli-service build 时生成的生产环境构建文件的目录
3. assetsDir：放置生成的静态资源 (js、css、img、fonts) 的 (相对于 outputDir 的) 目录
4. lintOnSave：是否在开发环境下通过 eslint-loader 在每次保存时 lint 代码
5. productionSourceMap：如果你不需要生产环境的 source map，可以将其设置为 false 以加速生产环境构建
6. devServer：所有 webpack-dev-server 的选项都支持
    - [proxy](https://webpack.docschina.org/configuration/dev-server/#devserverproxy) ：当拥有单独的 API 后端开发服务器并且希望在同一域上发送 API 请求时使用
        - [pathRewrite](https://www.chensheng.group/2019/12/19/114-pathRewrite%E4%BB%80%E4%B9%88%E6%97%B6%E5%80%99%E4%BD%BF%E7%94%A8/)
7. configureWebpack：如果这个值是一个对象，则会通过 webpack-merge 合并到最终的配置中；如果这个值是一个函数，则会接收被解析的配置作为参数
8. chainWebpack：一个函数，会接收一个基于 webpack-chain 的 ChainableConfig 实例，允许对内部的 webpack 配置进行更细粒度的修改
---
## [ESLint](https://cli.vuejs.org/zh/config/#eslint)
- [Vue 风格指南对应 ESLint 配置](https://www.cnblogs.com/dreamsqin/p/10906951.html)
---