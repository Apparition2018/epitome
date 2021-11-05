// 注册全局组件
import Vue from 'vue'
import BackTop from '../backTop/BackTop'
import AccountLogin from '../accountLogin/AccountLogin'
import PhoneLogin from '../phoneLogin/PhoneLogin'

Vue.component('backTop', BackTop)
Vue.component('accountLogin', AccountLogin)
Vue.component('phoneLogin', PhoneLogin)