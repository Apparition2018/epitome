# React

---
## 参考网站
1. [React 官方中文文档](https://react.docschina.org)
2. [Ant Design Mobile](https://mobile.ant.design/index-cn)
---
## 特性
1. 声明式
2. 组件化
3. 一次学习，随处编写
---
## Create React App
```bash
npx create-react-app my-app
cd my-app
npm start
```
---
## [JSX](https://www.runoob.com/react/react-jsx.html)
>### 简介
>1. JavaScript 的语法扩展
>2. 可以使用花括号 {} 内嵌任何 JavaScript Expressions
>3. JSX 属性：className，htmlFor ...
>### 编译后
>1. 一种语法糖 - React.createElement()
>2. ReactElement 对象
---
## 基本概念
1. Props（属性）- NameCard.js
    - 组件像一个函数一样，接受特定的不可变的输入(props)，产出特定的输出(React elements)，纯函数
    - V = f(props) 
2. State（状态) - LikesButton.js
    - 组件内部的数据，可以动态改变
    - this.setState() 是更新 state 的唯一途径
3. 生命周期 - DigitalClock.js  
    <img alt="生命周期" src="https://img3.mukewang.com/60785824000118b219201080.jpg" width="500"/>
4. Forms（表单）- CommentBox.js
    - 表单元素和其它 DOM 元素的区别
    - Controlled Components - 受控组件
5. Context - theme-context.js, ThemeBar.js
   - 共享全局数据
   - 不要仅仅为了避免在几个层级下的组件传递 props 而使用 context
---
## 不同于其它双向绑定框架的概念
1. 状态提升 (lifting state up)
2. 自下而上的数据流 (top-down data flow)
---
## CSS
1. style={{background: 'red', color: '#fff'}}
2. className='xxx'，CSS 文件中 .xxx {...}
---