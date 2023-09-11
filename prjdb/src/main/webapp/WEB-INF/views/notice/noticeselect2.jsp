<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>게시판 상세보기</title>
		<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
		<style>
			#replyWrap,
			#noticeWrap {
				width: 1280px;
				margin: 0 auto;
			}

			#noticeWrap {
				margin-bottom: 50px;
			}

			#replyAdd {
				border: 1px solid #ccc;
				border-collapse: collapse;
				margin-bottom: 50px;
			}

			th,
			td {
				border: 1px solid #ccc;
				padding: 10px;
			}

			h3 {
				padding: 10px 0;
			}
		</style>
	</head>

	<body>
		<div align="center"></div>
		<jsp:include page="../menu/header.jsp" />
		<div id="noticeWrap">
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
						<td colspan="5"><img alt="" src="attech/notice/THUMB_${n.noticeImage}"></td>
					</tr>
					<tr>
						<th class="thead">제 목</th>
						<td colspan="5">${n.noticeTitle}</td>
					</tr>
					<tr>
						<th class="thead">내 용</th>
						<td colspan="5"><textarea rows="3" cols="80" disabled>${n.noticeSubject}</textarea>
						</td>
					</tr>
					<tr>
						<th class="thead">첨부파일</th>
						<td colspan="5">${n.noticeAttech}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div id="replyWrap">
			<h3>댓글등록
				<button id="addRow">댓글 추가</button>
				<button id="button">댓글 삭제</button>
			</h3>
			<table id="replyAdd" class="display" style="width:100%">
				<thead>
					<tr>
						<th>댓글번호</th>
						<td><input type="text" name="rid"></td>
					</tr>
					<tr>
						<th>댓글내용</th>
						<td><input type="text" name="content"></td>
					</tr>
					<tr>
						<th>댓글작성자</th>
						<td><input type="text" name="writer"></td>
					</tr>
					<tr>
						<th>댓글날짜</th>
						<td><input type="text" name="replyDate"></td>
					</tr>
				</thead>
			</table>

			<h3>댓글목록</h3>
			<table id="example" class="display" style="width:100%;">
				<thead>
					<tr>
						<th>댓글번호</th>
						<th>댓글내용</th>
						<th>댓글작성자</th>
						<th>댓글날짜</th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th>댓글번호</th>
						<th>댓글내용</th>
						<th>댓글작성자</th>
						<th>댓글날짜</th>
					</tr>
				</tfoot>
			</table>
		</div>
		<script>
			let noticeId = "${n.noticeId}";
			const table = new DataTable('#example', {
				ajax: 'ajaxReplyList.do?noticeId=' + noticeId + '&param=jquery',
				columns: [
					{ data: 'replyId' },
					{ data: 'reply' },
					{ data: 'replyer' },
					{ data: 'replyDate' }
				]
			});

			// 추가
			function addNewRow() {
				table.row
					.add(
						{
							replyId: $('input[name="rid"]').val(),
							reply: $('input[name="content"]').val(),
							replyer: $('input[name="writer"]').val(),
							replyDate: $('input[name="replyDate"]').val()
						}
					)
					.draw(false);
			}

			document.querySelector('#addRow').addEventListener('click', addNewRow);

			// 삭제
			table.on('click', 'tbody tr', (e) => {
				let classList = e.currentTarget.classList;

				if (classList.contains('selected')) {
					classList.remove('selected');
				}
				else {
					table.rows('.selected').nodes().each((row) => row.classList.remove('selected'));
					classList.add('selected');
				}
			});

			document.querySelector('#button').addEventListener('click', function () {
				table.row('.selected').remove().draw(false);
			});

		</script>
	</body>

	</html>