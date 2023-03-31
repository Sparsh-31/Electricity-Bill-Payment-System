package com.masai.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

import com.masai.DTO.ConsumerBinClass;
import com.masai.Exception.InputMisMatch;
import com.masai.Exception.WrongCredentials;
import com.masai.utils.DBUtils;

public class ConsumerDAO implements ConsumerInterface{

	@Override
	public void signUpConsumer(ConsumerBinClass consumer) throws InputMisMatch {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = DBUtils.linkBetween();
			String query = "Insert into consumer(first_name,last_name,username,password,address,mobile_number,email) Values(?,?,?,?,?,?,?)";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, consumer.getFirst_name());
			pst.setString(2, consumer.getLast_name());
			pst.setString(3, consumer.getUsername());
			pst.setString(4, consumer.getPassword());
			pst.setString(5, consumer.getAddress());
			pst.setString(6, consumer.getMobile_number());
			pst.setString(7, consumer.getEmail());
			
			pst.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new InputMisMatch("Unable to signup because "+e.getMessage());
		}
		
	}

	@Override
	public boolean loginConsumer(String userName, String password) throws WrongCredentials {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = DBUtils.linkBetween();
			
			String query = "Select username from consumer where username = ? AND password = ?";
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, userName);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery();
			
			if(DBUtils.checkIsEmptyOrNot(rs)) throw new WrongCredentials("Log-In error");
			
			else {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new WrongCredentials("Log-In error because of"+e.getMessage());
		}
	}

	
	
}
