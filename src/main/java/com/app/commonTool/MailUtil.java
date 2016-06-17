package com.app.commonTool;

import java.util.Date;
import java.util.Properties;

import javax.json.Json;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.app.pojo.Mail;
import com.mysql.jdbc.Util;

public class MailUtil {

	protected final Logger logger = Logger.getLogger(getClass());

//	public boolean send(Mail mail) {
//		// 发送email
//		HtmlEmail email = new HtmlEmail();
//		try {
//			// 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
//			email.setHostName(mail.getHost());
//			// 字符编码集的设置
//			email.setCharset(Mail.ENCODEING);
//			// 收件人的邮箱
//			email.addTo(mail.getReceiver());
//			// 发送人的邮箱
//			email.setFrom(mail.getSender(), mail.getName());
//			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
//			email.setAuthentication(mail.getUsername(), mail.getPassword());
//			// 要发送的邮件主题
//			email.setSubject(mail.getSubject());
//			// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
//			email.setMsg(mail.getMessage());
//			// 发送
//			email.send();
//			if (logger.isDebugEnabled()) {
//				logger.debug(mail.getSender() + " 发送邮件到 " + mail.getReceiver());
//			}
//			return true;
//		} catch (EmailException e) {
//			e.printStackTrace();
//			logger.info(mail.getSender() + " 发送邮件到 " + mail.getReceiver()
//					+ " 失败");
//			return false;
//		}
//	}

	 public static void sendMail(String fromMail, String user, String password,
             String toMail,
             String mailTitle,
             String mailContent) throws Exception {
		Properties props = new Properties(); //可以加载一个配置文件
		// 使用smtp：简单邮件传输协议
		props.put("mail.smtp.host", "mail.sinopharm.com");//存储发送邮件服务器的信息
		props.put("mail.smtp.auth", "true");//同时通过验证
		
		Session session = Session.getInstance(props);//根据属性新建一个邮件会话
		//session.setDebug(true); //有他会打印一些调试信息。
		
		MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
		message.setFrom(new InternetAddress(fromMail));//设置发件人的地址
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));//设置收件人,并设置其接收类型为TO
		message.setSubject(mailTitle);//设置标题
		//设置信件内容
		//message.setText(mailContent); //发送 纯文本 邮件 todo
		message.setContent(mailContent, "text/html;charset=utf-8"); //发送HTML邮件，内容样式比较丰富
		message.setSentDate(new Date());//设置发信时间
		message.saveChanges();//存储邮件信息
		
		//发送邮件
		Transport transport = session.getTransport("smtp");
		//Transport transport = session.getTransport();
		transport.connect(user, password);
		transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
		transport.close();
	 }
	 
	 public static void sendMail(Mail mail) throws Exception {
		 System.out.println("util:"+JSON.toJSONString(mail));
		Properties props = new Properties(); //可以加载一个配置文件
		// 使用smtp：简单邮件传输协议
		props.put("mail.smtp.host", "mail.sinopharm.com");//存储发送邮件服务器的信息
		props.put("mail.smtp.auth", "true");//同时通过验证
		
		Session session = Session.getInstance(props);//根据属性新建一个邮件会话
		//session.setDebug(true); //有他会打印一些调试信息。
		
		MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
		message.setFrom(new InternetAddress(mail.getFromMail()));//设置发件人的地址
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getToMail()));//设置收件人,并设置其接收类型为TO
		message.setSubject(mail.getMailTitle());//设置标题
		//设置信件内容
		//message.setText(mailContent); //发送 纯文本 邮件 todo
		message.setContent(mail.getMailContent(), "text/html;charset=utf-8"); //发送HTML邮件，内容样式比较丰富
		message.setSentDate(new Date());//设置发信时间
		message.saveChanges();//存储邮件信息
		
		//发送邮件
		Transport transport = session.getTransport("smtp");
		//Transport transport = session.getTransport();
		transport.connect(mail.getUser(), mail.getPassword());
		transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
		transport.close();
	 }
	 
}
