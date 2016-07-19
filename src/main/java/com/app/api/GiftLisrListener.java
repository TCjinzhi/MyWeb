package com.app.api;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class GiftLisrListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("---Thread start---");
		Thread thread = new Thread(new GiftListThread());
		thread.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
