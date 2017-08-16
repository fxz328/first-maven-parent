/** 
 * <pre>项目名称:web-service 
 * 文件名称:RoleSerivce.java 
 * 包名:com.jk.service 
 * 创建日期:2017年7月26日下午9:47:49 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.jk.service;

import java.util.List;

import com.jk.pojo.User;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

/** 
 * <pre>项目名称：web-service    
 * 类名称：RoleSerivce    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月26日 下午9:47:49    
 * 修改人：范相震    
 * 修改时间：2017年7月26日 下午9:47:49    
 * 修改备注：       
 * @version </pre>    
 */
public interface RoleSerivce {

	/** <pre>queryUserInfo(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月26日 下午10:40:00    
	 * 修改人：范相震      
	 * 修改时间：2017年7月26日 下午10:40:00    
	 * 修改备注： 
	 * @param roleRequest
	 * @return</pre>    
	 */
	List<RoleResponse> queryUserInfo(RoleRequest roleRequest);

	/** <pre>selectRoleMenuListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月27日 上午11:00:10    
	 * 修改人：范相震      
	 * 修改时间：2017年7月27日 上午11:00:10    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest);

	/** <pre>insertRoleMenuList(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月27日 下午4:08:31    
	 * 修改人：范相震      
	 * 修改时间：2017年7月27日 下午4:08:31    
	 * 修改备注： 
	 * @param menuRequestList</pre>    
	 */
	void insertRoleMenuList(List<MenuRequest> menuRequestList);

}
