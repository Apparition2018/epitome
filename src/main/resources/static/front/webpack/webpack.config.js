const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const {CleanWebpackPlugin} = require('clean-webpack-plugin')
const webpack = require('webpack')

module.exports = {
    entry: {
        index: './src/index.js',
        another: './src/another-module.js'
    },
    // 控制是否生成，以及如何生成 source map
    // https://v4.webpack.docschina.org/configuration/devtool
    devtool: 'inline-source-map',
    devServer: {
        contentBase: './dist',
        hot: true
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            }
        ]
    },
    plugins: [
        new CleanWebpackPlugin(),
        new HtmlWebpackPlugin({
            title: 'html 标题',
        }),
        // 模块热替换插件
        new webpack.HotModuleReplacementPlugin()
    ],
    output: {
        filename: '[name].bundle.js',
        path: path.resolve(__dirname, 'dist')
    },
    // mode: 'production' 会自动添加 ModuleConcatenationPlugin 插件
    // mode: 'production',
}