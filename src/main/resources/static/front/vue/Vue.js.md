# Vue

---
## 参考网站
1. [Vue.js 介绍 — Vue.js 中文文档](https://vuejs.bootcss.com/guide/)
2. [API — Vue.js 中文文档](https://vuejs.bootcss.com/api/)
3. [Vue.js 教程](https://learning.dcloud.io/#/)
4. [vue-20-hello-world - CodeSandbox](https://codesandbox.io/s/github/vuejs/vuejs.org/tree/master/src/v2/examples/vue-20-hello-world)   
5. [Vue Documentation Guide](https://scrimba.com/learn/vuedocs)
6. [Vue风格指南总结及对应ESLint规则配置](https://www.cnblogs.com/dreamsqin/p/10906951.html)
7. [Vue项目中的文件/文件夹命名规范](https://www.jianshu.com/p/7e397fc3dd5c)
8. [vue 开发命名规范](https://segmentfault.com/a/1190000009805187)
---
## 教程
>### 模板语法
>1. 插值 {{val}}
>2. 指令
>3. 缩写
>### Class 与 Style 绑定
>- v-bind 用于 class 和 style 时，Vue.js 做了专门的增强  ，表达式结果可以是 字符串，对象，数组
---
## API
>### 全局 API
>```
>Vue.nextTick([callback, context])           在下次 DOM 更新循环结束之后执行延迟回调
>Vue.set(target, propertyName/index, value)  向响应式对象中添加一个 property，并确保这个新 property 同样是响应式的，且处罚试图更新
>Vue.filter(id, [definition])                注册或获取全局过滤器
>Vue.component(id, [definition])             注册或获取全局组件
>Vue.use(plugin)                             安装 Vue.js 插件。如果插件是一个对象，必须提供 install 方法。需在调用 new Vue() 之前被调用
>```
>### 选项
>>### Data
>>```
>>data                                       数据对象
>>props                                      父组件数据
>>computed                                   计算属性 (数据联动)
>>methods                                    方法
>>watch                                      侦听器 (异步场景)
>>```
>>### DOM
>>```
>>el                                         挂载目标
>>template                                   模板
>>```
>>### [生命周期钩子](https://cn.vuejs.org/v2/guide/instance.html#生命周期图示)
>>```
>>created                                    在实例创建完成后被立即调用
>>mounted                                    实例被挂载后调用
>>beforeDestroy                              实例销毁之前调用
>>```
>>### 资源
>>```
>>components                                 组件
>>```
>>### 其它
>>```
>>name
>>```
>### 实例属性
>- [$attrs 和 inheritAttrs](https://zhuanlan.zhihu.com/p/195455758)
>```
>vm.$data                                    Vue 实例观察的数据对象
>vm.$el                                      Vue 实例使用的根 DOM 元素
>vm.$refs                                    一个对象，持有注册过 ref attribute 的所有 DOM 元素和组件实例
>vm.$attrs                                   包含了父作用域中不作为 props 被识别或获取 的 attribute bindings (class 和 style 除外)                 
>```
>### 实例方法|数据
>```
>vm.$set                                     Vue.set 的别名；set 绑在 Vue.set 上，$set 绑在 Vue.prototype.$set 上
>```
>### 实例方法|事件
>```
>vm.$on                                      监听当前实例上的自定义事件。事件可以由 vm.$emit 触发
>vm.$emit                                    触发当前实例上的事件。附加参数都会传给监听器回调
>```
>### 实例方法|生命周期
>```
>vm.$mount                                   手动地挂载一个未挂载的实例
>vm.$nextTick                                将回调延迟到下次 DOM 更新循环之后执行
>```
>### 指令
>```
>v-text                                      textConent
>v-html                                      innerHTML
>v-show                                      条件渲染 (display: none)
>v-if                                        条件渲染
>v-else
>v-else-if
>v-for                                       列表渲染
>v-on:           @                           绑定事件
>v-bind          :                           绑定属性或组件
>v-model                                     双向绑定属性或组件
>v-slot          #                           可放置在函数参数位置的 JavaScript 表达式
>v-pre                                       跳过这个元素和它的子元素的编译过程
>```
>### 内置的组件
>```
>slot                                        作为组件模板之中的内容分发插槽，自身将被替换
>```
---
## 调试方法
1. console.log(), alter(), debugger + this
2. mounted() 里 window.vue = this
```
    mounted () {
        window.vue = this
    }
```
3. vm 实例：var app = new Vue({...})
4. Chrome
    - Network (XHR 等)
    - Presets (Slow 3G)
5. Vue Devtools
---
## [风格指南](https://vuejs.bootcss.com/style-guide/)
>### [必要](https://vuejs.bootcss.com/style-guide/#%E4%BC%98%E5%85%88%E7%BA%A7-A-%E7%9A%84%E8%A7%84%E5%88%99%EF%BC%9A%E5%BF%85%E8%A6%81%E7%9A%84-%E8%A7%84%E9%81%BF%E9%94%99%E8%AF%AF)
>1. 组件名为多个单词
>   - 因为所有的 HTML 元素名称都是单个单词的
>2. 组件 data 必须是一个函数，除了 (new Vue)
>   - 使组件总是返回一份新的 data
>3. props 定义应尽量详细
>4. 为 v-for 设置 key
>5. 避免 v-if 和 v-for 使用在同一元素
>   - v-for 比 v-if 具有更高优先级
>6. 为组件样式设置作用域
>   - scoped
>   - CSS Modules
>   - BEM
>7. 在插件、混入等扩展中始终为自定义的私有属性使用 $_ 前缀 或使用模块作用域
>   - 不允许外部访问的函数的私有性
>### [强力推荐](https://vuejs.bootcss.com/style-guide/#%E4%BC%98%E5%85%88%E7%BA%A7-B-%E7%9A%84%E8%A7%84%E5%88%99%EF%BC%9A%E5%BC%BA%E7%83%88%E6%8E%A8%E8%8D%90-%E5%A2%9E%E5%BC%BA%E5%8F%AF%E8%AF%BB%E6%80%A7)
>1. 一个组件，一个 .vue 文件
>2. 单文件组件的文件名应该要么始终是单词 PascalCase (大写开头)，要么始终是 kebab-case (横线连接)
>```
>   MyComponent.vue | my-component.vue
>```
>3. 应用特定样式和约定的基础组件 (也就是展示类的、无逻辑的或无状态的组件) 应该全部以一个特定的前缀开头
>```
>   BaseButton.vue | BaseTable.vue | BaseIcon.vue
>```
>4. 只应该拥有单个活跃实例的组件应该以 The 前缀命名，以示其唯一性
>```
>   TheHeading.vue | TheSidebar.vue
>```
>5. 和父组件紧密耦合的子组件应该以父组件名作为前缀命名
>```
>   TodoList.vue | TodoListItem.vue | TodoListItemButton.vue
>```
>6. 组件名应该以高级别的 (通常是一般化描述的) 单词开头，以描述性的修饰词结尾
>```
>   SearchButtonClear.vue | SearchButtonRun.vue | SearchInputQuery.vue
>```
>7. 在单文件组件、字符串模板和 JSX 中没有内容的组件应该是自闭合的 (DOM 模板例外)
>```
>   <MyComponent/>                  单文件组件、字符串模板和 JSX
>   <my-component></my-component>   DOM 模板
>```
>8. 在单文件组件和字符串模板中组件名应该总是 PascalCase 的，在 DOM 模板中总是 kebab-case 的
>```
>   <MyComponent/>                  单文件组件、字符串模板
>   <my-component></my-component>   DOM 模板
>   <my-component></my-component>   所有地方
>```
>9. JS/JSX 中的组件名应该始终是 PascalCase 的
>   - 简单的应用中只使用 Vue.component 进行全局组件注册时，可以使用 kebab-case
>```
>   Vue.component('MyComponent', {
>     // ...
>   })
>```
>```
>   import MyComponent from './MyComponent.vue'
>   export default {
>     name: 'MyComponent',
>     // ...
>   }
>```
>10. 组件名应该倾向于完整单词而不是缩写
>```
>   StudentDashboardSettings.vue | UserProfileOptions.vue
>```
>11. 在声明 prop 的时候，应该始终使用 camelCase，而在模板和 JSX 中应该始终使用 kebab-case
>```
>   props: {
>     greetingText: String
>   }
>```
>```
>   <WelcomeMessage greeting-text="hi"/>
>```
>12. 多个 attribute 的元素应该分多行撰写，每个 attribute 一行
>```
>   <img
>     src="https://vuejs.org/images/logo.png"
>     alt="Vue Logo"
>   >
>```
>13. 组件模板应该只包含简单的表达式，复杂的表达式则应该重构为计算属性或方法
>14. 应该把复杂计算属性分割为尽可能多的更简单的 property
>15. 非空 HTML attribute 值应该始终带引号
>16. 指令缩写要么都用要么都不用
>### [推荐](https://vuejs.bootcss.com/style-guide/#%E4%BC%98%E5%85%88%E7%BA%A7-C-%E7%9A%84%E8%A7%84%E5%88%99%EF%BC%9A%E6%8E%A8%E8%8D%90-%E5%B0%86%E9%80%89%E6%8B%A9%E5%92%8C%E8%AE%A4%E7%9F%A5%E6%88%90%E6%9C%AC%E6%9C%80%E5%B0%8F%E5%8C%96)
>1. 组件/实例的选项的顺序
>```
>   1. 副作用（触发组件外的影响）
>      - el
>   2. 全局感知（要求组件以外的知识）
>      - name
>      - parent
>   3. 组件类型（更改组件的类型）
>      - functional
>   4. 模板修改器（改变模板的编译方式）
>      - delimiters
>      - comments
>   5. 模板依赖（模板内使用的资源）
>      - components
>      - directives
>      - filters
>   6. 组合（向选项里合并 property）
>      - extends
>      - mixins
>   7. 接口（组件的接口）
>      - inheritAttrs
>      - model
>      - props / propsData
>   8. 本地状态（本地的响应式 property)
>      - data
>      - computed
>   9. 事件（通过响应式事件触发的回调）
>      - watch
>      - 生命周期钩子（按照它们被调用的顺序）
>              2.x                  3.x
>                    - beforeCreate
>                    - created
>                    - beforeMount
>                    - mounted
>                    - beforeUpdate
>                    - updated
>                    - activated
>        - deactivated
>        - beforeDestroy      - beforeUnmount
>        - destroyed          - unmounted
>                             - errorCaptured
>                             - renderTracked
>                             - renderTriggered
>   10. 非响应式的 property（不依赖响应系统的实例 property）
>      - methods
>   11. 渲染（组件输出的声明式描述）
>      - template / render
>      - renderError
>```
>2. 元素 attribute 的顺序
>```
>   1. 定义（提供组件的选项）
>      - is
>   2. 列表渲染（创建多个变化的相同元素）
>      - v-for
>   3. 条件渲染（元素是否渲染/显示）
>      - v-if
>      - v-else-if
>      - v-else
>      - v-show
>      - v-cloak
>   4. 渲染方式（改变元素的渲染方式）
>      - v-pre
>      - v-once
>   5. 全局感知（需要超越组件的知识）
>      - id
>   6. 唯一的 attribute（需要唯一值的 attribute）
>      - ref
>      - key
>   7. 双向绑定（把绑定和事件结合起来）
>      - v-model
>   8. 其它 attribute（所有普通的绑定或未绑定的 attribute）
>   9. 事件（组件事件监听器）
>      - v-on
>   10. 内容（覆写元素的内容）
>      - v-html
>      - v-text
>   ```
>3. 单文件组件的顶级元素的顺序
>```
>   <template>...</template>
>   <script>/* ... */</script>
>   <style>/* ... */</style>
>```
>4. 在多个 property 之间增加空行
>### [谨慎使用](https://vuejs.bootcss.com/style-guide/#%E4%BC%98%E5%85%88%E7%BA%A7-D-%E7%9A%84%E8%A7%84%E5%88%99%EF%BC%9A%E8%B0%A8%E6%85%8E%E4%BD%BF%E7%94%A8-%E6%9C%89%E6%BD%9C%E5%9C%A8%E5%8D%B1%E9%99%A9%E7%9A%84%E6%A8%A1%E5%BC%8F)
>1. 没有在 v-if/v-else-if/v-else 中使用 key
>2. scoped 中使用的元素选择器
>   - 大量使用元素选择器是很慢的
>3. 隐性的父子组件通信
>   - 优先通过 prop 和事件进行父子组件之间的通信，而不是 this.$parent 或变更 prop
>4. 非 Flux 的全局状态管理
>   - 应该优先通过 Vuex 管理全局状态，而不是通过 this.$root 或一个全局事件总线
---
