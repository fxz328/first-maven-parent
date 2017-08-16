/** 
 * <pre>项目名称:ssi-controller-01 
 * 文件名称:RoleController.java 
 * 包名:com.jk.controller.role 
 * 创建日期:2017年7月26日下午2:37:21 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.pojo.User;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;
import com.jk.service.RoleSerivce;

/** 
 * 
 * <pre>项目名称：web-controller    
 * 类名称：RoleController    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月26日 下午9:47:14    
 * 修改人：范相震    
 * 修改时间：2017年7月26日 下午9:47:14    
 * 修改备注：       
 * @version </pre>
 */


@Controller
public class RoleController {

	@Resource
	private RoleSerivce roleService;
	
	@RequestMapping("showRole")
	String showRole(){
		
		return "role_show";
	}
	
	
	 /**
	 * 查询全部数据
	 * @return</pre>
	 * @throws IOException 
	 */
	@RequestMapping("queryRoleTableInfo")
	@ResponseBody
	public HashMap<String,Object> queryRoleTableInfo(RoleRequest roleRequest) {
		
		
		List<RoleResponse> list= roleService.queryUserInfo(roleRequest);
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("total", 3);
		hashMap.put("rows", list);
		//String gson = new Gson().toJson(hashMap);
			
		return hashMap;
	}
	
	 @RequestMapping("toMenuPage")
     public String toMenuPage(ModelMap mm,RoleRequest roleRequest){
    	 mm.addAttribute("roleID", roleRequest.getRoleID());
    	 return "menu_manager";
     }
     /**
			查询角色返回
      */
     @RequestMapping("selectRoleMenuListJson")
     @ResponseBody
     public List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest){
    	 List<MenuResponse> list = roleService.selectRoleMenuListJson(menuRequest);
    	 return list;
     }
     
    @RequestMapping("insertRoleMenuList")
 	@ResponseBody
 	String insertRoleMenuList(@RequestBody List<MenuRequest> menuRequestList) {
    	roleService.insertRoleMenuList(menuRequestList);
 		return "{}";
 	}

}
