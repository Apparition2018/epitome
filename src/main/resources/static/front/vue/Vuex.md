# Vuex
把组件的共享状态抽取出来，以一个全局单例模式管理

---
## 参考网站
1. [Vuex](https://vuex.vuejs.org/zh/)
2. [Vuex Tutorial: Learn Vuex For Free | Scrimba](https://scrimba.com/learn/vuex)
---
## 样例
```javascript
// '@/store/index.js'
export default new Vuex.Store({
    state: {
        lists: []
    },
    mutations: {
        addItem (state, value) {
            state.lists.push(value)
        }
    },
    actions: {},
    modules: {}
})

// '@/views/login/Add.vue'
export default {
    name: 'Add',
    data () {
        return {
            title: '',
            content: ''
        }
    },
    methods: {
        add () {
            store.commit('addItem', {
                title: this.title,
                content: this.content
            })
            this.title = ''
            this.content = ''
            this.$router.push('/home/list')
        }
    }
}
```
---
## 核心概念
1. State
2. Mutations
3. Getters
4. Actions
5. Modules