package com.yesbulo.cardatacolsystem.pojo;

/**
 * <p>@Title:SystemSet</P>
 * <p>@Description:carDataColSystem</P>
 * <p>@Company: RongleXie </P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-10 上午11:35:34
 */
public class SystemSet {
	private int systemId;//系统设置ID
	private String systemPhone;	//手机号
	private String systemHour;//间隔-小时
	private String systemMinute;//间隔-分钟
	private String systemAutocolFlg;//自动采集标识
	private String userKey;//备用Key
	private String insertTime;//插入时间
	private String updateTime;//更新时间
	public int getSystemId() {
		return systemId;
	}
	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}
	public String getSystemPhone() {
		return systemPhone;
	}
	public void setSystemPhone(String systemPhone) {
		this.systemPhone = systemPhone;
	}
	public String getSystemHour() {
		return systemHour;
	}
	public void setSystemHour(String systemHour) {
		this.systemHour = systemHour;
	}
	public String getSystemMinute() {
		return systemMinute;
	}
	public void setSystemMinute(String systemMinute) {
		this.systemMinute = systemMinute;
	}
	public String getSystemAutocolFlg() {
		return systemAutocolFlg;
	}
	public void setSystemAutocolFlg(String systemAutocolFlg) {
		this.systemAutocolFlg = systemAutocolFlg;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
	
	
	
}
	