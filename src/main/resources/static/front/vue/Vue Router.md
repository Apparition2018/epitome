# Vue Router

---
## 参考网站
1. [Vue Router](https://router.vuejs.org/zh/guide/)
2. [Course: Vue Router For Everyone](https://vueschool.io/courses/vue-router-for-everyone)
---
## [路由的两种模式](https://blog.csdn.net/chenjuan1993/article/details/82084698)
1. Hash 模式：地址栏包含 # 符号，# 后的内容不被后台获取
    - 微信支付，分享 url 作为参数传递时，# 为特殊字符，需改成 History 模式
2. [History 模式](https://router.vuejs.org/zh/guide/essentials/history-mode.html)
   ：具有对 url 历史记录进行修改的功能
    - 需要后台配合处理 404 的问题
    ```javascript
    export default new Router({
        mode: 'history',
        routes: []
    })
   ```
---
## 实例方法
    router.push
---
## 样例
> ### JS
>```javascript
>// main.js
>import Vue from 'vue'
>import App from './App.vue'
>import router from './router'
>
>new Vue({
>   router,
>   render: h => h(App)
>}).$mount('#app')
>```
>```javascript
>// '@/router/index.js'
>// 0. 如果使用模块化机制编程，导入Vue和VueRouter，要调用 Vue.use(VueRouter)
>import Vue from 'vue'
>import VueRouter from 'vue-router'
>import Foo from '../views/xxx/Foo'
>
>Vue.use(VueRouter)
>
>// 1. 定义 (路由) 组件
>// 可以从其他文件 import 进来（立即加载组件）
>const Bar = { template: '<div>bar</div>' }
>
>// 2. 定义路由
>// 每个路由应该映射一个组件。 
>// 其中"component" 可以是通过 Vue.extend() 创建的组件构造器，
>// 或者，只是一个组件配置对象。
>const routes = [
>   // 访问再加载组件
>   { path: '/', component: () => import('@/view/Index') },
>   { path: '/foo', name: Foo, component: Foo, hidden: true },
>   { path: '/bar', naem: Bar, component: Bar, children: [] }
>]
>
>// 3. 创建 router 实例
>// 然后传 `routes` 配置
>const router = new VueRouter({
>   mode: 'history', // History 模式
>   routes // 相当于 routes: routes
>})
>
>// 4. 创建和挂载根实例。
>export default router
>// 或者通过 router 配置参数注入路由，从而让整个应用都有路由功能
>// const app = new Vue({
>//     router
>// }).$mount('#app')
>```
>### HTML
>```html
><div id="app">
>  <h1>Hello App!</h1>
>  <p>
>    <!-- 使用 router-link 组件来导航. -->
>    <!-- 通过传入 `to` 属性指定链接. -->
>    <!-- <router-link> 默认会被渲染成一个 `<a>` 标签 -->
>    <router-link to="/foo">Go to Foo</router-link>
>    <router-link to="/bar">Go to Bar</router-link>
>  </p>
>  <!-- 路由出口 -->
>  <!-- 路由匹配到的组件将渲染在这里 -->
>  <router-view></router-view>
></div>
>```
---
