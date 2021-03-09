package com.somapay.paywages.services;

import com.somapay.paywages.model.BankAccount;
import com.somapay.paywages.model.Company;
import com.somapay.paywages.model.DataProcessing;

public interface CompanyServices {
	
	public void dataProcessing(DataProcessing dataProcessing);
	
	public void addCompany(Company company);
	
	public Double getBalance(BankAccount bankAccount);
	
	public Company getCompany(String cnpj, Integer account);

}
