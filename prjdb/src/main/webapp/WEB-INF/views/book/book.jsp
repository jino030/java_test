<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Page</title>
<link rel="stylesheet" href="css/book.css">
</head>
<body>
	<jsp:include page="../menu/header.jsp" />
	
	<div id="container">
		<form action="ajaxbookadd.do" id="book_form">
			<div id="input_wrap">
				<label><span>도서코드</span><input type="text" id="bookCode" name="bookCode"></label>
				<label><span>도서명</span><input type="text" id="bookName" name="bookName"></label>
				<label><span>저자</span><input type="text" id="bookAuthor" name="bookAuthor"></label>
				<label><span>출판사</span><input type="text" id="bookPublisher" name="bookPublisher"></label>
				<label><span>금액</span><input type="text" id="bookPrice" name="bookPrice"></label>
			</div>
			<div class="btn_wrap">
				<button id="saveBtn" class="btn">저장</button>
				<button id="delBtn" class="btn">선택삭제</button>
			</div>
		</form>
		<div class="table_wrap">
			<table id="book_list">
				<thead>
					<tr>	
						<th><input type="checkbox" id="all"></th>
						<th>도서코드</th>
						<th>도서명</th>
						<th>저자</th>
						<th>출판사</th>
						<th>가격</th>
						<th>삭제</th>
					</tr>
				</thead>
				<!-- ajax tag start -->
				<tbody class="col">
					<!-- <tr>
						<td><input type="checkbox"></td>
						<td>p20231000</td>
						<td>이것이 자바다</td>
						<td>이민형</td>
						<td>127출판</td>
						<td>25000</td>
						<td><button class="del_btn btn" onclick="del(0)">삭제</button></td>
					</tr> -->
				</tbody>
				<!-- ajax tag end -->
			</table>
		</div>
	</div>
	<script src="js/book.js"></script>
	<script type="text/javascript">
		// Book 객체 생성
		const bookObj = new Book();
		bookObj.showInfo();
		const fields = ['bookCode', 'bookName', 'bookAuthor', 'bookPublisher', 'bookPrice'];
		
		let tbody = document.querySelector('.col');

		// 리스트 출력하기
		window.onload = function() {
			// 책목록 불러오기..
			bookObj.bookList(function(data){
				console.log(data);
				for(let i=0; i<data.length; i++){
					makeTr(data[i]);
				}
			}, function(){
				console.log('오류났나봐..');
			})
		}

		// 등록 이벤트 핸들러
		document.querySelector('#saveBtn').addEventListener('click', function(e){
			e.preventDefault(); //form의 기본기능을 차단

			let bookCode = document.querySelector('input[name=bookCode]').value;
			let bookName = document.querySelector('input[name=bookName]').value;
			let bookAuthor = document.querySelector('input[name=bookAuthor]').value;
			let bookPublisher = document.querySelector('input[name=bookPublisher]').value;
			let bookPrice = document.querySelector('input[name=bookPrice]').value;

			const b = {bCode: bookCode, bName: bookName, bAuthor: bookAuthor, bPublisher: bookPublisher, bPrice: bookPrice};
			console.log(b);

			bookObj.bookAdd(b, function(data) {
				console.log(data);
				if (data.retCode == 'Success') {
					console.log('성공');
					makeTr(data.book);
					fieldInit();
				} else if (data.retCode == 'Fail') {
					alert("처리중 에러.");
				} else {
					alert("잘못된 코드 반환.");
				}
			})
		})

		// 삭제 이벤트 핸들러
		function del(bookId, target) {
			bookObj.bookRemove(bookId, function(data){
				console.log(data);
				console.log(target);

				if (data.retCode == 'Success') {
					target.parentElement.parentElement.remove();
					} else if (data.retCode == 'Fail') {
						alert("처리중 에러.");
					} else {
						alert("잘못된 코드 반환.");
					}
			})
		}

		// 선택삭제
		document.querySelector('#delBtn').addEventListener('click', function(e){
			e.preventDefault(); //form의 기본기능을 차단
			let checkboxs = document.querySelectorAll('.book_chk');

			for(let c of checkboxs){
				if(c.checked == true) {
					let bookId = c.getAttribute('data-bid');
					console.log(bookId);

					bookObj.bookRemove(bookId, function(data){
						console.log(data);

						if (data.retCode == 'Success') {
							c.parentElement.parentElement.remove();
							} else if (data.retCode == 'Fail') {
								alert("처리중 에러.");
							} else {
								alert("잘못된 코드 반환.");
							}
					})
				}
			}
			
		})

		// 전체체크
		document.querySelector('#all').addEventListener('change', function(e){
			let checkboxs = document.querySelectorAll('input[type=checkbox]');
			console.log(e);
			console.log(e.target.checked);
			if(e.target.checked == true) {
				for(let c of checkboxs){
					c.checked = true;
				}
			} else {
				for(let c of checkboxs){
					c.checked = false;
				}
			}
		})

		function fieldInit() {
			document.querySelector('input[name=bookCode]').value = '';
			document.querySelector('input[name=bookName]').value = '';
			document.querySelector('input[name=bookAuthor]').value = '';
			document.querySelector('input[name=bookPublisher]').value = '';
			document.querySelector('input[name=bookPrice]').value = '';
		}

		// tr태그 추가 함수
		function makeTr(book) {
			let tr = document.createElement('tr');
			let td = document.createElement('td');
			let checkbox = document.createElement('input');
			let delBtn = document.createElement('button');
			checkbox.setAttribute('type', 'checkbox');
			delBtn.setAttribute('class', 'del_btn btn');
			delBtn.innerText = '삭제';
			
			// 체크박스 추가
			checkbox.setAttribute('data-bid', book.bookId);
			checkbox.setAttribute('class', 'book_chk');
			td.append(checkbox);
			tr.append(td);
			
			// 도서코드, 도서명, 저자, 출판사, 금액
			td = document.createElement('td');
			td.innerText = book.bookCode;
			tr.append(td);

			td = document.createElement('td');
			td.innerText = book.bookName;
			tr.append(td);

			td = document.createElement('td');
			td.innerText = book.bookAuthor;
			tr.append(td);

			td = document.createElement('td');
			td.innerText = book.bookPublisher;
			tr.append(td);

			td = document.createElement('td');
			td.innerText = book.bookPrice;
			tr.append(td);
			
			// 삭제버튼 추가
			td = document.createElement('td');
			delBtn.setAttribute('onclick', 'del(' + book.bookId + ', this)')
			td.append(delBtn);
			tr.append(td);

			// tbody에 추가
			tbody.append(tr);
		}
		


	</script>
</body>
</html>