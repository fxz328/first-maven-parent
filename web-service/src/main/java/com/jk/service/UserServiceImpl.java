package com.jk.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jk.dao.UserDao;
import com.jk.pojo.User;
import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.role.RoleRequest;
import com.sun.org.apache.bcel.internal.generic.JsrInstruction;

import common.constant.Constant;
import common.util.JedisUtil;
import common.util.JsonUtil;

@Service("userService")
 public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;


	public List<User> queryUserInfo(User user) {
		
		return userDao.queryUserInfo(user);
	}

	
	//@Override
	public int selectUserCount(User user) {
		return userDao.selectUserCount( user);
	}
	//@Override
	public void insertUserInfo(User user) {
		userDao.insertUserInfo(user);
		
		
		 // 添加用户时默认为普通用户
		userDao.insertUserRoleDefault(user);
		
	}

	//@Override
	public void deletedUserAll(User user) {
		userDao.deletedUserAll(user);
		
	}

	//@Override
	public User queryById(User user) {
		return userDao.queryById(user);
	}

	//@Override
	public void updateUserInfo(User user) {
		userDao.updateUserInfo(user);
		
	}
	
	
	//@Override
	public List<User> selectUserRoleListJson(RoleRequest roleRequest) {
		
		return userDao.selectUserRoleListJson(roleRequest);
	}

	
	//@Override
	public void insertUserRoleList(List<RoleRequest> roleRequestList) {
				// 1、删除用户之前的所有角色（mid）
				userDao.deleteAllRolesByUserID(roleRequestList.get(0));
				// 2、添加用户勾选的所有角色（mid）
				userDao.insertUserRoleList(roleRequestList);
		
	}
	/**
	 * 登录验证
	 */
	public HashMap<String, Object> loginCheck(User user,HttpServletRequest request){
			HashMap<String,Object> hashMap = new HashMap<String,Object>();
		//获取session对象
		HttpSession session = request.getSession();
		//获取存在session中的验证码 
		Object codeObj = session.getAttribute("imageCode");
		//判断若为null 置为空字符串 以防报空指针异常
		if (null == codeObj) {
			codeObj = "";
		}
		String code = codeObj.toString();
		//把正确的验证码赋给user定义属性 做验证
		user.setSysImgCode(code);
		
		//判断验证码
		if (null == user.getUserImgCode() || "".equals(user.getUserImgCode())) {
			//验证码为空
			 hashMap.put("flag", Constant.LOGIN_CODE_NULL);
		} else if (user.getUserImgCode().equals(user.getSysImgCode())) {
			 //连接数据库，查询用户信息
			User u = userDao.loginCheck(user);
			
			//判断是否被锁定（小于连续3次失败并且距离最近一次失败大于5分钟）
			if (null != u){
				if(0 < u.getLoginFailNum()&&u.getLoginFailDate() > 60000){
					userDao.setZero(u);
				}
				if(0 == u.getLoginFailNum() || 0 < (u.getLoginFailNum() % 3) || u.getLoginFailDate() > 60000){
					if (u.getUserPassword().equals(user.getUserPassword())) {
						//登陆成功
						//用户信息放进session中
						session.setAttribute("userInfo", u);
						
						
						//设置过期时间 单位：秒
						session.setMaxInactiveInterval(60);
						userDao.setZero(u);
						hashMap.put("userInfo", u);
						hashMap.put("flag", Constant.LOGIN_SUCCESS);
					}else{
						//密码错误
						//连接数据库，修改登陆失败的次数
						//查询密码错误次数
						User u2 = userDao.loginCheck(user);
						//密码错误一次往数据库改变一次错误次数
						userDao.updateLoginFailNum(user);
						
						hashMap.put("failCount", u2.getLoginFailNum() + 1);
						hashMap.put("flag", Constant.LOGIN_PWD_ERROR);
					}
					
				}else{
					//锁定
					hashMap.put("flag", Constant.ACCOUNT_LOCKED);
				}
				
			}else{
				//用户不存在
				 hashMap.put("flag", Constant.LOGIN_ACCOUNT_ERROR);
				
			}
					
		} else {
			
			//验证码错误
			 hashMap.put("flag", Constant.LOGIN_CODE_ERROR);
		}
		return hashMap;	 	
	}


	/**
	 * 递归树
	 */
	//@Override
	public List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest) {
		List<Map<String, Object>> treeList = new ArrayList<Map<String,Object>>();
		//从redis缓存中获取权限列表
		String string = JedisUtil.getString(menuRequest.getUserID()+"#treeList");
		
		//如果没有获取到，则查询数据库，如果查询到了结果，直接返回
		if(string == null){
			 treeList = userDao.selectTreeListJson(menuRequest);
			if (null != treeList && 0 < treeList.size()) {
				//开始调用递归
				treeList = queryTreeListNodes(treeList, menuRequest);
				
				//把查询的结果存一份到redis上
				JedisUtil.setString(menuRequest.getUserID() + "#treeList", 
						JsonUtil.toJsonString(treeList), 300);
			} 
		}else{
				treeList = JsonUtil.fromJson(string, new ArrayList<Map<String, Object>>(){}.getClass());
			
		}
				
				return treeList;
	}
	
	//递归查询树菜单
		private List<Map<String, Object>> queryTreeListNodes(List<Map<String, Object>> treeList, MenuRequest menuRequest) {
			for (Map<String, Object> map : treeList) {
				if ("0".equals(map.get("pid").toString())) {
					//取出ID作为下次查询的pid
					int pid = Integer.valueOf(map.get("id").toString());
					menuRequest.setPid(pid);
					List<Map<String, Object>> queryTreeListNodes = 
							queryTreeListNodes(userDao.selectTreeListJson(menuRequest), menuRequest);
					map.put("nodes", queryTreeListNodes);
				}
			}
			return treeList;
		}


		/**
		 * 查询用户所拥有的的所有权限
		 */
		//@Override
		public List<HashMap<String, Object>> queryAllTreeByUserID(User user) {
			
			return userDao.queryAllTreeByUserID(user);
		}
		
		/**
		 * 图片上传
		 */
		//@Override
		public void insertUserPhoto(User user) {
			userDao.insertUserPhoto(user);
			
		}


		/**
		 * 根据MD5查询数据库中有没有相同数据
		 */
		//@Override
		public User queryByMd5(String md5) {
			
			return userDao.queryByMd5(md5);
		}
}
