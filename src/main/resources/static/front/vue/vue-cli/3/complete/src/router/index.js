import Vue from 'vue'
import VueRouter from 'vue-router'
import Link from '@/components/link/Link'
import A from '@/components/link/A'
import B from '@/components/link/B'
import A1 from '@/components/link/A1'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('@/views/Index')
  },
  {
    path: '/link',
    name: 'Link',
    component: Link,
    children: [
      {
        path: '/a',
        component: A,
        children: [
          {
            path: '/a1',
            component: A1
          }
        ]
      }, {
        path: '/b',
        component: B
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
