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

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.yesbulo.cardatacolsystem.impl.ObjectDaoImpl;
import com.yesbulo.cardatacolsystem.pojo.Users;
import com.yesbulo.cardatacolsystem.server.ObjectDao;
import com.yesbulo.cardatacolsystem.tools.MailSend;

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
	
	// 前台传入
	private String useName;
	private String usePwd;
	private String usePhone;
	private String useEmail;
	private String phoneCode;
	private String useImage;
	private String newUsepassword;

	// 反馈到前台
	private String code;
	public JSONObject json = new JSONObject();

	private static ObjectDao objectDao = new ObjectDaoImpl();

	/** 获取Dao */
	public ObjectDao giveDao() {
		if (objectDao == null)
			objectDao = new ObjectDaoImpl();
		return objectDao;
	}

	
	// #用户登录
	public String login() {
		List<?> list1 = giveDao().getObjectListByfield("Users", "user_email",
				"'"+useEmail+"'");
		System.out.println(list1.size());
		System.out.println(useEmail);
		if (list1.size() > 0) {
			List<?> list = giveDao().check4List("Users", "'"+useEmail+"'", "'"+usePwd+"'");
			if (list.size() > 0) {
				Users user = (Users) list.get(0);
				ServletActionContext.getRequest().getSession().setAttribute("Users",
						user);// 将登陆用户保存到session
				System.out.println("++"+user.toString());
				setCode("1");// 登录成功
			} else {
				setCode("0");// 登录失败
			}
		}else {
			setCode("4");//用户不存在
		}
		return "success";
	}

	// #用户注册
	public String activate() {
			Object object = ServletActionContext.getRequest().getSession()
					.getAttribute("email_yzm");
			String sysPhoneCode = object != null ? (String) object : null;
			System.out.println("phoneCode"+phoneCode+"sysPhoneCode"+sysPhoneCode);
			if (phoneCode.equals(sysPhoneCode)) {
				Users user = new Users();
				user.setUserName(useName);
				user.setUserEmail(useEmail);
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
	
	// 用户验证邮件发送
	public String sendEmailSet() {
		if(useEmail != null && usePwd != null){
			
			List<?> list = giveDao().getObjectListByfield("Users", "user_email",
					"'"+useEmail+"'");
			Users user = list.size() > 0 ? (Users) list.get(0) : null;
			if (user!=null) {
				setCode("2");//邮箱已注册
				return "success";
			}
			
			Random random = new Random();
			String code = "";
			for (int i = 0; i < 6; i++) {
				code += random.nextInt(10);
			}
			ServletActionContext.getRequest().getSession()
					.setAttribute("email_yzm", code);
				String message = useEmail
						+ "您好，您正在进行注册验证操作，您的验证码是<a>"+code+"</a>,请您尽快验证！！！";
				if (MailSend.SendMail("smtp.qq.com", "587", useEmail,
						"252254002@qq.com", "机车APP验证码", message)){
					message = "OK，已发送邮件，请注意查收";
					setCode("5");// 发送成功
					System.out.println("发送成功");
					System.out.println("Email:"+useEmail+"Pwd:"+usePwd+"Code:"+code);}
				else {
					setCode("6");// 发送失败
					message = "ERROR，发送邮件失败";}
			}else {
			Random random = new Random();
			String code = "";
			for (int i = 0; i < 6; i++) {
				code += random.nextInt(10);
			}
			ServletActionContext.getRequest().getSession()
					.setAttribute("email_yzm", code);
			List<?> list = giveDao().getObjectListByfield("Users", "user_email",
					"'"+useEmail+"'");
			Users user = list.size() > 0 ? (Users) list.get(0) : null;
			if (user != null) {
				String message = useEmail + "您好，您正在进行找回密码操作，您的验证码是<a>" + code
						+ "</a>,请您尽快验证！！！";
				if (MailSend.SendMail("smtp.qq.com", "587", useEmail,
						"252254002@qq.com", "机车APP验证码", message)) {
					message = "OK，已发送邮件，请注意查收";
					setCode("5");// 发送成功
					System.out.println("发送找回密码验证码成功");
					System.out.println("Email:" + useEmail + "Pwd:" + usePwd
							+ "Code:" + code);
				} else {
					setCode("6");// 发送失败
					message = "ERROR，发送邮件失败";
				}
		    }else {
				setCode("2");
			}
		}
		return "success";
	}
	
	// 发送离线包
	public String sendMailForDownload() {
		String message = useEmail + "您好，您正在进行找回密码操作，您的验证码是<a>" + code
				+ "</a>,请您尽快验证！！！";
		if (MailSend.SendMail("smtp.qq.com", "587", useEmail,
				"252254002@qq.com", "机车APP验证码", message)) {
			message = "OK，已发送邮件，请注意查收";
			setCode("5");// 发送成功
			System.out.println("发送找回密码验证码成功");
			System.out.println("Email:" + useEmail + "Pwd:" + usePwd + "Code:"
					+ code);
		} else {
			setCode("6");// 发送失败
			message = "ERROR，发送邮件失败";
		}
		return "success";
	}
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
		System.out.println("UserAction.checkPhoneNum()");
		boolean flag = false;
		Object object = ServletActionContext.getRequest().getSession()
				.getAttribute("email_yzm");
		String sysPhoneCode = object != null ? (String) object : null;
		System.out.println("phoneCode" + phoneCode + "sysPhoneCode"
				+ sysPhoneCode);
		if (phoneCode.equals(sysPhoneCode)) {
			List<?> list = giveDao().getObjectListByfield("Users", "user_email",
					"'"+useEmail+"'");
			System.out.println(list.size());
			System.out.println(useEmail);
			Users user = null;
			if (list.size() > 0) {
					user = (Users) list.get(0);
					ServletActionContext.getRequest().getSession().setAttribute("Users",
							user);// 将登陆用户保存到session
			}
			if (user != null) {
				System.out.println("验证成功");
				setCode("1");// 手机验证码验证成功
			}
		}else {
			setCode("0");
			System.out.println("验证失败");
		}
			
			
		
		return "success";
	}
	
	//用户头像修改
	public String giveCurrentUser() {
		System.out.println("UserAction.giveCurrentUser()");
		Object object = ServletActionContext.getRequest().getSession()
				.getAttribute("Users");
		Users user = object != null ? (Users) object : null;
		System.out.println(user.toString());
		json.put("User", user);
		return "success";
	}
	
	// #用户信息修改
	public String updateInfo() {
		Object object = ServletActionContext.getRequest().getSession()
				.getAttribute("Users");// 将登陆用户取出
		Users user = object != null ? (Users) object : null;

		user.setUserImg(useImage.substring(useImage.lastIndexOf("/") + 1));
		user.setUserName(useName);
		user.setUserPwd(usePwd);

		try {
			giveDao().update(user);
			setCode("1");
		} catch (Exception e) {
			setCode("0");
		}

		return "success";
	}

	// 用户修改密码
	public String updatePass() {

		Object object = ServletActionContext.getRequest().getSession()
				.getAttribute("Users");// 将登陆用户取出
		Users user = object != null ? (Users) object : null;

		if (user != null && user.getUserPwd().equals(usePwd)) {
			user.setUserPwd(newUsepassword);
			giveDao().update(user);
			setCode("1");// 修改密码成功
		} else
			setCode("3");// 原密码不正确

		return "success";
	}
	
	public static void main(String[] args) {
//		MailSenderInfo mailInfo = new MailSenderInfo();    
//	     mailInfo.setMailServerHost("smtp.163.com");    
//	     mailInfo.setMailServerPort("25");    
//	     mailInfo.setValidate(true);    
//	     mailInfo.setUserName("15775692243@163.com");    
//	     mailInfo.setPassword("zhuting!5331218?");//您的邮箱密码    
//	     mailInfo.setFromAddress("15775692243@163.com");    
//	     mailInfo.setToAddress("252254002@qq.com");    
//	     mailInfo.setSubject("设置邮标题 ");    
//	     mailInfo.setContent("设置邮箱内容 ");    
//	        //这个类主要来发送邮件   
//	     SimpleMailSender sms = new SimpleMailSender();   
//	         sms.sendHtmlMail(mailInfo);//发送html格式  
		List<?> list1 = new ObjectDaoImpl().getObjectListByfield("Users", "userEmail",
				"'362929422@qq.com'");
		System.out.println(list1.size());
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

	public String getUseEmail() {
		return useEmail;
	}

	public void setUseEmail(String useEmail) {
		this.useEmail = useEmail;
	}
	
	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getUseImage() {
		return useImage;
	}

	public void setUseImage(String useImage) {
		this.useImage = useImage;
	}

	public String getNewUsepassword() {
		return newUsepassword;
	}

	public void setNewUsepassword(String newUsepassword) {
		this.newUsepassword = newUsepassword;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	

}
