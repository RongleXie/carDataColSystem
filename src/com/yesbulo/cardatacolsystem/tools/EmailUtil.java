//package com.yesbulo.cardatacolsystem.tools;
//
//import java.util.Properties;
//
//import javax.mail.internet.MimeMessage;
//
//import org.apache.cxf.common.util.PropertiesLoaderUtils;
//import org.apache.log4j.Logger;
//import org.drools.io.impl.ClassPathResource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Component
//
//public class EmailUtil 
//{
//	
//	
//	private static final Logger log = Logger.getLogger(EmailUtil.class);
//	 @Value("${mail.from}") 
//	  String emailFrom
////	  ;
//	  ="发送者的@163.com"; 
//	 @Value("${mail.host}") 
//	  String emailHost
////	  ;
//	  ="smtp.163.com"; 
//	 @Value("${mail.port}") 
//	  String emailPost
////	  ;
//	  ="25"; 
//	 @Value("${mail.username}") 
//	  String emailUser
////	  ;
//	  ="loyoikaoqin"; 
//	 @Value("${mail.password}") 
//	  String emailPassword
////	  ;
//	  ="发送者的密码"; 
//	 @Value("${jdbc0.username}") 
//	  String ww;
////	  private JavaMailSender javaMailSender;
////	  private SimpleMailMessage simpleMailMessage;
//	  public  void sendemail(String text,String topic){
//	  try{
//		  JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
//		   
//		  Properties props = new Properties();
////		  
////          props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("configuration/platform.properties"));
////          String  driverClass= (String) props.get("mail.password");
////		  props.load(new FileInputStream(new File("src//configuration//platform.properties")));
////		 String  sakjd=props.getProperty("mail.port");
//		  //props.put("mail.smtp.auth", "true");
////		  MailSSLSocketFactory sf = new MailSSLSocketFactory();
////			sf.setTrustAllHosts(true);
////			props.put("mail.smtp.ssl.enable", "true");
////			props.put("mail.smtp.ssl.socketFactory", sf);
//
//		  senderImpl.setHost(emailHost);
//		  senderImpl.setPort(Integer.parseInt(emailPost));
//		  
//		  senderImpl.setUsername(emailUser);
//		  senderImpl.setPassword(emailPassword);
//		  senderImpl.setJavaMailProperties(props);
//		  MimeMessage mimeMessge = senderImpl.createMimeMessage();
//		 
//		  MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessge,true,"utf-8");
////		  FileSystemResource img = new FileSystemResource(new File("I:/liang.jpg"));
//		    
//		  mimeMessageHelper.setTo("接收者@qq.com");
//		  mimeMessageHelper.setFrom(emailFrom);
//		  mimeMessageHelper.setSubject(topic);
////		  mimeMessageHelper.
//		  mimeMessageHelper.setText(text,true);  
////		  mimeMessageHelper.addAttachment(MimeUtility.encodeWord("3M样品仓库标准送货单模板.jpg"),img);   
//		   senderImpl.send(mimeMessge);
//		 }catch(Exception e){
//		  e.printStackTrace();
////		  if(e.equals(new) ){
////			  
////			  log.debug("", e);
////		  }
//		 }
//	  }
////	  /**
////	     * 
////	     * @方法名: sendMail 
////	     * @参数名：@param subject 邮件主题
////	     * @参数名：@param content 邮件主题内容
////	     * @参数名：@param to        收件人Email地址
////	     * @param isHtml  是否是html格式(发送html时使用utf-8编码)
////	     * @描述语:  发送邮件
////	     * @throws MessagingException 发送发生了异常
////	     */
////	    public void sendMail(String subject, String content,boolean isHtml, String to) throws MessagingException
////	    {
////	        
////	        
////	            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
////	          
////	            MimeMessageHelper messageHelper =null;
////	            if(isHtml)
////	            {
////	            	 messageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
////	            }
////	            else
////	            {
////	               messageHelper = new MimeMessageHelper(mimeMessage,true);
////	            }
////	            messageHelper.setFrom(simpleMailMessage.getFrom()); //设置发件人Email
////	            messageHelper.setSubject(subject); //设置邮件主题
////	            if(isHtml)
////	            {
////	            	 messageHelper.setText(content,true);   //设置邮件主题内容(html格式)
////	            }
////	            else
////	            {
////	            	 messageHelper.setText(content);   //设置邮件主题内容
////	            }
////	            
////	           
////	            messageHelper.setTo(to);          //设定收件人Email
////	          
////	            
////	            javaMailSender.send(mimeMessage);  
////	    }
//}