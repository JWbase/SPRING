<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.content-wrap {
	width: 960px;
	text-align: center;
	margin: 0 auto;
	
}

.login-wrap{
	width: 256px;
	height: 256px;
	margin: 0 auto;
	padding-top: 50px;
	box-sizing: border-box;
}
.login-wrap input{
	height: 25px;
	border: 0;
	border-bottom: 1px solid #7c7c7c;
	margin-bottom: 20px;
}
.submit{
	width: 173px;
	border: 0;
	margin-bottom: 20px;
	height: 30px;
}
.submit:hover{
	cursor: pointer;
	background-color: #000;
	color: #fff;
}
</style>
<body>
	<div class="content-wrap">
		<h1>로그인</h1>
		<div class="login-wrap">
			<form action="/login.do" method="post">
				<div class="id-wrap">
					<input type="text" id="id" placeholder="아이디">
				</div>
				<div class="password-wrap">
					<input type="password" id="password" placeholder="비밀번호">
				</div>
				<div class="btn-wrap">
					<a href="/join.do"><button class="submit" type="button">회원가입</button></a>
					<button class="submit" type="submit">로그인</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>