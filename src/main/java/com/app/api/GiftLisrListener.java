package com.app.api;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class GiftLisrListener implements ServletContextListener {

	private GiftListThread giftListThread;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if(giftListThread == null){
			Thread thread = new Thread(giftListThread);
			thread.start();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
