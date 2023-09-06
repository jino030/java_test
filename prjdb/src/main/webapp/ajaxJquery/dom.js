/**
 * dom.js
 */
let ul = document.createElement('ul'); //DOM 요소를 생성
let li = document.createElement('li');
li.setAttribute('class', 'second');
let txt = document.createTextNode('Apple');
li.appendChild(txt);
ul.appendChild(li);

li = document.createElement('li');
li.setAttribute('class', 'second');
txt = document.createTextNode('Banana');
li.appendChild(txt);
ul.appendChild(li);

document.getElementById('clone').appendChild(ul);