export default {
    /* case */
    memberInfo (state) {
        switch (state.userStatus) {
            case 0:
                return '普通会员'
            case 1:
                return 'vip会员'
            case 2:
                return `高级vip${state.vipLevel}会员`
            default:
                return '普通会员'
        }
    },

    /* guide */
    doneTodos: state => state.todos.filter(todo => todo.done),
    doneTodosCount: (state, getters) => getters.doneTodos.length,
    getTodoById: (state) => (id) => state.todos.find(todo => todo.id === id)
}