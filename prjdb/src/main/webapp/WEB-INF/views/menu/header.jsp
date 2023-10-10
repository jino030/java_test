<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/prjdb/css/menu.css">
</head>
<body>
	<nav id="nav-wrap">
		<ul>
			<li><a href="productList.do">Gamsung</a></li>
			<li><a href="home.do">Home</a></li>
			<li><a href="book.do">Book</a></li>
			<li><a href="noticeselectlist.do">Notice</a></li>
			<c:if test="${empty id}">
				<li><a href="memberjoinform.do">Join</a></li>
				<li><a href="memberloginform.do">Login</a></li>
			</c:if>
			<c:if test="${not empty id }">
				<li><a href="memberlist.do">Member</a></li>
				<li><a href="#">MyPage</a></li>
				<li><a href="memberlogout.do">Logout</a></li>
				<li><a href="TodoList.do">MyTodo</a></li>
				<li>${name}님 접속중</li>
			</c:if>
		</ul>
	</nav>
</body>
</html>