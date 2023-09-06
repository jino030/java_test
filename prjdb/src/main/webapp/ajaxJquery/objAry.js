/**
 * objAry.js
 */

const mem1 = {
  name: "홍길동",
  age: 20,
  phone: "010-1234-5678"
}

const mem2 = {
  name: "박길동",
  age: 35,
  phone: "010-8765-5678"
}

const mem3 = {
  name: "최길동",
  age: 28,
  phone: "010-5874-5678"
}

const members = [mem1, mem2, mem3];

let target = document.querySelector("#memberList>tbody");
members.forEach(addMemberFnc);

function del(e) {
  e.parentElement.parentElement.remove();
}

// 등록하기
document.querySelector(".add").addEventListener('click', function (e) {
  let name = document.querySelector('input[name=name]').value;
  let age = document.querySelector('input[name=age]').value;
  let phone = document.querySelector('input[name=phone]').value;

  const member = {
    name, age, phone
  }

  addMemberFnc(member);
  mouseEvent();

})

function sum(a=0, b=0) {
  return a+b;
}
console.log(sum(1))

// 테이블에 멤버추가 함수
function addMemberFnc(member = {}) {
  let tr = document.createElement('tr'); //<tr></tr>

  for (let prop in member) {
    let td = document.createElement('td'); //<td></td>
    td.innerText = member[prop]; //<td>홍길동</td>
    tr.appendChild(td); //<tr><td>홍길동</td></tr>
  }

  //button 삭제
  let td = document.createElement('td');
  let btn = document.createElement('button');
  // btn.addEventListener('click', function(e){ //클릭 이벤트가 매개변수로 전달
  //   console.log(e); //this
  //   console.log(this); //this
  //   e.target.parentElement.parentElement.remove(); //e.target == btn == this..
  // })
  btn.addEventListener('click', function (e) { del(this) })

  btn.innerText = '삭제';
  td.appendChild(btn);
  tr.appendChild(td);

  target.appendChild(tr);
}

// 테이블 마우스오버 이벤트
function mouseEvent() {
  document.querySelectorAll("#memberList>tbody>tr").forEach(function (tr) {
    tr.onmouseover = function () {
      tr.setAttribute('style', 'background-color: yellow;');
    }
    tr.onmouseout = function () {
      tr.setAttribute('style', 'background-color: none;');
    }
  })
}