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
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/test',
    name: 'Test',
    component: () => import('@/views/Test')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
