package com.masai.UI;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masai.ConsoleColors.ConsoleColor;
import com.masai.DAO.AdminDAO;
import com.masai.DAO.AdminInterface;
import com.masai.DTO.BillBinClass;
import com.masai.DTO.ConsumerBinClass;
import com.masai.Exception.EmptySet;
import com.masai.Exception.InputMisMatch;
import com.masai.Exception.NoConsumerFound;
import com.masai.Exception.WrongCredentials;

public class AdminUI {

	public static void displayAdminFunctionalities() {
		System.out.println(ConsoleColor.LIGHT_GREEN_BACKGROUND+ConsoleColor.PURPLE_BOLD_BRIGHT+"+-------------------------------------------------------------------+\n"
		          +"|                                                                   |\n"
				  +"| ->  Press 0 to Log-Out Admin.                                     |\n"
				  +"| ->  Press 1 to Display All consumers.                             |\n"
				  +"| ->  Press 2 to View the bill of the consumer.                     |\n"
				  +"| ->  Press 3 to View all the bills.                                |\n"
				  +"| ->  Press 4 to View all bills paid and pending                    |\n"
				  +"| ->  Press 5 to Delete consumer                                    |\n"
				  +"| ->  Press 6 to generate bill                                      |\n"
				  +"|                                                                   |\n"
				  +"+-------------------------------------------------------------------+"+ConsoleColor.RESET);
	}
	
	public static void adminLogin(Scanner sc){
		System.out.println("Enter Admin User-Name");
		String adName = sc.next();
		System.out.println("Enter Password");
		String adPassword = sc.next();
		
		AdminInterface ai = new AdminDAO();
		
		try {
			if(ai.adminLogin(adName, adPassword)){
				System.out.println("Admin Login Successful");
				adminFunctionalities(sc);
			}
		} catch (WrongCredentials e) {
			// TODO Auto-generated catch block
			System.out.println(ConsoleColor.RED+e.getMessage()+ConsoleColor.RESET);
		}
		
	}
	
	public static void displayAllConsumers() {
		
		AdminInterface ai = new AdminDAO();
		
		try {
			List<ConsumerBinClass> list = ai.displayAllConsumers();
			
			for(int i=0;i<list.size();i++) {
				if(i%2 == 0) System.out.println(ConsoleColor.WHITE+ConsoleColor.BLACK_BACKGROUND+list.get(i).toString());
				
				else System.out.println(ConsoleColor.RED+ConsoleColor.BLACK_BACKGROUND_BRIGHT+list.get(i).toString());
			}
			
		} catch (EmptySet e) {
			// TODO Auto-generated catch block
			System.out.println(ConsoleColor.RED+e.getMessage()+ConsoleColor.RESET);
		}
	}
	
	public static void displayBillOfConsumerByID(Scanner sc) {
		System.out.println("Enter the Consumer ID of consumer you want to get bill details");
		int consumerID = sc.nextInt();
		
		AdminInterface ai = new AdminDAO();
		
		try {
			List<BillBinClass> list = ai.displayBillofConsumer(consumerID);
			
			for(int i=0;i<list.size();i++) {
				if(i%2 == 0) System.out.println(ConsoleColor.LIGHT_BLUE+ConsoleColor.RED_BACKGROUND+list.get(i).toString()+ConsoleColor.RESET);
				
				else System.out.println(ConsoleColor.RED_BACKGROUND_BRIGHT+ConsoleColor.LIGHT_BLUE+list.get(i).toString()+ConsoleColor.RESET);
			}
		} catch (EmptySet e) {
			// TODO Auto-generated catch block
			System.out.println(ConsoleColor.RED+e.getMessage()+ConsoleColor.RESET);
		}
	}
	
