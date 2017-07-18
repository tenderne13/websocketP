package com.lxp.websocket.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.lxp.websocket.po.FriendGroup;
import com.lxp.websocket.po.JsonResultType;
import com.lxp.websocket.po.StatusUser;
import com.lxp.websocket.po.User;
import com.lxp.websocket.po.result.BaseDataResult;
import com.lxp.websocket.po.result.JsonResult;

@Controller
@RequestMapping("api")
public class ChatController {

	Map<Integer, User> users = new HashMap<Integer, User>();

	// 模拟一些数据
	@ModelAttribute
	public void setReqAndRes() {
		User u1 = new User();
		u1.setId(1);
		u1.setUsername("李小朋");
		users.put(u1.getId(), u1);

		User u2 = new User();
		u2.setId(2);
		u2.setUsername("贾欣琪");
		users.put(u2.getId(), u2);

	}
	
	
	// 用户登录
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String doLogin(User user, HttpSession httpSession) {
		httpSession.removeAttribute("user");
		user.setUsername(users.get(user.getId()).getUsername());
		httpSession.setAttribute("user", user);
		return "redirect:talk";
	}

	// 跳转到交谈聊天页面
	@RequestMapping(value = "talk", method = RequestMethod.GET)
	public String talk() {
		return "talk";
	}
	
	//根据用户获得不同的列表
	@RequestMapping("getList")
	@ResponseBody
	public String getList(String uid){
		return getDataList(uid);
	}
	
	public String getDataList(String uid){
		BaseDataResult dataResult=new BaseDataResult();
		StatusUser mine1=new StatusUser();
		mine1.setId(1);
		mine1.setUsername("李小朋");
		mine1.setAvatar("https://avatars7.githubusercontent.com/u/28944859?v=4&s=460");
		
		StatusUser mine2=new StatusUser();
		mine2.setId(2);
		mine2.setUsername("贾欣琪");
		mine2.setAvatar("http://tva1.sinaimg.cn/crop.0.0.540.540.180/89b4acf3jw8enzkd2ld8zj20f00f0aao.jpg");
		
		
		List<FriendGroup> friend=new ArrayList<FriendGroup>();
		
		FriendGroup group1 = new FriendGroup();
		group1.setId(1001);
		group1.setOnline(1);
		group1.setGroupname("女神");
		
		FriendGroup group2 = new FriendGroup();
		group2.setId(1002);
		group2.setOnline(1);
		group2.setGroupname("男神");
		
		
		
		List<User> userList1=new ArrayList<User>();
		List<User> userList2=new ArrayList<User>();
		
		User user1=new User();
		user1.setId(2);
		user1.setUsername("贾欣琪");
		user1.setAvatar("http://tva1.sinaimg.cn/crop.0.0.540.540.180/89b4acf3jw8enzkd2ld8zj20f00f0aao.jpg");
		
		User user2=new User();
		user2.setId(1);
		user2.setUsername("李小朋");
		user2.setAvatar("https://avatars7.githubusercontent.com/u/28944859?v=4&s=460");
		
		
		userList1.add(user1);
		group1.setList(userList1);
		
		
		userList2.add(user2);
		group2.setList(userList2);;
		
		if(uid.equals("1")){
			friend.add(group1);
			dataResult.setMine(mine1);
			dataResult.setFriend(friend);
		}else{
			friend.add(group2);
			dataResult.setMine(mine2);
			dataResult.setFriend(friend);
		}
		
		JsonResult jsonResult=new JsonResult();
		jsonResult.setCode(JsonResultType.typeSuccess);
		jsonResult.setMsg("");
		jsonResult.setData(dataResult);
		
		
		return JSON.toJSONString(jsonResult);
	}
	
	public static void main(String[] args) {
		
	}
}
