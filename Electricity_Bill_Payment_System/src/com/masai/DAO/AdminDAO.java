package com.masai.DAO;

import com.masai.Exception.WrongCredentials;

public class AdminDAO implements AdminInterface {

	@Override
	public boolean adminLogin(String name, String password) throws WrongCredentials {
		// TODO Auto-generated method stub
		
		if(name.equals("admin") && password.equals("admin")) return true;
		
		else {
			throw new WrongCredentials("Wrong Credentials provided by admin");
		}
	} 
 
	
}
