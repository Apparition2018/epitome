module.exports = {
    configureWebpack: {
        resolve: {
            alias: {
                // 运行时 + 编译器 vs. 只包含运行时：
                // https://cn.vuejs.org/v2/guide/installation.html#%E8%BF%90%E8%A1%8C%E6%97%B6-%E7%BC%96%E8%AF%91%E5%99%A8-vs-%E5%8F%AA%E5%8C%85%E5%90%AB%E8%BF%90%E8%A1%8C%E6%97%B6
                'vue$': 'vue/dist/vue.esm.js'
            }
        }
    }
}