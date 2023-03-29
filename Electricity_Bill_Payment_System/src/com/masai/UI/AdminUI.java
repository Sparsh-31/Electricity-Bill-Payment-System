package com.masai.UI;

import java.util.Scanner;

import com.masai.DAO.AdminDAO;
import com.masai.DAO.AdminInterface;
import com.masai.Exception.WrongCredentials;

public class AdminUI {

	public static void adminLogin(Scanner sc){
		System.out.println("Enter Admin User-Name");
		String adName = sc.next();
		System.out.println("Enter Password");
		String adPassword = sc.next();
		
		AdminInterface ai = new AdminDAO();
		
		try {
			if(ai.adminLogin(adName, adPassword)){
				System.out.println("Admin Login Successful");
				adminFunctionalities();
			}
		} catch (WrongCredentials e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void adminFunctionalities() {
		System.out.println("Go ahead you are admin now");
	}
	
}
