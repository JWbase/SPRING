<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<link rel="stylesheet" href="/resources/css/dmCss.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<h1>내 쪽지함</h1>
	<hr>
	<h3>쪽지보내기</h3>
	<button onclick="sendDmMoDal();">쪽지보내기</button>
	<hr>
	<h3>받은 쪽지함</h3>
	<table border="1" class="receiveDmTbl">
		<thead>
			<tr>
				<th>보낸사람</th>
				<th>내용</th>
				<th>시간</th>
				<th>읽음 여부</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<h3>보낸 쪽지함</h3>
	<table border="1" class="sendDmTbl">
		<thead>
			<tr>
				<th>받은사람</th>
				<th>내용</th>
				<th>시간</th>
				<th>읽음 여부</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<!-- 쪽지보내기 모달 -->
	<div id="sendDm-modal" class="modal-wrapper" style="display: none;">
		<div class="modal">
			<div class="modal-header">
				<h2>쪽지보내기</h2>
			</div>
			<hr>
			<div class="modal-content">
				<div class="sendDmFrm">
					<label>수신자 : </label>
					<select name="receiver">
					</select>
					<textarea name="dmContent"></textarea>
					<input type="hidden" id="sender" value="${sessionScope.m.memberId }">
					<button onclick="dmSend();">쪽지보내기</button>
					<button onclick="closeModal();">닫기</button>
				</div>
			</div>
		</div>
	</div>

	<div id="dmDetail" class="modal-wrapper" style="display: none;">
		<div class="modal">
			<div class="modal-header">
				<h2>쪽지내용</h2>
			</div>
			<hr>
			<div class="modal-content">
				<div class="dmFrm">
					<div>
						<span>발신자 : </span>
						<span id="detailSender"></span>
					</div>
					<div>
						<span>날짜 : </span>
						<span id="detailDate"></span>
					</div>
					<div id="detailContent"></div>
					<button onclick="dmSend();">답장</button>
					<button onclick="closeDetail();">닫기</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/resources/js/dm.js"></script>
	<script>
		/* 	$("#sendDm").on("click", function() {
				const receiver = $("[name=receiver]").val();
				const dmContent = $("[name=dmContent]").val();
				$.ajax({
					url : "/sendDm.do",
					data : {
						receiver : receiver,
						dmContent : dmContent
					},
					success : function(data) {
						console.log(data);
					}
				});
			}); */

		function getSendDm() {
			const sender = $("#sender").val();
			$.ajax({
				url : '/myDmList.do',
				data : {
					sender : sender
				},
				success : function(list) {
					console.log(list);
					const tbody = $(".sendDmTbl>tbody");
					tbody.empty();
					for (let i = 0; i < list.length; i++) {
						const dm = list[i];
						const tr = $("<tr>");
						// 보낸사람, 내용, 시간, 읽음 여부
						const receiverTd = $("<td>");
						receiverTd.text(dm.receiver);
						const contentTd = $("<td>");
						contentTd.text(dm.dmContent);
						const dmDateTd = $("<td>");
						dmDateTd.text(dm.dmDate);
						const readTd = $("<td>");
						if (dm.readCheck == 0) {
							readTd.text("읽지않음");
						} else {
							readTd.text("읽음");
						}
						tr.append(receiverTd).append(contentTd).append(dmDateTd).append(readTd);
						tbody.append(tr);
					}
				}
			});
		};

		function getReceiveDm() {
			const receiver = $("#sender").val();
			$.ajax({
				url : '/myDmList.do',
				data : {
					receiver : receiver
				},
				success : function(list) {
					console.log(list);
					const tbody = $(".receiveDmTbl>tbody");
					tbody.empty();
					for (let i = 0; i < list.length; i++) {
						const dm = list[i];
						const tr = $("<tr>");
						// 보낸사람, 내용, 시간, 읽음 여부
						const senderTd = $("<td>");
						senderTd.text(dm.sender);
						const contentTd = $("<td>");
						contentTd.text(dm.dmContent);
						contentTd.attr("onclick", "dmDetail(" + dm.dmNo + ")");
						const dmDateTd = $("<td>");
						dmDateTd.text(dm.dmDate);
						const readTd = $("<td>");
						if (dm.readCheck == 0) {
							readTd.text("읽지않음");
						} else {
							readTd.text("읽음");
						}
						tr.append(senderTd).append(contentTd).append(dmDateTd).append(readTd);
						tbody.append(tr);
					}

				}
			});
		};
	</script>
</body>
</html>
