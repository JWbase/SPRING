<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<style>
.fileZone {
	width: 300px;
	height: 200px;
	box-sizing: border-box;
	display: flex;
	flex-wrap: wrap;
	border: 2px dotted #0b85a1;
	align-content: flex-start;
	border: 2px dotted #0b85a1;
}

.fileMsg {
	color: #0b85a1;
	font-size: 13px;
	width: 100%;
	text-align: center;
	align-self: center;
}
.fileName{
	width: 100%;
	position: relative;
	height: 18px;
}
.fileName>sapn{
	font-size: 16px;
	line-height: 18px; 
}
.closeBtn{
	position: absolute;
	right: 10px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="page-wrap">
		<div class="page-title">게시판 작성</div>
		<form action="/boardWrite.do" method="post" enctype="multipart/form-data">
			<table id="boardWrite" border="1">
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="boardTitle" style="width: 100%; border: none; box-sizing: border-box;">
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<div class="fileZone">
							<div class="fileMsg">파일을 끌어다 주세요</div>
						</div>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea id="boardContent" name="boardContent" style="width: 100%; border: none; box-sizing: border-box;"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="file" name="boardFile" multiple style="display: none;">
						<input type="hidden" name="boardWriter" value="${sessionScope.m.memberId }">
						<input type="submit" value="작성하기" style="width: 100%">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script>
		const fileZone = $(".fileZone");
		const files = new Array();
		// 드래그 영역에 들어올 때
		fileZone.on("dragenter", function(e) {
			e.stopPropagation(); // 이벤트 버블링 제거
			e.preventDefault(); // 기본이벤트 제거
			$(this).css("border", "2px dashed #0b85a1");
		});
		// 드래그 영역에서 나갈 때
		fileZone.on("dragleave", function(e) {
			e.stopPropagation(); // 이벤트 버블링 제거
			e.preventDefault(); // 기본이벤트 제거
			$(this).css("border", "2px dotted #0b85a1");
		});
		// 드래그 영역에 올라와 있을때
		fileZone.on("dragover", function(e) {
			e.stopPropagation(); // 이벤트 버블링 제거
			e.preventDefault(); // 기본이벤트 제거
		});
		// 드래그 영역에 내려놓을 때
		fileZone.on("drop", function(e) {
			e.stopPropagation(); // 이벤트 버블링 제거
			e.preventDefault(); // 기본이벤트 제거
			//e.originalEvent.dataTransfer.files
			
			for(let i=0; i<e.originalEvent.dataTransfer.files.length; i++) {
				files.push(e.originalEvent.dataTransfer.files[i]);
			}
			/* <div class="fileName">
			<span>업로드한 파일명</sapn>
			<span class="closeBtn">X</span>
			</div> */
			
			$(".fileMsg").hide();
			$(this).find(".fileName").remove();
			for(let i=0; i<files.length; i++) {
				const fileNameDiv = $("<div>");
				fileNameDiv.addClass("fileName");
				const fileNameSpan = $("<span>");
				fileNameSpan.text(files[i].name);
				const closeBtn = $("<span>");
				closeBtn.addClass("closeBtn");
				closeBtn.text("X");
				closeBtn.attr("onclick","deleteFile(this)");
				fileNameDiv.append(fileNameSpan).append(closeBtn);
				$(this).append(fileNameDiv);
			}
			fileSetting();
		});
		function deleteFile(obj) {
			const deleteFilename = $(obj).prev().text();
			for(let i=0; i<files.length; i++) {
				if(files[i].name == deleteFilename) {
					files.splice(i,1);
					break;
				}
			}
			if(files.length ==0) {
				$(".fileMsg").show();
				fileZone.css("border", "2px dotted #0b85a1");
			}
			$(obj).parent().remove();
			fileSetting();
		}
		function fileSetting() {
			// input[type=file] value는 보안상 변경불가
			// input[type=file] 변경용 객체필요
			const dataTransfer = new DataTransfer();
			for(let i = 0; i < files.length; i ++) {
				dataTransfer.items.add(files[i]);
			}
			$("input[name=boardFile]").prop("files", dataTransfer.files);
		}
	</script>
</body>
</html>