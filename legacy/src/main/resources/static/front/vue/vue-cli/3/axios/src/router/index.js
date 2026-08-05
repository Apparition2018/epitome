import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/request-method',
    name: 'axios 请求方法',
    component: () => import('../views/demo/RequestMethod')
  },
  {
    path: '/concurrent-request',
    name: 'axios 并发请求',
    component: () => import('../views/demo/Concurrency')
  },
  {
    path: '/contact-list',
    name: '联系人列表',
    component: () => import('../views/case/ContactList')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
