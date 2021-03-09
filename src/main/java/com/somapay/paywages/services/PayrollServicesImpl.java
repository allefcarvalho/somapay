package com.somapay.paywages.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somapay.paywages.dao.EmployeeDao;
import com.somapay.paywages.dao.PayrollDao;
import com.somapay.paywages.model.Employee;
import com.somapay.paywages.model.Payroll;

@Service
public class PayrollServicesImpl implements PayrollServices{
	
	@Autowired
	private PayrollDao payrollDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ResponseServices responseServices;
	

	@Override
	public String processPayroll(Payroll payroll) {
		String message = "";
		Double balance = payroll.getCompany().getBankAccount().getBalance();
		Double updated_balance = balance - (balance * (payroll.getService_fee()/100.0));
		Double debitAmount = payroll.getTotal_payroll() + (balance * (payroll.getService_fee()/100.0));
		
		if (payroll.getTotal_payroll() > updated_balance) {
			message = responseServices.messageError("insufficient bank account balance.");
			
		}else {			
			payrollDao.debitCompany(payroll.getCompany(), debitAmount);
			payrollDao.addCompanyTransactionHistory(payroll, debitAmount);
			
			for (Employee employee :payroll.getEmployee()) {
				employee = employeeDao.getEmployee(employee.getCpf(), employee.getBankAccount().getAccount());
				payrollDao.creditEmployee(employee, employee.getSalary());
				payrollDao.addEmployeeTransactionHistory(payroll, employee, employee.getSalary());
			}			
			message = responseServices.messageInfo("payroll processing completed.");
			
		}
		return message.toString();		
	}
	
	
}
