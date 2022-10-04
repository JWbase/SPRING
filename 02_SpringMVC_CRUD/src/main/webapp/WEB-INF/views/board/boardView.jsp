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
	<div>
		<button type="button">
			<a href="boardDelete.do?boardNo=${b.boardNo }">삭제</a>
		</button>
		<button type="button">
			<a href="/boardUpdateFrm.do?boardNo=${b.boardNo }">수정</a>
		</button>
	</div>

	<table id="boardView">
		<tr>
			<th>제목</th>
			<td>${b.boardTitle }</td>
			<th>작성자</th>
			<td>${b.boardWriter}</td>
			<th>작성일</th>
			<td>${b.boardDate }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="5">
				<div>${b.boardContent }</div>
			</td>
		</tr>
	</table>
</body>
</html>