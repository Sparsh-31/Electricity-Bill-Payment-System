package com.masai.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.masai.DTO.BillBinClass;
import com.masai.DTO.ConsumerBinClass;
import com.masai.Exception.EmptySet;
import com.masai.Exception.InputMisMatch;
import com.masai.Exception.NoConsumerFound;
import com.masai.Exception.WrongCredentials;
import com.masai.utils.DBUtils;

public class AdminDAO implements AdminInterface {

	@Override
	public boolean adminLogin(String name, String password) throws WrongCredentials {
		// TODO Auto-generated method stub
		
		if(name.equals("admin") && password.equals("admin")) return true;
		
		else {
			throw new WrongCredentials("Wrong Credentials provided by admin");
		}
	}

	@Override
	public List<ConsumerBinClass> displayAllConsumers() throws EmptySet {
		// TODO Auto-generated method stub
		Connection con = null;
		
		try {
			con = DBUtils.linkBetween();
			
			String query = "Select * from Consumer";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			if(DBUtils.checkIsEmptyOrNot(rs)) throw new EmptySet("No consumer records founded");
			else {
				List<ConsumerBinClass> list = new ArrayList<>();
				
				while(rs.next()) {
					
					list.add(new ConsumerBinClass(rs.getInt("consumer_id") ,rs.getString("first_name"), rs.getString("last_name"), rs.getString("username"), rs.getString("password"), rs.getString("address"), rs.getString("mobile_number"), rs.getString("email")));
					
				}
				return list;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new EmptySet("No consumer records founded");
		}
		finally {
			try {
				DBUtils.closeConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<BillBinClass> displayBillofConsumer(int cid) throws EmptySet {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = DBUtils.linkBetween();
			
			String query = "Select bill_no,amount,bill_from,bill_to,first_name,last_name from bill b join consumer c on b.consumer_id = c.consumer_id where c.consumer_id ="+cid;
			
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			if(DBUtils.checkIsEmptyOrNot(rs)) throw new EmptySet("No Bills allotted to this Consumer");
			else {
				List<BillBinClass> list = new ArrayList<>();
				
				while(rs.next()) {
					list.add(new BillBinClass(rs.getInt("bill_no"), rs.getDouble("amount"), rs.getDate("bill_from").toLocalDate() , rs.getDate("bill_to").toLocalDate(), ""+rs.getString("first_name")+" "+rs.getString("last_name")));
				}
				
				return list;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new EmptySet("No Bills allotted to this Consumer");
		}
		
	}

	@Override
	public List<BillBinClass> displayAllBill() throws EmptySet {
		// TODO Auto-generated method stub
		
		Connection con = null;
		try {
			con = DBUtils.linkBetween();
			
			String query = "Select bill_no,amount,bill_from,bill_to,first_name,last_name from bill b join consumer c on b.consumer_id = c.consumer_id where c.is_active = 1";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			
			if(DBUtils.checkIsEmptyOrNot(rs)) throw new EmptySet("No Bills allotted to this Consumer");
			else {
				List<BillBinClass> list = new ArrayList<>();
				
				while(rs.next()) {
					list.add(new BillBinClass(rs.getInt("bill_no"), rs.getDouble("amount"), rs.getDate("bill_from").toLocalDate() , rs.getDate("bill_to").toLocalDate(), ""+rs.getString("first_name")+" "+rs.getString("last_name")));
				}
				
				return list;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new EmptySet("No Bills allotted to this Consumer"+e.getMessage());
		}
	}

	@Override
	public void deleteConsumer(int cid) throws NoConsumerFound {
		// TODO Auto-generated method stub
		Connection con = null;
		
		try {
			con = DBUtils.linkBetween();
			
			String query = "Update consumer set is_active = 0 where consumer_id = ?";
			
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, cid);
			
			pst.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new NoConsumerFound("Consumer not found");
		}
		
		
//		PreparedStatement pStatement = con.prepareStatement(query)
	}

	@Override
	public void generateBill(BillBinClass bill) throws InputMisMatch {
		// TODO Auto-generated method stub
		
		double amt = bill.getAmount()*10;
		amt = amt+((amt*2.5)/100);
		
		Connection con = null;
		try {
			con = DBUtils.linkBetween();
			
			String query = "Insert into bill(consumer_id,amount,bill_from,bill_to) Values(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, bill.getConsumer_id());
			pst.setDouble(2, amt);
			pst.setDate(3, Date.valueOf(bill.getFromDate()));
			pst.setDate(4, Date.valueOf(bill.getToDate()));
			
			pst.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new InputMisMatch("Input mis-match "+e.getMessage());
		}
		
	}

	@Override
	public List<BillBinClass> paidAndPandigBills(int status) throws EmptySet {
		// TODO Auto-generated method stub
		Connection con = null;
		
		try {
			con = DBUtils.linkBetween();
			
			String query = "Select bill_no,amount,bill_from,bill_to,first_name,last_name from bill b join consumer c on b.consumer_id = c.consumer_id where c.is_active = 1 and b.status = ?";
			
			PreparedStatement pst = con.prepareStatement(query);

			pst.setInt(1, status);
			
			ResultSet rs = pst.executeQuery();
			
			if(DBUtils.checkIsEmptyOrNot(rs)) throw new EmptySet("No Bills allotted to this Consumer");
			else {
				List<BillBinClass> list = new ArrayList<>();
				
				while(rs.next()) {
					list.add(new BillBinClass(rs.getInt("bill_no"), rs.getDouble("amount"), rs.getDate("bill_from").toLocalDate() , rs.getDate("bill_to").toLocalDate(), ""+rs.getString("first_name")+" "+rs.getString("last_name")));
				}
				
				return list;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	} 
	
}
