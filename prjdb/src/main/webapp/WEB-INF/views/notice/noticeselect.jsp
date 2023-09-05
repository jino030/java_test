<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 상세보기</title>
</head>
<body>
	<div align="center">
		<jsp:include page="../menu/header.jsp" />
		<div>
			<table border="1">
				<tr>
					<th width="100">작성자</th>
					<td width="150">${n.noticeWriterName}</td>
					<th width="100">작성일자</th>
					<td width="150" align="center">${n.noticeDate}</td>
					<th width="100">조회수</th>
					<td width="50" align="center">${n.noticeHit}</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td colspan="5">
						<img alt="" src="attech/notice/${n.noticeImage}">
					</td>
				</tr>
				<tr>
					<th>제 목</th>
					<td colspan="5">${n.noticeTitle}</td>
				</tr>
				<tr>
					<th>내 용</th>
					<td colspan="5">
						<textarea rows="10" cols="80">${n.noticeSubject}</textarea>
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="5">${n.noticeAttech}</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>