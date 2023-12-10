import Vue from 'vue'
import App from './App.vue'
import { currency } from '@/api/currency'
import router from './router'
import store from './store'

Vue.config.productionTip = false
Vue.filter('currency', currency)

router.beforeEach((to, from, next) => {
  if (store.state.userInfo || to.path === '/login' || to.path.startsWith('/guide')) {
    next()
  } else {
    next({
      path: '/login'
    })
  }
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
