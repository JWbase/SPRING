<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>마이페이지</h1>
	<hr>
	<form action="/update.do" method="post">
		<fieldset style="width: 280px;">
			<legend>회원정보수정</legend>
			<table border="1" style="margin: 0 auto">
				<tr>
					<th>번호</th>
					<td>
						<input type="text" name="memberNo" value="${sessionScope.m.memberNo }" disabled>
					</td>
				</tr>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="memberId" value="${sessionScope.m.memberId }" readonly style="color: gray;">
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="password" name="memberPw" value="${sessionScope.m.memberPw }">
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="memberName" value="${sessionScope.m.memberName }" disabled>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="text" name="phone" value="${sessionScope.m.phone }">
					</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>
						<input type="text" name="email" value="${sessionScope.m.email }" disabled>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<button type="submit" value="수정">수정</button>
					</th>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>