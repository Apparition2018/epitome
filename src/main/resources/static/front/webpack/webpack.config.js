const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const { CleanWebpackPlugin } = require('clean-webpack-plugin')
const webpack = require('webpack')

module.exports = {
  mode: 'development',
  entry: {
    index: './src/index.js'
  },
  devServer: {
    contentBase: './dist',
    hot: true
  },
  plugins: [
    new CleanWebpackPlugin(),
    // 1.创建 html 文件
    // 2.把所有 bundle.js 添加到 html 文件
    // https://blog.csdn.net/u012443286/article/details/93363949
    new HtmlWebpackPlugin({
      title: 'html 标题'
    }),
    // 模块热替换插件
    new webpack.HotModuleReplacementPlugin()
  ],
  output: {
    filename: '[name].bundle.js',
    chunkFilename: '[name].bundle.js',
    path: path.resolve(__dirname, 'dist')
  },
  optimization: {
    // 不导出未使用的代码
    // usedExports: true
    // 提取公共代码
    // splitChunks: {
    //     chunks: 'all'
    // }
  }
}
