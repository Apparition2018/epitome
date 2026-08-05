// 在 Node.js API 中使用 webpack dev server：https://v4.webpack.docschina.org/guides/hot-module-replacement/#%E9%80%9A%E8%BF%87-node-js-api
const webpackDevServer = require('webpack-dev-server')
const webpack = require('webpack')

const config = require('./webpack.config.js')
const options = {
  contentBase: './dist',
  hot: true,
  host: 'localhost'
}

webpackDevServer.addDevServerEntrypoints(config, options)
const compiler = webpack(config)
const server = new webpackDevServer(compiler, options)

server.list(5000, 'localhost', () => {
  console.log('dev server listening on port 5000')
})
