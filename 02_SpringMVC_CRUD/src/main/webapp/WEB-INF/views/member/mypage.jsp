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
	<h1>회원정보 수정</h1>
	<hr>
	<form action="/update.do" method="post">
		<fieldset>
			번호 : <input type="text" name="memberNo"
				value="${sessionScope.m.memberNo }" disabled="disabled"><br>
			아이디 : <input type="text" name="memberId" value="${sessionScope.m.memberId }"
					readonly="readonly"><br>
			비밀번호 : <input type="password" name="memberPw"
					value="${sessionScope.m.memberPw }"><br>
			이름 : <input type="text" name="memberName"
					value="${sessionScope.m.memberName }" disabled="disabled"><br>
			전화번호 : <input type="text" name="phone" value="${sessionScope.m.phone }"><br>
			이메일 : <input type="text" name="email" value="${sessionScope.m.email }"><br>
			<button type="submit" value="회원정보수정">수정하기</button>
		</fieldset>
	</form>
</body>
</html>