<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Socket Example</title>
<style>
	/* 여러 채팅창 간의 간격과 배열 위치 */
	.float-left {
		float: left;
		margin: 5px;
	}
</style>
</head>
<body>
	<!-- 유저가 접속할 때마다 이 템플릿으로 채팅창 생성 -->
	<div class="template" style="display: none;">
		<form>
			<!-- 메세지 텍스트 박스 -->
			<input type="text" class="message" onkeydown="if(event.keyCode === 13) return false;">
			<!-- 전송 버튼 -->
			<input value="Send" type="button" class="sendBtn">
		</form>
		<br>
		<!-- 서버와 메세지를 주고 받는 콘솔 텍스트 영역 -->
		<textarea rows="10" cols="50" class="console" disabled="disabled"></textarea>
	</div>
	
	<!-- JQuery 사용 -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script>
		// 서버의 admin의 서블릿으로 웹 소켓
		const webSocket = new WebSocket("ws://localhost:9090/WebSocket_Chatting_Practice/admin");
		// 운영자에서의 open, close, error는 의미 없어 형태만 선언
		webSocket.onopen = function(message) {};
		webSocket.onclose = function(message) {};
		webSocket.onerror = function(message) {};
		// 서버로부터 메세지가 오면
		webSocket.onmessage = function(message) {
			console.log(message);
			// 메세지의 구조 JSON 형태
			let node = JSON.parse(message.data);
			// 메세지의 statu는 유저의 접속 형태
			// visit은 유저가 접속했을 때 알리는 메세지
			if (node.status === "visit") {
				// 위 템플릿 div 취득
				let form = $(".template").html();
				// div를 감싸고 속성 data-key에 unique키를 넣음
				form = $("<div class='float-left'></div>").attr("data-key", node.key).append(form);
				// body에 추가
				$("body").append(form);
			// message는 유저가 메세지를 보낼 때 알려주는 메세지
			} else if (node.status === "message") {
				// key로 해당 div 영역 찾음
				let $div = $("[data-key='" + node.key + "']");
				// console 영역을 찾음
				let log = $div.find(".console").val();
				// 아래에 메세지 추가
				$div.find(".console").val(log + "(user) => " + node.message + "\n");
			// bye는 유저가 접속을 끊었을 때 알려주는 메세지
			} else if (node.status === "bye") {
				// 해당 키로 div를 찾아서 dom 제거
				$("[data-key='" + node.key + "']").remove();
			}
		};
		
		// 전송 버튼 클릭시 발생하는 이벤트
		$(document).on("click", ".sendBtn", function() {
			// div 태그 찾기
			let $div = $(this).closest(".float-left");
			// 메세지 텍스트 박스를 찾아서 값을 취득
			let message = $div.find(".message").val();
			// 유저 key 취득
			let key = $div.data("key");
			// console 영역 찾기
			let log = $div.find(".console").val();
			// 아래에 메세지 추가
			$div.find(".console").val(log + "(me) => " + message + "\n");
			// 텍스트 박스의 값 초기화
			$div.find(".message").val("");
			// 웹 소켓으로 메세지 전송
			webSocket.send(key + "####" + message);
		});
		
		// 텍스트 박스 엔터키 이벤트
		$(document).on("keydown", ".message", function() {
			// keyCode 13 엔터
			if (event.keyCode === 13) {
				// 버튼 클릭 트리거 발생
				$(this).closest(".float-left").find(".sendBtn").trigger("click");
				// form에 의해 자동 submit 방지
				return false;
			}
			return true;
		});
	</script>
</body>
</html>