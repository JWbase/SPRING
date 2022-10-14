<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${sessionScope.m.memberId eq b.boardWriter }">
		<div>
			<button type="button">
				<a href="boardDelete.do?boardNo=${b.boardNo }">삭제</a>
			</button>
			<button type="button">
				<a href="/boardUpdateFrm.do?boardNo=${b.boardNo }">수정</a>
			</button>
		</div>
	</c:if>
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
			<th>첨부파일</th>
			<td colspan="5">
				<c:forEach items="${b.fileList }" var="f">
					<a href="/boardFileDown.do?fileNo=${f.fileNo}">${f.filename }</a>
				</c:forEach>
			</td>
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