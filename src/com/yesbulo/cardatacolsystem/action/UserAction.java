package com.yesbulo.cardatacolsystem.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.mail.Flags.Flag;


import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.yesbulo.cardatacolsystem.impl.ObjectDaoImpl;
import com.yesbulo.cardatacolsystem.pojo.Users;
import com.yesbulo.cardatacolsystem.server.ObjectDao;
import com.yesbulo.cardatacolsystem.tools.PhoneCodeTools;

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
	private String phoneCode;
	// 前台传入

	

	// 反馈到前台

	public String getPhoneCode() {
		return phoneCode;
	}


	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}


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
		List<?> list1 = giveDao().getObjectListByfield("Users", "user_phone",
				usePhone);
		System.out.println(usePhone);
		System.out.println("1");
		if (list1.size() > 0) {
			System.out.println("2");
			List<?> list = giveDao().check4List("Users", usePhone, usePwd);
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
			Object object = ServletActionContext.getRequest().getSession()
					.getAttribute("phone_yzm");
			String sysPhoneCode = object != null ? (String) object : null;
			System.out.println("phoneCode"+phoneCode+"sysPhoneCode"+sysPhoneCode);
			if (phoneCode.equals(sysPhoneCode)) {
				Users user = new Users();
				user.setUserName(useName);
				user.setUserPhone(usePhone);
				user.setUserPwd(usePwd);
				user.setUserKey("0");
				Date date = new Date();
				user.setUpdateTime(date);
				user.setInsertTime(date);
				giveDao().save(user);
				System.out.println(user.toString());
				setCode("1");// 注册成功
			} else {
				setCode("7");// 手机验证码验证不成功
			}

		return "success";
	}
	
//	// #用户忘记密码
//	public String forgetPass() {
//		sendPhoneCode();
//		return "success";
//	}
	
	// 用户验证短信发送
	public String sendPhone() {
		if(useName != null && usePwd != null){
			Random random = new Random();
			String code = "";
			for (int i = 0; i < 6; i++) {
				code += random.nextInt(10);
			}
			ServletActionContext.getRequest().getSession()
					.setAttribute("phone_yzm", "444444");
			//if (PhoneCodeTools.send(usePhone, code)) {
				setCode("5");// 发送成功
				System.out.println("发送成功");
				System.out.println(useName+usePwd);
			//} else
			//	setCode("6");// 发送失败
		}else {
			Random random = new Random();
			String code = "";
			for (int i = 0; i < 6; i++) {
				code += random.nextInt(10);
			}
			ServletActionContext.getRequest().getSession()
					.setAttribute("phone_yzm", "444444");
			List<?> list = giveDao().getObjectListByfield("Users", "user_phone",
					usePhone);
			Users user = list.size() > 0 ? (Users) list.get(0) : null;
			if (user != null) {
//				if (PhoneCodeTools.send(usePhone, code))
					setCode("5");// 发送成功
					System.out.println("忘记密码验证码发送成功!");
//				else
//					setCode("6");// 发送失败
		    }
		}
		return "success";
	}
	
	// #用户找回密码验证码验证
	public String checkPhoneNum() {

		boolean flag = false;
		Object object = ServletActionContext.getRequest().getSession()
				.getAttribute("phone_yzm");
		String sysPhoneCode = object != null ? (String) object : null;
		System.out.println("phoneCode" + phoneCode + "sysPhoneCode"
				+ sysPhoneCode);
		if (phoneCode.equals(sysPhoneCode)) {
			
			System.out.println("验证成功");
			setCode("1");// 注册成功
		} else {
			setCode("7");// 手机验证码验证不成功
			System.out.println("验证不成功");
		}
		
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
