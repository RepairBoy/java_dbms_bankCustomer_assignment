package com.ilp04.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.ilp04.dao.CustomerDAO;
import com.ilp04.entity.Customer;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public ArrayList<Customer> getAllCustomers() {
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		CustomerDAO customerDAO = new CustomerDAO();
		Connection connection = customerDAO.getConnection();
		customerList = customerDAO.getAllCustomers(connection);
		return customerList;
	}

	@Override
	public int insertIntoCustomer(Customer customer) {
		CustomerDAO customerDAO = new CustomerDAO();
		Connection connection = customerDAO.getConnection();
		int isInsertSuccess = customerDAO.insertIntoTable(connection, customer);
		if(isInsertSuccess == 1) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override	
	public int updateCustomer(String updateQuerry) {
		CustomerDAO customerDAO = new CustomerDAO();
		Connection connection = customerDAO.getConnection();
		int isUpdateSuccess = customerDAO.updateTable(connection, updateQuerry);
		if(isUpdateSuccess == 1) {
			return 1;
		}
		else {
			return 0;
		}
	}

}
