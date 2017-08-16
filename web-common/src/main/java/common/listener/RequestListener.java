  
package common.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextListener;

/**
 *  
 * <pre>项目名称：web-common    
 * 类名称：RequestListener    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月24日 上午7:31:57    
 * 修改人：范相震    
 * 修改时间：2017年7月24日 上午7:31:57    
 * 修改备注：       
 * @version </pre>
 */
public class RequestListener extends RequestContextListener {


	@Override
	public void requestInitialized(ServletRequestEvent requestEvent) {
		HttpServletRequest servletRequest = (HttpServletRequest) requestEvent.getServletRequest();
		System.out.println("request：" + servletRequest.getRequestURI());
	}
}
