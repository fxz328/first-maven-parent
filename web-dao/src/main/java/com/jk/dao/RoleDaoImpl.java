/** 
 * <pre>项目名称:web-dao 
 * 文件名称:RoleDaoImpl.java 
 * 包名:com.jk.dao 
 * 创建日期:2017年7月26日下午9:50:54 
 * Copyright (c) 2017, lxm_man@163.com All Rights Reserved.</pre> 
 */  
package com.jk.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;
import com.jk.pojo.role.RoleRequest;
import com.jk.pojo.role.RoleResponse;

/** 
 * <pre>项目名称：web-dao    
 * 类名称：RoleDaoImpl    
 * 类描述：    
 * 创建人：范相震
 * 创建时间：2017年7月26日 下午9:50:54    
 * 修改人：范相震    
 * 修改时间：2017年7月26日 下午9:50:54    
 * 修改备注：       
 * @version </pre>    
 */
@Repository
public class RoleDaoImpl extends SqlMapClientDaoSupport implements RoleDao {

	
	@Override
	public List<RoleResponse> queryUserInfo(RoleRequest roleRequest) {
		
		return getSqlMapClientTemplate().queryForList("role.queryUserInfo",roleRequest);
	}

	
	@Override
	public List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest) {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("role.selectRoleMenuListJson",menuRequest);
	}


	
	@Override
	public void deleteMenuByRoleID(MenuRequest menuRequest) {
		getSqlMapClientTemplate().delete("role.deleteMenuByRoleID",menuRequest);
		
	}


	
	@Override
	public void insertRoleMenuList(final List<MenuRequest> menuRequestList) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
			
			@Override
			public Object doInSqlMapClient(SqlMapExecutor sqlMap) throws SQLException {
				//开启批量
				sqlMap.startBatch();
				//添加批量操作语句
				for (MenuRequest menuRequest : menuRequestList) {
					sqlMap.insert("role.insertRoleMenuList", menuRequest);
				}
				//执行批量操作
				sqlMap.executeBatch();
				return null;
			}
		});
		
	}

}
