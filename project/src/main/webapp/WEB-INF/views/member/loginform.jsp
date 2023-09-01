<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
	<div><h1>로 그 인</h1></div>
	<div>
		<form id="frm" action="login.do" method="post"> <!-- LoginController에가서 작업시작하고 membermessage.jsp에 결과를 가지고 간다.. -->
			<div>
				<table border="1">
					<tr>
						<th width="150">아이디</th>
						<th width="200">
							<input type="text" id="memberId" name="memberId">
						</th>
					</tr>
					<tr>
						<th width="150">비밀번호</th>
						<th width="200">
							<input type="password" id="memberPassword" name="memberPassword">
						</th>
					</tr>
				</table>
			</div>
			<div>
				<input type="button" onclick="location.href='home.do'" value="홈가기">&nbsp;&nbsp;
				<input type="submit" value="로그인">&nbsp;&nbsp;
				<input type="reset" value="취소">
			</div>
			
		</form>
	</div>
</div>
</body>
</html>