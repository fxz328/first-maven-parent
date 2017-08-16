  
package common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/** 
 * 
 * <pre>项目名称：web-common    
 * 类名称：ApplicationListener    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月24日 上午7:30:53    
 * 修改人：范相震    
 * 修改时间：2017年7月24日 上午7:30:53    
 * 修改备注：       
 * @version </pre>
 */

public class ApplicationListener implements ServletContextListener {
	
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("hehe");
	}

	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
