// 纯函数
function sum (a, b) {
  return a + b
}

// 非纯函数
function sum2 (a, b) {
  a = a + b
  return a
}
