/** 
 * <pre>项目名称:web-service 
 * 文件名称:RoleServiceImpl.java 
 * 包名:com.jk.service 
 * 创建日期:2017年7月26日下午9:48:09 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.jk.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jk.dao.RoleDao;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

/** 
 * <pre>项目名称：web-service    
 * 类名称：RoleServiceImpl    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月26日 下午9:48:09    
 * 修改人：范相震    
 * 修改时间：2017年7月26日 下午9:48:09    
 * 修改备注：       
 * @version </pre>    
 */
@Service("roleService")
public class RoleServiceImpl implements RoleSerivce {

	@Resource
	private RoleDao roleDao;

	
	@Override
	public List<RoleResponse> queryUserInfo(RoleRequest roleRequest) {
		
		return roleDao.queryUserInfo(roleRequest);
	}


	
	@Override
	public List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest) {
		
		return roleDao.selectRoleMenuListJson(menuRequest);
	}



	
	@Override
	public void insertRoleMenuList(List<MenuRequest> menuRequestList) {
		
		roleDao.deleteMenuByRoleID(menuRequestList.get(0));
		
		roleDao.insertRoleMenuList(menuRequestList);
		
		
	}
}
