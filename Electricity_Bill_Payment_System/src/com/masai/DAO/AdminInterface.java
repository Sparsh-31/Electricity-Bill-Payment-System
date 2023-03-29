package com.masai.DAO;

import com.masai.Exception.WrongCredentials;

public interface AdminInterface {

	public boolean adminLogin(String name,String password) throws WrongCredentials;
	
}
