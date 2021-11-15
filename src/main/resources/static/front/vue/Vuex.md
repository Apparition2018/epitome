# Vuex
![data flow](https://vuex.vuejs.org/flow.png)
把组件的共享状态抽取出来，以一个全局单例模式管理
- 多个视图依赖于同一状态
- 来自不同视图的行为需要变更同一状态
---
## 参考网站
1. [Vuex](https://vuex.vuejs.org/zh/)
2. [Vuex Tutorial: Learn Vuex For Free | Scrimba](https://scrimba.com/learn/vuex)
---
## 开始
```javascript
// main.js
import Vue from 'vue'
import App from './App.vue'
import sotre from './store'

new Vue({
    // 将 store 从根组件注入到所有子组件，子组件可以通过 this.$store 进行调用
    store,
    render: h => h(App)
}).$mount('#app')
```
```javascript
// @/store/index.js
import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import getters from './getters'
import mutations from './mutations'
import actions from './actions'
import modules from './modules'

Vue.use(Vuex)

export default new Vuex.Store({
    state,
    getters,
    mutations,
    actions,
    modules
})
```
---
## 核心概念
![vuex](https://vuex.vuejs.org/vuex.png)
>## [State](https://vuex.vuejs.org/zh/guide/state.html)
>- 单一状态树：这个单一对象包含了所有的应用层状态
>- 和全局对象的区别：
>   1. Vuex 的状态是响应式的
>   2. 不能直接改变 store 的状态，应该显式 commit mutation
>```vue
><template>
>   <div>{{ count }}</div>
></template>
>```
>```javascript
>export default {
>   computed: {
>       count() {
>           return this.$store.state.count
>       }
>   }
>}
>```
>```javascript
>import { mapState } from 'vuex'
>export default {
>   data() {
>       return {
>           x: 0
>       }
>   }
>   computed: mapState({
>       // 常规函数，可通过 this 访问 local state
>       count (state) {
>           return state.count + this.x
>       }
>       // 箭头函数
>       count2: state => state.count,
>       // 等同于 state => state.count
>       count3: 'count',
>   })
>}
>```
>```javascript
>import { mapState } from 'vuex'
>export default {
>   // 当 computed property 和 state sub tree name 相同，可以传入一个字符串数组 
>   computed: mapState(['count'])
>}
>```
>```javascript
>import { mapState } from 'vuex'
>export default {
>   computed: {
>       // 使用对象展开运算符将此对象混入到外部对象中
>       ...mapState({})
>   }
>```
---
## [API](https://vuex.vuejs.org/zh/api/)
>### [组件绑定的辅助函数](https://vuex.vuejs.org/zh/api/#%E7%BB%84%E4%BB%B6%E7%BB%91%E5%AE%9A%E7%9A%84%E8%BE%85%E5%8A%A9%E5%87%BD%E6%95%B0)
>1. [mapState](https://vuex.vuejs.org/zh/guide/state.html#mapstate-%E8%BE%85%E5%8A%A9%E5%87%BD%E6%95%B0) 
    ：创建组件 computed options 以返回 Vuex store 子树
---