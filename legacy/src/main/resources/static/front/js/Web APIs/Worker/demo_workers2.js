self.onmessage = function (e) {
  const message = e.data
  // 利用数组的 reverse() 实现字符串的反转
  const messageReverse = message.split('').reverse().join('')
  postMessage(messageReverse)
}
