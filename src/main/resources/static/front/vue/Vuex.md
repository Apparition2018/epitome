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