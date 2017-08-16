 
package com.jk.task;

import javax.ejb.Schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** 
 * <pre>项目名称：web-service    
 * 类名称：MsgTask    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年8月1日 上午9:39:32    
 * 修改人：范相震    
 * 修改时间：2017年8月1日 上午9:39:32    
 * 修改备注：       
 * @version </pre>    
 */
@Component
public class MsgTask {

	/*@Scheduled(fixedRate=5000)
	public void test() {
		System.out.println("一个频度定时器");
	}
	*/
	@Scheduled(cron="0 50 10 * * ?")
	public void test1() {
		System.out.println("一个定时器");
	}
}
