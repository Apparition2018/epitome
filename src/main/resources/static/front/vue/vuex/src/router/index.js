import Vue from 'vue'
import VueRouter from 'vue-router'
import Case from '@/views/case'
import UserCenter from '@/views/case/UserCenter'
import Guide from '@/views/guide'
import {mapState, mapGetters, mapMutations, mapActions} from 'vuex'
import Login from "@/views/case/Login";

Vue.use(VueRouter)

const Title = (title) => {
    return {
        template: `
            <div>
                <h2>{{ title }}</h2>
                <router-view></router-view>
                <h3><router-link to="/guide">Back</router-link></h3>
            </div>
        `,
        data() {
            return {
                title: title
            }
        }
    }
}

const State1 = {
    template: `<div>{{ count }}</div>`,
    computed: {
        count () {
            return this.$store.state.count
        }
    }
}
const State2 = {
    template: `
      <div>
        <div>{{ count }}</div>
        <div>{{ count2 }}</div>
        <div>{{ count3 }}</div>
      </div>
    `,
    data () {
        return {
            x: 0
        }
    },
    computed: mapState({
        count (state) {
            return state.count + this.x
        },
        count2: state => state.count,
        count3: 'count'
    })
}
const State3 = {
    template: `<div>{{ count }}</div>`,
    computed: mapState(['count'])
}
const State4 = {
    template: `<div>{{ count }}</div>`,
    computed: {...mapState(['count'])}
}

const Getters1 = {
    template: `
        <div>
            <div>{{ doneTodos }}</div>
            <div>{{ doneTodosCount }}</div>
            <div>{{ getTodoById(2) }}</div>
        </div>
    `,
    computed: {
        doneTodos () {
            return this.$store.getters.doneTodos
        },
        getTodoById () {
            return (id) => this.$store.getters.getTodoById(id)
        },
        ...mapGetters(['doneTodosCount'])
    }
}

const Mutations1 = {
    template: `
        <div>
            <div>{{ count }}</div>
            <p>
              <span @click="increment" style="cursor: pointer">+1</span> |
              <span @click="incrementBy(1)" style="cursor: pointer">+1</span> |
              <span @click="incrementBy2(2)" style="cursor: pointer">+2</span> |
              <span @click="incrementBy3(3)" style="cursor: pointer">+3</span> |
              <span @click="add" style="cursor: pointer">+1</span>
            </p>
        </div>
    `,
    computed: {...mapState(['count'])},
    methods: {
        increment () {
            this.$store.commit('increment')
        },
        incrementBy (n) {
            this.$store.commit('incrementBy', n);
        },
        incrementBy2 (n) {
            this.$store.commit('incrementBy2', {
                n: n
            });
        },
        incrementBy3 (n) {
            this.$store.commit({
                type: 'incrementBy2',
                n: n
            })
        },
        ...mapMutations({add: 'increment'})
    }
}

const Actions1 = {
    template: `
        <div>
            <div>{{ count }}</div>
            <p>
              <span @click="increment" style="cursor: pointer">+1</span> |
              <span @click="incrementBy(2)" style="cursor: pointer">+2</span> |
              <span @click="add" style="cursor: pointer">+1</span>
            </p>
        </div>
    `,
    computed: {...mapState(['count'])},
    methods: {
        increment () {
            this.$store.dispatch('increment').then();
        },
        incrementBy (n) {
            this.$store.dispatch({
                type: 'incrementBy',
                n: n
            }).then();
        },
        ...mapActions({add: 'increment'})
    }
}

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
        component: Guide
    },
    {
        path: '/guide/state',
        name: 'State',
        component: Title('State'),
        children: [
            {path: '1', component: State1},
            {path: '2', component: State2},
            {path: '3', component: State3},
            {path: '4', component: State4},
        ]
    },
    {
        path: '/guide/getters',
        name: 'Getters',
        component: Title('Getters'),
        children: [
            {path: '1', component: Getters1}
        ]
    },
    {
        path: '/guide/mutations',
        name: 'Mutations',
        component: Title('Mutations'),
        children: [
            {path: '1', component: Mutations1}
        ]
    },
    {
        path: '/guide/actions',
        name: 'Actions',
        component: Title('Actions'),
        children: [
            {path: '1', component: Actions1}
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
