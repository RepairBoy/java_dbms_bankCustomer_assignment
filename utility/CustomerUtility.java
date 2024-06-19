package com.ilp04.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp04.entity.Customer;
import com.ilp04.service.CustomerService;
import com.ilp04.service.CustomerServiceImpl;

public class CustomerUtility {

	public static void main(String[] args) {
		char goToMain;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("1.Add Customer, 2.Display all customer 3.Edit customer");
			int mainChoice =  scanner.nextInt();
			switch (mainChoice) {
			case 1 : insertIntoCustomers();
				break;
			case 2 : getAllCustomers();
				break;
			case 3 : updateTable();
				break;
			default : System.out.println("Invalid input!!");
			}
			System.out.println("Go to main menu (Y/N): ");
			goToMain = scanner.next().charAt(0);
		}
		while (goToMain == 'Y' || goToMain == 'y');
	}

	private static void getAllCustomers() {

		CustomerService customerService = new CustomerServiceImpl();
		ArrayList<Customer> customerList = customerService.getAllCustomers();
		
		for (Customer customer : customerList){
			System.out.println("Customer code : " + customer.getCustomerCode());
			System.out.println("First name : " + customer.getCustomerFirstName());
			System.out.println("Last name : " + customer.getCustomerLastName());
			System.out.println("Address : " + customer.getAddress());
			System.out.println("Phone number : " + customer.getPhnNumber());
			System.out.println("Aadhaar number : " + customer.getAadhaarNum());
			System.out.println();
		}
		
	}
	
	private static void insertIntoCustomers() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter customer details.");
		System.out.println("Enter customer first name : ");
		String customerFirstName = scanner.nextLine();
		System.out.println("Enter customer last name : ");
		String customerLastName = scanner.nextLine();
		System.out.println("Enter customer address : ");
		String address = scanner.nextLine();
		System.out.println("Enter customer phone number : ");
		long phnNumber = scanner.nextLong();
		System.out.println("Enter customer aadhaar number : ");
		long aadhaarNumber = scanner.nextLong();
		Customer customer = new Customer(customerFirstName, customerLastName, address, phnNumber, aadhaarNumber); 
		CustomerService customerService = new CustomerServiceImpl();
		int isInsertSuccess = customerService.insertIntoCustomer(customer);
		if (isInsertSuccess == 1) {
			System.out.println("Added the new customer to the Database.");
		}
		else {
			System.out.println("Failed!!");
		}
	}
	
	private static void updateTable() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter customer code : ");
		int isCustomerCodePresent = 0;
		int customerCode = scanner.nextInt();
		CustomerService customerService = new CustomerServiceImpl();
		ArrayList<Customer> customerList = customerService.getAllCustomers();
		for (Customer customer : customerList){
			if(customer.getCustomerCode() == customerCode) {
				isCustomerCodePresent = 1;
				System.out.println("User found.");
				System.out.println("Customer code : " + customer.getCustomerCode());
				System.out.println("First name : " + customer.getCustomerFirstName());
				System.out.println("Last name : " + customer.getCustomerLastName());
				System.out.println("Address : " + customer.getAddress());
				System.out.println("Phone number : " + customer.getPhnNumber());
				System.out.println("Aadhaar number : " + customer.getAadhaarNum());
				System.out.println();
				updateContent(customerCode);
			}
		}
		if(isCustomerCodePresent == 0) {
			System.out.println("Invalid customer Code !!");
		}
	}

	private static void updateContent(int customerCode) {
		Scanner scanner = new Scanner(System.in);
		int isInsertSuccess = 0;
		String newQuerry = "";
		CustomerService customerService = new CustomerServiceImpl();
		char updateAgain;
		do {
			System.out.println("Enter the feild to be updated.");
			System.out.println("1.First name  2.Last name  3.Address  4.Phone number  5.Aadhaar Number ");
			int updateChoice =  scanner.nextInt();
			switch(updateChoice) {
			case 1: System.out.println("Enter your updated First Name : ");
				scanner.nextLine();
				String firstName = scanner.nextLine();
				newQuerry = "update customer SET customer_firstname = '" + firstName + "' WHERE customer_code = " + customerCode + ";";
				isInsertSuccess = customerService.updateCustomer(newQuerry);
				break;
			case 2: System.out.println("Enter your updated Last Name : ");
				scanner.nextLine();
				String lastName = scanner.nextLine();
				newQuerry = "update customer SET customer_lastname = '" + lastName + "' WHERE customer_code = " + customerCode + ";";
				isInsertSuccess = customerService.updateCustomer(newQuerry);
				break;
			case 3: System.out.println("Enter your updated Last Name : ");
				scanner.nextLine();
				String address = scanner.nextLine();
				newQuerry = "update customer SET address = '" + address + "' WHERE customer_code = " + customerCode + ";";
				isInsertSuccess = customerService.updateCustomer(newQuerry);
				break;
			case 4: System.out.println("Enter your updated Last Name : ");
				scanner.nextLine();
				long phNum = scanner.nextLong();
				newQuerry = "update customer SET phone_number = " + phNum + " WHERE customer_code = " + customerCode + ";";
				isInsertSuccess = customerService.updateCustomer(newQuerry);
				break;
			case 5: System.out.println("Enter your updated Last Name : ");
				scanner.nextLine();
				long aadhaarNum = scanner.nextLong();
				newQuerry = "update customer SET aadhaar_number = " + aadhaarNum + " WHERE customer_code = " + customerCode + ";";
				isInsertSuccess = customerService.updateCustomer(newQuerry);
				break;
			default : System.out.println("Invalid input!!");
			}
			
			
			if(isInsertSuccess == 1) {
				System.out.println("Update success");
			}
			else {
				System.out.println("Error!! Could'nt update.");
			}
			System.out.println("Do you want to make more updates (Y/N) : ");
			updateAgain = scanner.next().charAt(0);
		}
		while (updateAgain == 'Y' || updateAgain == 'y');
	}
}
