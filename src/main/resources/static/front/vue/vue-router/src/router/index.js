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

const Nested = {
    template: `
        <div>
            <h2>Nested</h2>
            <router-view></router-view>
        </div>
    `
}
const NestedChildren0 = {template: '<div>0</div>'}
const NestedChildren1 = {template: '<div>1</div>'}
const NestedChildren2 = {template: '<div>2</div>'}

const NamedView0 = {template: '<div>0</div>'}
const NamedView1 = {template: '<div>1</div>'}
const NamedView2 = {template: '<div>2</div>'}

const Props = {
    template: `
        <div>
            <h2>Props</h2>
            <router-view></router-view>
        </div>
    `
}
function propsFn (route) {
    const now = new Date()
    return {
        name: now.getFullYear() + ' ' + route.params.name
    }
}

const Meta = {
    template: `
        <div>
            <span v-for="(item, index) in $route.meta" key="index">{{ '/' + item }}</span>
        </div>
    `
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
        component: Dynamic
    },
    {
        path: '/nested',
        component: Nested,
        children: [
            {path: '', component: NestedChildren0},
            {path: '1', component: NestedChildren1},
            {path: '2', component: NestedChildren2},
        ]
    },
    {
        path: '/named-routes/:id',
        name: 'NamedRoutes',
        component: {template: '<div>NameRoutes {{ $route.params.id }}</div>'}

    },
    {
        path: '/named-views',
        components: {
            default: NamedView0,
            a: NamedView1,
            b: NamedView2
        }
    },
    {
        path: '/redirect',
        redirect: '/'
    },
    {
        path: '/alias',
        alias: '/a',
        component: {template: '<div>alias</div>'}
    },
    {
        path: '/props',
        component: Props,
        children: [
            {path: 'boolean/:name', props: true, component: {props: ['name'], template: '<div>{{ name }}</div>'}},
            {path: 'object/static', props: {name: 'world'}, component: {template: '<div>{{ $attrs.name }}</div>'}},
            {path: 'function/:name', props: propsFn, component: {template: '<div>{{ $attrs.name }}</div>'}},
        ]
    },
    {
        path: '/meta',
        component: Meta,
        meta: ['a', 'b', 'c']
    },
]
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
    scrollBehavior (to, from, savedPosition) {
        if (savedPosition) {
            savedPosition.behavior = 'smooth'
            return savedPosition
        }
        return { x: 0, y: 0 }
    }
})

export default router
