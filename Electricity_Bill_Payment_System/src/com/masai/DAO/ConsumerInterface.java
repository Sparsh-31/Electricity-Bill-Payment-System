package com.masai.DAO;

import com.masai.DTO.ConsumerBinClass;
import com.masai.Exception.InputMisMatch;
import com.masai.Exception.WrongCredentials;

public interface ConsumerInterface {

	public void signUpConsumer(ConsumerBinClass consumer) throws InputMisMatch;
	
	public boolean loginConsumer(String userName,String password) throws WrongCredentials;
	
}
