package kr.or.dm.model.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class DirectMessageHandler extends TextWebSocketHandler {

	// 쪽지 소켓에 접속한 회원정보 저장할 (key: id, value : 접속세션)
	private HashMap<String, WebSocketSession> connectionMembers;
	@Autowired
	private DirectMessageService service;
	
	public DirectMessageHandler() {
		super();
		connectionMembers = new HashMap<String, WebSocketSession>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {

	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		JsonParser parser = new JsonParser();
		System.out.println("전송 메세지 : " + message.getPayload());
		JsonElement element = parser.parse(message.getPayload());
		// 전송데이터 목적 구분위한 type 값 확인
		String type = element.getAsJsonObject().get("type").getAsString();
		if (type.equals("enter")) {
			String memberId = element.getAsJsonObject().get("memberId").getAsString();
			connectionMembers.put(memberId, session);
			// 최초접속 시 현재 내가 읽지 않은 수를 리턴
			int dmCount = service.dmCount(memberId);
			TextMessage tm = new TextMessage(String.valueOf(dmCount));
			session.sendMessage(tm);
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

	}

}
