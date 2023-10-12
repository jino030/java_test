// 비동기방식 fetch();
// 비동기를 동기방식으로???? promise  객체??

async function asyncFunc() {
	let memberAry = [];
	let promise = await fetch('./MOCK_DATA.json');
	let json = await promise.json();
	console.log(json);

	memberAry = json;
	console.log(memberAry);

	// reduce 사용해서.......
	let ul = document.createElement('ul');
	memberAry.reduce((acc, mem, idx, ary) => {
		let li = document.createElement('li');
		li.innerHTML = 'id: ' + mem.id + ', name: ' + mem.first_name;

		if (mem.gender == "Female") {
			li.style.background = 'pink';
		} else if (mem.gender == "Male") {
			li.style.background = 'lightblue';
		}
		ul.append(li);

		if (idx + 1 == ary.length) {
			acc.append(ul);
		}

		return acc;
	}, document.querySelector('#list'));


	// memberAry.reduce((acc, mem, idx, ary) => {
	//   if (idx == 5) {
	//     document.querySelector('#list').innerHTML = acc;
	//   }
	//   return acc += '<li>id: ' + mem.id + ', name: ' + mem.first_name + '</li>'
	// }, "");
}

asyncFunc();