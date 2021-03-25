# [vue2.5入门](https://www.imooc.com/learn/980)
简介：对于很多刚接触Vue的同学，最难做到的就是编程思路的切换，这门课程，我们将通过形象的例子给大家讲解Vue的基础语法及编程思路，带大家快速的上手Vue的基础开发，这门课也包含了关于组件话和vue-cli等内容的基础讲解。

## 第1章 课程介绍
*对课程讲解内容做完整陈述，帮助大家理清学习思路。*
- 1-1 课程介绍

第2章 Vue基础语法
*本章节通过浅显易懂的实例来给大家讲解Vue2.0的基本语法，包含计算属性，侦听器，基础模版指令等。*
- 2-1 创建第一个Vue实例
- 2-2 挂载点，模版与实例
- 2-3 Vue实例中的数据,事件和方法
- 2-4 Vue中的属性绑定和双向数据绑定
- 2-5 Vue中的计算属性和侦听器
- 2-6 v-if, v-show与v-for指令

第3章 Vue中的组件
*本章节讲解Vue中组件的概念和使用，结合对组件的完整理解，我们还将做一个TodoList功能模块。*
- 3-1 todolist功能开发
- 3-2 todolist组件拆分
- 3-3 组件与实例的关系
- 3-4 实现todolist的删除功能

第4章 Vue-cli的使用
*本章节我们讲给大家介绍如何使用Vue-cli脚手架工具构建项目，并讲解单文件组件和局部全局样式的知识。*
- 4-1 vue-cli的简介与使用
- 4-2 使用vue-cli开发TodoList
- 4-3 全局样式与局部样式
- 4-4 课程总结
---
## 创建项目
    1. vue init webpack demo
    2. Project name
    3. Project description
    4. Author
    5. Vue build                                            Runtime + Compiler: recommended for most users
    6. Install vue-router?                                  No
    7. Use ESLint to lint your code?                        Y, Standard
    8. Set up unit tests                                    No
    9. Setup e2e tests with Nightwatch?                     No
    10.Should we run `npm install` for you after the project has been created?    Yes, use NPM
---
## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report
```

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
