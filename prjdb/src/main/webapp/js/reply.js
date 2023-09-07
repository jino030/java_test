/**
 * reply.js
 */
console.log('js/reply.js');

class Reply {
	showInfo() {
		console.log('sssss');
	}

	// 목록 조회 기능..
	replyList(noticeId, callback, errorcall) {
		// let xhttp = new XMLHttpRequest();
		// xhttp.open('get', 'ajaxReplyList.do?noticeId=' + noticeId);
		// xhttp.send(); // 정보를 전송하고 결과를 받아올수 있다.
		// xhttp.onload = function (e) { // onload 이벤트가 발생하면..
		// 	let json = xhttp.responseText;
		// 	let data = JSON.parse(json);
		// 	console.log(data);
		// 	callback(data);
		// }
		fetch('ajaxReplyList.do?noticeId=' + noticeId) // 반환 : promise => pending(처리중), fullfiled(정상완료), 실패..
			.then(resolve => resolve.json()) // 정상적으로 실행됐을 때 resolve에 결과값이 들어옴, // resolve.json() => 받은 결과 값을 json 객체로 바꿔서 반환..
			.then(result => callback(result))
			.catch(err => errorcall(err))// 비정상적으로 실행됐을 때 실행

	}

	// 댓글 수정 기능
	replyModify(reply = { rid: 10, reply: "변경된 댓글" }, callback) {
		// let xhttp = new XMLHttpRequest();
		// xhttp.open('post', 'ajaxReplyModify.do');
		// xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		// xhttp.send('rid=' + reply.rid + '&content=' + reply.reply); // 헤더정보에 데이터 포맷을 넘겨줘야함..?
		// xhttp.onload = function (e) {
		// 	let data = JSON.parse(xhttp.responseText);
		// 	callback(data);
		// }
		fetch('ajaxReplyModify.do', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded',
			},
			body: 'rid=' + reply.rid + '&content=' + reply.reply})
			.then(resolve => resolve.json())
			.then(result => callback(result))
			.catch(console.log("ajaxReplyAdd.do 요청 중 에러발생"));
	}

	// 댓글 등록 기능.
	replyAdd(reply = { nid: 1, replyer: 'user1', reply: 'test' }, callback) {
		// let xhttp = new XMLHttpRequest();
		// xhttp.open('post', 'ajaxReplyAdd.do');
		// xhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		// xhttp.send('nid=' + reply.nid + '&content=' + reply.reply + '&writer=' + reply.replyer); // 헤더정보에 데이터 포맷을 넘겨줘야함..?
		// xhttp.onload = function (e) {
		// 	let data = JSON.parse(xhttp.responseText);
		// 	callback(data);
		// }
		fetch('ajaxReplyAdd.do', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded',
			},
			body: 'nid=' + reply.nid + '&content=' + reply.reply + '&writer=' + reply.replyer})
			.then(resolve => resolve.json())
			.then(result => callback(result))
			.catch(console.log("ajaxReplyAdd.do 요청 중 에러발생"));
	}

	// 댓글 한건 조회
	replySearch(replyId, callback) {
		// let xhttp = new XMLHttpRequest();
		// xhttp.open('get', 'ajaxReplySearch.do?rid=' + replyId);
		// xhttp.send();
		// xhttp.onload = function (e) {
		// 	let data = JSON.parse(xhttp.responseText);
		// 	callback(data);
		// }
		fetch('ajaxReplySearch.do?rid=' + replyId)
		.then(resolve => resolve.json())
		.then(result => callback(result))
		.catch(console.log("ajaxReplySearch.do 요청 중 에러발생"));

	}

	// 댓글 삭제 기능..
	replyRemove(replyId, callback, errorcall) {
		// let xhttp = new XMLHttpRequest();
		// xhttp.open('get', 'ajaxReplyDelete.do?rid=' + replyId);
		// xhttp.send(); // 정보를 전송하고 결과를 받아올수 있다.
		// xhttp.onload = function (e) { // onload 이벤트가 발생하면..
		// 	let json = xhttp.responseText;
		// 	let data = JSON.parse(json);
		// 	console.log(data);
		// 	callback(data);
		// }
		fetch('ajaxReplyDelete.do?rid=' + replyId)
		.then(resolve => resolve.json())
		.then(result => callback(result))
		.catch(err => errorcall(err));
	}

}