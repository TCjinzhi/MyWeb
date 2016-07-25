package com.app.ActiveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Received {

	private static String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;
		
		Connection connection = null;
		
		Session session;
		
		Destination destination;
		
		MessageConsumer messageConsumer;
		
		connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKERURL);
		
		try {
			connection = connectionFactory.createConnection();
			
			connection.start();
			
			session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
			
			destination = session.createQueue("FirstQueue");
			
			messageConsumer = session.createConsumer(destination);
			
			while(true){
				TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
				if(textMessage != null){
					System.out.println("收到的消息："+ textMessage.getText());
				}else {
					break;
				}
			}
			
		} catch (JMSException e) {
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
	
	
}
