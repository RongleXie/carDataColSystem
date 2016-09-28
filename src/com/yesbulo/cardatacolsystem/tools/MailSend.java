package com.yesbulo.cardatacolsystem.tools;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class MailSend {

	private MailSend() {
	}

	// 默认配置
	private static String host = "smtp.qq.com";// 邮箱类型-qq邮箱 ，163则是smtp.163.com
	private static String port = "587";// 端口465或587，经测试，587可以成功发送，465不能
	private static String to = "362929422@qq.com";// 接受者
	private static String from = "252254002@qq.com";// 发送者
	private static String subject = "普通邮件";// 主题
	private static String message = "text";// 正文

	// 用户自定义配置
	public static boolean SendMail(String host1, String port1, String to1,
			String from1, String subject1, String message1) {
		host = host1;
		port = port1;
		to = to1;
		from = from1;
		subject = subject1;
		message = message1;
		return SendMail();
	}

	// 邮件发送
	public static boolean SendMail() {

		boolean sessionDebug = false;
		Properties props = new Properties();
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		// 系统需要的信息
		Authenticator authenticator = new Email_Authenticator(from,
				"xgjgdvsbzlfbbhfc"); // 身份：用户名-密码（QQ则是授权码）
		Session session = Session.getDefaultInstance(props, authenticator);
		// 一次对话，一个session ，这个session 要获取固定的发送邮件信息
		session.setDebug(sessionDebug);
		try {
			MimeMessage meg = new MimeMessage(session);// 生成消息实例
			meg.setFrom(new InternetAddress(from));// 指定发件人
			InternetAddress[] address = { new InternetAddress(to) };// 生成收件人地址数组
			meg.setRecipients(Message.RecipientType.TO, address);// 指定收件人数组
			meg.setSubject(subject);// 指定主题
			meg.setText(message);// 指定正文
			meg.setSentDate(new Date());// 指定发送时间
			meg.saveChanges();// 保存信息
			Transport.send(meg);
			return true;
		} catch (Exception ex) {
			return false;
		}

	}
	public static void main(String[] args) {
		MailSend mailSend = new MailSend();
		boolean flag = mailSend.SendMail();
		System.out.println("发送是否成功"+flag);
	}

}

class Email_Authenticator extends Authenticator {
	String userName = null;
	String password = null;

	public Email_Authenticator() {
	}

	public Email_Authenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
}
