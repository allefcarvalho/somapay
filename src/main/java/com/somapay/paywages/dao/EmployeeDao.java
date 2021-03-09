package com.somapay.paywages.dao;

import com.somapay.paywages.model.BankAccount;
import com.somapay.paywages.model.Employee;

public interface EmployeeDao {

	public void addEmployee(Employee employee);

	public Double getBalance(BankAccount bankAccount);

	public Employee getEmployee(String cpf, Integer account);
	
	

}
