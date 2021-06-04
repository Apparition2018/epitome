# ES6

![ES6 思维导图](http://img1.mukewang.com/60abe37100010d0805000417.jpg)

---

## 声明常量

```javascript
var es = 'es6';
es = 'es2015';
console.log(es);
```

---
## 定义函数
```javascript
function sum (x, y) {
    return x + y;
}

const sum2 = function (x, y) {
    return x + y;
}

// 箭头函数
const sum3 = (x, y) => {
    return x + y;
}
```
>### 箭头函数注意点 
>- 箭头函数不绑定 this 和 arguments，它会从自己的作用域链的上一层继承 this, arguments
---
