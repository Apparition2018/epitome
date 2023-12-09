define(function (require, exports, module) {
  require('https://unpkg.com/jquery@^1/dist/jquery.min.js')
  const math = require('./math')
  const result = math.multiply(9, 9)
  $('.cmd').html(`9 * 9 = ${result}`)
})
