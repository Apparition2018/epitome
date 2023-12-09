require.config({
  paths: {
    jquery: 'https://unpkg.com/jquery@^1/dist/jquery.min'
  }
})

require(['jquery', 'math'], function ($, math) {
  const result = math.multiply(9, 9)
  $('.amd').html(`9 * 9 = ${result}`)
})
