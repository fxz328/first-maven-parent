package com.jk.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jk.pojo.User;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.role.RoleRequest;


@Repository
public class UserDaoImpl extends SqlMapClientDaoSupport implements UserDao {

	
	/**
	 * 查询用户信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> queryUserInfo(User user) {
		
		return getSqlMapClientTemplate().queryForList("users.selectUserList",user);
	}

	/**
	 * 查询总条数
	 */
	@Override
	public int selectUserCount(User user) {
		
		return (Integer) getSqlMapClientTemplate().queryForObject("users.selectUserCount",user);
	}
	
	/**
	 * 添加用户
	 */
	@Override
	public void insertUserInfo(User users) {
		/*int ceil = (int) Math.ceil(Math.random()*1000000000);
		users.setUserID(ceil);*/
		users.setUserDate(new Date());
		getSqlMapClientTemplate().insert("users.insertUser",users);
	}
	
	/**
	 * 添加用户时默认为普通用户
	 */
	@Override
	public void insertUserRoleDefault(User user) {
		user.setRoleID(3);
		getSqlMapClientTemplate().insert("users.insertUserRoleDefault",user);
	}

	@Override
	public void deletedUserAll(User users) {
		String ids = users.getIds();
		if(ids.length()==1){
			RoleRequest roleRequest = new RoleRequest();
			roleRequest.setUserID(Integer.valueOf(ids));
			deleteAllRolesByUserID(roleRequest);
		}
		getSqlMapClientTemplate().delete("users.delectUserAll",ids );
		
	}

	@Override
	public User queryById(User users) {
		@SuppressWarnings("unchecked")
		List<User> list = getSqlMapClientTemplate().queryForList("users.selectUserById",users);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateUserInfo(User user) {
		user.setUserDate(new Date());
		getSqlMapClientTemplate().update("users.updateUserInfo", user);
	}

	
	@Override
	public List<User> selectUserRoleListJson(RoleRequest roleRequest) {
		
		return getSqlMapClientTemplate().queryForList("users.selectUserRoleListJson",roleRequest);
	}
	
	
	@Override
	public void deleteAllRolesByUserID(RoleRequest roleRequest) {
		
		this.getSqlMapClientTemplate().delete("users.deleteAllRolesByUserID", roleRequest);
		
	}
	
	
	@Override
	public void insertUserRoleList(final List<RoleRequest> roleRequestList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
			
			@Override
			public Object doInSqlMapClient(SqlMapExecutor sqlMap) throws SQLException {
				//开启批量
				sqlMap.startBatch();
				//添加批量操作语句
				for (RoleRequest roleRequest : roleRequestList) {
					sqlMap.insert("users.insertUserRole", roleRequest);
				}
				//执行批量操作
				sqlMap.executeBatch();
				return null;
			}
		});
		
	}
	@Override
	public User loginCheck(User user) {
		
		return (User) getSqlMapClientTemplate().queryForObject("users.loginCheck",user);
	}
	
	
	/**
	 * 修改登录失败次数
	 */
	@Override
	public void updateLoginFailNum(User user) {
		
		getSqlMapClientTemplate().update("users.updateLoginFailNum", user);
	}

	/**
	 * 登录成功 登录错误次数置0
	 */
	@Override
	public void setZero(User u) {
		getSqlMapClientTemplate().update("users.setZero", u);
		
	}


	/**
	 * 同部树
	 */
	@Override
	public List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest) {
		
		return getSqlMapClientTemplate().queryForList("users.selectTreeListJson",menuRequest);
	}


	/**
	 * 查询本用户所拥有的所有权限
	 */
	@Override
	public List<HashMap<String, Object>> queryAllTreeByUserID(User user) {
		
		return getSqlMapClientTemplate().queryForList("users.queryAllTreeByUserID",user);
		
	}

	
	/**
	 * 图片上传
	 */
	@Override
	public void insertUserPhoto(User user) {
		getSqlMapClientTemplate().insert("users.insertUserPhoto",user);
		
	}

	/**
	 * 根据MD5码查询数据库中有没有相同数据
	 */
	@Override
	public User queryByMd5(String md5) {
		 
		return (User) getSqlMapClientTemplate().queryForObject("users.queryByMd5",md5);
	}
}
