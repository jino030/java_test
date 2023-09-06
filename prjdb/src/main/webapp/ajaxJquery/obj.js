/**
 * obj.js
 */
let name = "홍길동"; //string
let age = 20;	//number

let obj = { //객체..
	name, //속성과 값의 변수명이 같으면 이렇게 작성가능..
	age: age,
	phone: '010-1111-2222',
	showInfo: function(){
		return this.name + ", " + this.age;
	}
}

// 출력해보기
console.log("-------------------------------------------");

obj.hobbies = ['reading', 'eating', 'sleeping'];
console.log("obj.hobbies[0]::: " + obj.hobbies[0]);
console.log("obj.name::: " + obj.name);
console.log("obj.age::: " + obj.age);
console.log("obj['phone']::: " + obj['phone']);
console.log("obj.showInfo()::: " + obj.showInfo());

let yourHobbies = ['운동하기', '영화보기', '자전거타기'];

console.log("-------------------------------------------");

for(let prop in obj){
	console.log(prop); //obj 객체의 속성값을 가져옴..
}

console.log("-------------------------------------------");

for(let prop in obj){
	console.log(`속성: ${prop}, 값: ${obj[prop]}`); //obj 객체의 속성값의 value를 가져옴..
}

console.log("-------------------------------------------");

// dom 객체 만들기
function makeHobbies(arrys) {
	for(let i in arrys) {
		li = document.createElement('li');
		txt = document.createTextNode(arrys[i]);
	
		li.appendChild(txt);
		document.querySelector("#myHobby").appendChild(li);
	}
}

makeHobbies(obj.hobbies);
makeHobbies(yourHobbies);