/** 
 * <pre>项目名称:ssi-common-01 
 * 文件名称:SessionListener.java 
 * 包名:common.listener 
 * 创建日期:2017年7月22日上午11:57:14 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package common.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *  
 * <pre>项目名称：web-common    
 * 类名称：SessionListener    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月24日 上午7:32:40    
 * 修改人：范相震    
 * 修改时间：2017年7月24日 上午7:32:40    
 * 修改备注：       
 * @version </pre>
 */

public class SessionListener implements HttpSessionListener {
	
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		String id = se.getSession().getId();
		System.out.println("sessionID：" + id);
	}
	
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
	}

}
