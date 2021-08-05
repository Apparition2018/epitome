import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: "Index",
    component: () => import('@/views/Index.vue')
  },
  {
    path: '/login',
    name: "Login",
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/user-center',
    name: "UserCenter",
    component: () => import('@/views/UserCenter.vue')
  },
  {
    path: '/course/:id',
    name: "Course",
    component: () => import('@/views/Course.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
