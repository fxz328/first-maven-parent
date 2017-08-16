package com.jk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jk.pojo.User;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.role.RoleRequest;

public abstract interface UserService {


	abstract List<User> queryUserInfo(User user);

	void insertUserInfo(User user);

	void deletedUserAll(User user);

	abstract User queryById(User user);

	abstract void updateUserInfo(User user);

	/** <pre>loginCheck(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月19日 下午5:07:29    
	 * 修改人：范相震      
	 * 修改时间：2017年7月19日 下午5:07:29    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	abstract HashMap<String, Object> loginCheck(User user,HttpServletRequest request);

	/** <pre>selectUserRoleListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月25日 下午7:35:20    
	 * 修改人：范相震      
	 * 修改时间：2017年7月25日 下午7:35:20    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	abstract List<User> selectUserRoleListJson(RoleRequest roleRequest);

	/** <pre>insertUserRoleList(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月25日 下午9:08:19    
	 * 修改人：范相震      
	 * 修改时间：2017年7月25日 下午9:08:19    
	 * 修改备注： 
	 * @param roleRequestList</pre>    
	 */
	abstract void insertUserRoleList(List<RoleRequest> roleRequestList);

	/** <pre>selectUserCount(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月26日 下午9:04:28    
	 * 修改人：范相震      
	 * 修改时间：2017年7月26日 下午9:04:28    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	abstract int selectUserCount(User user);

	/** <pre>selectTreeListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月27日 下午5:15:22    
	 * 修改人：范相震      
	 * 修改时间：2017年7月27日 下午5:15:22    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	abstract List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest);

	/** <pre>queryAllTreeByUserID(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月28日 下午4:22:17    
	 * 修改人：范相震      
	 * 修改时间：2017年7月28日 下午4:22:17    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	abstract List<HashMap<String, Object>> queryAllTreeByUserID(User user);

	/** <pre>insertUserPhoto(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年8月3日 下午10:09:35    
	 * 修改人：范相震      
	 * 修改时间：2017年8月3日 下午10:09:35    
	 * 修改备注： 
	 * @param user</pre>    
	 */
	abstract void insertUserPhoto(User user);

	/** <pre>queryByMd5(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年8月4日 上午8:45:01    
	 * 修改人：范相震      
	 * 修改时间：2017年8月4日 上午8:45:01    
	 * 修改备注： 
	 * @param md5
	 * @return</pre>    
	 */
	abstract User queryByMd5(String md5);
	

}
