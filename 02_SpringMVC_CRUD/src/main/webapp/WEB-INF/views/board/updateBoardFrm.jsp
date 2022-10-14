<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="page-wrap">
		<div class="page-content">
			<form action="/boardUpdate.do" method="post">
				<table>
					<tr>
						<th>제목</th>
						<td><input type="text" name="boardTitle" value="${b.boardTitle }" style="outline-style: none;"> <input type="hidden" name="boardNo" value="${b.boardNo }"></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: left; padding: 0; padding-top: 8px;"><textarea id="boardContent" name="boardContent"><${b.boardContent }></textarea></td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="submit">수정하기</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>