package com.beans;

public class User {

	public boolean validate(String username, String password){
		
		if(username.equals("mj") && password.equals("1")){
			return true;
		}
		return false;
	}
	
}


