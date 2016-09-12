package com.yesbulo.cardatacolsystem.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;




import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.yesbulo.cardatacolsystem.impl.ObjectDaoImpl;
import com.yesbulo.cardatacolsystem.pojo.Users;
import com.yesbulo.cardatacolsystem.server.ObjectDao;

/**
 * <p>@Title:UserAction</P>
 * <p>@Description:carDataColSystem</P>
 * <p>@Company: RongleXie </P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-2 下午03:07:31
 */
public class UserAction {

	// Fields

	private Integer useId;
	
	private String useName;
	private String usePwd;
	private String usePhone;
	
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

	
	// #用户登录
	public String login() {
		List<?> list1 = giveDao().getObjectListByfield("Users", "user_name",
				useName);
		System.out.println(useName);
		System.out.println("1");
		if (list1.size() > 0) {
			System.out.println("2");
			List<?> list = giveDao().check4List("Users", useName, usePwd);
			if (list.size() > 0) {
				System.out.println("3");
				Users user = (Users) list.get(0);
						setCode("1");// 登录成功
			} else {
				setCode("0");// 登录失败
			}
		}
		
		return "success";
	}

	// #用户注册
	public String activate() {

		// TODO 查询已激活学生人数,内测限定100(投入时取消)
		int size = giveDao().getObjectSizeBycond(
				"select count(*) from Users where useIscompany==0");
		if (size > 100) {
			setCode("20");
			return "success";
		}

		//List<?> list = giveDao().getObjectListByfieldInActivate("Users",
			//	"user_name", useName);
//		Users user = list.size() > 0 ? (Users) list.get(0) : null;
		
		Users user = new Users();
		user.setUserName(useName);
		user.setUserPhone(usePhone);
		user.setUserPwd(usePwd);
		
//		if (user != null && user.getUseId() > 0) {

//			Object object = ServletActionContext.getRequest().getSession()
//					.getAttribute("phone_yzm");
//			String sysPhoneCode = object != null ? (String) object : null;
//			if (phoneCode.equals(sysPhoneCode)) {
//				user.setUseIscompany(0);
//				user.setUsePhone(usePhone);
//				user.setUseEmei(useEmei);
				giveDao().save(user);
				System.out.println(user.toString());
				setCode("1");// 注册成功
//			} else
//				setCode("7");// 手机验证码验证不成功
//		} else
//			setCode("4");// 考号/学号不存在

		return "success";
	}

	

	




	public void setUseId(Integer useId) {
		this.useId = useId;
	}

	

	public void setUsePwd(String usePwd) {
		this.usePwd = usePwd;
	}

	

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}


	

	public void setUseName(String useName) {
		this.useName = useName;
	}


	public String getUsePhone() {
		return usePhone;
	}


	public void setUsePhone(String usePhone) {
		this.usePhone = usePhone;
	}

	

}
