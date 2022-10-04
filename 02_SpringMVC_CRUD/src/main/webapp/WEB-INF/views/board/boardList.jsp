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
	<h1>게시판 리스트</h1>
	<div class="table">
		<c:if test="${not empty sessionScope.m }">
			<div>
				<a href="/boardWriteFrm.do">글쓰기</a>
				<a href="/boardWriteFrm2.do">글쓰기(파일첨부)</a>
			</div>
		</c:if>
		<table>
			<thead>
				<tr>
					<th scope="col" style="width: 10%;">번호</th>
					<th scope="col" style="width: 60%;">제목</th>
					<th scope="col" style="width: 10%;">작성자</th>
					<th scope="col" style="width: 15%;">등록일</th>
				</tr>
			</thead>
			<c:forEach items="${list }" var="b">
				<tbody>
					<tr>
						<th scope="row">${b.boardNo }</th>
						<td><a href="/boardView.do?boardNo=${b.boardNo }">${b.boardTitle }</a></td>
						<td>${b.boardWriter }</td>
						<td>${b.boardDate }</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
</body>
</html>