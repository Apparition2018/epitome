self.onmessage = function(e) {
    let message = e.data;
    // 利用数组的 reverse() 实现字符串的反转
    let messageReverse = message.split('').reverse().join('');
    postMessage(messageReverse);
};