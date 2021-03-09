package com.somapay.paywages.services;

import com.somapay.paywages.model.BankAccount;
import com.somapay.paywages.model.DataProcessing;
import com.somapay.paywages.model.Employee;

public interface EmployeeServices {
	
	public void dataProcessing(DataProcessing dataProcessing);
	
	public void addEmployee(Employee employee);
	
	public Double getBalance(BankAccount bankAccount);
	
	public Employee getEmployee(String cpf, Integer account);
}
