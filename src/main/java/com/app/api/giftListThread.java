package com.app.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.app.pojo.APIGift;
import com.app.pojo.APIResponse;

public class giftListThread implements Runnable {

	@Override
	public void run() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String url = "jdbc:mysql://localhost/study";
		String user = "root";
		String password = "123456";
		String sql = "insert into gifts (id,gift_name,gift_point,gift_image) values (?,?,?,?);";
		
		PreparedStatement checkps = null;
		String checksql = "select * from gifts where id = ?";
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			preparedStatement = connection.prepareStatement(sql);
			
			checkps = connection.prepareStatement(checksql);
			
			APIResponse result = getGiftLists();
			
			for(APIGift data : result.getData()){
				preparedStatement.setString(1, data.getId());
				preparedStatement.setString(2, data.getGift_name());
				preparedStatement.setString(3, data.getGift_point());
				preparedStatement.setString(4, data.getGift_image());
				
				checkps.setString(1, data.getId());
				
				if(checkps.executeQuery().next()){
					continue;
				}
					preparedStatement.execute();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				checkps.close();
				connection.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		try {
			Thread.sleep(1000*60*60*8);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static APIResponse getGiftLists(){
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
//		        System.out.println(EntityUtils.toString(response.getEntity()));
		        String string = EntityUtils.toString(response.getEntity());
//		        JSONObject obj = JSONObject.fromObject(string);
		        APIResponse apiResponse = JSON.parseObject(string,APIResponse.class);
		        System.out.println("apiResponse:"+apiResponse.getData().size());
		        System.out.println(JSON.toJSONString(apiResponse, true));
//		        System.out.println(obj.get("data").toString());
		        return apiResponse;
		        
		      }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		return null;
	    
	}
	

}
