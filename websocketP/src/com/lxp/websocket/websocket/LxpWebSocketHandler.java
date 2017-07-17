package com.lxp.websocket.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.lxp.websocket.po.SocketUser;
import com.lxp.websocket.po.User;
import com.lxp.websocket.po.message.ToServerTextMessage;
import com.lxp.websocket.websocket.manager.IUserManager;
import com.lxp.websocket.websocket.manager.UserManager;
import com.lxp.websocket.websocket.sender.MessageSender;

@Component
public class LxpWebSocketHandler implements WebSocketHandler{

	/**
	 * 关闭时
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
			throws Exception {
		UserManager.getInstance().removeSession(session);
		print("用户下线了。。。");
	}

	
	/**
	 * 建立连接后
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		User user = (User) session.getAttributes().get("user");
		
		SocketUser socketUser=new SocketUser();
		socketUser.setUserId(user.getId());
		socketUser.setSession(session);
		print("当前用户为:"+socketUser);
		IUserManager manager = UserManager.getInstance();
		
		if(manager.isIntheMap(Integer.toString(user.getId()))){
			System.out.println("已将其他地点的登录状态踢掉。。。");
		}
		manager.addUser(socketUser);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
			throws Exception {
		if(message.getPayloadLength()==0){
			return;
		}
		
		String data =  message.getPayload().toString();
		ToServerTextMessage textMessage=JSON.parseObject(data, ToServerTextMessage.class);
		print("传过来的数据为："+data);
        //得到接收的对象
        MessageSender sender = new MessageSender();
        sender.sendMessage(textMessage);
		
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception)
			throws Exception {
		print("出错了。。。。");
	}

	@Override
	public boolean supportsPartialMessages() {
		
		return false;
	}
	
	
	public void print(String message){
		System.out.println(message);
	}

}
