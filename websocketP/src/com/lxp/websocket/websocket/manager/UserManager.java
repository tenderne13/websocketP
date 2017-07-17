package com.lxp.websocket.websocket.manager;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;

import com.lxp.websocket.po.SocketUser;

public class UserManager implements IUserManager{

	private static Map<String, SocketUser> socketUserMap;
	
	private UserManager(){
		socketUserMap=new ConcurrentHashMap<String, SocketUser>();
	}
	
	private static UserManager manager=new UserManager();
	
	public static IUserManager getInstance(){
		return manager;
	}
	
	@Override
	public boolean addUser(SocketUser user) {
		String sessionUserId =  Integer.toString(user.getUserId());
		removeUser(sessionUserId);
		socketUserMap.put(sessionUserId, user);
		return true;
	}

	@Override
	public boolean removeUser(SocketUser user) {
		String sessionUserId =  Integer.toString(user.getUserId());
		//移除在线
		
		return removeUser(sessionUserId);
	}
	
	 private boolean removeUser(String sessionUserId) {
	        socketUserMap.remove(sessionUserId);
	        return true;
	    }

	@Override
	public int getOnlineCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SocketUser getUser(int userId) {
		String Key=Integer.toString(userId);
		if(socketUserMap.containsKey(Key)){
			return socketUserMap.get(Key);
		}
		return new SocketUser();
	}
	
	//移除会话
	public boolean removeSession(WebSocketSession session){
		Iterator<Entry<String, SocketUser>> it = socketUserMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String,SocketUser> entry=it.next();
			if(entry.getValue().getSession().getId().equals(session.getId())){
				socketUserMap.remove(entry.getKey());
				System.out.println("session用户移除成功:"+entry.getKey());
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断用户是否已经在其他地点登录
	 */
	@Override
	public boolean isIntheMap(String userId) {
		if(socketUserMap.containsKey(userId)){
			try {
				socketUserMap.get(userId).getSession().close();
				socketUserMap.remove(userId);
				return true;
			} catch (IOException e) {
				System.out.println("关闭session异常:"+e);
			}
		}
		return false;
	}

}
