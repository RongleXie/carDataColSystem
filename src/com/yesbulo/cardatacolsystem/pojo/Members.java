package com.yesbulo.cardatacolsystem.pojo;

import java.util.Date;

/**
 * <p>@Title:Members</P>
 * <p>@Description:carDataColSystem</P>
 * <p>@Company: RongleXie </P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-10 上午11:58:15
 */
public class Members {
	private int memberId;//ID
	private int groupId;//组ID
	private String groupMember;//成员名
	private String joinReason;//加入理由
	private String memberKey;//备用Key
	private Date insertTime;//插入时间
	private Date updateTime;//更新时间
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupMember() {
		return groupMember;
	}
	public void setGroupMember(String groupMember) {
		this.groupMember = groupMember;
	}
	public String getJoinReason() {
		return joinReason;
	}
	public void setJoinReason(String joinReason) {
		this.joinReason = joinReason;
	}
	public String getMemberKey() {
		return memberKey;
	}
	public void setMemberKey(String memberKey) {
		this.memberKey = memberKey;
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
	
	
}
