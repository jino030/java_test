<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>공지사항</title>
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
		text-align: left;
	}

	thead {
		font-weight: bold;
		color: #fff;
		background: #73685d;
	}

	td,
	th {
		padding: 1em .5em;
		vertical-align: middle;
	}

	td {
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

		thead {
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
			<h2>게시글 목록</h2>
		</div>
		<div>
			<form id="search">
				<table border="1">
					<tr>
						<td>
							<label for="key">choose a key: </label>
							<select id="key" name="key">
								<option value="title" selected="true">제목</option>
								<option value="subject">내용</option>
								<option value="writer">작성자</option>
							</select>
							<input type="text" name="val" id="val" onkeydown="enterKey(event)"> &nbsp;&nbsp;
							<input type="button" id="btn" value="검색" onclick="searchList()">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<br>
		<div>
			<table border="1" id="maintable">
				<thead>
					<tr>
						<th width="50">순번</th>
						<th width="100">이미지</th>
						<th width="250">제목</th>
						<th width="100">작성자</th>
						<th width="100">작성일자</th>
						<th width="50">조회수</th>
						<th width="100">첨부파일</th>
					</tr>
				</thead>
				<tbody id="maintbody">
					<c:choose>
						<c:when test="${empty notices }">
							<tr>
								<td colspan="7" align="center">데이터가 존재하지 않습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${notices }" var="n">
								<tr onmouseover="this.style.background='#e3fe2e'" onmouseout="this.style.background='#fff'"
									onclick="selectNotice(${n.noticeId})">
									<td align="center">${n.noticeId }</td>
									<td align="center">
										<img src="attech/notice/THUMB_${n.noticeImage }" alt="THUMB_${n.noticeImage }">
									</td>
									<td>${n.noticeTitle }</td>
									<td align="center">${n.noticeWriterName }</td>
									<td align="center">${n.noticeDate }</td>
									<td align="center">${n.noticeHit }</td>
									<td align="center">${n.noticeAttech }</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<br>
		<div>
			<c:if test="${not empty id }">
				<button type="button" onclick="location.href='noticewriteform.do'">글쓰기</button>
			</c:if>
			<form id="sform" action="noticeselect.do" method="post">
				<input type="hidden" id="noticeId" name="noticeId">
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function selectNotice(id) {
			let form = document.getElementById("sform");
			form.noticeId.value = id;
			form.submit(); //noticeselect.do로 이동
		}

		function searchList() {
			let form = document.getElementById("search");
			let key = form.key.value;
			let val = form.val.value;
			/* const formData = new FormData(form); //자바스크립트 FormData 클래스
			formData.append('key', form.key.value);
			formData.append('val', form.val.value);
	
			// 폼 객체 key 와 value 값을 순회.
			let entries = formData.entries();
			for (const pair of entries) {
					console.log(pair[0]+ ', ' + pair[1]); 
			} */

			let payload = "key=" + key + "&val=" + val; //일반 변수에 한번 담아주는게 좋다...(일반화)
			console.log(payload);
			let url = "ajaxNoticeSearch.do";
			// 어제는 get방식 이번에는 post방식으로.
			fetch(url, {
				method: 'POST',
				headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
				body: payload
			}).then(response => response.json())
				.then(json => htmlView(json));
		}

		function htmlView(datas) {
			document.querySelector('#maintbody').remove(); //본문에서 tbody 삭제..
			const tbody = document.createElement('tbody');
			tbody.setAttribute('id', 'maintbody');
			tbody.innerHTML = datas.map(data => htmlConvert(data)).join('');
			document.querySelector('#maintable').appendChild(tbody);
		}

		function htmlConvert(n) {
			if (n.noticeAttech == null) {
				n.noticeAttech = '';
			}

			return `<tr onmouseover="this.style.background='#e3fe2e'"
						onmouseout="this.style.background='#fff'"
						onclick = "selectNotice(\${n.noticeId})">
						<td align="center">\${n.noticeId }</td>
						<td align="center">
							<img src="attech/notice/THUMB_\${n.noticeImage }" alt="THUMB_${n.noticeImage}">
						</td>
						<td>\${n.noticeTitle }</td>
						<td align="center">\${n.noticeWriterName }</td>
						<td align="center">\${n.noticeDate }</td>
						<td align="center">\${n.noticeHit }</td>
						<td align="center">\${n.noticeAttech }</td>
					</tr>`
		}

		function enterKey(e) {
			if (e.keyCode == 13) {
				document.getElementById("btn").focus();
			}
		}

	</script>
</body>

</html>