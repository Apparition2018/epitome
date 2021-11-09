import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const Dynamic = {
    template: `<div>Dynamic {{ $route.params.id }}</div>`,
    watch: {
        $route (to, from) {
            console.log('from: ', from)
            console.log('to: ', to)
        }
    },
    beforeRouteUpdate (to, from, next) {
        console.log('from: ', from)
        console.log('to: ', to)
        console.log(this.$route.params) // 等于 from.params.id
        next();
        console.log(this.$route.params) // 等于 to.params.id
    }
}

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
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
        path: '/dynamic/:id',
        name: 'Dynamic',
        component: Dynamic
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
