# vue

---
## 参考网站
1. [Vue.js 介绍 — Vue.js 中文文档](https://vuejs.bootcss.com/guide/)
2. [API — Vue.js 中文文档](https://vuejs.bootcss.com/api/)
3. [vue-20-hello-world - CodeSandbox](https://codesandbox.io/s/github/vuejs/vuejs.org/tree/master/src/v2/examples/vue-20-hello-world)   
4. [Vue Documentation Guide](https://scrimba.com/learn/vuedocs)
---
## 模板语法
1. 插值 {{val}}
2. 指令
3. 缩写
---
## 选项|数据
    data                    数据对象
    props                   父组件数据
    methods                 方法
    computed                计算属性
    watch                   监听数据变化
---
## 选项|DOM
    el                      挂载目标
    template                模板
---
## 选项|资源
    components              组件
---
## 选项|生命周期钩子
    created                 在实例创建完成后被立即调用
---
## 选项|其它
    name                    
---
## 实例 property
    vm.$refs                一个对象，持有注册过 ref attribute 的所有 DOM 元素和组件实例
---
## 实例方法|事件
    vm.$emit                触发当前实例上的事件。附加参数都会传给监听器回调
---
## 指令
    v-text                  textConent
    v-html                  innerHTML
    v-show                  条件渲染 display: none
    v-if                    条件渲染
    v-for                   列表渲染
    v-on:           @       绑定事件
    v-bind          :       绑定 attr(+) 或 组件(1) prop
    v-model                 在表单或组件上双向绑定
---
## 全局 API
    Vue.component(id, [definition])      全局组件
---
## 常用命令
    vue-init webpack vue-cli-todo
---