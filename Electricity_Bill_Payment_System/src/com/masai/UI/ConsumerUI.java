package com.masai.UI;

import java.util.Scanner;

import com.masai.DAO.ConsumerDAO;
import com.masai.DAO.ConsumerInterface;
import com.masai.DTO.ConsumerBinClass;
import com.masai.Exception.InputMisMatch;
import com.masai.Exception.WrongCredentials;

public class ConsumerUI {
	
	public static void consumerMain(Scanner sc) {
		
		int choice = 0;
		
		do {
			System.out.println("+------------------------------------+\n"
			         + "|                                    |\n" 
			         + "| -> Press 0 For Exit                |\n" 
			         + "| -> Press 1 For New Concumer SignUp |\n"
			         + "| -> Press 2 For Consumer Login      |\n"
			         + "|                                    |\n"
			         + "+------------------------------------+");
			choice = sc.nextInt();
			switch (choice) {
			case 1: {			
				signUpConsumer(sc) ;
				break;
			}
			case 2: {
				logInConsumer(sc);
				break;
			}
			case 0: {
				
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		} while (choice != 0 );
		
		
	}
	
	public static void signUpConsumer(Scanner sc) {
		System.out.println("For creating new customer");
		System.out.println();
		System.out.println("Enter First Name");
		String fname = sc.next();
		System.out.println("Enter Last Name");
		String lnameString = sc.next();
		System.out.println("Enter username of consumer");
		String userName = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
		System.out.println("Enter address");
		String address = sc.next();
		System.out.println("Enter Mobile-Number");
		String mobNo = sc.next();
		System.out.println("Enter your email");
		String email = sc.next();
		
		ConsumerInterface ci = new ConsumerDAO();
		try {
			ci.signUpConsumer(new ConsumerBinClass(fname, lnameString, userName, password, address, mobNo, email));
			System.out.println("Welcom now you are a consumer of Electricity Bill Paymernt System");
		} catch (InputMisMatch e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());		}
	}
	
	public static void logInConsumer(Scanner sc) {
		System.out.println("Enter User-Name");
		String userName = sc.next();
		
		System.out.println("Enter password");
		String password = sc.next();
		
		ConsumerInterface ci = new ConsumerDAO();
		
		try {
			
			if(ci.loginConsumer(userName, password)) {
				System.out.println("Consumer Log-In Successful");
				
			}
			
			
		} catch (WrongCredentials e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		
	}
}
