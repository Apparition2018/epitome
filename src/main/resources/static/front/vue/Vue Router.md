# Vue Router

---
## 参考网站
1. [Vue Router](https://router.vuejs.org/zh/guide/)
2. [Course: Vue Router For Everyone](https://vueschool.io/courses/vue-router-for-everyone)
---
## [起步](https://router.vuejs.org/zh/guide/)
```javascript
// @/main.js
import Vue from 'vue'
import App from './App.vue'
import router from './router'

new Vue({
   router,
   render: h => h(App)
}).$mount('#app')
```
```javascript
// @/router/index.js
// 0. 如果使用模块化机制编程，导入Vue和VueRouter，要调用 Vue.use(VueRouter)
import Vue from 'vue'
import VueRouter from 'vue-router'
import Foo from '../views/xxx/Foo'

Vue.use(VueRouter)

// 1. 定义 (路由) 组件
// 可以从其他文件 import 进来（立即加载组件）
const Bar = { template: '<div>bar</div>' }

// 2. 定义路由
// 每个路由应该映射一个组件。 
// 其中"component" 可以是通过 Vue.extend() 创建的组件构造器，
// 或者，只是一个组件配置对象。
const routes = [
   // 访问再加载组件
   { path: '/', component: () => import('@/view/Index') },
   { path: '/foo', name: Foo, component: Foo, hidden: true },
   { path: '/bar', naem: Bar, component: Bar, children: [] }
]

// 3. 创建 router 实例
// 然后传 `routes` 配置
const router = new VueRouter({
   mode: 'history', // History 模式
   routes // (缩写) 相当于 routes: routes
})

// 4. 创建和挂载根实例。
// 通过 router 配置参数注入路由，从而让整个应用都有路由功能
const app = new Vue({
   router
}).$mount('#app')
```
```html
<!-- @/App.vue -->
<div id="app">
  <h1>Hello App!</h1>
  <p>
    <!-- 使用 router-link 组件来导航. -->
    <!-- 通过传入 `to` 属性指定链接. -->
    <!-- <router-link> 默认会被渲染成一个 `<a>` 标签 -->
    <router-link to="/foo">Go to Foo</router-link>
    <router-link to="/bar">Go to Bar</router-link>
  </p>
  <!-- 路由出口 -->
  <!-- 路由匹配到的组件将渲染在这里 -->
  <router-view></router-view>
</div>
```
---
## [动态路由匹配](https://router.vuejs.org/zh/guide/essentials/dynamic-matching.html)
- 匹配模式：vue-router 使用 [path-to-regexp](https://github.com/pillarjs/path-to-regexp/tree/v1.7.0) 作为路径匹配引擎
- 匹配优先级：路由定义得越早，优先级就越高
```vue
<div class="nav">
  <router-link to="/dynamic/foo">/dynamic/foo</router-link> |
  <router-link to="/dynamic/bar">/dynamic/bar</router-link>
  <router-view/>
</div>
```
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
```vue
<div class="nav">
  <router-link to="/nested">/nested</router-link> |
  <router-link to="/nested/1">/nested/1</router-link> |
  <router-link to="/nested/2">/nested/2</router-link>
  <router-view/>
</div>
```
```javascript
const Nested = {
    template: `
        <div>
            <h2>Nested</h2>
            <router-view></router-view>
        </div>
        `
}
const NestedChildren0 = {template: '<div>0</div>'}
const NestedChildren1 = {template: '<div>1</div>'}
const NestedChildren2 = {template: '<div>2</div>'}
const router = new VueRouter({
    routes: [
        {
            path: '/nested', component: Nested,
            children: [
                {path: '', component: NestedChildren0},
                // 当 /nested/foo/1 匹配成功时，NestedChildren0 会被渲染在 Nested 的 <router-view> 中
                {path: '1', component: NestedChildren1},
                {path: '2', component: NestedChildren2},
            ]
        }
    ]
})
```
---
## [命名路由](https://router.vuejs.org/zh/guide/essentials/named-routes.html)
```vue
<div class="nav">
  <router-link :to="{ name: 'NamedRoutes', params: { id: 123 } }">/named-routes</router-link>
  <router-view/>
</div>
```
```javascript
const router = new VueRouter({
    routes: [
        {
            path: '/named-routes/:id',
            name: 'NamedRoutes',
            component: {template: '<div>NameRoutes {{ $route.params.id }}</div>'}

        }
    ]
})
```
---
## [命名视图](https://router.vuejs.org/zh/guide/essentials/named-views.html#%E5%91%BD%E5%90%8D%E8%A7%86%E5%9B%BE)
```vue
<div class="nav">
  <router-link to="/named-views">/named-views</router-link>
</div>
<router-view/>
<router-view name="a"></router-view>
<router-view name="b"></router-view>
```
```javascript
const NamedView0 = {template: '<div>0</div>'}
const NamedView1 = {template: '<div>1</div>'}
const NamedView2 = {template: '<div>2</div>'}
const router = new VueRouter({
    routes: [
        {
            path: '/named-views',
            components: {
                default: NamedView0,
                a: NamedView1,
                b: NamedView2
            }
        }
    ]
})
```
---
## [重定向和别名](https://router.vuejs.org/zh/guide/essentials/redirect-and-alias.html#%E9%87%8D%E5%AE%9A%E5%90%91%E5%92%8C%E5%88%AB%E5%90%8D)
```vue
<div class="nav">
  <router-link to="/redirect">/redirect</router-link> |
  <router-link to="/alias">/alias</router-link>
</div>
<router-view/>
```
```javascript
const router = new VueRouter({
    routes: [
        // 重定向
        {path: '/redirect', redirect: '/'},
        // 别名
        {path: '/alias', alias: '/a', component: {template: '<div>alias</div>'}}
    ]
})
```
---
## [传递 Props 到路由组件](https://router.vuejs.org/zh/guide/essentials/passing-props.html#%E8%B7%AF%E7%94%B1%E7%BB%84%E4%BB%B6%E4%BC%A0%E5%8F%82)
```vue
<div class="nav">
  <router-link to="/props/boolean/Henry">/props/boolean/Henry</router-link> |
  <router-link to="/props/object/static">/props/object/static</router-link> |
  <router-link to="/props/function/Henry">/props/function/Henry</router-link>
</div>
<router-view/>
```
```javascript
const Props = {
    template: `
        <div>
            <h2>Props</h2>
            <router-view></router-view>
        </div>
        `
}
function propsFn(route) {
    const now = new Date()
    return {
        name: now.getFullYear() + ' ' + route.params.name
    }
}
const router = new VueRouter({
    routes: [
        {
            path: '/props',
            component: Props,
            children: [
                // 布尔模式
                {path: 'boolean/:name', props: true, component: {props: ['name'], template: '<div>{{name}}</div>'}},
                // 对象模式
                {path: 'object/static', props: {name: 'world'}, component: {template: '<div>{{$attrs.name}}</div>'}},
                // 函数模式
                {path: 'function/:name', props: propsFn, component: {template: '<div>{{$attrs.name}}</div>'}},
            ]
        },
    ]
})
```
---
## [导航守卫](https://router.vuejs.org/zh/guide/advanced/navigation-guards.html)
1. 全局
    1. 前置守卫：`router.beforeEach((to, from, next) => {})`
    2. 解析守卫：`router.beforeResolve((to, from, next) => {})`
    3. 后置守卫：`router.afterEach((to, from) => {})`
2. 路由：`beforeEnter: (to, from, next) => {}`
3. 组件
    1. 前置：`beforeRouteEnter(to, from, next) {}`
    2. 复用：`beforeRouteUpdate(to, from, next) {}`
    3. 后置：`beforeRouteLeave(to, from, next) {}`
- 导航解析流程：
```
1. 导航被触发。
2. 在失活的组件里调用 beforeRouteLeave 守卫。
3. 调用全局的 beforeEach 守卫。
4. 在重用的组件里调用 beforeRouteUpdate 守卫。
5. 在路由配置里调用 beforeEnter。
6. 解析异步路由组件。
7. 在被激活的组件里调用 beforeRouteEnter。
8. 调用全局的 beforeResolve 守卫。
9. 导航被确认。
10. 调用全局的 afterEach 钩子。
11. 触发 DOM 更新。
12. 调用 beforeRouteEnter 守卫中传给 next 的回调函数，创建好的组件实例会作为回调函数的参数传入。
```
---
## [Route Meta](https://router.vuejs.org/zh/guide/advanced/meta.html#%E8%B7%AF%E7%94%B1%E5%85%83%E4%BF%A1%E6%81%AF)

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
>>1. [router.push](https://router.vuejs.org/zh/guide/essentials/navigation.html#router-push-location-oncomplete-onabort) ：
>>      `router.push(location, onComplete?, onAbort?)`
>>      - 相当于`<router-link :to="...">`，会向 history 栈添加一个新的记录
>>      - 如果目的地和当前路由相同，只有参数发生了改变 (/users/1 -> /users/2)，则使用 beforeRouteUpdate 来响应变化 (如抓取用户信息)
>>      ```
>>      // 字符串
>>      router.push('home')
>>      // 对象
>>      router.push({ path: 'home' })
>>      // 带查询参数，变成 /register?plan=private
>>      router.push({ path: 'register', query: { plan: 'private' }})
>>      // 命名的路由
>>      router.push({ name: 'user', params: { userId: '123' }})
>>      ```
>>2. router.place：`router.replace(location, onComplete?, onAbort?)`
>>      - 相当于`<router-link :to="..." replace>`，替换当前的 history 记录
>>3. router.go：`router.go(n)`
>>      - 相当于`window.history.go(n)`，在 history 记录中向前或者后多少步
>## [Route](https://router.vuejs.org/api/#the-route-object)
>- 表示当前激活的路由的状态信息，包含了当前 URL 解析得到的信息，还有 URL 匹配到的路由记录
>- 出现的地方：
>     1. 在组件内，即 this.$route
>     2. 在 $route 观察者回调内 
>     3. router.match(location) 的返回值
>     4. `router.beforeEach((to, from, next) => {})`
>     5. `scrollBehavior(to, from, savedPosition)`
>>### [Route 属性](https://router.vuejs.org/zh/api/#%E8%B7%AF%E7%94%B1%E5%AF%B9%E8%B1%A1%E5%B1%9E%E6%80%A7)
>>1. $route.path：String，当前路由的路径，总是解析为绝对路径
>>2. $route.params：Object，包含了动态片段和全匹配片段
>>      - $route.params.pathMatch：String，URL 通过通配符被匹配的部分
>>3. $route.query：Object，URL 查询参数
>>4. $route.hash：String，当前路由的 hash 值 (带 #)
> 5. $route.fullPath：String，完成解析后的 URL，包含查询参数和 hash 的完整路径
> 6. $route.matched：Array&lt;RouteRecord&gt;，当前路由匹配的所有路由记录
---
