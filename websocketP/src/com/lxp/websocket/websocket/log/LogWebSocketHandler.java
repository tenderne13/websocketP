package com.lxp.websocket.websocket.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;


@Component
public class LogWebSocketHandler implements WebSocketHandler{

	
	private Process process;
	private InputStream inputStream;
	
	private static String task;
	
	
	static{
		Properties properties = new Properties();
		try {
			properties.load(LogWebSocketHandler.class.getClassLoader().getResourceAsStream("task.properties"));
			task=properties.getProperty("task");
			System.out.println("命令行是:"+task);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭时
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
			throws Exception {
		if(process != null)
			process.destroy();
		print("用户关闭了日志窗口。。。");
	}

	
	/**
	 * 建立连接后
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		process=Runtime.getRuntime().exec(task);
		inputStream = process.getInputStream();
		
		LogTailThread tailThread=new LogTailThread(inputStream, session);
		tailThread.start();
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message)
			throws Exception {
		
		
		
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
