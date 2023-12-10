import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import getters from './getters'
import mutations from './mutations'
import actions from './actions'
import cart from './modules/cart'
import products from './modules/products'

Vue.use(Vuex)

const fruits = {
  namespaced: true,
  state: () => ({
    apple: 2
  }),
  mutations: {
    buyApple (state, n) {
      state.apple += n
    }
  },
  actions: {
    buyApple2 ({ rootState }, n) {
      rootState.fruits.apple += n
    }
  }
}

const animals = {
  state: () => ({
    cat: 3
  }),
  getters: {
    // rootState: 全局的 state，第三个参数
    // rootGetters: 全局的 getters，第四个参数
    total: (state, getters, rootState, rootGetters) => {
      return state.cat + rootState.fruits.apple
    }
  },
  mutations: {
    buyAnimal (state, n) {
      state.cat += n
    }
  },
  actions: {
    buy ({ commit }) {
      commit('buyAnimal', 1)
      // 在全局命名空间内分发 action 或提交 mutation，需传入第三参数 {root: true}
      commit('fruits/buyApple', 1, { root: true })
    },
    // 在带命名空间的模块注册全局 action，需添加 root: true，并将这个 action 的定义放在函数 handler 中
    buyRoot: {
      root: true,
      handler ({ commit }) {
        commit('buyAnimal', 1)
        commit('fruits/buyApple', 1)
      }
    }
  }
}

export default new Vuex.Store({
  state,
  getters,
  mutations,
  actions,
  modules: { cart, products, fruits, animals }
})
