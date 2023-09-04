<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
	<div align="center">
		<jsp:include page="../menu/header.jsp" />
		<div>
			<h2>게시글 목록</h2>
		</div>
		<div>
			<form action="">
				<tabel border="1">
					<tr>
						<td>
							<label for="key">Choose a key:</label>
							<select name="key" id="key">
								<option value="title">제목</option>
								<option value="subject">내용</option>
								<option value="writer">작성자</option>
							</select>
							<input type="text" id="val" name="val">&nbsp;&nbsp;
							<input type="button" id="btn" value="검색" onclick="searchList()">
						</td>
					</tr>
				</tabel>
			</form>
		</div>
		<div>
			<table>
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
				<tbody>
					<tr>
						<td width="50"></td>
						<td width="100"></td>
						<td width="250"></td>
						<td width="100"></td>
						<td width="100"></td>
						<td width="50"></td>
						<td width="100"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>