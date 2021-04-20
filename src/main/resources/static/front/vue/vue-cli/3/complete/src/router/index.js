import Vue from 'vue'
import VueRouter from 'vue-router'
import First from '@/components/First'
import A from '@/components/A'
import B from '@/components/B'
import A1 from '@/components/A1'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'First',
    component: First,
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
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
