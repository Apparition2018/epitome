import _ from 'lodash'
import printMe from "./print"
import './style.css'
import {cube} from './math'

if (process.env.NODE_ENV !== 'production') {
    console.log('Looks like we are in development mode!');
}

function component () {
    let element = document.createElement('div');
    let btn = document.createElement('button');

    element.innerHTML = _.join(['Hello', 'webpack'], ' ');

    btn.innerHTML = 'Click me and check the console!';
    btn.onclick = printMe;
    element.appendChild(btn);

    let mathElement = document.createElement('div');
    mathElement.innerHTML = [
        '5 cubed is equal to ' + cube(5)
    ].join('\n\n');
    element.appendChild(mathElement);

    return element;
}

// 存储 element，以在 print.js 修改时重新渲染
let element = component();
document.body.appendChild(element);

// 模块热替换
if (module.hot) {
    module.hot.accept('./print.js', function () {
        console.log('Accepting the updated printMe module!');
        document.body.removeChild(element);
        // 重新渲染 "component"，以便更新 click 事件处理函数
        element = component();
        document.body.appendChild(element);
    })
}