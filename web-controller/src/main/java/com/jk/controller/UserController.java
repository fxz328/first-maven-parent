package com.jk.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.jk.pojo.User;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.role.RoleRequest;
import com.jk.service.UserService;

import common.util.DateUtil;
import common.util.FileUtil;
import common.util.FtpUtil;


@Controller
public class UserController {

	
	@Resource(name="userService")
	private UserService userService;
	
	private HttpServletResponse response;
	
	   @ModelAttribute
	   public void setReqAndRes( HttpServletResponse response){

	       this.response = response;
	   }
	 
	/**
	 * 查询全部数据
	 * @return</pre>
	 * @throws IOException 
	 *//*
	@RequestMapping("queryUserInfo")
	public void queryUserInfo() throws IOException{
		List<User> list= userService.queryUserInfo();
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		JSONObject json = new JSONObject();
		json.put("rows", list);
		writer.print(json);
		writer.close();
		 ModelAndView mv = new ModelAndView();
		    mv.addObject("list", list);
		    mv.setViewName("user_list");
		   // return mv;
	}*/
	
	   /**
		 * 查询全部数据
		 * @return</pre>
		 * @throws IOException 
		 */
		@RequestMapping("queryUserInfo")
		@ResponseBody
		public HashMap<String,Object> queryUserInfo(User user) {
			
			int end = user.getStart()+user.getEnd();
			user.setEnd(end);
			//查询总条数
			int totalCount = userService.selectUserCount(user);
			
			
			List<User> list= userService.queryUserInfo(user);
			HashMap<String,Object> hashMap = new HashMap<String,Object>();
			hashMap.put("total", totalCount);
			hashMap.put("rows", list);
			//String gson = new Gson().toJson(hashMap);
				
			return hashMap;
		}
	/**
	 * 添加用户信息带图片
	 * @param user
	 * @param uimg
	 * @param request
	 * @return
	 * @throws IOException</pre>
	 */
	@RequestMapping("insertUserInfo")
	public String insertUserInfo(User user){
		
		if(user.getUserID()!=null){
			
			//User u = userService.queryById(user);
			//String imgUploadUser = imgUploadUser(uimg,request);
			
			//if(imgUploadUser!=null&&!imgUploadUser.equals("")){
				//user.setUserImg(imgUploadUser);
			//}
	   			userService.updateUserInfo(user);
	   		
	   	 }else{
	   		// user.setUserImg(imgUploadUser(uimg,request));
	   		 userService.insertUserInfo(user);
	   	 }
			
			 return "redirect:show.ht";
	}
	/**
	 * 批量删除
	 * @param user</pre>
	 */
	@RequestMapping("deletedUserAll")
	public void deletedUserAll(User user){
		userService.deletedUserAll(user);
	}
	
	  	 /**
	      * 1. 使用RequestMapping注解来映射请求的URL
	      * 2. 返回值会通过视图解析器解析为实际的物理视图, 对于InternalResourceViewResolver视图解析器，会做如下解析
	      * 通过prefix+returnVal+suffix 这样的方式得到实际的物理视图，然后会转发操作
	      * "/WEB-INF/views/success.jsp"
	      * @return
	      */
	     @RequestMapping("toAddPage")
	     public ModelAndView toAddPage(User user){
	    	 
	    	 ModelAndView view = new ModelAndView();
	    	  
	    	 if(null!=user&&user.getUserID()!=null){
	    		 
	    		 user = userService.queryById(user);
	    		 view.addObject("user", user);
	    	 }
	    	// System.out.println(user);
	        
	        view.setViewName("../addUser");
	         return view;
	     }
	     /**
	      * 页面跳转
	      * @return</pre>
	      */
	     @RequestMapping("show")
	     public String show(){
	    	 
	         return "user_list";
	     }
	     /**
	      跳转到role页面 带id参数
	      */
	     @RequestMapping("toRolePage")
	     public String toRolePage(ModelMap mm,User user){
	    	 mm.addAttribute("userID", user.getUserID());
	    	 return "role_manager";
	     }
	     /**
				查询角色返回
	      */
	     @RequestMapping("selectUserRoleListJson")
	     @ResponseBody
	     public List<User> selectUserRoleListJson(RoleRequest roleRequest){
	    	 List<User> list = userService.selectUserRoleListJson(roleRequest);
	    	 return list;
	     }
	     
	    @RequestMapping("insertUserRoleList")
	 	@ResponseBody
	 	String insertUserRoleList(@RequestBody List<RoleRequest> roleRequestList) {
	 		userService.insertUserRoleList(roleRequestList);
	 		return "{}";
	 	}

