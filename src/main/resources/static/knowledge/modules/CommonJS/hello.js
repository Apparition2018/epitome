// 廖雪峰Node.js：https://www.liaoxuefeng.com/wiki/1022910821149312/1023025235359040
// 阮一峰CommonJS规范：http://javascript.ruanyifeng.com/nodejs/module.html

'use strict';
console.log('Hello, world.');

// 把函数greet作为模块的输出暴露出去
var s = 'Hello';

function greet(name) {
    console.log(s + ', ' + name + '!');
}

module.exports = greet;