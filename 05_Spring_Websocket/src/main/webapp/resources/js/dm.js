/**
 * 
 */
 
 function sendDmMoDal(){
 	$.ajax({
 		url : "/selectAllMemberId.do",
 		success : function(list){
 			console.log(list);
 			$("[name=receiver]").empty();
 			for(let i = 0; i < list.length; i++) {
 			
	 			const option = $("<option>");
	 			option.val(list[i]);
	 			option.text(list[i]);
	 			$("[name=receiver]").append(option);
 			}
 			$("#sendDm-modal").css("display","flex");
 		}
 	});
 }
 
 function closeModal(){
 	$("#sendDm-modal").hide();
 	$("textarea[name=dmContent]").val("");
 }
 
 function dmSend() {
 	const receiver = $("[name=receiver]").val();
 	const dmContent = $("[name=dmContent]").val();
 	const sender = $("#sender").val();
 	
 	$.ajax({
 		url : "/insertDm.do",
 		data : {
 				receiver : receiver,
 				dmContent : dmContent,
 				sender : sender
		 		},
		 success : function(data){
		 	getSendDm();
		 	if(data == "1") {
		 		closeModal();
		 	} else {
		 		alert("실패");
		 	}
		 }
 	});
 }
 	function dmDetail(dmNo){
 		$.ajax({
 			url : "/dmDetail.do",
 			data : {dmNo : dmNo},
 			success : function(data) {
 				$("#detailSender").text(data.sender);
 				$("#detailDate").text(data.dmDate);
 				$("#detailContent").text(data.dmContent);
 				if(data.readCheck == 0) {
 					getReceiveDm();
 				}
 				$("#dmDetail").css("display","flex");
 			}
 		})
 	
 	}
 	function closeDetail(){
 		$("#dmDetail").hide();
 	}
 	$(function(){
		getReceiveDm();
		getSendDm();
 	});

 	