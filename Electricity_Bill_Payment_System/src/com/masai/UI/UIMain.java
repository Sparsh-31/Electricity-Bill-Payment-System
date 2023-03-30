package com.masai.UI;

import java.util.Scanner;

import com.masai.ConsoleColors.ConsoleColor;

public class UIMain {
    
	static void displaylogin() {
		System.out.println(ConsoleColor.LIGHT_GREEN_BACKGROUND+ConsoleColor.PURPLE_BOLD_BRIGHT+"+--------------------------------+\n"
		         + "|   Press 0 to Exit              |\n"
		         + "|   Press 1 for Admin Login      |\n"
		         + "|   Press 2 for Consumer Loging  |\n"
		         + "|                                |\n"
		         + "+--------------------------------+"+ConsoleColor.RESET);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		int choice = 0;
			
		do {
			System.out.println("Enter Your choice");
			displaylogin();
			choice = sc.nextInt();
			switch (choice) {
			case 1: {
				AdminUI.adminLogin(sc);
				break;
			}
			case 2: {
				
				break;
			}
			case 0: {
				System.out.println("Thank you for using our services");
				break;
			}
			default:
				System.out.println("Invalid choice");
			}
		}while(choice != 0);
		
		
	}

}
