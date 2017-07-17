package com.lxp.websocket.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class LxpWebSocketHandler implements WebSocketHandler{

	@Override
	public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1)
			throws Exception {
		
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		
	}

	@Override
	public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> arg1)
			throws Exception {
		
	}

	@Override
	public void handleTransportError(WebSocketSession arg0, Throwable arg1)
			throws Exception {
		
	}

	@Override
	public boolean supportsPartialMessages() {
		
		return false;
	}

}
