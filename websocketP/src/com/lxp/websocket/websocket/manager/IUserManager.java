package com.lxp.websocket.websocket.manager;

import org.springframework.web.socket.WebSocketSession;

import com.lxp.websocket.po.SocketUser;

public interface IUserManager {
	boolean addUser(SocketUser user);

    boolean removeUser(SocketUser user);

    int getOnlineCount();

    SocketUser getUser(int userId);
    
    boolean removeSession(WebSocketSession socketSession);
    
    boolean isIntheMap(String userId);
}
