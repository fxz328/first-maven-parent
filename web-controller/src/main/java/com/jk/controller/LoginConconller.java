/** 
 * <pre>项目名称:maven-web 
 * 文件名称:LoginConconller.java 
 * 包名:com.jk.controller 
 * 创建日期:2017年7月19日下午12:11:39 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.jk.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JEditorPane;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.jk.pojo.User;
import com.jk.service.UserService;

import common.base.MySessionContext;
import redis.clients.jedis.Jedis;

/** 
 * <pre>项目名称：maven-web    
 * 类名称：LoginConconller    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月19日 下午12:11:39    
 * 修改人：范相震    
 * 修改时间：2017年7月19日 下午12:11:39    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
public class LoginConconller {

	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("login")
	@ResponseBody
	public HashMap<String,Object> login(User user,HttpServletRequest request){
		
		HashMap<String,Object> hashMap = userService.loginCheck(user,request);
		HttpSession session = request.getSession();
		Object userInfo = hashMap.get("userInfo");
		if (null != userInfo) {
			User userResponse = (User) userInfo;
			//用户信息放进session中
			session.setAttribute("userInfo", userInfo);
			//设置session过期时间(单位：秒)
			session.setMaxInactiveInterval(600);
			
			String userID = userResponse.getUserID() + "";
			MySessionContext.removeSession(userID,session);
			MySessionContext.addSession(userID, session);
			
			
				User u = new User();
				u.setUserID(Integer.parseInt(userID));
			//查询用户所拥有的的所有权限
			List<HashMap<String,Object>> thisUserTree = userService.queryAllTreeByUserID(u);
			//装进redis 转成json字符串
			String string = new Gson().toJson(thisUserTree);
			Jedis jedis = new Jedis("192.168.40.130",6379);
			jedis.auth("fxz");
			jedis.set(u.getUserID()+"#thisUserTree", string);
			jedis.expire(u.getUserID()+"#thisUserTree", 300);
			
		
		}
		return hashMap;
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("userInfo");
		return "redirect:index.jsp";
	}
	

}
