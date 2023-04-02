package com.masai.DAO;

import java.util.List;

import com.masai.DTO.BillBinClass;
import com.masai.DTO.ConsumerBinClass;
import com.masai.DTO.TransactionBinClass;
import com.masai.Exception.EmptySet;
import com.masai.Exception.InputMisMatch;
import com.masai.Exception.NoBillFound;
import com.masai.Exception.WrongCredentials;

public interface ConsumerInterface {

	public void signUpConsumer(ConsumerBinClass consumer) throws InputMisMatch;
	
	public ConsumerBinClass loginConsumer(String userName,String password) throws WrongCredentials;
	
	public List<BillBinClass> displayBillofConsumer(int cid) throws EmptySet;
	
	public double billAmount(int billNo) throws NoBillFound;
	
	public void updateStatus(int billNo) throws NoBillFound;
	
	public void updateTransection(int cId, double amount) throws InputMisMatch;
	
	public List<TransactionBinClass> transactionHistory(int cid) throws EmptySet;
}
