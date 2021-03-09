package com.somapay.paywages.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.somapay.paywages.model.BankAccount;
import com.somapay.paywages.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	
	private static final Logger logger = LogManager.getLogger("EmployeeDaoImpl");
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void addEmployee(Employee employee) {
		try {
			em.persist(employee);
		} catch (Exception e) {
			logger.error(e);
		}		
	}

	@Override
	public Double getBalance(BankAccount bankAccount) {
		Double balance = 0.0; 
		try {
			Query query = em.createNativeQuery("SELECT balance FROM public.bank_account WHERE account = ? AND agency = ?");
			query.setParameter(1, bankAccount.getAccount());
			query.setParameter(2, bankAccount.getAgency());
			balance = (Double) query.getSingleResult();
		} catch (Exception e) {
			logger.error(e);
		}
		return balance;
	}

	@Override
	public Employee getEmployee(String cpf, Integer account) {
		Employee employee = new Employee();
		try {
			Query query = em.createNativeQuery("SELECT * FROM public.employee WHERE cpf = ? "
					+ " AND bank_account_id IN (SELECT id FROM public.bank_account WHERE account = ?)", Employee.class);
			query.setParameter(1, cpf);
			query.setParameter(2, account);
			employee = (Employee) query.getSingleResult();
		} catch (Exception e) {
			logger.error(e);
		}

		return employee;
	}

}
