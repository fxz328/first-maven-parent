/** 
 * <pre>项目名称:web-common 
 * 文件名称:FTPClientTest.java 
 * 包名:common.ftp 
 * 创建日期:2017年8月1日下午5:25:19 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

/** 
 * <pre>项目名称：web-common    
 * 类名称：FTPClientTest    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年8月1日 下午5:25:19    
 * 修改人：范相震    
 * 修改时间：2017年8月1日 下午5:25:19    
 * 修改备注：       
 * @version </pre>    
 */
public class FTPClientTest {
	
	@org.junit.Test
	public void Test(){
		//实例化ftp客户端
		FTPClient ftp = new FTPClient();
		try {
			
			ftp.connect("192.168.1.119",21);
			boolean login = ftp.login("root","root");
			System.out.println(login);
			//切换到根路径
			ftp.changeWorkingDirectory("/");
			//判断是否存在
			//创建文件夹(返回true:服务器上没有，已被创建；返回false:服务器上有，创建失败)
			boolean d = ftp.makeDirectory("newFile");
			//切换到刚刚创建完的路径下
			boolean b = ftp.changeWorkingDirectory("newFile");
			
			//System.out.println(b);
			FileInputStream file = new FileInputStream("E:/1_111214094415_1.jpg");
			//设置文件类型（二进制）   
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);  
            
            //在这个路径下保存文件
			//f.storeFile(new String("ye.jpg".getBytes("GBK"), "ISO-8859-1"), is)
			boolean storeFile = ftp.storeFile("ye.jpg", file);
			System.out.println(storeFile);
			// boolean deleteFile = ftp.deleteFile("/newFile/sss.txt");
			
			 //System.out.println(deleteFile);
			
			 
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
