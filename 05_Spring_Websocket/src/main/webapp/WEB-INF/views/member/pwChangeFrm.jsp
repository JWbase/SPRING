<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/pwChange.do" method="post">
		<input type="hidden" name="memberNo" value="${sessionScope.m.memberNo }">
		새 비밀번호 입력 :
		<input type="password" name="memberPw">
		<br>
		<input type="submit" value="비밀번호 변경">
	</form>
</body>
</html>