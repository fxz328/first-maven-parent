/** 
 * <pre>项目名称:web-controller 
 * 文件名称:MenuController.java 
 * 包名:com.jk.controller 
 * 创建日期:2017年7月27日下午9:02:51 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.jk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.service.MenuService;

/** 
 * <pre>项目名称：web-controller    
 * 类名称：MenuController    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月27日 下午9:02:51    
 * 修改人：范相震    
 * 修改时间：2017年7月27日 下午9:02:51    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@RequestMapping("showMenu")
	public String showMenu(){
		return "menu_show";
	}
	
	@RequestMapping("selectMenuListJson")
	@ResponseBody
	public List<MenuResponse> selectMenuListJson(MenuRequest menuRequest){
		
		List<MenuResponse> list = menuService.selectMenuListJson(menuRequest);
		
		return list;
	}
	
	@RequestMapping("toAddMenuPage")
	public String toAddMenuPage(Model m){
		List<MenuResponse> list = menuService.selectFirstMenuList(new MenuRequest());
		m.addAttribute("menulist",list);
		return "add_menu";
	}
	
	@RequestMapping("insertMenu")
	@ResponseBody
	String insertMenu(MenuRequest menuRequest) {
		menuService.insertMenu(menuRequest);
		return "{}";
	}

	
}
