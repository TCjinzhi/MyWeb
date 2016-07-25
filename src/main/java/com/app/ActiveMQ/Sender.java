package com.app.ActiveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

	private static String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final int SENDNUM = 10; 
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;
		
		Connection connection = null;
		
		Session session;
		
		Destination destination;
		
		MessageProducer messageProducer;
		
		connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKERURL);
		
		
		try {
			connection = connectionFactory.createConnection();
			
			connection.start();
			
			session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
			
			destination = session.createQueue("FirstQueue");
			
			messageProducer = session.createProducer(destination);
			
			sendMessage(session, messageProducer);
			
			session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(connection != null){
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void sendMessage(Session session,MessageProducer messageProducer) throws Exception{
		for(int i=0; i < SENDNUM; i++){
			TextMessage textMessage = session.createTextMessage("ActiveMQ Sender 发送消息: "+i);
			System.out.println("ActiveMQ Sender 发送消息: "+i);
			messageProducer.send(textMessage);
		}
	}
	
}
