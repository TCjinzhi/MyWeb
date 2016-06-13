package com.app.webservice;

import javax.jws.WebService;

@WebService
public class WebServiceImpl implements WebServiceI {

	@Override
	public String sayHello(String username) {
		System.out.println("Say hello to" + username);
		return "Helle," + username;
	}

}
