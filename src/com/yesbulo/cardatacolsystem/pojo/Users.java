package com.yesbulo.cardatacolsystem.pojo;

/**
 * <p>@Title:Users</P>
 * <p>@Description:carDataColSystem</P>
 * <p>@Company: RongleXie </P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-2 上午11:36:06
 */
public class Users {
	private int useId;
	private String useName;
	private String usePwd;
	
	public Users() {
		// TODO Auto-generated constructor stub
	}
	
	public int getUseId() {
		return useId;
	}
	public void setUseId(int useId) {
		this.useId = useId;
	}
	public String getUseName() {
		return useName;
	}
	public void setUseName(String useName) {
		this.useName = useName;
	}
	public String getUsePwd() {
		return usePwd;
	}
	public void setUsePwd(String usePwd) {
		this.usePwd = usePwd;
	}

	@Override
	public String toString() {
		return "Users [useId=" + useId + ", useName=" + useName + ", usePwd="
				+ usePwd + ", getUseId()=" + getUseId() + ", getUseName()="
				+ getUseName() + ", getUsePwd()=" + getUsePwd() + "]";
	}

	public Users(int useId, String useName, String usePwd) {
		super();
		this.useId = useId;
		this.useName = useName;
		this.usePwd = usePwd;
	}
	
	
}
