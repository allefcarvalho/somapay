package com.somapay.paywages.dao;

import com.somapay.paywages.model.Company;
import com.somapay.paywages.model.Employee;
import com.somapay.paywages.model.Payroll;

public interface PayrollDao {
	
	public void addEmployeeTransactionHistory(Payroll payroll, Employee employee, Double creditAmount);
	
	public void addCompanyTransactionHistory(Payroll payroll, Double debitAmount);
	
	public void debitCompany(Company company, Double debitAmount);
	
	public void creditEmployee(Employee employee, Double creditAmount);

}
