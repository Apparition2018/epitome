require.config({
  // 路径配置，js 文件省略后缀名
  paths: {
    jquery: 'https://unpkg.com/jquery@^1/dist/jquery.min'
  }
})

/**
 * 根据依赖定义函数：https://requirejs.org/docs/api.html#deffunc
 */
require(['jquery', 'math'], function ($, math) {
  const result = math.multiply(9, 9)
  $('.amd').html(`9 * 9 = ${result}`)
})
