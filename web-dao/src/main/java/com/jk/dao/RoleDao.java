/** 
 * <pre>项目名称:web-dao 
 * 文件名称:RoleDao.java 
 * 包名:com.jk.dao 
 * 创建日期:2017年7月26日下午9:50:38 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.jk.dao;

import java.util.List;

import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

/** 
 * <pre>项目名称：web-dao    
 * 类名称：RoleDao    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月26日 下午9:50:38    
 * 修改人：范相震    
 * 修改时间：2017年7月26日 下午9:50:38    
 * 修改备注：       
 * @version </pre>    
 */
public interface RoleDao {

	/** <pre>queryUserInfo(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月26日 下午10:41:24    
	 * 修改人：范相震      
	 * 修改时间：2017年7月26日 下午10:41:24    
	 * 修改备注： 
	 * @param roleRequest
	 * @return</pre>    
	 */
	List<RoleResponse> queryUserInfo(RoleRequest roleRequest);

	/** <pre>selectRoleMenuListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月27日 上午11:01:08    
	 * 修改人：范相震      
	 * 修改时间：2017年7月27日 上午11:01:08    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest);

	/** <pre>deleteMenuByRoleID(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月27日 下午4:11:22    
	 * 修改人：范相震      
	 * 修改时间：2017年7月27日 下午4:11:22    
	 * 修改备注： 
	 * @param menuRequest</pre>    
	 */
	void deleteMenuByRoleID(MenuRequest menuRequest);

	/** <pre>insertRoleMenuList(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月27日 下午4:11:59    
	 * 修改人：范相震      
	 * 修改时间：2017年7月27日 下午4:11:59    
	 * 修改备注： 
	 * @param menuRequestList</pre>    
	 */
	void insertRoleMenuList(List<MenuRequest> menuRequestList);
	
}
