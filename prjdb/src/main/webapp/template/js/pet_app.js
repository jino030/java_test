
App = {
	adoptedAry: [], //adopted.json의 정보
	init: function () {
		
		// pets.json 파일의 정보를 읽어서 애완견의 정보를 활용해서 페이지 생성.
		$.getJSON('../template/json/pets.json', (data) => {
			for(let d of data){
				let clone = $('#petTemplate').clone();
				clone.css('display', 'block');
				clone.find('.panel-title').text(d.name);
				clone.find('.panel-body img').attr('src', './' + d.picture)
				clone.find('.pet-breed').text(d.breed);
				clone.find('.pet-age').text(d.age);
				clone.find('.pet-location').text(d.location);
				clone.find('.btn-adopt').attr('data-id', d.id);
				$("#petsRow").append(clone);
			}
			
			this.initContract();
			console.log(this.adoptedAry);
		})
	},

	initContract: function () {
		// initMarkData 호출.
		// bindEvents 호출.
		this.bindEvents(); // 버튼에 클릭이벤트 걸어주기
		this.initMarkData(); // adopted.json 정보를 활용해서 입양처리 메소드
	},

	bindEvents: function () {
		// 입양버튼에 이벤트 등록. > markAdopted 를 활용하세요.
		let btns = $('.btn-adopt').on('click', function(e){
			App.markAdopted(e);
		})
	},

	initMarkData: function () {
		// adopted.json 정보를 활용해서 입양처리하기.
		$.getJSON('../template/json/adopted.json', (data) => { //일반 function(){} 하게되면 함수자체의 this를 가리킴?? 화살표 함수를 쓰자..
			for(let d of data) {
				this.adoptedAry.push(d);
			}
			// 버튼 disabled 하기
			let btns = $('.btn-adopt');
			for(let b of btns) {
				let id = parseInt($(b).attr('data-id'));
				for(let i=0; i<this.adoptedAry.length; i++){
					if(id == this.adoptedAry[i]){
						$(b).attr('disabled', 'true');
					}
				}
			}
		})
	},

	markAdopted: function (e) {
		// 얘는 사용자가 클릭하는 시점에 실행됨.. 비동기방식 처리 다되고 나서 실행된다..?
		// 입양처리. adoptedAry에 추가.
		let cnt = 0;
		let id = parseInt($(e.target).attr('data-id'));
		this.adoptedAry.push(id);
		console.log(this.adoptedAry);
		//버튼 비활성화 메소드 호출..
		this.handleAdopt(e);
	},

	handleAdopt: function (e) {
		// 사용자화면에서 입양버튼 클릭 시 처리. 버튼 비활성화 작업..
		$(e.target).attr('disabled', 'true');
	}

}; // end of App;


$(function () {
	App.init();
});
