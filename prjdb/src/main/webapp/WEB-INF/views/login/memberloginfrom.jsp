<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
form {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	gap: 10px;
}
label {
	display: flex;
	justify-content: space-between;
	width: 250px;	
}
label input {

}
</style>
</head>
<body>
	<div align="center">
		<jsp:include page="../menu/header.jsp"></jsp:include>
		<form id="frm" action="memberlogin.do" method="post">
			<label for="memberId">아이디<input type="text" id="memberId" name="memberId" required="required"></label>
			<label for="memberPassword">비밀번호<input type="password" id="memberPassword" name="memberPassword" required="required"></label>
			<div>
				<input type="submit" value="로그인"> <input type="reset"
					value="취소">
			</div>
		</form>
	</div>
</body>
</html>