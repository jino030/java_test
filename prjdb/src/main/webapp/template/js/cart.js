// cart.js 의 시작부분.
console.log('cart.js');

// Intl 객체를 사용하여 포맷 지정.
function number_format(amount) {
	return new Intl.NumberFormat('ko-KR', {
		style: 'currency',
		currency: 'KRW'
	}).format(amount);
}

// prototype에 정의해서 메소드 추가: 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function () {
	if (this == 0) return 0;
	let regex = /(^[+-]?\d+)(\d{3})/;
	let nstr = (this + '');
	while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
	return nstr;
};
// 1,000,000 => 1000000
//console.log('1,000,000'.replace(/,/g, ''));

let basket = {
	cartCount: 0,
	cartTotal: 0,
	delCheckedItem: function () {
		// 선택된 상품을 삭제....금액을 재계산.
		let checked = document.querySelectorAll('input[type="checkbox"]:checked');
		console.log(checked);
		for(let c of checked){
			c.closest('.data').remove();
		}
		this.reCalc();
	},
	delAllItem: function () {
		// 장바구니 비우기 하면 실행되도록..
		let datas = document.querySelectorAll('.data');
		for(let d of datas) {
			d.remove();
		}
		this.reCalc();
	},
	reCalc: function () {
		// 금액을 재계산.
		let sum_p_num = document.querySelector('#sum_p_num'); //상품객수: 4개
		let sum_p_price = document.querySelector('#sum_p_price'); //합계금액: 74,200원
		
		// 상품갯수 계산하기..
		let pcount = 0;
		let p_nums = document.querySelectorAll('.p_num');
		for(let pn of p_nums) {
			pcount += Number(pn.value);
		}
		// 합계금액 계산하기..
		let psum = 0;
		let sums = document.querySelectorAll('.sum');
		for(let sum of sums) {
			psum += Number(sum.getAttribute('data-sum'));
		}

		// 상품갯수, 합계금액 셋팅.
		sum_p_num.textContent = '상품갯수: ' + pcount + '개';
		sum_p_price.textContent = '합계금액: ' + (psum).formatNumber() + '원';

	},
	updateUI: function () {
		// 화면을 refresh.
	},
	changePNum: function (target, itemNo, type) {
		// 수량변경.
		let el = document.querySelector('#p_num' + itemNo);
		let p_price = el.closest('.subdiv').querySelector('.p_price'); //상품1개 가격
		let p_sum = el.closest('.subdiv').querySelector('.sum'); //상품 합계
		
		// 수량(el.value) 바꾸기
		if(type == 'up') el.value = Number(el.value) + 1;
		if(type == 'down') {
			if(el.value > 1) {
				el.value = Number(el.value) - 1;
			} else {
				alert('수량은 1개부터 주문 가능합니다.');
			}
		}

		// 해당 상품의 합계 업데이트
		let sum = 0;
		sum = p_price.value * el.value;
		p_sum.setAttribute('data-sum', sum);
		p_sum.textContent = (sum).formatNumber() + '원';

		// 총갯수, 총합계 금액 업데이트
		this.reCalc();
		
	},
	delItem: function (target) {
		// 삭제버튼 클릭시.
		let el = target.parentElement.parentElement.parentElement;
		el.remove();
		this.reCalc();
	},
	cartList: function () {
		// 상품목록 출력...아래에 있는 상품정보를 활용해서 수량만큼 출력이 되도록.
		cartItems.forEach((item, idx) => {
			let num = 1;
			let template = document.querySelector('div.row.data').cloneNode(true);
			template.style.display = 'block';
			// 이미지 경로 설정
			let img = template.querySelector('.img img');
			img.setAttribute('src','./img/'+ item.image);
			// 상품이름 변경
			let pname = template.querySelector('.pname span');
			pname.innerText = item.productNm;
			// 가격 변경
			let p_price = template.querySelector('#p_price1');
			let basketprice = template.querySelector('.basketprice');
			p_price.setAttribute('id', 'p_price' + item.no);
			p_price.setAttribute('value', item.price);
			basketprice.childNodes[2].nodeValue = (item.price).formatNumber() + '원'; //여기 중요!!!
			// 수량, 합계 변경
			let sum = template.querySelector('.sum');
			let p_num = template.querySelector('input[name=p_num1]');
			sum.setAttribute('data-sum', item.price * item.qty);
			p_num.setAttribute('id', 'p_num' + item.no);
			p_num.setAttribute('value', item.qty);
			sum.innerText = ((p_num.value * item.price).formatNumber()) + '원';
			// up/down 버튼에 onclick 속성 추가
			let up = template.querySelector('.up');
			let down = template.querySelector('.down');
			up.setAttribute('onclick', 'up(this, ' + item.no + ')');
			down.setAttribute('onclick', 'down(this, ' + item.no + ')');
			// 삭제버튼에 onclick 속성 추가
			let delbtn = template.querySelector('.delbtn');
			delbtn.setAttribute('onclick', 'del(this)');

			// 상품목록 append
			let basketdiv = document.querySelector('.basketdiv');
			basketdiv.append(template);

		})
		this.reCalc();
	}
};

var cartItems = [{
		no: 1,
		productNm: '장바구니1',
		qty: 2,
		price: 12000,
		image: 'basket1.jpg'
	},
	{
		no: 2,
		productNm: '장바구니2',
		qty: 1,
		price: 22000,
		image: 'basket2.jpg'
	},
	{
		no: 3,
		productNm: '고급장바구니',
		qty: 1,
		price: 13600,
		image: 'basket3.jpg'
	},
	{
		no: 4,
		productNm: '내가만든장바구니',
		qty: 1,
		price: 206000,
		image: 'basket2.jpg'
	},
	{
		no: 5,
		productNm: '고구마',
		qty: 5,
		price: 20000,
		image: 'basket2.jpg'
	}
]

basket.cartList();

//상품 삭제 함수
function del(target) {
	basket.delItem(target);
}

//상품 갯수관련 함수
function up(target, itemNo){
	let type='up';
	basket.changePNum(target, itemNo, type);
}
function down(target, itemNo){
	let type='down';
	basket.changePNum(target, itemNo, type);
}

//장바구니 비우기 이벤트 핸들러
document.querySelector('#delcart').addEventListener('click', function(e){
	basket.delAllItem();
})

//선택상품 삭제 이벤트 핸들러
document.querySelector('#chkdelbtn').addEventListener('click', function(e){
	basket.delCheckedItem();
})

// 1. db를 연결해서 사용하려면 아래의 내용으로 작업을 하면 됨.
//fetch('cartSelectList')
//	.then(resolve => resolve.json())
//	.then(result => {
//		//console.log(result);
//		basket.cartList();
//	})
//	.catch(err => console.log(err))

