/** 
 * <pre>项目名称:web-dao 
 * 文件名称:MenuDaoImpl.java 
 * 包名:com.jk.dao 
 * 创建日期:2017年7月27日下午9:05:05 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.jk.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.jk.pojo.User;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;

/** 
 * <pre>项目名称：web-dao    
 * 类名称：MenuDaoImpl    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月27日 下午9:05:05    
 * 修改人：范相震    
 * 修改时间：2017年7月27日 下午9:05:05    
 * 修改备注：       
 * @version </pre>    
 */
@Repository
public class MenuDaoImpl extends SqlMapClientDaoSupport implements MenuDao {

	
	@Override
	public List<MenuResponse> selectMenuListJson(MenuRequest menuRequest) {
	
		return getSqlMapClientTemplate().queryForList("menu.selectMenuListJson",menuRequest);
	}

	
	@Override
	public List<MenuResponse> selectFirstMenuList(MenuRequest menuRequest) {
		
		return getSqlMapClientTemplate().queryForList("menu.selectFirstMenuList",menuRequest);
	}


	
	@Override
	public void insertMenu(MenuRequest menuRequest) {
		
		getSqlMapClientTemplate().insert("menu.insertMenu", menuRequest);
		
	}
	

}
