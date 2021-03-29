# [3小时速成 Vue2.x 核心技术](https://www.imooc.com/learn/1091)
简介：通过本课程，大家可以了解vue2.x的核心技术，建立前端组件化的思想，包括：常用的vue语法，vue-router，vuex，vue-cli等。我会手把手带大家使用vue-cli工具，快速构建vue项目。认识项目目录的同时，学会如何集成vue2.x到已有项目中，以及调试vue组件。从理论到实践，从小白到熟悉应用，我们配合实际的多案例共同进步。

## 第1章 课程介绍
*整体介绍课程知识点，建立知识框架，前置开发环境的介绍与准备。*
- 1-1 课程介绍
- 1-2 课程安排
- 1-3 前置学习环境

## 第2章 Vue框架常用知识点
*认识Vue的模板语法、计算属性与侦听器、条件渲染、列表渲染、Class与Style绑定介绍与基本的用法。*
- 2-1 知识点解释
- 2-2 第一个vue应用
- 2-3 模板语法
- 2-4 计算属性与侦听器
- 2-5 条件渲染、列表渲染、Class与Style绑定

## 第3章 vue核心技术
*介绍Vue-cli工具，使用vue-cli快速生成项目目录，介绍项目的目录组成。前端组件化模块化的思想，组件的创建、应用、样式的调整，Vue核心技术：vue-router,vuex及开发调试。*
- 3-1 认识vue-cli
- 3-2 组件化的思想
- 3-3 vue代码规范
- 3-4 vue-router介绍
- 3-5 vuex介绍
- 3-6 如何进行调试

## 第4章 集成Vue
*集成vue，开发工作流及git使用，通过2个单页面应用回顾前面的知识点，如何使用Cli工具快速开发。*
- 4-1 如何集成vue？
- 4-2 单页面应用Demo1
- 4-3 如何高仿别人的APP？
- 4-4 单页面应用Demo2（1）
- 4-5 单页面应用Demo2（2）

## 第5章 课程总结
*回顾整体课程，建立知识体系。*
- 5-1 重点知识点回顾
- 5-2 课程总结
---
## 创建项目 
    1. vue create demo
    2. Please pick a preset                                 Manually select features
    3. Check the features needed for your project           Babel + Router + Vuex + CSS Pre-processors + Linter / Formatter
    4. Use history mode for router?                         Yes
    5. Pick a CSS pre-processor                             Sass/SCSS
    6. Pick a linter / formatter config                     ESLint + Airbnb config
    7. Pick additional lint feautres                        Lint on Save
    8. Where do you prefer placing config for Babel, PostCSS, ESLint, etc.?     In dedicated config files
    9. Save this as a preset for future projects?           N

    1. vue ui
    2. localhost:8000
    3. 选择 + Create → + Create a new project here
    4. 输入项目名 → 选择包管理器 npm → 下一步
    5. 手动配置项目
    6. Babel + Router + Vuex + Css Pre-processors + Linter / Formatter + 使用配置文件（将插件的配置保存在各自的配置文件（比如'.babelrc'）中
    7. Pick a CSS pre-processor                             Sass/SCSS
       Pick a linter / formatter config                     ESLint + Standard config
       创建项目
    8. 保存为新预设：vuex+router
---
## Build Setup
``` bash
# Project setup
npm install

# Compiles and hot-reloads for development
npm run serve

# Compiles and minifies for production
npm run build

# Run your tests
npm run test

# Lints and fixes files
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
