package com.masai.DAO;

import java.util.List;

import com.masai.DTO.BillBinClass;
import com.masai.DTO.ConsumerBinClass;
import com.masai.Exception.EmptySet;
import com.masai.Exception.InputMisMatch;
import com.masai.Exception.NoConsumerFound;
import com.masai.Exception.WrongCredentials;

public interface AdminInterface {

	public boolean adminLogin(String name,String password) throws WrongCredentials;
	
	public List<ConsumerBinClass> displayAllConsumers() throws EmptySet;
	
	public List<BillBinClass> displayBillofConsumer(int cid) throws EmptySet;
	
	public List<BillBinClass> displayAllBill() throws EmptySet;
	
	public void deleteConsumer(int cid) throws NoConsumerFound;
	
	public void generateBill(BillBinClass bill) throws InputMisMatch;
	
	public List<BillBinClass> paidAndPandigBills(int status) throws EmptySet;
	
}
