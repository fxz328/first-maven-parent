/** 
 * <pre>项目名称:web-common 
 * 文件名称:Test.java 
 * 包名:common.ftp 
 * 创建日期:2017年8月2日上午10:40:04 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package common.ftp;

/** 
 * <pre>项目名称：web-common    
 * 类名称：Test    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年8月2日 上午10:40:04    
 * 修改人：范相震    
 * 修改时间：2017年8月2日 上午10:40:04    
 * 修改备注：       
 * @version </pre>    
 */
public class Test {

	
	public static void main(String[] args) {
		ThreadTest threadTest = new ThreadTest();
		ThreadTest threadTest2 = new ThreadTest();
		
		threadTest.start();
		threadTest2.start();
		
	}
}
