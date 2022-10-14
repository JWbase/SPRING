<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보조회</h1>
	<hr>
	<ul>
		<li>${m.memberNo }</li>
		<li>${m.memberId }</li>
		<li>${m.memberPw }</li>
		<li>${m.memberName }</li>
		<li>${m.phone }</li>
		<li>${m.email }</li>
	</ul>
	<a href="/">메인으로</a>
</body>
</html>