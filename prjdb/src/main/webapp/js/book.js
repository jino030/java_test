/**
 * book 정보
 */
 
 class Book {
	showInfo() {
		console.log('sssss');
	}

	// 목록 조회 기능..
	bookList(callback) {
		fetch('ajaxbooklist.do') // 반환 : promise => pending(처리중), fullfiled(정상완료), 실패..
			.then(resolve => resolve.json()) // 정상적으로 실행됐을 때 resolve에 결과값이 들어옴, // resolve.json() => 받은 결과 값을 json 객체로 바꿔서 반환..
			.then(result => callback(result))
			.catch(err => console.log(err))// 비정상적으로 실행됐을 때 실행
	}
	
	// 책 등록 기능.
	bookAdd(book = { bCode: 'p20231003', bName: '책이름이다', bAuthor: '종대갈', bPublisher: '출판사자나', bPrice: 7777 }, callback) {
		fetch('ajaxbookadd.do', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/x-www-form-urlencoded',
			},
			body: 'bookCode=' + book.bCode 
					+ '&bookName=' + book.bName 
					+ '&bookAuthor=' + book.bAuthor
					+ '&bookPublisher=' + book.bPublisher
					+ '&bookPrice=' + book.bPrice
		})
			.then(resolve => resolve.json())
			.then(result => callback(result))
			.catch(err => console.log(err));
	}
		
		// 삭제 기능..
		bookRemove(bookId, callback) {
			fetch('ajaxbookdelete.do?bid=' + bookId)
			.then(resolve => resolve.json())
			.then(result => callback(result))
			.catch(err => console.log(err));
		}
		
		// 댓글 수정 기능
		// bookModify(reply = { rid: 10, reply: "변경된 댓글" }, callback) {
		// 	fetch('ajaxReplyModify.do', {
		// 		method: 'POST',
		// 		headers: {
		// 			'Content-Type': 'application/x-www-form-urlencoded',
		// 		},
		// 		body: 'rid=' + reply.rid + '&content=' + reply.reply})
		// 		.then(resolve => resolve.json())
		// 		.then(result => callback(result))
		// 		.catch(console.log("ajaxReplyAdd.do 요청 중 에러발생"));
		// }
	}