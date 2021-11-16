# ES6
![ES6 思维导图](http://img1.mukewang.com/60abe37100010d0805000417.jpg)

---
## 参考网站
1. [ECMAScript2015~2020语法全解析](http://es.xiecheng.live/)
2. [带你快速入坑ES6](https://www.imooc.com/learn/1246)
---
## const
1. 不允许重复声明
2. 不属于顶层对象 window
3. 不存在变量提升
4. 暂时性死区
5. 块级作用域
6. 优先选择 const，再次 let
```javascript
console.log(es);
var es = 'es6';
es = 'es2015';
console.log(es);        // es2015

// ES3
var BASE_URL = 'http://es.xiecheng.live';
BASE_URL = 'http://www.imooc.com';
console.log(BASE_URL);  // http://www.imooc.com

// ES5
console.log(es);
Object.defineProperty(window, 'es', {
    value: 'es6',
    writable: false
})
es = 'es2015';
console.log(es);        // es6
console.log(window.es); // es6

// ES6
console.log(es);        // Uncaught ReferenceError: Cannot access 'es' before initialization
const es = 'es6';
console.log(window.es); // undefined
es = 'es2015';
console.log(es);        // Uncaught TypeError: Assignment to constant variable.
const es2;
es2 = 'es6';            // Uncaught SyntaxError: Missing initializer in const declaration
const es = 'es2015';    // Uncaught SyntaxError: Identifier 'es' has already been declared
if (true) {
    const str = 'es6';
}
console.log(str);       // Uncaught ReferenceError: str is not defined

const arr = ['es6', 'es7', 'es8'];
arr[0] = 'es2015';
console.log(arr);       // ["es2015", "es7", "es8"]
const arr2 = ['es6', 'es7', 'es8'];
Object.freeze(arr2);
arr2[0] = 'es2015';
console.log(arr2);      // ["es6", "es7", "es8"]
const esObj = {
    name: 'es6',
    year: 2015,
    extension: ['es7', 'es8', 'es9']
};
Object.freeze(esObj);
esObj.extension[0] = 'es2016';
console.log(esObj);     // {name: "es6", year: 2015, extension: ["es2016", "es8", "es9"]}
const esObj2 = {
    name: 'es6',
    year: 2015,
    extension: ['es7', 'es8', 'es9']
};
myFreeze(esObj2);
esObj2.extension[0] = 'es2016';
console.log(esObj2);    // {name: "es6", year: 2015, extension: ["es2016", "es8", "es9"]}
function myFreeze(obj) {
    Object.freeze(obj);
    Object.keys(obj).forEach(function(key) {
        if (typeof obj[key] === 'object') {
            myFreeze(obj[key]);
        }
    })
}
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