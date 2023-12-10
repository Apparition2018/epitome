import React from 'react'
import ReactDOM from 'react-dom'
// App 组件，大写字母开头
import TodoList from './TodoList'
import './style.css'

ReactDOM.render(
  <React.StrictMode>
    <TodoList />
  </React.StrictMode>,
  document.getElementById('root')
)
