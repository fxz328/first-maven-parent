package com.jk.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jk.pojo.User;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.role.RoleRequest;


public interface UserDao {

	

	List<User> queryUserInfo(User user);

	void insertUserInfo(User user);

	void deletedUserAll(User user);

	User queryById(User user);

	void updateUserInfo(User user);

	/** <pre>loginCheck(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月19日 下午5:09:02    
	 * 修改人：范相震      
	 * 修改时间：2017年7月19日 下午5:09:02    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	User loginCheck(User user);

	/** <pre>updateLoginFailNum(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月20日 下午5:57:36    
	 * 修改人：范相震      
	 * 修改时间：2017年7月20日 下午5:57:36    
	 * 修改备注： 
	 * @param user</pre>    
	 */
	void updateLoginFailNum(User user);

	/** <pre>setZero(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月20日 下午6:57:40    
	 * 修改人：范相震      
	 * 修改时间：2017年7月20日 下午6:57:40    
	 * 修改备注： 
	 * @param u</pre>    
	 */
	void setZero(User u);

	/** <pre>selectUserRoleListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月25日 下午7:36:23    
	 * 修改人：范相震      
	 * 修改时间：2017年7月25日 下午7:36:23    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	List<User> selectUserRoleListJson(RoleRequest roleRequest);

	/** <pre>deleteAllRolesByUserID(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月25日 下午9:11:08    
	 * 修改人：范相震      
	 * 修改时间：2017年7月25日 下午9:11:08    
	 * 修改备注： 
	 * @param roleRequest</pre>    
	 */
	void deleteAllRolesByUserID(RoleRequest roleRequest);

	/** <pre>insertUserRoleList(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月25日 下午9:16:02    
	 * 修改人：范相震      
	 * 修改时间：2017年7月25日 下午9:16:02    
	 * 修改备注： 
	 * @param roleRequestList</pre>    
	 */
	void insertUserRoleList(List<RoleRequest> roleRequestList);

	/** <pre>selectUserCount(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月26日 下午9:05:00    
	 * 修改人：范相震      
	 * 修改时间：2017年7月26日 下午9:05:00    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	int selectUserCount(User user);

	/** <pre>selectTreeListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月27日 下午6:59:39    
	 * 修改人：范相震      
	 * 修改时间：2017年7月27日 下午6:59:39    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest);

	/** <pre>queryAllTreeByUserID(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月28日 下午4:24:37    
	 * 修改人：范相震      
	 * 修改时间：2017年7月28日 下午4:24:37    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	List<HashMap<String, Object>> queryAllTreeByUserID(User user);

	/** <pre>insertUserRoleDefault(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年7月29日 上午10:57:25    
	 * 修改人：范相震      
	 * 修改时间：2017年7月29日 上午10:57:25    
	 * 修改备注： 
	 * @param user</pre>    
	 */
	void insertUserRoleDefault(User user);

	/** <pre>insertUserPhoto(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年8月3日 下午10:10:35    
	 * 修改人：范相震      
	 * 修改时间：2017年8月3日 下午10:10:35    
	 * 修改备注： 
	 * @param user</pre>    
	 */
	void insertUserPhoto(User user);

	/** <pre>queryByMd5(这里用一句话描述这个方法的作用)   
	 * 创建人：范相震
	 * 创建时间：2017年8月4日 上午8:46:27    
	 * 修改人：范相震      
	 * 修改时间：2017年8月4日 上午8:46:27    
	 * 修改备注： 
	 * @param md5
	 * @return</pre>    
	 */
	User queryByMd5(String md5);
	

}
