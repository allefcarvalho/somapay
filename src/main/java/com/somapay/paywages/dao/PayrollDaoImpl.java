package com.somapay.paywages.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.somapay.paywages.model.Company;
import com.somapay.paywages.model.Employee;
import com.somapay.paywages.model.Payroll;

@Repository
public class PayrollDaoImpl implements PayrollDao{
	
	private static final Logger logger = LogManager.getLogger("PayrollDaoImpl");
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void debitCompany(Company company, Double debitAmount) {
		try {
			Query query = em.createNativeQuery("UPDATE public.bank_account SET balance = ? WHERE id = ?;");
			query.setParameter(1, company.getBankAccount().getBalance() - debitAmount);
			query.setParameter(2, company.getBankAccount().getId());
			query.executeUpdate();
		} catch (Exception e) {
			logger.error(e);
		}
		
	}

	@Override
	@Transactional
	public void creditEmployee(Employee employee, Double creditAmount) {
		try {
			Query query = em.createNativeQuery("UPDATE public.bank_account SET balance=? WHERE id = ?;");
			query.setParameter(1, employee.getBankAccount().getBalance() + creditAmount);
			query.setParameter(2, employee.getBankAccount().getId());
			query.executeUpdate();
		} catch (Exception e) {
			logger.error(e);
		}
		
	}

	@Override
	@Transactional
	public void addEmployeeTransactionHistory(Payroll payroll, Employee employee, Double creditAmount) {
		try {
			Query query = em.createNativeQuery("INSERT INTO public.employee_transaction_history"
					+ " (company_account_id, company_id, employee_account_id, employee_id, final_employee_balance, initial_employee_balance, transaction_amount, transaction_date)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, now());");
			query.setParameter(1, payroll.getCompany().getBankAccount().getId());
			query.setParameter(2, payroll.getCompany().getId());
			query.setParameter(3, employee.getBankAccount().getId());
			query.setParameter(4, employee.getId());
			query.setParameter(5, employee.getBankAccount().getBalance() + creditAmount);
			query.setParameter(6, employee.getBankAccount().getBalance());
			query.setParameter(7, creditAmount);
			query.executeUpdate();
		} catch (Exception e) {
			logger.error(e);
		}
		
	}

	@Override
	@Transactional
	public void addCompanyTransactionHistory(Payroll payroll, Double debitAmount) {
		try {
			Query query = em.createNativeQuery("INSERT INTO public.company_transaction_history("
					+ " company_account_id, company_id, debit_amount, final_company_balance, gross_transaction_amount, initial_company_balance, service_fee, transaction_date)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, now());");
			query.setParameter(1, payroll.getCompany().getBankAccount().getId());
			query.setParameter(2, payroll.getCompany().getId());
			query.setParameter(3, debitAmount);
			query.setParameter(4, payroll.getCompany().getBankAccount().getBalance() - debitAmount);
			query.setParameter(5, payroll.getTotal_payroll());
			query.setParameter(6, payroll.getCompany().getBankAccount().getBalance());
			query.setParameter(7, payroll.getService_fee());
			query.executeUpdate();
		} catch (Exception e) {
			logger.error(e);
		}
		
	}

}
