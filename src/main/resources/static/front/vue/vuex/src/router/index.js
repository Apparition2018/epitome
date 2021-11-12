import Vue from 'vue'
import VueRouter from 'vue-router'
import Case from '@/views/case'
import UserCenter from '@/views/case/UserCenter'
import Guide from '@/views/guide'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Case',
        component: Case
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/case/Login')
    },
    {
        path: '/user-center',
        name: 'UserCenter',
        component: UserCenter
    },
    {
        path: '/course/:id',
        name: 'Course',
        component: () => import('@/views/case/Course')
    },
    {
        path: '/guide',
        name: 'Guide',
        component: () => Guide

    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
