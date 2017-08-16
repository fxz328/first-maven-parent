 
package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/** 
 * 
 * <pre>项目名称：web-common    
 * 类名称：ParamFilter    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月24日 上午7:29:46    
 * 修改人：范相震    
 * 修改时间：2017年7月24日 上午7:29:46    
 * 修改备注：       
 * @version </pre>
 */


public class ParamFilter implements Filter {
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("欢迎来到....");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		System.out.println(httpRequest.getSession().getId());
		
		//调用这个方法，让请求延续
		chain.doFilter(request, response);
	}
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	
	@Override
	public void destroy() {
		
	}

}
