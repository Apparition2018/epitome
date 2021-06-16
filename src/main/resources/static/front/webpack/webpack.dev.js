const {merge} = require('webpack-merge')
const common = require('./webpack.common')

module.exports = merge(common, {
    mode: 'development',
    // 控制是否生成，以及如何生成 source map
    // https://v4.webpack.docschina.org/configuration/devtool
    devtool: 'inline-source-map',
    // https://v4.webpack.docschina.org/configuration/dev-server
    devServer: {
        contentBase: './dist'
    }
})