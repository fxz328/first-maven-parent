package com.jk.pojo;

import java.io.Serializable;
import java.util.Date;



public class User implements Serializable{


	private static final long serialVersionUID = -5504223351317456739L;

	private Integer userID;
	
	private String  userName;
	
	private String  userPassword;

	private Date userDate;
	
	private String userImg;
	
	private String ids;
	//验证码
	private String userImgCode;
	
	private String sysImgCode;
	
	private Integer roleID;

	/**
	 * 登录失败次数
	 */
	private int loginFailNum;
	
	/**
	 * 登录失败时间
	 */
	private long loginFailDate;
	
	private String md5;

	public long getLoginFailDate() {
		return loginFailDate;
	}

	public void setLoginFailDate(long loginFailDate) {
		this.loginFailDate = loginFailDate;
	}

	public int getLoginFailNum() {
		return loginFailNum;
	}

	public void setLoginFailNum(int loginFailNum) {
		this.loginFailNum = loginFailNum;
	}
	public String getSysImgCode() {
		return sysImgCode;
	}

	public void setSysImgCode(String sysImgCode) {
		this.sysImgCode = sysImgCode;
	}

	public String getUserImgCode() {
		return userImgCode;
	}

	public void setUserImgCode(String userImgCode) {
		this.userImgCode = userImgCode;
	}
	
	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Date getUserDate() {
		return userDate;
	}

	public void setUserDate(Date userDate) {
		this.userDate = userDate;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	
	
	private Integer start;
	
	private Integer end;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	
	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", userPassword=" + userPassword + ", userDate="
				+ userDate + ", userImg=" + userImg + ", ids=" + ids + ", userImgCode=" + userImgCode + ", sysImgCode="
				+ sysImgCode + ", roleID=" + roleID + ", loginFailNum=" + loginFailNum + ", loginFailDate="
				+ loginFailDate + ", md5=" + md5 + ", start=" + start + ", end=" + end + "]";
	}
	


	
	
}
