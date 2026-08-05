import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/count-to',
    name: 'CountTo',
    component: () => import('@/views/CountTo')
  },
  {
    path: '/file-saver',
    name: 'FileSaver',
    component: () => import('@/views/FileSaver')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