	     /*
	      * 通过流的方式上传文件
	      * @RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
	      */
	     @RequestMapping("imgUploadUser")
	     public String  imgUploadUser(@RequestParam("uimg") CommonsMultipartFile uimg,HttpServletRequest request) throws IOException {
	        
	    	 String backUrl = null;
	    	 
	    	 if(uimg.getOriginalFilename()!=null&&!uimg.getOriginalFilename().equals("")){
	    		 // System.out.println("fileName："+userImg.getOriginalFilename());
	 	    	
		         try {
		             //获取输出流
		        	 String realPath = request.getSession().getServletContext().getRealPath("upload");
		        	 File file=new File(realPath);
		        		if (!file.exists()) {     //创建文件
		        			file.mkdirs();    
		        		}
		        	 String imgUrl = "/"+new Date().getTime()+uimg.getOriginalFilename();
		        	 //添加到数据库的路径
		        	 backUrl = "upload"+imgUrl;
		        	 
		             OutputStream os=new FileOutputStream(realPath+imgUrl);
		             //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
		            
		             InputStream is=uimg.getInputStream();
		             int temp;
		             //一个一个字节的读取并写入
		             while((temp=is.read())!=(-1))
		             {
		                 os.write(temp);
		             }
		            os.flush();
		            os.close();
		            is.close();
		          
		         } catch (FileNotFoundException e) {
		             // TODO Auto-generated catch block
		             e.printStackTrace();
		         }
	    		 
	    	 }  
	        // userService.insertUserInfo(user);
	         return backUrl; 
	     }
	     
	     
	     @RequestMapping("uploadFile")
	     //@ResponseBody
	     public void  uploadFile(@RequestParam("uimg") CommonsMultipartFile uimg,HttpServletRequest request) throws IOException {
	        
	    	 String backUrl = null;
	    	 
	    	 if(uimg.getOriginalFilename()!=null&&!uimg.getOriginalFilename().equals("")){
	    		 // System.out.println("fileName："+userImg.getOriginalFilename());
	    		 
		         try {
		        	 
		        	 //int available = file.available();
		        	 
		        	 //获取MD5指纹
		            String md5 = FileUtil.getMD5(uimg.getInputStream(), "md5");
		            System.out.println("文件指纹是：" + md5);
		            
		            //从数据库判断这个指纹存在
		            User md = userService.queryByMd5(md5);
		            if(null!=md){
		            	//如果存在，直接把地址返回给用户
		            	backUrl = md.getUserImg();
		            }else{
		            	//如果不存在，保存这个文件到FTP服务器，并且把保存的路径以及文件指纹存到数据库
		            	//获取文件名
						String originalFilename = uimg.getOriginalFilename();
						//获取后缀名
						String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
						//上传后文件名
			            String fileName = String.valueOf(new Date().getTime())+suffix;
			            //文件路径
			            String path = "userPhoto/" + DateUtil.formatDateToString(new Date(), "yyyy/MM/dd");
			           
			             
			            boolean uploadFile = FtpUtil.uploadFile2(uimg.getInputStream(), fileName, path);
			            System.out.println("上传文件结果："+uploadFile);
			            
			            backUrl = "ftp://root:root@192.168.1.119:21/"+path+"/"+fileName;
			           
			            
			            if(uploadFile){
			            	User user = new User();
			            	user.setMd5(md5);
			            	user.setUserImg(backUrl);
			            	userService.insertUserPhoto(user);
			            }
		            	
		            }
		            System.out.println(backUrl);
	            	 JSONObject jsonObject = new JSONObject();
	    	    	 jsonObject.put("backUrl", backUrl);
	    	    	
	    	    	 response.setCharacterEncoding("UTF-8");
	    	    	 PrintWriter writer = response.getWriter();
	    	    	 writer.println(jsonObject);
	    	    	 writer.close();
		            
		         } catch (FileNotFoundException e) {
		             e.printStackTrace();
		         }
	    	 }  
	    	 /*HashMap<String,String> hashMap = new HashMap<String,String>();
	    	 hashMap.put("backUrl", backUrl);*/
	        // userService.insertUserInfo(user);
	    	
	         //return hashMap; 
	     }
	     
	     
	    @RequestMapping("selectTreeListJson")
	    @ResponseBody
	    List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest){
	    	
	    	List<Map<String, Object>> treeList = userService.selectTreeListJson(menuRequest);
			return treeList;
	     }
	     
}
