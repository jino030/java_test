/**
 * ary.js
 */

const myNumbers = [20, 30, 45, 66, 88, 22];
let sum = 0;

console.log("-------------------------------------------");

for(let i=0; i<myNumbers.length; i++){
  console.log(myNumbers[i]);
  sum += myNumbers[i];
}
console.log(sum);

console.log("-------------------------------------------");

sum = 0;
for(let num of myNumbers) {
  console.log(num);
  sum += num;
}
console.log(sum);

console.log("-------------------------------------------");

sum = 0;
console.log("★ myNumbers.forEach 메서드!");
myNumbers.forEach(function(e, i){
  console.log(`${i}번째 요소의 값은 ${e}입니다.`);
  sum += e;
});
console.log("합계는 " + sum);

console.log("-------------------------------------------");

let fruits = ['Apple', 'Banana', 'Cherry'];

document.querySelector(".fruits").innerHTML = '';
fruits.forEach(function(e, i){
  li = document.createElement('li');
  txt = document.createTextNode(e);
  li.appendChild(txt);
  
  let btn = document.createElement('button');
  btn.innerText = '삭제';
  li.appendChild(btn);

  btn.onclick = function() {
    alert("버튼 클릭! ");
    //btn.parentNode.remove();
    btn.parentElement.remove();
  }

  document.querySelector(".fruits").appendChild(li);
})