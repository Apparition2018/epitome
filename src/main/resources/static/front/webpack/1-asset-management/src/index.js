import _ from 'lodash'
import './style.css'
import Airplane from './airplane.png'
import Data from './data.xml'

function component() {
    let element = document.createElement('div');
    
    element.innerHTML = _.join(['Hello', 'webpack', '你好 webpack'], ' ');
    element.classList.add('hello');
    
    let airplane = new Image();
    airplane.src = Airplane;
    element.appendChild(airplane);
    
    console.log(Data);
    
    return element;
}

document.body.appendChild(component())