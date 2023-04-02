package com.masai.UI;

import java.util.List;
import java.util.Scanner;

import com.masai.ConsoleColors.ConsoleColor;
import com.masai.DAO.ConsumerDAO;
import com.masai.DAO.ConsumerInterface;
import com.masai.DTO.BillBinClass;
import com.masai.DTO.ConsumerBinClass;
import com.masai.DTO.TransactionBinClass;
import com.masai.Exception.EmptySet;
import com.masai.Exception.InputMisMatch;
import com.masai.Exception.NoBillFound;
import com.masai.Exception.WrongCredentials;

public class ConsumerUI {
	
	public static void consumerMain(Scanner sc) {
		
		int choice = 0;
		
		do {
			System.out.println(ConsoleColor.LIGHT_GREEN_BACKGROUND+ConsoleColor.PURPLE_BOLD_BRIGHT+"+------------------------------------+\n"
			         + "|                                    |\n" 
			         + "| -> Press 0 For Exit                |\n" 
			         + "| -> Press 1 For New Concumer SignUp |\n"
			         + "| -> Press 2 For Consumer Login      |\n"
			         + "|                                    |\n"
			         + "+------------------------------------+"+ConsoleColor.RESET);
			System.out.println(ConsoleColor.BLACK+ConsoleColor.CYAN_BACKGROUND_BRIGHT+"Enter Your choice");
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
				System.out.println("Thank you for considering our services.");
				break;
			}
			default: {
				System.out.println("Invalid choice");
				break;
			}	
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
		System.out.println(ConsoleColor.BLACK+ConsoleColor.CYAN_BACKGROUND_BRIGHT+"Enter User-Name");
		String userName = sc.next();
		
		System.out.println("Enter password");
		String password = sc.next();
		System.out.println(ConsoleColor.RESET);
		ConsumerInterface ci = new ConsumerDAO();
		

		try {
			ConsumerBinClass consumer = ci.loginConsumer(userName, password);
			
			if(consumer.isValid()) {
				System.out.println(ConsoleColor.BLUE_BACKGROUND_BRIGHT+ConsoleColor.WHITE+"Hey "+consumer.getName()+" Welcome\n"
						+ "Please let me know how can I help you\n"
						+ "Please Enter your choice");
				consumerFunctionalities(sc,consumer.getConsumer_id());
				
			}
		} catch (WrongCredentials e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void consumerFunctionalities(Scanner sc, int consumerId) {
		int choice = 0;
		do {
			System.out.println(ConsoleColor.LIGHT_GREEN_BACKGROUND+ConsoleColor.PURPLE_BOLD_BRIGHT+"+-------------------------------------+\n"
			         + "|                                     |\n" 
			         + "| -> Press 0 For Exit                 |\n" 
			         + "| -> Press 1 To Pay Bill 			  |\n"
			         + "| -> Press 2 To View All Transaction  |\n"
			         + "|                                     |\n"
			         + "+-------------------------------------+"+ConsoleColor.RESET);
			System.out.println(ConsoleColor.BLACK+ConsoleColor.CYAN_BACKGROUND_BRIGHT+"Enter Your choice");
			choice = sc.nextInt();
			switch (choice) {
			case 1: {
				payBill(consumerId,sc);
				break;
			}
			case 2: {
				transactionHistory(consumerId);
				break;
			}
			case 0: {
				System.out.println("Consumer LocOut");
				break;
			}
			default:
				System.out.println("Invalid choice");
				break;
			}
			
		} while (choice != 0);
	}
	
	public static void payBill(int cid,Scanner sc) {
		
		ConsumerInterface ci = new ConsumerDAO();
		
		try {
			List<BillBinClass> list = ci.displayBillofConsumer(cid);
			System.out.println("----------All pending Bills----------");
			double totalAmount = 0;
			for(int i=0;i<list.size();i++) {
				if(i%2 == 0) {
					System.out.println(ConsoleColor.LIGHT_BLUE+ConsoleColor.RED_BACKGROUND+list.get(i).toString()+ConsoleColor.RESET);
					totalAmount += list.get(i).getAmount();
				}
				
				else {
					System.out.println(ConsoleColor.RED_BACKGROUND_BRIGHT+ConsoleColor.LIGHT_BLUE+list.get(i).toString()+ConsoleColor.RESET);
					totalAmount += list.get(i).getAmount();
				}
			}
			System.out.println("Your total pending amount: "+totalAmount);
			
			System.out.println("Please enter the bill number for the payment you wish to make at this moment.");
			int billNo = sc.nextInt();
			
			double amount = ci.billAmount(billNo);
			
			
			System.out.println("Please confirm your payment by entering 'y' or decline it by entering 'n' \nYour payment Amount is : "+amount);
			
			String payOrNot = sc.next();
			
			if(payOrNot.equals("y")) {
				ci.updateStatus(billNo);
				System.out.println("Payment Successful");
				totalAmount = (totalAmount-amount);
				System.out.println("Now your total pending amount: "+totalAmount);
				
				ci.updateTransection(cid, amount);
			}
			else {
				System.out.println("Our payment has been declined. Thank you for considering our services.");
			}
		} catch (EmptySet | NoBillFound | InputMisMatch e) {
			// TODO Auto-generated catch block
			System.out.println(ConsoleColor.RED+e.getMessage()+ConsoleColor.RESET);
		}
	}
	
	public static void transactionHistory(int cid) {
		
		ConsumerInterface ci = new ConsumerDAO();
		
		try {
			List<TransactionBinClass> list = ci.transactionHistory(cid);
			
			for(int i=0;i<list.size();i++) {
				if(i%2 == 0) System.out.println(ConsoleColor.WHITE+ConsoleColor.BLACK_BACKGROUND+list.get(i).toString());
				
				else System.out.println(ConsoleColor.WHITE+ConsoleColor.BLACK_BACKGROUND_BRIGHT+list.get(i).toString());
			}
			
			System.out.println(ConsoleColor.RESET);
		} catch (EmptySet e) {
			// TODO Auto-generated catch block
			System.out.println(ConsoleColor.RED+e.getMessage()+ConsoleColor.RESET);
		}
		
		
	}
	
}

