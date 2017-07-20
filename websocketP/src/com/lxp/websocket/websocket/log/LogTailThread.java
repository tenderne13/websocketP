package com.lxp.websocket.websocket.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class LogTailThread extends Thread{

	private BufferedReader reader;
	private WebSocketSession socketSession;
	
	public LogTailThread(InputStream inputStream,WebSocketSession socketSession) throws UnsupportedEncodingException{
		this.reader=new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
		this.socketSession=socketSession;
	}
	
	@Override
	public void run() {
		String line;
		while(true){
			try {
				if( (line=reader.readLine())!=null){
					socketSession.sendMessage(new TextMessage(line+"<br>"));
				}
			} catch (IOException e) {
				System.out.println("出现异常:"+e);
			}
		}
	}

	public static void main(String[] args) {
		try {
			Process process=Runtime.getRuntime().exec("tail -f C:/Users/Administrator/Desktop/tmp/layui.layim-v3.0.1/chat.htm");
			InputStream inputStream = process.getInputStream();
			BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
			while(true){
				if( reader.readLine()!=null){
					System.out.println(reader.readLine());
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
}
