import Vue from 'vue'
import Router from 'vue-router'
import First from '@/components/First'
import A from '@/components/A'
import B from '@/components/B'
import A1 from '@/components/A1'

Vue.use(Router)

export default new Router({
  routes: [
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
  ]
})
