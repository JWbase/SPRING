<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
</head>
<body>
	<div class="join-box">
		<label for="memberEmail">이메일</label>
		<input type="text" name="email" id="email" value="${m.email }" readonly="readonly">
		<span class="comment"></span>
		<button id="sendEmailBtn" class="sendEmailBtn" type="button">인증메일전송</button>
		<div id="auth" style="display: none;">
			<input type="text" id="authCode" placeholder="인증코드입력">
			<button class="inputCode" id="authBtn" type="button">인증하기</button>
			<div class="timeBox">
				<span id="timeZone"></span> <span id="authMsg"></span>
			</div>
		</div>
		<span class="comment"></span>
	</div>

	<script>
		$("#sendEmailBtn").on("click", function() {
			const email = $("#email").val();
			console.log(email);
			const authCode = $("#authCode");

			$.ajax({
				url : "/sendmail.do",
				data : {
					email : email
				},
				type : "post",
				success : function(data) {
					mailCode = data;
					$("#auth").show();
					authTime();
				}
			});
		});
	</script>
</body>
</html>