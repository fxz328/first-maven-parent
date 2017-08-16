/** 
 * <pre>项目名称:web-service 
 * 文件名称:MenuServiceImpl.java 
 * 包名:com.jk.service 
 * 创建日期:2017年7月27日下午9:06:26 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.jk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.MenuDao;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;

/** 
 * <pre>项目名称：web-service    
 * 类名称：MenuServiceImpl    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月27日 下午9:06:26    
 * 修改人：范相震    
 * 修改时间：2017年7月27日 下午9:06:26    
 * 修改备注：       
 * @version </pre>    
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

	
	@Autowired
	private MenuDao menuDao;


	@Override
	public List<MenuResponse> selectMenuListJson(MenuRequest menuRequest) {
		
		return menuDao.selectMenuListJson(menuRequest);
	}


	
	@Override
	public List<MenuResponse> selectFirstMenuList(MenuRequest menuRequest) {
		
		return menuDao.selectFirstMenuList(menuRequest);
	}
	
	
	@Override
	public void insertMenu(MenuRequest menuRequest) {
		if(menuRequest.getPid()==0){
			menuRequest.setParent(true);
		}
		menuDao.insertMenu(menuRequest);
		
		
	}
}
