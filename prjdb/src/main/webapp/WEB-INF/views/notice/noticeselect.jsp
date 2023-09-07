<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>게시판 상세보기</title>
		<style type="text/css">
			table {
				border: 1px #a39485 solid;
				font-size: .9em;
				box-shadow: 0 2px 5px rgba(0, 0, 0, .25);
				width: 90%;
				border-collapse: collapse;
				border-radius: 5px;
				overflow: hidden;
				margin: 0 20px 20px 20px;
				box-sizing: border-box;
			}

			th {
				text-align: center;
			}

			.thead {
				font-weight: bold;
				color: #fff;
				background: #73685d;
			}

			td,
			th {
				padding: 1em .5em;
				vertical-align: middle;
			}

			#replyList td {
				border-bottom: 1px solid rgba(0, 0, 0, .1);
				background: #fff;
			}

			@media all and (max-width: 768px) {

				table,
				thead,
				tbody,
				th,
				td,
				tr {
					display: block;
				}

				th {
					text-align: right;
				}

				table {
					position: relative;
					padding-bottom: 0;
					border: none;
					box-shadow: 0 0 10px rgba(0, 0, 0, .2);
				}

				.thead {
					float: left;
					white-space: nowrap;
				}

				tbody {
					overflow-x: auto;
					overflow-y: hidden;
					position: relative;
					white-space: nowrap;
				}

				tr {
					display: inline-block;
					vertical-align: top;
				}

				th {
					border-bottom: 1px solid #a39485;
				}

				td {
					border-bottom: 1px solid #e5e5e5;
				}
			}
		</style>
	</head>

	<body>
		<div align="center">
			<jsp:include page="../menu/header.jsp" />
			<div>
				<table border="1">
					<tbody>
						<tr>
							<th width="100" class="thead">작성자</th>
							<td width="150">${n.noticeWriterName}</td>
							<th width="100" class="thead">작성일자</th>
							<td width="150" align="center">${n.noticeDate}</td>
							<th width="100" class="thead">조회수</th>
							<td width="50" align="center">${n.noticeHit}</td>
						</tr>
						<tr>
							<th class="thead">이미지</th>
							<td colspan="5">
								<img alt="" src="attech/notice/THUMB_${n.noticeImage}">
							</td>
						</tr>
						<tr>
							<th class="thead">제 목</th>
							<td colspan="5">${n.noticeTitle}</td>
						</tr>
						<tr>
							<th class="thead">내 용</th>
							<td colspan="5">
								<textarea rows="3" cols="80" disabled>${n.noticeSubject}</textarea>
							</td>
						</tr>
						<tr>
							<th class="thead">첨부파일</th>
							<td colspan="5">${n.noticeAttech}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table>
					<tr>
						<th>댓글내용</th>
						<td><input type="text" name="content"></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="writer"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button id="addReply">댓글등록</button> <!-- 누르면 ajax 호출.. -->
						</td>
					</tr>
				</table>
				<h3>댓글목록</h3>
				<table border="1">
					<thead class="thead">
						<tr>
							<th>댓글번호</th>
							<th>댓글</th>
							<th>작성자</th>
							<th>작성일자</th>
							<th>댓글삭제</th>
						</tr>
					</thead>
					<tbody id="replyList">
						<!-- <tr>
						<td>1</td>
						<td>댓글내용보여주기</td>
						<td>작성자</td>
						<td>2023-09-05</td>
					</tr> -->
					</tbody>

				</table>
			</div>
		</div>

		<!-- 모달 창 start -->
		<style>
			body {
				font-family: Arial, Helvetica, sans-serif;
			}

			.modal {
				display: none;
				position: fixed;
				z-index: 1;
				padding-top: 200px;
				left: 0;
				top: 0;
				width: 100%;
				height: 100%;
				overflow: auto;
				background-color: rgb(0, 0, 0);
				background-color: rgba(0, 0, 0, 0.4);
			}

			.modal p {
				margin: 10px;
			}

			.modal-content {
				background-color: #fefefe;
				margin: auto;
				padding: 20px;
				border: 1px solid #888;
				width: 80%;
			}

			.close {
				color: #aaaaaa;
				float: right;
				font-size: 28px;
				font-weight: bold;
			}

			.close:hover,
			.close:focus {
				color: #000;
				text-decoration: none;
				cursor: pointer;
			}

			input {
				display: inline-block;
				width: 500px;
				height: 30px;
				padding: 5px;
			}
		</style>
		<div id="myModal" class="modal">
			<!-- Modal content -->
			<div class="modal-content">
				<span class="close">&times;</span>
				<p>23</p>
				<p><input class="content" type="text"></p>
				<p><button id="editBtn">수정</button></p>
			</div>
		</div>
		<!-- 모달 창 end -->

		<script type="text/javascript" src="js/reply.js"></script>
		<script type="text/javascript">
			// Reply 객체 생성.
			const replyObj = new Reply();
			replyObj.showInfo();

			let noticeId = '${n.noticeId}';
			const fields = ['replyId', 'reply', 'replyer', 'replyDate'];
			replyObj.replyList(noticeId, function (data) {
				//console.log(data);
				// 출력할 필드 선언
				data.forEach(function (reply) {
					let tr = makeTr(reply);
					replyList.appendChild(tr);
				});
			})

			// 댓글정보를 매개값으로해서 tr요소를 생성
			function makeTr(reply) {
				let tr = document.createElement('tr');
				tr.addEventListener('dblclick', showEditForm);
				tr.setAttribute('rid', reply.replyId); // 댓글번호 속성 추가
				for (let prop of fields) {
					let td = document.createElement('td');

					if (prop == 'replyDate') {
						console.log("헤헤헤");
						// console.log(reply);
						// console.log(reply.prop);
						// console.log(reply[prop]);
						td.innerText = reply[prop][0] + "-"
							+ (reply[prop][1] >= 1 && reply[prop][1] <= 9 ? "0" + reply[prop][1] : reply[prop][1]) + "-"
							+ (reply[prop][2] >= 1 && reply[prop][2] <= 9 ? "0" + reply[prop][2] : reply[prop][2])
					} else {
						td.innerText = reply[prop];
					}
					tr.appendChild(td);

				}
				// 삭제버튼
				td = document.createElement('td');
				let button = document.createElement('button');
				button.addEventListener('click', deleteReplyFnc); // 클릭이벤트..
				button.innerText = "삭제";
				td.appendChild(button);
				tr.appendChild(td);


				//replyList.appendChild(tr);

				return tr;
			}

			// 댓글 삭제 이벤트 핸들러
			function deleteReplyFnc(e) {
				// 매개변수로 이벤트가 전달되면.. 이벤트의 타켓을 의미...? => 버튼...?
				//console.log(e.target.parentElement.parentElement.getAttribute('rid'));

				let rid = e.target.parentElement.parentElement.getAttribute('rid');

				replyObj.replyRemove(rid, function (result) {
					console.log(result);

					if (result.retCode == 'Success') {
						e.target.parentElement.parentElement.remove();
					} else if (result.retCode == 'Fail') {
						alert("처리중 에러.");
					} else {
						alert("잘못된 코드 반환.");
					}
				})
			}

			// 댓글 등록 이벤트 핸들러
			document.querySelector('#addReply').addEventListener('click', function (e) {
				let content = document.querySelector('input[name=content]').value;
				let writer = document.querySelector('input[name=writer]').value;
				const r = { nid: noticeId, replyer: writer, reply: content }
				replyObj.replyAdd(r, function (data) {

					const fields = ['replyId', 'reply', 'replyer', 'replyDate'];
					if (data.retCode == 'Success') {
						let tr = makeTr(data.data);
						replyList.appendChild(tr);
						fieldInit();

					} else if (data.retCode == 'Fail') {
						alert("처리중 에러.");
					} else {
						alert("잘못된 코드 반환.");
					}
				})

			})

			function fieldInit() {
				document.querySelector('input[name=content]').value = '';
				document.querySelector('input[name=writer]').value = '';
			}

			// 수정화면 open..
			function showEditForm(e) {
				var modal = document.getElementById("myModal");
				modal.style.display = "block";

				let rid = this.getAttribute('rid');
				replyObj.replySearch(rid, function (data) {
					console.log(data);

					document.querySelector('#myModal p').innerText = rid;
					document.querySelector('#myModal input.content').value = data.reply;
				})
			}

			// modal 창의 닫기 버튼 클릭 이벤트
			document.querySelector('span.close').addEventListener('click', function () {
				var modal = document.getElementById("myModal");
				modal.style.display = "none";
			})

			window.onclick = function (event) {
				var modal = document.getElementById("myModal");
				if (event.target == modal) {
					modal.style.display = "none";
				}
			}

			// 수정버튼 이벤트..
			document.querySelector('#editBtn').addEventListener('click', editReplyHandler);

			// 댓글 수정 이벤트 핸들러
			function editReplyHandler(e) {
				// Ajax 호출(DB변경), 화면수정
				let rid = document.querySelector('#myModal p').innerText;
				let content = document.querySelector('#myModal input.content').value;
				console.log(rid, content);
				replyObj.replyModify({ rid: rid, reply: content }, function (data) {
					console.log(data);
					if(data.retCode == 'Success') {
						let oldTr = document.querySelector('tr[rid="' + rid + '"]');
						let newTr = makeTr(data.data);
						document.querySelector('#replyList').replaceChild(newTr, oldTr); // 요소 바꾸기!!
					} else {
						alert('처리중 오류...');
					}

					// 수정 후 모달 창 닫기
					var modal = document.getElementById("myModal");
					modal.style.display = "none";
				});
			}

		</script>

	</body>

	</html>