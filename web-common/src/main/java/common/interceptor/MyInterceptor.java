/** 
 * <pre>项目名称:maven-web 
 * 文件名称:MyInterceptor.java 
 * 包名:com.jk.interceptor 
 * 创建日期:2017年7月19日下午12:12:18 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package common.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



/** 
 * <pre>项目名称：maven-web    
 * 类名称：MyInterceptor    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月19日 下午12:12:18    
 * 修改人：范相震    
 * 修改时间：2017年7月19日 下午12:12:18    
 * 修改备注：       
 * @version </pre>    
 */
public class MyInterceptor extends HandlerInterceptorAdapter{

	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)    
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
				/*//获取session对象
				HttpSession session = request.getSession();
				System.out.println(session.getId());
				//获取session创建时间
				long creationTime = session.getCreationTime();
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String format = sim.format(new Date(creationTime));
				System.out.println("session创建时间："+format);
				//获取访问的url
				String requestURI = request.getRequestURI();
				System.out.println(requestURI);
				//获取访问参数
				String queryString = request.getQueryString();
				System.out.println(queryString);
				//获取客户端主机
				String remoteHost = request.getRemoteHost();
				System.out.println(remoteHost);
				//获取传参类型
				String method = request.getMethod();
				System.out.println(method);*/
		//获取请求名
		String servletPath = request.getServletPath();
		//获取session
		HttpSession session = request.getSession();
		Object u = session.getAttribute("userInfo");
		
		if(servletPath.equals("/login.ht")){
			return true;
		}else{
			if(u != null){
				return true;
			}else{
				//未登录，重定向页面到登陆页面
				//判断是否是ajax请求
				
				String type = request.getHeader("X-Requested-With");// XMLHttpRequest
				// 重定向
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
				// 转发
				if (StringUtils.equals("XMLHttpRequest", type)) {
					// ajax请求
					response.setHeader("SESSIONSTATUS", "TIMEOUT");
					response.setHeader("CONTEXTPATH", basePath+"login.jsp");
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				} else {
					//常规
					response.sendRedirect(request.getContextPath() + "/login.jsp");
				}
			}
		}
		
		return false;	
		
	}
}
