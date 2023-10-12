let memberArr = [];

fetch('./MOCK_DATA.json')
  .then(resolve => resolve.json())
  .then(result => {
    memberArr = result;
    console.log(memberArr);

    let str = "";
    memberArr.forEach((member, idx) => {
      if (idx == 0) str += '<ul>';
      if (idx < 5) str += '<li>id: ' + member.id + ', 이름: ' + member.first_name + '</li>';
      if (idx + 1 == memberArr.length) str += '</ul>';
    });

    //document.querySelector('#list').innerHTML=str;

    let ary5 = memberArr.filter((member, idx, ary) => {
      return idx < 5;
    })

    ary5.reduce((acc, member, idx, ary) => {
      console.log(acc, member, idx, ary);
      return member; // 다음 acc(누산값)....
    }, 0);

    let val = [3, 2, 6, 9, 5].reduce((acc, num, idx, ary) => {
      console.log(acc, num, idx, ary);
      return idx + 1 == ary.length ? (acc + num) / ary.length : acc + num;
    }, 0);

    console.log(val);

  })