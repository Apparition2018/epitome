# Vuex
把组件的共享状态抽取出来，以一个全局单例模式管理
- 多个视图依赖于同一状态
- 来自不同视图的行为需要变更同一状态
---
## 参考网站
1. [Vuex](https://vuex.vuejs.org/zh/)
2. [Vuex Tutorial: Learn Vuex For Free | Scrimba](https://scrimba.com/learn/vuex)
---
## 核心概念
```
1. State        数据仓库
2. Getters      获取数据
3. Mutations    修改数据
4. Actions      提交 Mutations
5. Modules      模块
```
---
## 样例
```javascript
// 'main.js'
import Vue from 'vue'
import App from './App.vue'
import sotre from './store'

new Vue({
    store,
    render: h => h(App)
}).$mount('#app')
```
```javascript
// '@/store/index.js'
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
<!-- '@/views/Login.vue' -->
<script>
import store from "@/store";

export default {
    name: 'Login',
    computed: {
        userRole() {
            return store.state.userType === 1 ? '管理员' : '普通用户'
        }
    }
}
</script>
```
---