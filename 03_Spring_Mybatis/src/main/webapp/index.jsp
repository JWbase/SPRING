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
	<h1>03_Spring_Mybatis</h1>

	<c:choose>
		<c:when test="${empty sessionScope.m }">
			<form action="/login.do" method="post">
				<fieldset>
					<legend>로그인</legend>
					아이디 : <input type="text" name="memberId"><br> 비밀번호 : <input
						type="password" name="memberPw"><br> <input
						type="submit" value="로그인">
				</fieldset>
			</form>
			<a href="/joinFrm.do">회원가입</a>
		</c:when>
		<c:otherwise>
			<h2>[${sessionScope.m.memberName }]</h2>
			<h3><a herf="/logout.do">로그아웃</a></h3>
			<h3><a href="/selectAllMember.do">전체회원조회</a></h3>
			
			<form action="/searchMemberId.do">
				아이디 : <input type="text" name="memberId">
				<input type="submit" value="조회">
			</form>
			<h3><a href="/mypage.do">마이페이지</a></h3>
			<h3><a href="/deleteMember.do?memberId=${sessionScope.m.memberId }">회원탈퇴</a></h3>
			<form action="/searchMemberName.do">
				이름 : <input type="text" name="memberName">
				<input type="submit" value="조회">
			</form>
		</c:otherwise>
	</c:choose>

</body>
</html>