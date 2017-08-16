/** 
 * <pre>项目名称:web-common 
 * 文件名称:FileUtil.java 
 * 包名:common.util 
 * 创建日期:2017年8月3日下午5:50:27 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

/** 
 * <pre>项目名称：web-common    
 * 类名称：FileUtil    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年8月3日 下午5:50:27    
 * 修改人：范相震    
 * 修改时间：2017年8月3日 下午5:50:27    
 * 修改备注：       
 * @version </pre>    
 */
public class FileUtil {

	public static String getMD5(InputStream is, String type){
		String result = null;
		 try {
			 MessageDigest md = MessageDigest.getInstance(type);
			 byte[] buffer = new byte[8192];
			 int length = -1;
			 while ((length = is.read(buffer)) != -1) {
			     md.update(buffer, 0, length);
			 }
			 byte[] digest = md.digest();
			 StringBuffer strBuffer = new StringBuffer();
			  for (int i = 0; i < digest.length; i++) {
			   strBuffer.append(Integer.toHexString(0xff & digest[i]));
			  }
			  result = strBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    try {
		    	is.close();
		    } catch (IOException ex) {
		    	
		    }
		}
		return result;
	}
	
	public static void main(String[] args) {
		FileInputStream file=null;
		try {
			file = new FileInputStream("E:1.jpg");
			String md5 = getMD5(file,"md5");
			System.out.println(md5);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
