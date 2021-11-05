import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/backTopOne',
        name: 'backTopOne',
        component: () => import('../views/BackTopOne')
    },
    {
        path: '/backTopTwo',
        name: 'backTopTwo',
        component: () => import('../views/BackTopTwo')
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/Login')
    }
]

export default new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})
