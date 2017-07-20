package com.lxp.websocket.websocket;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.lxp.websocket.websocket.log.LogWebSocketHandler;
@Component
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{

	@Resource
	private LxpWebSocketHandler handler;
	@Resource
	private LogWebSocketHandler logHandler;
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registy) {
		registy.addHandler(handler, "/chat").addInterceptors(new HandShake());
		
		registy.addHandler(logHandler, "/log").addInterceptors(new com.lxp.websocket.websocket.log.HandShake());

		registy.addHandler(handler, "/chat/sockjs").addInterceptors(new HandShake()).withSockJS();
	}

}
