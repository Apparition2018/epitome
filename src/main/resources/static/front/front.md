# 前端

---
## 问题
1. [浅谈script标签的defer和async](https://segmentfault.com/a/1190000006778717)
---
## 优化
- [前端性能优化指南](https://cnodejs.org/topic/55e31bd6898f6bdc7e5551ac)
### JS
1. 函数调用不要传太多参数，可读性差，可构建一个对象作为参数
    ```javascript
    function greet(user) { }
    greet({name: 'Bob', gender: 'male'})
    ```
2. 减少 [reflow](./js/术语/reflow.html)
3. Be Lazy：使脚本尽可能少地执行，或者不执行
    1. 短路求值：`a && b || c`
    2. 基于事件去写相应的处理方法 ???
    3. 惰性函数 
---