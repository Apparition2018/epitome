export default {
    /* case */
    login (state, v) {
        state.userInfo = v
    },
    setMemberInfo (state, v) {
        state.userStatus = v.userStatus
        state.vipLevel = v.vipLevel
    },

    /* guide */
    increment (state) {
        state.count++
    },
    incrementBy (state, n) {
        state.count += n
    },
    incrementBy2 (state, payload) {
        state.count += payload.n
    }
}