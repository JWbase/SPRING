package kr.or.common;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.xml.internal.ws.resources.SenderMessages;

public class AllMemberChat extends TextWebSocketHandler {
	// 접속한 회원 세션을 저장하는 리스트
	private ArrayList<WebSocketSession> sessionList;

	// 세션별로 아이디를 저장할 map
	private HashMap<WebSocketSession, String> memberList;

	public AllMemberChat() {
		super();
		sessionList = new ArrayList<WebSocketSession>();
		memberList = new HashMap<WebSocketSession, String>();
	}

	// 클라이언트가 웹소켓에 최초로 접속했을 때 자동으로 수행되는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
	}

	// 클라이언트가 웹소켓 서버로 메세지를 전송하면 수행되는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("사용자 전송 메세지 : " + message.getPayload());
		// 문자열을 Json객체로 변환
		JsonParser parser = new JsonParser();
		// Parser를 이용해서 문자열 -> Json 변환
		JsonElement element = parser.parse(message.getPayload());
		// key가 type인 값을 추출
		String type = element.getAsJsonObject().get("type").getAsString();
		//key가 msg 인 값을 추출
		String msg = element.getAsJsonObject().get("msg").getAsString();

		if (type.equals("enter")) {
			// 최초접속이므로 멤버 목록에 추가
			memberList.put(session, msg);
			String sendMsg = "<p>" + msg + "님이 입장하셨습니다.</p>";
			for (WebSocketSession s : sessionList) {
				if (!s.equals(session)) {
					// 클라이언트 전송용 객체 생성
					TextMessage tm = new TextMessage(sendMsg);
					// 클라이언트에게 전송
					s.sendMessage(tm);
				}
			}
		} else if (type.equals("chat")) {
			String sendMsg = "<div class='chat left'><span class='chatId'> " + memberList.get(session) + " : </span>"
					+ msg + "</div>";
			// 클라이언트 전송용 객체 생성
			TextMessage tm = new TextMessage(sendMsg);
			for (WebSocketSession s : sessionList) {
				if (!s.equals(session)) {
					// 클라이언트에게 전송
					s.sendMessage(tm);
				}
			}
		}
	}

	// 클라이언트와 연결이 끊어졌을때 자동으로 수행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
		String sendMsg = "<p>" + memberList.get(session) + "님이 퇴장하셨습니다.</p>";
		memberList.remove(session);
		TextMessage tm = new TextMessage(sendMsg);
		for (WebSocketSession s : sessionList) {
				s.sendMessage(tm);
		}
	}
}