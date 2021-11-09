# Vue Router

---
## 参考网站
1. [Vue Router](https://router.vuejs.org/zh/guide/)
2. [Course: Vue Router For Everyone](https://vueschool.io/courses/vue-router-for-everyone)
---
## [起步](https://router.vuejs.org/zh/guide/)
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
>   routes // (缩写) 相当于 routes: routes
>})
>
>// 4. 创建和挂载根实例。
>// 通过 router 配置参数注入路由，从而让整个应用都有路由功能
>const app = new Vue({
>   router
>}).$mount('#app')
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
## [动态路由匹配](https://router.vuejs.org/zh/guide/essentials/dynamic-matching.html)
- 匹配模式：vue-router 使用 [path-to-regexp](https://github.com/pillarjs/path-to-regexp/tree/v1.7.0) 作为路径匹配引擎
- 匹配优先级：路由定义得越早，优先级就越高
```javascript
const Dynamic = {
    template: `<div>Dynamic {{ $route.params.id }}</div>`,
    // 监测路由变化1
    watch: {
        $route (to, from) {
            console.log('from: ', from)
            console.log('to: ', to)
        }
    },
    // 监测路由变化2
    beforeRouteUpdate (to, from, next) {
        console.log('from: ', from)
        console.log('to: ', to)
        console.log('params: ', this.$route.params) // 等于 from.params.id 
        next();
        console.log('params: ', this.$route.params) // 等于 to.params.id
    }
}
const router = new VueRouter({
    routes: [
        // 动态路径参数 以冒号开头
        {path: '/dynamic/:id', component: Dynamic},
        // 匹配所有路径，通常用于客户端 404 错误
        {path: '*'}
    ]
})
```
---
## [嵌套路由](https://router.vuejs.org/zh/guide/essentials/nested-routes.html#%E5%B5%8C%E5%A5%97%E8%B7%AF%E7%94%B1)

---
## [API](https://router.vuejs.org/zh/api/)
>## [<router-link>](https://router.vuejs.org/zh/api/#router-link)
>- 支持用户在具有路由功能的应用中 (点击) 导航，默认渲染成带有正确链接的 &lt;a&gt; 标签
>```vue
><!--
>   to:       表示目标路由的链接，调用 router.push()
>   replace:  表示目标路由的链接，调用 router.replace()
>   tag:      渲染成其它标签
>-->
><router-link
>   to="/foo" 
>   tag="li">
>   foo
></router-link>
>```
>## [<router-view>](https://router.vuejs.org/zh/api/#router-view)
>- 一个 functional 组件，渲染路径匹配到的视图组件
>## Router
>>### [Router 构建选项](https://router.vuejs.org/zh/api/#router-%E6%9E%84%E5%BB%BA%E9%80%89%E9%A1%B9)
>>1. mode：配置路由模式
>>    1. hash：使用 URL hash 值来作路由
>>    2. history：依赖 HTML5 History API 和服务器配置
>>    3. abstract：支持所有 JavaScript 运行环境，如 Node.js 服务器端
>>2. base：应用的基路径
>>3. routes：
>>### [Router 实例方法](https://router.vuejs.org/zh/api/#router-%E5%AE%9E%E4%BE%8B%E6%96%B9%E6%B3%95)
>>1. router.push
>>    ```
>>    router.push(location, onComplete?, onAbort?)
>>    router.push(location).then(onComplete).catch(onAbort)
>>    ```
>>2. router.place：同上，但不会向 history 添加新记录
>>3. router.go：`router.go(n)`
>>4. router.back()：`router.back()`
>>5. router.forward()：`router.forward()`
>## [Route](https://router.vuejs.org/api/#the-route-object)
>- 表示当前激活的路由的状态信息，包含了当前 URL 解析得到的信息，还有 URL 匹配到的路由记录
>- 出现的地方：
>     1. 在组件内，即 this.$route
>     2. 在 $route 观察者回调内 
>     3. router.match(location) 的返回值
>     4. `router.beforeEach((to, from, next) => {})`
>     5. `scrollBehavior(to, from, savedPosition)`
>>### [Route 属性](https://router.vuejs.org/zh/api/#%E8%B7%AF%E7%94%B1%E5%AF%B9%E8%B1%A1%E5%B1%9E%E6%80%A7)
>>1. $route.path：string，当前路由的路径，总是解析为绝对路径
>>2. $route.params：object，包含了动态片段和全匹配片段
>>      - $route.params.pathMatch：string，URL 通过通配符被匹配的部分
>>3. $route.query：object，URL 查询参数
>>4. $route.hash：string，当前路由的 hash 值 (带 #)
---
