# ES6
![ES6 思维导图](http://img1.mukewang.com/60abe37100010d0805000417.jpg)

---
## 参考网站
1. [es.xiecheng.live](ECMAScript2015~2020语法全解析)
2. [带你快速入坑ES6](https://www.imooc.com/learn/1246)
---
## 声明常量
```javascript
var es = 'es6';
es = 'es2015';
console.log(es);
```
---
## 箭头函数
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
- 箭头函数不绑定 this 和 arguments，它会从自己的作用域链的上一层继承 this, arguments
```javascript
    const obj = {
        name: 'obj',
        showName: function() {
            console.log(this.name); // this 指向 obj
        }
    }
    obj.showName(); // obj

    const obj2 = {
        name: 'obj2',
        showName: () => {
            console.log(this.name); // this 指向 window
        }
    }
    obj2.showName(); // undefined
```
---
## 解构赋值
```javascript
    // 对象解构赋值
    const course = {
        name: 'es6',
        price: 500,
        teacher: {
            name: '张三',
            age: 34
        }
    }
    const {name, price, teacher: {name: teacherName, age}} = course;
    console.log(teacherName); // 张三

    // 数组解构赋值
    const courseArr = ['es6', 'es7', 'es8'];
    const [a, b, c] = courseArr;
```
- 解构赋值应用
```javascript
    // 应用 1
    const sum2 = ([a, b, c]) => {
        return a + b + c;
    }
    console.log(sum([1, 2, 3])); // 6

    // 应用 2
    const foo = ({name, age, school = 'xx学校'}) => {
        console.log(name, age);
    }
    foo({name: '张三', age: 20}) // 张三 20

    // 应用 3
    const foo = () => {
        return {
            name: '张三',
            age: 20
        }
    }
    const {name, age} = foo();
    console.log(name, age); // 张三 20

    // 应用 4
    let a = 1, b = 2;
    [a, b] = [b, a];
    console.log(a, b); // 2 1

    // 应用 5
    axios.get('./data.json').then(({data: {name, type}}) => {
        console.log(name, type);
    })
```
---
## babel
```
1. 进入项目目录，执行初始化 npm init -y 
2. npm install --save-dev babel-preset-env babel-cli
    (npm install -D @babel/cli @babel/core @babel/preset-env)
    (npm install -g @babel/core @babel/cli)
3. 创建文件并配置 .babelrc
    {
      "presets": ["@babel/preset-env"]
    }
```
```
1. 文件：babel src/index.js -o dist/index.js
2. 文件夹：babel src -d dist
3. 实时监控：babel src -w -d dist
```
---