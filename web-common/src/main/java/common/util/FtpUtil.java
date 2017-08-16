/** 
 * <pre>项目名称:web-common 
 * 文件名称:FtpUtil.java 
 * 包名:common.util 
 * 创建日期:2017年8月3日下午5:50:40 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/** 
 * <pre>项目名称：web-common    
 * 类名称：FtpUtil    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年8月3日 下午5:50:40    
 * 修改人：范相震    
 * 修改时间：2017年8月3日 下午5:50:40    
 * 修改备注：       
 * @version </pre>    
 */
public class FtpUtil {

	public static boolean uploadFile(InputStream is, String fileName, String path) {
		boolean storeResult = false;
		//1、实例化ftp客户端
		FTPClient f = new FTPClient();
		try {
			//2、连接服务器
			f.connect(Constant.FTP_HOST, Constant.FTP_PORT);
			//3、登录验证
			f.login(Constant.FTP_USER, Constant.FTP_PWD);
			//设置被动连接
			f.enterLocalPassiveMode();
			//4、判断是否登陆成功
			int reply = f.getReplyCode();
			if (FTPReply.isPositiveCompletion(reply)) {
				//切换到根路径
				f.changeWorkingDirectory("/");
				//判断是否存在
				//创建文件夹(返回true:服务器上没有，已被创建；返回false:服务器上有，创建失败)
				boolean d = f.makeDirectory(path);
				//切换到刚刚创建完的路径下
				f.changeWorkingDirectory(path);
				//在这个路径下保存文件
				//f.storeFile(new String("黑名单.txt".getBytes("GBK"), "ISO-8859-1"), is);
				//设置文件类型为二进制文件
				f.setFileType(FTPClient.BINARY_FILE_TYPE);
				storeResult = f.storeFile(fileName, is);
				f.logout();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				f.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return storeResult;
	}
	
	
	public static boolean uploadFile2(InputStream is, String fileName, String path) {
		boolean storeResult = false;
		//1、实例化ftp客户端
		FTPClient f = new FTPClient();
		try {
			//2、连接服务器
			f.connect("192.168.1.119", 21);
			//3、登录验证
			boolean login = f.login("root", "root");
			//设置被动连接
			f.enterLocalPassiveMode();
			System.out.println(login);
			//4、判断是否登陆成功
			int reply = f.getReplyCode();
			if (FTPReply.isPositiveCompletion(reply)) {
				//切换到根路径
				f.changeWorkingDirectory("/");
				//判断是否存在
				//创建文件夹(返回true:服务器上没有，已被创建；返回false:服务器上有，创建失败)
				boolean d = f.makeDirectory(path);
				//切换到刚刚创建完的路径下
				f.changeWorkingDirectory(path);
				//在这个路径下保存文件
				//f.storeFile(new String("黑名单.txt".getBytes("GBK"), "ISO-8859-1"), is);
				//设置文件类型为二进制文件
				f.setFileType(FTPClient.BINARY_FILE_TYPE);
				storeResult = f.storeFile(fileName, is);
				f.logout();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				f.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return storeResult;
	}
	public static void main(String[] args) {
		
		InputStream inputStream;
		try {
			inputStream = new FileInputStream("E://1.jpg");
			boolean uploadFile = uploadFile2(inputStream, "test.jpg","test");
			System.out.println(uploadFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
