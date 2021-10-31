import './style.css'
import Icon from './asset/image.png'

function component() {
  const element = document.createElement('div');

  element.innerHTML = 'Hello Webpack!!';
  element.classList.add('hello');
  
  let myIcon = new Image();

  myIcon.src = Icon;
  element.appendChild(myIcon);

  return element;
}

document.body.appendChild(component())