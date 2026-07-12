import Vue from 'vue'
import VueRouter from 'vue-router'
import Link from '@/components/link/Link'
import LinkA from '@/components/link/LinkA'
import LinkB from '@/components/link/LinkB'
import LinkA1 from '@/components/link/LinkA1'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('@/views/Index'),
  },
  {
    path: '/link',
    name: 'Link',
    component: Link,
    children: [
      {
        path: '/a',
        component: LinkA,
        children: [
          {
            path: '/a1',
            component: LinkA1,
          },
        ],
      },
      {
        path: '/b',
        component: LinkB,
      },
    ],
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
})

export default router
