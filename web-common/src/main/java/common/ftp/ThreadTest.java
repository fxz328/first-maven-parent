/** 
 * <pre>项目名称:web-common 
 * 文件名称:ThreadTest.java 
 * 包名:common.ftp 
 * 创建日期:2017年8月2日上午10:29:17 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package common.ftp;

/** 
 * <pre>项目名称：web-common    
 * 类名称：ThreadTest    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年8月2日 上午10:29:17    
 * 修改人：范相震    
 * 修改时间：2017年8月2日 上午10:29:17    
 * 修改备注：       
 * @version </pre>    
 */
public class ThreadTest extends Thread{

	/* (non-Javadoc)    
	 * @see java.lang.Thread#run()    
	 */
	@Override
	public void run() {
		for (int i = 0; i < 99; i++) {
			System.out.println(i);
		}
	}
}

 