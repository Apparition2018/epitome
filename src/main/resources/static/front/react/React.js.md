# React

---
## 参考网站
1. [React 官方中文文档](https://zh-hans.react.dev/learn)
2. [Ant Design Mobile](https://mobile.ant.design/index-cn)
---
## [JSX](https://legacy.reactjs.org/docs/introducing-jsx.html#gatsby-focus-wrapper)
1. 简介
    1. JavaScript 的语法扩展
    2. 可以使用花括号 {} 内嵌任何 JavaScript Expressions
    3. JSX 属性：className，htmlFor ...
2. 编译后
    1. 一种语法糖 - React.createElement()
    2. ReactElement 对象
---
## [创建一个 React 应用](https://legacy.reactjs.org/docs/create-a-new-react-app.html#create-react-app)
- [react/create-react-app](https://github.com/facebook/create-react-app)
- [Getting Started](https://create-react-app.dev/docs/getting-started)
```bash
npx create-react-app my-app [--template typescript]
# or
npm init react-app my-app
# or
yarn create react-app my-app

cd my-app

npm start
# or
yarn start

npm test
# or
yarn test

npm run build
# or
yarn build
```
## Lifecycle
| 阶段 | React 15（旧版）                | React 16.3+（新版）                   | 变化说明                      |
|:---|:----------------------------|:----------------------------------|:--------------------------|
| 挂载 | `constructor`               | `constructor`                     | 无变化                       |
| 挂载 | `componentWillMount`        | `static getDerivedStateFromProps` | 旧方法已移除，改为静态方法，在挂载和更新前统一调用 |
| 挂载 | `render`                    | `render`                          | 无变化                       |
| 挂载 | `componentDidMount`         | `componentDidMount`               | 无变化                       |
| 更新 | `componentWillReceiveProps` | `static getDerivedStateFromProps` | 旧方法已移除，新静态方法替代            |
| 更新 | `shouldComponentUpdate`     | `shouldComponentUpdate`           | 无变化                       |
| 更新 | `componentWillUpdate`       | `getSnapshotBeforeUpdate`         | 旧方法已移除，新方法在提交 DOM 前调用     |
| 更新 | `render`                    | `render`                          | 无变化                       |
| 更新 | `componentDidUpdate`        | `componentDidUpdate`              | 无变化                       |
| 卸载 | `componentWillUnmount`      | `componentWillUnmount`            | 无变化                       |
| 错误 | 无                           | `static getDerivedStateFromError` | 新增，用于错误边界                 |
| 错误 | 无                           | `componentDidCatch`               | 新增，用于记录错误日志               |
---
## 基本概念
1. [React.Component](https://zh-hans.reactjs.org/docs/react-component.html)
    - 要定义 React 组件类，需要 extend React.Component
    - 如：`class ShoppingList extends React.Component {}`
2. Props（属性）- NameCard.js
    - 组件像一个函数一样，接受特定的不可变的输入(props)，产出特定的输出(React elements)，纯函数
    - V = f(props)
3. State（状态）- LikesButton.js
    - 组件内部的数据，可以动态改变
    - this.setState() 是更新 state 的唯一途径
4. 生命周期 - DigitalClock.js
    ![生命周期](https://img1.sycdn.imooc.com/60785824000118b219201080.jpg)
5. Forms（表单）- CommentBox.js
    - 表单元素和其它 DOM 元素的区别
    - Controlled Components - 受控组件
6. Context - theme-context.js, ThemeBar.js
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
## [压缩 JavaScript 代码](https://gist.github.com/gaearon/42a2ffa41b8319948f9be4076286e1f3)
1. `npm install terser`
2. `npx terser -c -m -o button.min.js -- button.js`
---
