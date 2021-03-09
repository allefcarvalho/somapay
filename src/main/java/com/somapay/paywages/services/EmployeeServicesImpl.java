package com.somapay.paywages.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somapay.paywages.dao.EmployeeDao;
import com.somapay.paywages.model.BankAccount;
import com.somapay.paywages.model.DataProcessing;
import com.somapay.paywages.model.Employee;

@Service
@Transactional
public class EmployeeServicesImpl implements EmployeeServices {

	@Autowired
	EmployeeDao employeeDao;

	@Override
	public void dataProcessing(DataProcessing dataProcessing) {
		for (Employee employee : dataProcessing.getEmployee()) {
			addEmployee(employee);
		}
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeDao.addEmployee(employee);
	}

	@Override
	public Double getBalance(BankAccount bankAccount) {
		return employeeDao.getBalance(bankAccount);
	}

	@Override
	public Employee getEmployee(String cpf, Integer account) {
		return employeeDao.getEmployee(cpf, account);
	}

}
