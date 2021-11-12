import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/login/Login.vue'
import Home from '../views/login/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    children: [
      {
        path: 'list',
        name: 'List',
        component: () => import(/* webpackChunkName: "list" */ '../views/login/List.vue')
      }, {
        path: 'user',
        name: 'User',
        component: () => import(/* webpackChunkName: "user" */ '../views/login/User.vue')
      }
    ]
  }, {
    path: '/add',
    name: 'Add',
    component: () => import(/* webpackChunkName: "add" */ '../views/login/Add.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  linkActiveClass: 'active',
  routes
})

export default router
