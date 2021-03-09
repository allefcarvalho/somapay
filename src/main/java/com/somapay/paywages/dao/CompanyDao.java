package com.somapay.paywages.dao;

import com.somapay.paywages.model.BankAccount;
import com.somapay.paywages.model.Company;

public interface CompanyDao {
	
	public void addCompany(Company company);

	public Double getBalance(BankAccount bankAccount);

	public Company getCompany(String cnpj, Integer account);
}
