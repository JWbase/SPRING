<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h1>게시글 수정</h1>
	<hr>
	<form action="/boardUpdate.do" id="updateFrm" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>글번호</th>
				<td>
					<input type="hidden" name="boardNo" value="${b.boardNo }">${b.boardNo }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="boardTitle" value="${b.boardTitle }">
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${b.boardWriter }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${b.boardDate }</td>
			</tr>
			<tr>
				<th>첨부파일 추가</th>
				<td>
					<input type="file" name="boardFile" multiple>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:forEach items="${b.fileList }" var="f">
						<p>${f.filename }
							<button type="button" onclick="deleteFile(this, ${f.fileNo}, '${f.filepath }');">삭제</button>
						</p>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name="boardContent">${b.boardContent }</textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="수정하기">
				</th>
			</tr>
		</table>
	</form>
	<script>
		function deleteFile(obj,fileNo,filepath){
			const fileNoInput = $("<input>");
			fileNoInput.attr("name", "fileNoList");
			fileNoInput.val(fileNo);
			fileNoInput.hide();
			// <input type="text" name="fileNoList" value="fileNo">
			const filepathInput = $("<input>");
			filepathInput.attr("name","filepathList");
			filepathInput.val(filepath);
			filepathInput.hide();
			// <input type="text" name="filepathList" value="filepath">
			$("#updateFrm").append(fileNoInput).append(filepathInput);
			
			$(obj).parent().remove();
		}
	</script>
</body>
</html>