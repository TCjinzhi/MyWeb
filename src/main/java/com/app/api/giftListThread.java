package com.app.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.app.pojo.APIResponse;

public class giftListThread implements Runnable {

	@Override
	public void run() {
		CloseableHttpClient client = HttpClients.createDefault();
		URI uri = null;
		try {
		      uri = new URIBuilder()
		      .setScheme("http")
		      .setHost("uep.gooddr.com")
		      .setPath("/WebService/api/gifts.json")
		      .build();
		    } catch (URISyntaxException e1) {
		      e1.printStackTrace();
		    }
		 HttpGet get = new HttpGet(uri);
		 get.setHeader("app_id", "100001");
	     get.setHeader("app_secret", "b551894f510271c0d9ef00e4831de234");
	     
	     
	     CloseableHttpResponse response;
		try {
			response = client.execute(get);
			  System.out.println(response);
		      if(response.getStatusLine().getStatusCode()==200){
		        System.out.println(EntityUtils.toString(response.getEntity()));
		        String string = EntityUtils.toString(response.getEntity());
		        APIResponse apiResponse = JSON.parseObject(string,APIResponse.class);
		        
//		        System.out.println("apiResponse:"+apiResponse);
//		        JSONObject obj = JSONObject.fromObject(string);
		        
		        
		      }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
