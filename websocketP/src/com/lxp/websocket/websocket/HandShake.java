package com.lxp.websocket.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.lxp.websocket.po.User;

public class HandShake implements HandshakeInterceptor{

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
			WebSocketHandler handler, Exception exception) {
		
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler handler,
			Map<String, Object> attribute) throws Exception {
		
		if(request instanceof ServletServerHttpRequest){
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request;
			HttpSession httpSession=servletRequest.getServletRequest().getSession();
			User user= (User) httpSession.getAttribute("user");
			System.out.println("用户id:<"+user.getId()+">开始执行握手");
			if(user!=null){
				attribute.put("user", user);
			}else{
				return false;
			}
		}
		return true;
	}

}
