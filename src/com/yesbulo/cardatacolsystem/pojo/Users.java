package com.yesbulo.cardatacolsystem.pojo;

import java.util.Date;

/**
 * <p>@Title:Users</P>
 * <p>@Description:carDataColSystem</P>
 * <p>@Company: RongleXie </P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-2 上午11:36:06
 */
public class Users {
	private Integer userId;//用户ID
	private String userName;//用户名
	private String userPwd;//用户密码
	private String userEmail;//用户邮箱
	private String userPhone;//用户手机号
	private String userImg;//用户头像
	private Double totalpoints;//积分总计
	private Double validpoints;//有效积分
	private String userKey;//备用Key
	private Date insertTime;//插入时间
	private Date updateTime;//修改时间
	
	public Users() {
		// TODO Auto-generated constructor stub
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public Double getTotalpoints() {
		return totalpoints;
	}

	public void setTotalpoints(Double totalpoints) {
		this.totalpoints = totalpoints;
	}

	public Double getValidpoints() {
		return validpoints;
	}

	public void setValidpoints(Double validpoints) {
		this.validpoints = validpoints;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Users [insertTime=" + insertTime + ", totalpoints="
				+ totalpoints + ", updateTime=" + updateTime + ", userEmail="
				+ userEmail + ", userId=" + userId + ", userImg=" + userImg
				+ ", userKey=" + userKey + ", userName=" + userName
				+ ", userPhone=" + userPhone + ", userPwd=" + userPwd
				+ ", validpoints=" + validpoints + "]";
	}

	
	
	
	

	
	
	
}
