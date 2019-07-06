self.onmessage = function(e) {
    var message = e.data;
    // 利用数组的 reverse() 实现字符串的反转
    var messageReverse = message.split('').reverse().join('');
    postMessage(messageReverse);
};