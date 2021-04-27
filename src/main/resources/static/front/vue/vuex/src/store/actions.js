export default {
    buyVip({commit}, e) {
        return new Promise((resolve, reject) => {
            setTimeout(() => {
                commit('setMemberInfo', {
                    userStatus: e.userStatus,
                    vipLevel: e.vipLevel
                })
                resolve("购买成功")
            }, 500)
        })
    }
}