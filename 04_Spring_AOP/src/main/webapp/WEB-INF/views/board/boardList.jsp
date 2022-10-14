<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시판</h1>
	<c:if test="${not empty sessionScope.m }">
		<a href="/boardWriteFrm.do">게시글 작성</a>
	</c:if>
	<div class="table_wrap">
		<table class="table table-hover">
			<thead class="table">
				<tr>
					<th scope="col" style="width: 10%;">번호</th>
					<th scope="col" style="width: 55%;">제목</th>
					<th scope="col" style="width: 10%;">작성자</th>
					<th scope="col" style="width: 15%;">등록일</th>
				</tr>
			</thead>
			<c:forEach items="${ list}" var="b" varStatus="i">
				<tbody class="table">
					<tr>
						<th scope="row">${(reqPage) * numPerPage + i.count }</th>
						<td>
							<a href="/boardView.do?boardNo=${b.boardNo }">${b.boardTitle }</a>
						</td>
						<td>${b.boardWriter }</td>
						<td>${b.boardDate }</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<div class="pageNavi">${pageNavi }</div>
	</div>
</body>
</html>