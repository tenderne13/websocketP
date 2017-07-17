package com.lxp.websocket.po;

import org.springframework.web.socket.WebSocketSession;


/**
 * Created by pz on 16/11/23.
 */
public class SocketUser {
    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isExist(){
        return this.userId > 0;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private WebSocketSession session;
    private int userId;

    @Override
    public String toString() {
        return session.getId()+"_"+userId;
    }
}
