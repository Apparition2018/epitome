# Vuex
![data flow](https://vuex.vuejs.org/flow.png)
把组件的共享状态抽取出来，以一个全局单例模式管理
- 多个视图依赖于同一状态
- 来自不同视图的行为需要变更同一状态
---
## 参考网站
1. [Vuex](https://vuex.vuejs.org/zh/)
2. [Vuex Tutorial: Learn Vuex For Free | Scrimba](https://scrimba.com/learn/vuex)
3. [vuex/examples at dev · vuejs/vuex](https://github.com/vuejs/vuex/tree/dev/examples)
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

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        count: 0
    },
    getters: {
        double: state => state * 2
    },
    mutations: {
        increment (state) {
            state.count++   
        }
    },
    actions: {
        increment (context) {
            context.commit('increment')
        }
    },
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
>```javascript
>// state.js
>export default {
>   count: 0
>}
>```
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
>import {mapState} from 'vuex'
>export default {
>   data() {
>       return {
>           x: 0
>       }
>   },
>   // mapState
>   computed: mapState({
>       // 常规函数，可通过 this 访问 local state
>       count (state) {
>           return state.count + this.x
>       },
>       // 箭头函数
>       count2: state => state.count,
>       // 等同于 state => state.count
>       count3: 'count',
>   })
>}
>```
>```javascript
>import {mapState} from 'vuex'
>export default {
>   // 当 computed property 和 state sub tree name 相同，可以传入一个字符串数组 
>   computed: mapState(['count'])
>}
>```
>```javascript
>import {mapState} from 'vuex'
>export default {
>   // 使用对象展开运算符将此对象混入到外部对象中
>   computed: {...mapState(['count'])}
>}
>```
>## [Getters](https://vuex.vuejs.org/zh/guide/getters.html)
>- 当我们需要从 store 中的 state 中派生出一些状态，Vuex 允许在 store 中定义 getters。可以看作 computed properties
>```javascript
>// getters.js
>export default {
>   doneTodos: state => state.todos.filter(todo => todo.done),
>   // state 作为第一个参数，getters 作为第二个参数
>   doneTodosCount: (state, getters) => getters.doneTodos.length,
>   // 返回一个函数，实现给 getter 传参
>   getTodoById: (state) => (id) => state.todos.find(todo => todo.id === id)
>}
>```
>```vue
><template>
>   <div>
>       <div>{{ doneTodos }}</div>
>       <div>{{ doneTodosCount }}</div>
>       <div>{{ getTodoById(2) }}</div>
>   </div>
></template>
>```
>```javascript
>export default {
>   computed: {
>       doneTodos () {
>           return this.$store.getters.doneTodos
>       },
>       getTodoById () {
>           return (id) => this.$store.getters.getTodoById(id)
>       },
>       ...mapGetters(['doneTodosCount'])
>   }
>}
>```
>## [Mutations](https://vuex.vuejs.org/zh/guide/mutations.html)
>- 更改 Vuex 的 store 中的状态的唯一方法是 commit a mutation
>- 每个 mutation 都有一个事件类型 type (String) 和一个回调函数 handler
>- 不能直接调用 handler，需要以相应的 type 调用 `store.commit('increment')`
>- [mutation 必须是同步函数](https://www.zhihu.com/question/48759748/answer/112823337)
>- 遵守 vue 的相应规则：
>   1. 尽量提前在 store 中初始化好所有属性
>   2. 当需要添加新属性时：
>       1. `Vue.set( target, propertyName/index, value )`
>       2. 以新对象替换旧对象，如：`state.obj = { ...state.obj, newProp: 123 }`
>```javascript
>// mutations.js
>export default {
>   // state 作为第一个参数
>   increment (state) {
>       state.count++
>   },
>   // 可以传额外参数，称之为 payload
>   incrementBy (state, n) {
>       state.count += n
>   },
>   // payload 可以是一个对象
>   incrementBy2 (state, payload) {
>       state.count += payload.n
>   }
>}
>```
>```vue
><div>
>   <div>{{ count }}</div>
>   <p>
>       <span @click="increment" style="cursor: pointer">+1</span> |
>       <span @click="incrementBy(1)" style="cursor: pointer">+1</span> |
>       <span @click="incrementBy2(2)" style="cursor: pointer">+2</span> |
>       <span @click="incrementBy3(3)" style="cursor: pointer">+3</span> |
>       <span @click="add" style="cursor: pointer">+1</span> |
>   </p>
></div>
>```
>```javascript
>export default {
>   computed: {...mapState(['count'])},
>   methods: {
>       increment () {
>           this.$store.commit('increment')
>       },
>       incrementBy (n) {
>           this.$store.commit('incrementBy', n);
>       },
>       incrementBy2 (n) {
>           this.$store.commit('incrementBy2', {
>               n: n
>           });
>       },
>       // Object-Style Commit
>       incrementBy3 (n) {
>           this.$store.commit({
>               type: 'incrementBy2',
>               n: n
>           })
>       },
>       // mapMutations
>       ...mapMutations({add: 'increment'})
>   }
>}
>```
>## [Actions](https://vuex.vuejs.org/zh/guide/actions.html)
>- Actions 提交的是 mutations，而不是 state
>- Actions 可以包含任意异步操作
>```javascript
>// actions.js
>export default {
>   // context 与 store 具有相同的 methos 和 properties
>   increment (context) {
>       context.commit('increment')
>   },
>   // 解构赋值
>   incrementBy ({commit}, payload) {
>       // 异步操作
>       setTimeout(() => {
>           commit('incrementBy2', payload)
>       }, 500)
>   }
>}
>```
>```vue
><div>
>   <div>{{ count }}</div>
>   <p>
>       <span @click="increment" style="cursor: pointer">+1</span> |
>       <span @click="incrementBy(2)" style="cursor: pointer">+2</span> |
>       <span @click="add" style="cursor: pointer">+1</span>
>   </p>
></div>
>```
>```javascript
>export default {
>   computed: {...mapState(['count'])},
>   methods: {
>       increment () {
>           this.$store.dispatch('increment').then();
>       },
>       incrementBy (n) {
>           this.$store.dispatch({
>               type: 'incrementBy',
>               n: n
>           }).then();
>       },
>       // mapActions
>       ...mapActions({add: 'increment'})
>   }
>}
>```
>## [Modules](https://vuex.vuejs.org/zh/guide/modules.html)
>- `namespaced: true`：调用 getters, mutations, actions 时，需要加上命名空间
>- createNamespacedHelpers：创建 namespaced component binding helpers
>```javascript
>import { createNamespacedHelpers } from 'vuex'
>const { mapState, mapActions } = createNamespacedHelpers('fruits')
>```
>- 动态模块注册：`store.registerModule`，`store.unregisterModule`，`store.hasModule`
>- @see [store](./vuex/src/store/index.js) 的 fruits 和 animals，[router](./vuex/src/router/index.js) 的 Modules1
---