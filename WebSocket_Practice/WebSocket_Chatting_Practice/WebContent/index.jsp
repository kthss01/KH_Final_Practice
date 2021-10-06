<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Socket Example</title>
</head>
<body>
	<!-- 채팅 영역 -->
	<form>
		<!-- 텍스트 박스에 채팅의 내용을 작성 -->
		<input id="textMessage" type="text" onkeydown="return enter();">
		<!-- 서버에 메시지로 전송하는 버튼 -->
		<input onclick="sendMessage();" value="Send" type="button">
	</form>
	<br>
	<!-- 서버와 메세지를 주고 받는 콘솔 텍스트 영역 -->
	<textarea id="messageTextArea" rows="10" cols="50" disabled="disabled"></textarea>
	
	<script>
		// 서버의 broadsocket의 서블릿으로 웹 소켓을 하기
		const webSocket = new WebSocket("ws://localhost:9090/WebSocket_Chatting_Practice/broadsocket");
		// 콘솔 텍스트 영역
		const messageTextArea = document.getElementById("messageTextArea");
		// 접속이 완료되면
		webSocket.onopen = function(message) {
			// 콘솔에 메세지 남김
			messageTextArea.value += "Sever connect...\n";
		};
		// 접속이 끊기는 경우는 브라우저 닫는 경우임 이 이벤트는 의미 X
		webSocket.onclose = function(message) {};
		// 에러 발생시
		webSocket.onerror = function(message) {
			// 콘솔에 메세지 남김
			messageTextArea.value += "error...\n";
		};
		// 서버로부터 메세지가 도착하면 콘솔 화면에 메세지 남기기
		webSocket.onmessage = function(message) {
			messageTextArea.value += "(operator) => " + message.data + "\n";
		};
		
		// 서버로 메세지를 발송하는 함수
		// Send 버튼을 누르거나 텍스트 박스에서 엔터를 치면 실행
		function sendMessage() {
			// 텍스트 박스의 객체 가져옴
			let message = document.getElementById("textMessage");
			// 콘솔에 메세지 남김
			messageTextArea.value += "(me) => " + message.value + "\n";
			// 소켓으로 보냄
			webSocket.send(message.value);
			// 텍스트 박스 초기화
			message.value = "";
		}
		
		// 텍스트 박스에서 엔터 누르면
		function enter() {
			// keyCode 13 엔터
			if (event.keyCode === 13) {
				// 서버로 메세지 전송
				sendMessage();
				// form에 의해 자동 submit 막기
				return false;
			}
			return true;
		}
	</script>
</body>
</html>