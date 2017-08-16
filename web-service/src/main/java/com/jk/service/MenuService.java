/** 
 * <pre>项目名称:web-service 
 * 文件名称:MenuService.java 
 * 包名:com.jk.service 
 * 创建日期:2017年7月27日下午9:06:04 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.jk.service;

import java.util.List;

import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;

/** 
 * <pre>项目名称：web-service    
 * 类名称：MenuService    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月27日 下午9:06:04    
 * 修改人：范相震    
 * 修改时间：2017年7月27日 下午9:06:04    
 * 修改备注：       
 * @version </pre>    
 */
public interface MenuService {

	/** <pre>selectMenuListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月27日 下午9:51:05    
	 * 修改人：范相震      
	 * 修改时间：2017年7月27日 下午9:51:05    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<MenuResponse> selectMenuListJson(MenuRequest menuRequest);

	/** <pre>selectFirstMenuList(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月28日 上午8:58:41    
	 * 修改人：范相震      
	 * 修改时间：2017年7月28日 上午8:58:41    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<MenuResponse> selectFirstMenuList(MenuRequest menuRequest);

	/** <pre>insertMenu(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月28日 上午9:11:32    
	 * 修改人：范相震      
	 * 修改时间：2017年7月28日 上午9:11:32    
	 * 修改备注： 
	 * @param menuRequest</pre>    
	 */
	void insertMenu(MenuRequest menuRequest);

}
