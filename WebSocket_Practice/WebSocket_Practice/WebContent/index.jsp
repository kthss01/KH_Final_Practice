<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web Socket Example</title>
</head>
<body>
	<form>
		<!-- 송신 메세지를 작성하는 텍스트 박스 -->
		<input id="textMessage" type="text">
		<!-- 메세지 송신을 하는 버튼 -->
		<input onclick="sendMessage();" value="Send" type="button">
		<!-- WebSocket 접속 종료하는 버튼 -->
		<input onclick="disconnect();" value="Disconnect" type="button">
	</form>
	<br>
	<!-- 콘솔 메세지의 역할을 하는 로그 텍스트 에리어 (수신 메세지도 표시) -->
	<textarea id="messageTextArea" rows="10" cols="50"></textarea>
	
	<script>
		// [WebSocket_Practice]는 프로젝트명
		// [websocket]는 호스트명
		// WebSocket 오브젝트 생성 (자동으로 접속 시작. - onopen 함수 호출)
		const webSocket = new WebSocket("ws://localhost:9090/WebSocket_Practice/websocket");
		// 콘솔 텍스트 에리어 오브젝트
		const messageTextArea = document.getElementById("messageTextArea");
		// WebSocket 서버와 접속이 되면 호출되는 함수
		webSocket.onopen = function(message) {
			// 콘솔 텍스트에 메세지 출력
			messageTextArea.value += "Server Connect...\n";
		};
		// WebSocket 서버와 접속이 끊기면 호출되는 함수
		webSocket.onclose = function(message) {
			// 콘솔 텍스트에 메세지 출력
			messageTextArea.value += "Server Disconnect...\n";
		};
		// WebSocket 서버와 통신 중에 에러가 발생하면 요청되는 함수
		webSocket.onerror = function(message) {
			// 콘솔 텍스트에 메세지 출력
			messageTextArea.value += "error...\n";
		}
		// WebSocket 서버로부터 메시지가 오면 호출되는 함수
		webSocket.onmessage = function(message) {
			// 콘솔 텍스트에 메세지를 출력
			messageTextArea.value += "Receive From Server => " + message.data + "\n";
		};
		
		// Send 버튼을 누르면 호출되는 함수
		function sendMessage() {
			// 송신 메세지를 작성하는 텍스트박스 오브젝트
			const message = document.getElementById("textMessage");
			// 콘솔 텍스트에 메세지 출력
			messageTextArea.value += "Send to Server => " + message.value + "\n";
			// WebSocket 서버에 메세지를 송신
			webSocket.send(message.value);
			// 송신 메세지를 작성하는 텍스트 박스 초기화
			message.value = "";
		}
		
		// Disconnect 버튼을 누르면 호출되는 함수
		function disconnect() {
			// WebSocket 접속 해제
			webSocket.close();
		}
	</script>
</body>
</html>