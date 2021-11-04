'use strict';
console.log('Hello, world.');

// 把函数greet作为模块的输出暴露出去
var s = 'Hello';

function greet(name) {
    console.log(s + ', ' + name + '!');
}

module.exports = greet;