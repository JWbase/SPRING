<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>

<h3>읽지 않은 쪽지 수 : <span id="dmCount"></span></h3>

<input type="hidden" id="memberId" value="${sessionScope.m.memberId }">
<script>
	let memberId;
	let ws;
	$(function() {
		memberId = $("#memberId").val();
		ws = new WebSocket("ws://192.168.10.18/dm.do");
		ws.onopen = onOpen;
		ws.onmessage = receiveMsg;
		ws.onclose = onClose;
	});
	function onOpen() {
		console.log("쪽지소켓연결완료");
		const data = {
			type : "enter",
			memberId : memberId
		};
		ws.send(JSON.stringify(data));
	}
	function receiveMsg(param) {
		console.log(param.data)
		$("#dmCount").text(param.data);
	}
	function onClose() {
		console.log("쪽지소켓연결종료");
	}
</script>