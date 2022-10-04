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
	<div class="page-wrap">
		<div class="page-title">게시판</div>
		<form action="/boardWrite.do" method="post">
			<table id="boardWrite">
				<tr>
					<th>제목</th>
					<td><input type="text" name="boardTitle"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea id="boardContent" name="boardContent"></textarea><input
						type="hidden" name="boardWriter"
						value="${sessionScope.m.memberId }"></td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit">작성하기</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>