package com.kh.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

// 일반 유저에서 서버간의 윕 소켓 url
@ServerEndpoint("/broadsocket")
public class BroadSocket {
	// searchUser 함수의 filter 표현식을 위한 인터페이스
	private interface SearchExpression {
		// 람다식을 위한 함수
		boolean expression(User user);
	}
	
	// 서버와 유저간의 접속을 key로 구한기 위한 인라인 클래스
	private class User {
		Session session;
		String key;
	}
	
	// 유저와 서버간의 접속 리스트
	private static List<User> sessionUsers = Collections.synchronizedList(new ArrayList<>());
	// Session으로 접속 리스트에서 User 클래스 탐색
	private static User getUser(Session session) {
		return searchUser(x -> x.session == session);
	}
	// Key로 접속 리스트에서 User 클래스 탐색
	private static User getUser(String key) {
		return searchUser(x -> x.key.equals(key));
	}
	// 접속 리스트 탐색 함수
	private static User searchUser(SearchExpression func) {
		Optional<User> op = sessionUsers.stream().filter(x -> func.expression(x)).findFirst();
		// 결과가 있으면
		if (op.isPresent()) {
			// 결과 리턴
			return op.get();
		}
		// 없으면 null 처리
		return null;
	}
	
	// browser에서 웹 소켓으로 접속하면 호출되는 함수
	@OnOpen
	public void handleOpen(Session userSession) {
		// 인라인 클래스 User를 생성
		User user = new User();
		// Unque키를 발급 ('-'는 제거)
		user.key = UUID.randomUUID().toString().replace("-", "-");
		// WebSocket의 세션
		user.session = userSession;
		// 유저 리스트에 등록
		sessionUsers.add(user);
		// 운영자 Client에 유저가 접속한 것을 알림
		Admin.visit(user.key);
	}
	// browser에서 웹 소켓을 통해 메세지가 오면 호출되는 함수
	@OnMessage
	public void handleMessage(String message, Session userSession) throws IOException {
		// Session으로 접속 리스트에서 User 클래스를 탐색
		User user = getUser(userSession);
		// 접속 리스트에 User가 있으면 (무조건 있어야함)
		if (user != null) {
			// 운영자 Client에 유저 key와 메세지 보냄
			Admin.sendMessage(user.key, message);
		}
	}
	// 운영자 client가 유저에게 메세지를 보내는 함수
	public static void sendMessage(String key, String message) {
		// key로 접속 리스트에서 User 클래스를 탐색
		User user = getUser(key);
		// 접속 리스트에 User가 있으면 (무조건 있어야함)
		if (user != null) {
			try {
				// 유저 Session으로 socket을 취득한 후 메세지 전송
				user.session.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	// WebSocket이 종료 되면, 종료 버튼이 없기 대문에 유저 브라우저가 닫히면 발생
	@OnClose
	public void handleClose(Session userSession) {
		// Session으로 접속 리스트에서 User 클래스 탐색
		User user = getUser(userSession);
		// 접속 리스트에 User가 있으면 (무조건 있음)
		if (user != null) {
			// 운영자 Client에 유저 key로 접속 종료 알림
			Admin.bye(user.key);
			// 유저 접속 리스트에서 유저 삭제
			sessionUsers.remove(user);
		}
	}
	// 유저간의 접속 리슽의 키를 취득하려고 할 때
	public static String[] getUserKeys() {
		// 반환할 String 배열 선언
		String[] ret = new String[sessionUsers.size()];
		// 유저 리스트를 반복문에 돌림
		for (int i = 0; i < ret.length; i++) {
			// 유저의 키만 반환 변수에 넣음
			ret[i] = sessionUsers.get(i).key;
		}
		// 값 반환
		return ret;
	}
}
