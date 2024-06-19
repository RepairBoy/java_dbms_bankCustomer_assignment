package com.ilp04.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ilp04.entity.Customer;

public class CustomerDAO {

	//get connection	
	public Connection getConnection() {
		String connectionURL = "jdbc:mysql://localhost:3306/bankdb?useSSL=false";
		String userName = "root";
		String password = "experion@123";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionURL,userName,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//close connection
	public Connection closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	//get all customer details
	public ArrayList<Customer> getAllCustomers(Connection connection){
		Statement statement;
		ResultSet resultSet;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from customer");
			while(resultSet.next()) {
				int customerCode = resultSet.getInt(1);
				String customerFirstName = resultSet.getString(2);
				String customerLastName = resultSet.getString(3);
				String address = resultSet.getString(4);
				long phNumber = resultSet.getLong(6);
				long aadharNo = resultSet.getLong(7);
				Customer customer = new Customer(customerCode, customerFirstName, customerLastName, address, phNumber, aadharNo);
				customerList.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}
	
	public int insertIntoTable(Connection connection,Customer customer) {
		Statement statement;
		try {
			statement = connection.createStatement();
			String customerFirstName = customer.getCustomerFirstName();
			String customerLastName = customer.getCustomerLastName();
			String address = customer.getAddress();
			long phnNumber = customer.getPhnNumber();
			long aadhaarNum = customer.getAadhaarNum();
			
			int rowsAffected = statement.executeUpdate("insert into customer(customer_firstname, customer_lastname, address, phone_number, aadhaar_number) values('" + customerFirstName + "','" + customerLastName +"','"+ address +"', " + phnNumber +"," + aadhaarNum + ")");
			if(rowsAffected >0 ) {
				return 1;
			}
			else {
				return 0;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateTable(Connection connection, String updateQuerry) {
		Statement statement;
		try {
			statement = connection.createStatement();
			int rowsAffected = statement.executeUpdate(updateQuerry);
			if(rowsAffected >0 ) {
				return 1;
			}
			else {
				return 0;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}	
	}
	
}
