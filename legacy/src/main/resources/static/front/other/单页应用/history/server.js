const express = require('express')
const path = require('path')
const app = express()

app.get('/', function (req, res) {
  res.sendFile(path.resolve(__dirname, './www/history.html'))
})

app.get('*', function (req, res) {
  res.sendFile(path.resolve(__dirname, './www/history.html'))
})

const port = 3333

const server = app.listen(port, function () {
  console.log('访问地址为 http://localhost:' + port)
})