	public static void displayAllBills() {
		AdminInterface ai = new AdminDAO();
		
		try {
			List<BillBinClass> list = ai.displayAllBill();
			
			for(int i=0;i<list.size();i++) {
				if(i%2 == 0) System.out.println(ConsoleColor.LIGHT_BLUE+ConsoleColor.RED_BACKGROUND+list.get(i).toString()+ConsoleColor.RESET);
				
				else System.out.println(ConsoleColor.RED_BACKGROUND_BRIGHT+ConsoleColor.LIGHT_BLUE+list.get(i).toString()+ConsoleColor.RESET);
			}
		} catch (EmptySet e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deleteConsumer(Scanner sc) {
		System.out.println("Enter the Consumer ID you want to remove from consumer list");
		int cid = sc.nextInt();
		
		AdminInterface ai = new AdminDAO();
		
		try {
			ai.deleteConsumer(cid);
			System.out.println("Consumer record is now in active");
		} catch (NoConsumerFound e) {
			// TODO Auto-generated catch block
			System.out.println(ConsoleColor.RED+e.getMessage()+ConsoleColor.RESET);
		}
		
	}
	
	public static void generateBill(Scanner sc) {
		System.out.println("Enter Consumer-ID");
		int cId = sc.nextInt();
		System.out.println("Enter Unit Consume");
		int unit = sc.nextInt();
		System.out.println("Enter the Date of Bill in From(YYYY-MM-DD) To(YYYY-MM-DD)");
		LocalDate fDate = LocalDate.parse(sc.next());
		LocalDate toDate = LocalDate.parse(sc.next());
		
		BillBinClass bill = new BillBinClass(cId, unit, fDate, toDate);
		
		AdminInterface ai = new AdminDAO();
		
		try {
			ai.generateBill(bill);
			System.out.println("Bill generated successfully");
		} catch (InputMisMatch e) {
			// TODO Auto-generated catch block
			System.out.println(ConsoleColor.RED+e.getMessage()+ConsoleColor.RESET);
		}
	}
	
	public static void paidAndPandingBills() {
		
		AdminInterface ai = new AdminDAO();
		
		try {
			List<BillBinClass> list1 =  ai.paidAndPandigBills(0);
			System.out.println("----------Pending Bills----------");
			for(int i=0;i<list1.size();i++) {
				if(i%2 == 0) System.out.println(ConsoleColor.LIGHT_BLUE+ConsoleColor.RED_BACKGROUND+list1.get(i).toString()+ConsoleColor.RESET);
				
				else System.out.println(ConsoleColor.RED_BACKGROUND_BRIGHT+ConsoleColor.LIGHT_BLUE+list1.get(i).toString()+ConsoleColor.RESET);
			}
			
			List<BillBinClass> list2 =  ai.paidAndPandigBills(1);
			
			System.out.println();
			System.out.println("----------Paid Bills----------");
			for(int i=0;i<list2.size();i++) {
				if(i%2 == 0) System.out.println(ConsoleColor.LIGHT_BLUE+ConsoleColor.RED_BACKGROUND+list2.get(i).toString()+ConsoleColor.RESET);
				
				else System.out.println(ConsoleColor.RED_BACKGROUND_BRIGHT+ConsoleColor.LIGHT_BLUE+list2.get(i).toString()+ConsoleColor.RESET);
			}
			
		} catch (EmptySet e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void adminFunctionalities(Scanner sc) {
		System.out.println(ConsoleColor.GREEN_BOLD_BRIGHT+"Go ahead you are admin now"+ConsoleColor.RESET);
		int choice = 0;
		
		do {
			displayAdminFunctionalities();
			System.out.println(ConsoleColor.BLACK+ConsoleColor.CYAN_BACKGROUND_BRIGHT+"Enter Your choice");
			choice = sc.nextInt();
			switch (choice) {
			case 1: {
				displayAllConsumers();
 				break;
			}
			case 2: {
				displayBillOfConsumerByID(sc);
				break;
			}
			case 3: {
				displayAllBills();
				break;
			}
			case 4: {
				paidAndPandingBills();
				break;
			}
			case 5: {
				deleteConsumer(sc);
				break;
			}
			case 6: {
				generateBill(sc);
				break;
			}
			case 0: {
				System.out.println(ConsoleColor.RED_BOLD+"Admin Log-Out Successful");
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
		}while(choice != 0);
	}
	
}
