package com.yesbulo.cardatacolsystem.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;

import com.yesbulo.cardatacolsystem.impl.ObjectDaoImpl;
import com.yesbulo.cardatacolsystem.pojo.OfflinedlInfo;
import com.yesbulo.cardatacolsystem.pojo.Users;
import com.yesbulo.cardatacolsystem.server.ObjectDao;

/**
 * <p>@Title:OfflinedlInfoAction</P>
 * <p>@Description:carDataColSystem</P>
 * <p>@Company: RongleXie </P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-24 下午02:31:43
 */
public class OfflinedlInfoAction {
	

	private String useEmail;
	// 前台传入

	
	// 反馈到前台
	private String code;
	
	public static JSONObject json = new JSONObject();
	private static ObjectDao objectDao = new ObjectDaoImpl();

	/** 获取Dao */
	public ObjectDao giveDao() {
		if (objectDao == null)
			objectDao = new ObjectDaoImpl();
		return objectDao;
	}	
	
	
	// #用户下载
	public String download(){		
		Object object = ServletActionContext.getRequest().getSession()
		.getAttribute("Users");
		Users user = object != null ? (Users) object : null;
		OfflinedlInfo offlinedlinfo = new OfflinedlInfo();
		Date date = new Date();
		offlinedlinfo.setUserPhone("000");
		offlinedlinfo.setInsertTime(date);
		offlinedlinfo.setUpdateTime(date);
		offlinedlinfo.setRequestTime(date);
		offlinedlinfo.setDownloadFlg("0");
		offlinedlinfo.setUserEmail(user.getUserEmail());
		offlinedlinfo.setRequestEmail(useEmail);
		giveDao().save(offlinedlinfo);
		System.out.println(offlinedlinfo.toString());
		setCode("1");// 发送成功
		return "success";
	}

	
	public String getUseEmail() {
		return useEmail;
	}
	public void setUseEmail(String useEmail) {
		this.useEmail = useEmail;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
}
