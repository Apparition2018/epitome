((root, factory) => {
  if (typeof exports === 'object') {
    console.log('CommonJS')
    module.exports = factory()
  } else if (typeof define === 'function' && define.amd) {
    console.log('AMD')
    define(factory)
  } else if (typeof define === 'function' && define.cmd) {
    console.log('CMD')
    define(function (require, exports, module) {
      module.exports = factory()
    })
  } else {
    console.log('Default')
    root.math = factory()
  }
})(this, () => {
  return {
    plus: (a, b) => a + b,
    subtract: (a, b) => a - b,
    multiply: (a, b) => a * b,
    divide: (a, b) => a / b
  }
})
