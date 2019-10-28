package com.kh.bts.websocket;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.kh.bts.dto.ChatInfoDto;
import com.kh.bts.dto.MemberDto;
import com.kh.bts.dto.MessageDto;
import com.kh.bts.model.biz.ChatInfoBiz;
import com.kh.bts.model.biz.MessageBiz;

public class SocketHandler extends TextWebSocketHandler implements InitializingBean {
	
	private Logger logger = LoggerFactory.getLogger(SocketHandler.class);
	private Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession>();
	private Map<Integer, Set<WebSocketSession>> chatrooms = new HashMap<Integer, Set<WebSocketSession>>();
	@Autowired private ChatInfoBiz chatInfo;
	@Autowired private MessageBiz messagebiz;
	public SocketHandler() {
		super();
		this.logger.info("create SocketHandler instance!");
	}
	
	public Set<WebSocketSession> getSessionSet(){
		return sessionSet;
	}
	
	public MemberDto getMemberDto(WebSocketSession session) {
		logger.info(session.toString());
		System.out.println(session.getPrincipal());
		return  (MemberDto)((UsernamePasswordAuthenticationToken) session.getPrincipal()).getPrincipal();
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		//WebSocket 연결이 닫혔을 때 호출
		logger.info("WebSocket이 닫힙니다@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		boolean test = sessionSet.remove(session);
		System.out.println(test+"################################");
		int member_no = getMemberDto(session).getMember_no();		
		List<ChatInfoDto> list = chatInfo.selectListToMember(member_no);
		new Thread(()->{
			for(ChatInfoDto dto : list) {
				Set<WebSocketSession> set = chatrooms.get(dto.getChatroom_no());
				set.remove(session);
				if(set.size()==0) {
					chatrooms.remove(dto.getChatroom_no());
				}
			}
			logger.info("remove session!");
		}).start();
		
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		logger.info("@@@@@@@@@@@@@@@@@@webSocket연결이 열림!");
		// webSocket 연결이 열리고 사용이 준비될 때 호출됨.
		System.out.println("Session : " + session);
		sessionSet.add(session);
		int member_no = getMemberDto(session).getMember_no();
		List<ChatInfoDto> list = chatInfo.selectListToMember(member_no);
		new Thread(()->{
			for(ChatInfoDto dto : list) {
				if(chatrooms.get(dto.getChatroom_no())==null) {
					Set<WebSocketSession> set = new HashSet<WebSocketSession>();
					set.add(session);
					chatrooms.put(dto.getChatroom_no(), set);
				}else {
					chatrooms.get(dto.getChatroom_no()).add(session);
				}
			}
			logger.info("add session!");
		}).start();
		
	}
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		super.handleMessage(session, message);
		logger.info("receive message : " + message.getPayload().toString());
		sendChatRoomMessage(getMemberDto(session),message.getPayload().toString());
		//메시지가 도착했을때 호출
		
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		super.handleTransportError(session, exception);
		//전송에러 발생할 때 호출
		logger.error("web socket error!",exception);
	}
	
	@Override
	public boolean supportsPartialMessages() {
		//WebSocketHandler 가 부분 메시지를 처리할 때 호출
		logger.info("call method!");
		return super.supportsPartialMessages();
	}
	
	public void sendCount(WebSocketSession session,String msg) throws IOException {
		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		obj.put("type","count");
		obj.put("count", msg);
		if(session.isOpen()) {
			session.sendMessage(new TextMessage(obj.toJSONString()));
		}
	}
	public void updateChatinfoCount(MemberDto principal, int chatroom_no) {
		ChatInfoDto dto = new ChatInfoDto();
		dto.setChatroom_no(chatroom_no);
		dto.setMember_no(principal.getMember_no());
		chatInfo.updateCountUp(chatroom_no);
		chatInfo.updateCountZero(chatroom_no, principal.getMember_no());
	}
	
	public void sendChatRoomMessage (MemberDto principal,String message) {
		System.out.println("SEND CHATROOM 들어옴!!!!!!!!!!!!!!!");
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		int chatroom = -1;
		String msg = "";
		try {
			obj = (JSONObject)parser.parse(message);
			chatroom = Integer.parseInt((String)obj.get("chatroom_no"));
			msg = (String) obj.get("msg");
			obj.put("type","msg");
			System.out.println("JSON PUT완료!!!!!!!!!!");
		} catch (ParseException e1) {
			System.out.println("실패!!!!!!!!!!!!!!!!!!");
		}
		if(chatroom == -1) {
			logger.info("chatroom을 찾을수 없습니다.");
			return;
		}
		updateChatinfoCount(principal,chatroom);
		MessageDto dto = new MessageDto();
		dto.setChatroom_no(chatroom);
		dto.setMember_no(principal.getMember_no());
		dto.setMessage_content(msg);
		dto.setMessage_regdate(new Date());
		logger.info("@@@@@@@@@@@@@MSG DTO : "+dto.toString());
		dto = messagebiz.insert(dto);
		if(dto.getMessage_no() == 0) {
			logger.info("MESSAGE 저장 실패! 메세지를 전송하지 않습니다.");			
			return;
		}
		obj.put("nickname", principal.getMember_nickname());
		obj.put("regdate",dto.getMessage_regdate()+"");
		logger.info("전달하는 String 입니다!!!!!!!! : " + obj.toJSONString());
		Set<WebSocketSession> set = chatrooms.get(chatroom);
		for(WebSocketSession session: set) {
			if(session.isOpen()) {
				try {
					if(getMemberDto(session).getMember_no() != principal.getMember_no()) {
						session.sendMessage(new TextMessage(obj.toJSONString()));
					}
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("fail to send message! ", e);
				}
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
		new Thread(()->{
			while(true) {
				try {
					Set<WebSocketSession> set = getSessionSet();
					Set<String> nickSet = new HashSet<String>();
					for(WebSocketSession session : set) {
						nickSet.add(getMemberDto(session).getMember_nickname());
						int member_no = getMemberDto(session).getMember_no();
						List<ChatInfoDto> list = chatInfo.selectListToMember(member_no);
						int res = 0;
						for(ChatInfoDto dto : list) {
							res += dto.getCount();
						}
						sendCount(session, res+"");
					}
					JSONArray arr = new JSONArray();
					JSONObject json = new JSONObject();
					json.put("type","peers");
					for(String str : nickSet) {
						JSONObject obj = new JSONObject();
						obj.put("member_nickname", str);
						arr.add(obj);
					}
					json.put("peers", arr);
					logger.info(json.toJSONString());
					sendUsernickname(json.toJSONString());
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		}).start();
		
	}

	private void sendUsernickname(String jsonString) {
		for(WebSocketSession session: sessionSet) {
			if(session.isOpen()) {
				try {
					session.sendMessage(new TextMessage(jsonString));
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("fail to send message! ", e);
				}
			}
		}
		
	}
	

}
