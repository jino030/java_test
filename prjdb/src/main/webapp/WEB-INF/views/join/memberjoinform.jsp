<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style type="text/css">
#joinfrm label {
	display: inline-block;
	width: 115px;
	text-align: left;
}
#joinfrm input {width: 200px; height: 25px;}
#joinfrm li {margin-bottom: 10px;}
#joinfrm button {height: 25px;}
#memberId {width: 137px !important;}
.joinbtn {width:80px !important; height: 40px !important; margin: 25px 5px; border-radius: 15px; border: none; 
		  background: linear-gradient(-20deg, #6e45e2 0%, #88d3ce 100%); color: #fff; font-size: 16px;}

</style>
</head>
<body>
	<div align="center">
		<jsp:include page="../menu/header.jsp"></jsp:include>
		<form id="joinfrm" action="memberjoin.do" method="post">
			<ul>
				<li>
					<label for="memberId">아이디*</label>
					<input type="text" id="memberId" name="memberId" required="required">
					<button id="idCheck" value="No" onclick="memberIdCheck();">중복체크</button>
				</li>
				<li>
					<label for="memberPassword">비밀번호*</label>
					<input type="password" id="memberPassword" name="memberPassword" required="required">
				</li>
				<li>
					<label for="checkPassword">비밀번호 확인*</label>
					<input type="password" id="checkPassword" name="checkPassword" required="required">
				</li>
				<li>
					<label for="memberName">이름*</label>
					<input type="text" id="memberName" name="memberName" required="required">
				</li>
				<li>
					<label for="memberTel">TEL</label>
					<input type="tel" id="memberTel" name="memberTel">
				</li>
				<li style="color:red; padding: 15px; font-weight: bold;">* 표시 항목은 필수 입력 정보입니다.</li>
			</ul>
			<input class="joinbtn" type="submit" value="가입" onclick="formCheck();">
			<input class="joinbtn" type="reset" value="취소">
		</form>
	</div>
	<script type="text/javascript">
		function formCheck() {
			let form = document.getElementById("frm");
			let idCheck = document.getElementById("idCheck");
			let memberPassword = document.getElementById("memberPassword");
			let checkPassword = document.getElementById("checkPassword");
			
			if(idCheck.value == 'No'){
				alert("아이디 중복체크를 하세요.");
				return false;
			}
			
			if(memberPassword.value != checkPassword.value) {
				alert("입력하신 비밀번호가 일치하지 않습니다.");
				memberPassword.value = "";
				checkPassword.value = "";
				memberPassword.focus();
				return false;
			}
			return true;
		}
		
		function memberIdCheck() { //ajax 사용 아이디 중복 체크
			let url = "aJaxmembercheck.do?memberId="; //관례적으로 앞에 aJax를 명시함.
			let payload = document.getElementById("memberId").value;
			
			url += payload;
			//console.log(url);
			
			// ajax를 호출하는 fetch(get방식)
			fetch(url)
				.then(response => response.text()) //결과를 text로 받는다 => 돌려받는 콜백함수, 서블릿에서 넘기는 타입을 지정(response.text() : 돌려받을 데이터의 타입)
				.then(text => membercheck(text)); //받은 결과를 콜백함수에서 처리한다 => 처리하는 콜백함수
		}
		
		function membercheck(str) {
			if(str == 'Yes') {
				alert("사용가능한 아이디 입니다.");
				document.getElementById("idCheck").value = "Yes";
				document.getElementById("idCheck").disabled = true;
			} else {
				alert("이미 사용중인 아이디 입니다.");
				document.getElementById("memberId").value = "";
				document.getElementById("memberId").focus();
			}
		}
	
	</script>
</body>
</html>