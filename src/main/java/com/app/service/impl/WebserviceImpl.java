package com.app.service.impl;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.stereotype.Service;

import com.app.service.IWebService;

@Service
@WebService(endpointInterface="com.app.service.IWebService",serviceName="webservice1")
@SOAPBinding(style = Style.RPC)
public class WebserviceImpl implements IWebService {

	@Override
	public String sayHello(String string) {
		return "hello "+string;
	}

}
