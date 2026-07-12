import React from 'react'
import { createRoot } from 'react-dom/client'
// App 组件，大写字母开头
import TodoList from './TodoList'
import './style.css'

const root = createRoot(document.getElementById('root'))
root.render(
  <React.StrictMode>
    <TodoList />
  </React.StrictMode>,
)
