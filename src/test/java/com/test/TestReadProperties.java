package com.test;

import java.util.ResourceBundle;

public class TestReadProperties {
	
	public static void main(String[] args) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
		
		String driver = resourceBundle.getString("driver");
		
		System.out.println(driver);
	}
}
