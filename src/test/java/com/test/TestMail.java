package com.test;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

import com.app.commonTool.MailUtil;
import com.app.pojo.Mail;

import java.util.Date;
import java.util.Properties;

public class TestMail {
    public static void sendMail(String fromMail, String user, String password,
                                String toMail,
                                String mailTitle,
                                String mailContent) throws Exception {
        Properties props = new Properties(); //可以加载一个配置文件
        // 使用smtp：简单邮件传输协议
        props.put("mail.smtp.host", "mail.sinopharm.com");//存储发送邮件服务器的信息
        props.put("mail.smtp.auth", "true");//同时通过验证

        Session session = Session.getInstance(props);//根据属性新建一个邮件会话
//        session.setDebug(true); //有他会打印一些调试信息。

        MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
        message.setFrom(new InternetAddress(fromMail));//设置发件人的地址
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));//设置收件人,并设置其接收类型为TO
        message.setSubject(mailTitle);//设置标题
        //设置信件内容
//        message.setText(mailContent); //发送 纯文本 邮件 todo
        message.setContent(mailContent, "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富
        message.setSentDate(new Date());//设置发信时间
        message.saveChanges();//存储邮件信息

        //发送邮件
        Transport transport = session.getTransport("smtp");
//        Transport transport = session.getTransport();
        transport.connect(user, password);
        transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
        transport.close();
    }

    public static void main(String[] args) throws Exception {
        sendMail("zhanghaojie1@sinopharm.com", "zhanghaojie1", "MailPwdUpdate0511",
                "550727632@qq.com",
                "Java Mail 测试邮件",
                "<a>html 元素</a>：<b>邮件内容</b>");
        System.out.println("1111111111111");
    }
    
    @SuppressWarnings("static-access")
	@Test
    public void testmailutil(){
    	MailUtil mailUtil = new MailUtil();
    	try {
			mailUtil.sendMail("zhanghaojie1@sinopharm.com", "zhanghaojie1", "MailPwdUpdate0511",
			        "550727632@qq.com",
			        "Java Mail 测试邮件",
			        "<a>html 元素</a>：<b>This is a java test</b>");
		} catch (Exception e) {
			System.out.println("发送失败");
			e.printStackTrace();
		}
    }
    
    @Test
    public void test2(){
    	Mail mail = new Mail("zhanghaojie1@sinopharm.com", "zhanghaojie1", "MailPwdUpdate0511",
		        "550727632@qq.com",
		        "Java Mail 测试邮件",
		        "<a>html 元素</a>：<b>This is a java test</b>");
    	try {
			MailUtil.sendMail(mail);
			System.out.println("success");
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
    }
    
}