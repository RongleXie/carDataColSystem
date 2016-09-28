package com.yesbulo.cardatacolsystem.pojo;

import java.util.Date;

/**
 *  积分实体类
 * <p>@Title:Points</P>
 * <p>@Description:carDataColSystem</P>
 * <p>@Company: RongleXie </P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-27 下午05:02:09</p>
 */

public class Points {
	private int pointId;//积分ID
	private String userEmail;//用户邮箱
	private String point;//积分
	private String pointKey1;//备用Key1
	private String pointKey2;//备用Key2
	private Date insertTime;//插入时间
	private Date updateTime;//更新时间
	
	public Points() {
		// TODO Auto-generated constructor stub
	}

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getPointKey1() {
		return pointKey1;
	}

	public void setPointKey1(String pointKey1) {
		this.pointKey1 = pointKey1;
	}

	public String getPointKey2() {
		return pointKey2;
	}

	public void setPointKey2(String pointKey2) {
		this.pointKey2 = pointKey2;
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
		return "Points [insertTime=" + insertTime + ", point=" + point
				+ ", pointId=" + pointId + ", pointKey1=" + pointKey1
				+ ", pointKey2=" + pointKey2 + ", updateTime=" + updateTime
				+ ", userEmail=" + userEmail + "]";
	}
	
	
}
