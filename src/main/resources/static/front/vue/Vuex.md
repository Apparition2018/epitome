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
## 核心概念
![vuex](https://vuex.vuejs.org/vuex.png)
>## State
>- 单一状态树：这个单一对象包含了所有的应用层状态
>- 和全局对象的区别：
>   1. Vuex 的状态是响应式的
>   2. 不能直接改变 store 的状态，应该显式 commit mutation
---
## 样例
```javascript
// main.js
import Vue from 'vue'
import App from './App.vue'
import sotre from './store'

new Vue({
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
```html
<!-- @/views/Login.vue -->
<script>
export default {
    name: 'Login',
    computed: {
        userRole() {
            return this.$store.state.userType === 1 ? '管理员' : '普通用户'
        }
    }
}
</script>
```
---