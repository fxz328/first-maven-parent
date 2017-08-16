/** 
 * <pre>项目名称:web-common 
 * 文件名称:PermissionInterceptor.java 
 * 包名:common.interceptor 
 * 创建日期:2017年7月28日下午4:06:58 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package common.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import common.util.JsonUtil;
import redis.clients.jedis.Jedis;

/** 
 * <pre>项目名称：web-common    
 * 类名称：PermissionInterceptor    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月28日 下午4:06:58    
 * 修改人：范相震    
 * 修改时间：2017年7月28日 下午4:06:58    
 * 修改备注：       
 * @version </pre>    
 */
public class PermissionInterceptor implements HandlerInterceptor{

	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)    
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
				//获取请求url
				String uri = request.getRequestURI();
				//获取登录的用户信息 
				Object attribute = request.getSession().getAttribute("userInfo");
				 //使用json转化格式
				 Gson gson = new Gson();
				 String json = gson.toJson(attribute);
				 //定义一个变量
				 int flag=0;
				 //获取登陆用户的id
				 int userID = new JsonParser().parse(json).getAsJsonObject().get("userID").getAsInt();
				Jedis jedis = new Jedis("192.168.40.130",6379);
				jedis.auth("fxz");
				String string = jedis.get(userID+"#thisUserTree");
				//List<HashMap<String,Object>> thisUserTree = gson.fromJson(string, new ArrayList<HashMap<String, Object>>(){}.getClass());
				List<Map<String, Object>> thisUserTree = JsonUtil.fromJson(string, new ArrayList<Map<String, Object>>(){}.getClass());
				for (Map<String, Object> map : thisUserTree) {
					Object url = map.get("url");
					if (null == url) {
						continue;
					} else if (uri.contains(url.toString())) {
						flag = 1;
						break;
					}
				}
				
				if (1 == flag) {
					return true;
				} else {
					return false;
				}
		
	}
	
	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)    
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)    
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
