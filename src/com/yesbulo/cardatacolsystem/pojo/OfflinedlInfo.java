package com.yesbulo.cardatacolsystem.pojo;

import java.util.Date;


/**
 * 邮件实体类
 * <p>@Title:OfflinedlInfo</P>
 * <p>@Description:carDataColSystem</P>
 * <p>@Company: RongleXie </P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-24 下午05:59:17</p>
 */
public class OfflinedlInfo {
	private Integer downId;//用户ID
	private String userPhone;//用户手机号
	private String userEmail;//用户电子邮件
	private String requestEmail;//下载邮箱
	private String downloadFlg;//下载
	private String userKey;//备用Key
	private Date requestTime;//更新时间
	private Date insertTime;//插入时间
	private Date updateTime;//修改时间
	
	public Integer getDownId() {
		return downId;
	}
	public void setDownId(Integer downId) {
		this.downId = downId;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getRequestEmail() {
		return requestEmail;
	}
	public void setRequestEmail(String requestEmail) {
		this.requestEmail = requestEmail;
	}
	public String getDownloadFlg() {
		return downloadFlg;
	}
	public void setDownloadFlg(String downloadFlg) {
		this.downloadFlg = downloadFlg;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
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
		return "OfflinedlInfo [downId=" + downId + ", downloadFlg="
				+ downloadFlg + ", insertTime=" + insertTime
				+ ", requestEmail=" + requestEmail + ", requestTime="
				+ requestTime + ", updateTime=" + updateTime + ", userEmail="
				+ userEmail + ", userKey=" + userKey + ", userPhone="
				+ userPhone + "]";
	}
	
	
	
	
	
	
}
