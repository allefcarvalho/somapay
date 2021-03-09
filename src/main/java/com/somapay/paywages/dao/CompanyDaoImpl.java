package com.somapay.paywages.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.somapay.paywages.model.BankAccount;
import com.somapay.paywages.model.Company;

@Repository
public class CompanyDaoImpl implements CompanyDao {

	private static final Logger logger = LogManager.getLogger("CompanyDaoImpl");

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addCompany(Company company) {

		try {
			em.persist(company);
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
	public Company getCompany(String cnpj, Integer account) {
		Company company = new Company();
		try {
			Query query = em.createNativeQuery("SELECT * FROM public.company WHERE cnpj = ? "
					+ " AND bank_account_id IN (SELECT id FROM public.bank_account WHERE account = ?)", Company.class);
			query.setParameter(1, cnpj);
			query.setParameter(2, account);
			company = (Company) query.getSingleResult();
		} catch (Exception e) {
			logger.error(e);
		}

		return company;
	}

}
