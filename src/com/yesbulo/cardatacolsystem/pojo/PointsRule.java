package com.yesbulo.cardatacolsystem.pojo;

/** 积分规则实体类
 * <p>@Title:PointsRule</P>
 * <p>@Description:carDataColSystem</P>
 * <p>@Company: RongleXie </P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-27 下午05:10:08</p>
 */
public class PointsRule {
	private int pointsRuleId;//积分规则ID
	private Double pointUp;//积分增加大小
	private Double pointDown;//积分减少大小
	public int getPointsRuleId() {
		return pointsRuleId;
	}
	public void setPointsRuleId(int pointsRuleId) {
		this.pointsRuleId = pointsRuleId;
	}
	public Double getPointUp() {
		return pointUp;
	}
	public void setPointUp(Double pointUp) {
		this.pointUp = pointUp;
	}
	public Double getPointDown() {
		return pointDown;
	}
	public void setPointDown(Double pointDown) {
		this.pointDown = pointDown;
	}
	@Override
	public String toString() {
		return "PointsRule [pointDown=" + pointDown + ", pointUp=" + pointUp
				+ ", pointsRuleId=" + pointsRuleId + "]";
	}
	
	
	
	
}
