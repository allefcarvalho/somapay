package com.somapay.paywages.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somapay.paywages.dao.CompanyDao;
import com.somapay.paywages.model.BankAccount;
import com.somapay.paywages.model.Company;
import com.somapay.paywages.model.DataProcessing;

@Service
@Transactional
public class CompanyServicesImpl implements CompanyServices {

	@Autowired
	CompanyDao companydao;
	
	@Override
	public void dataProcessing(DataProcessing dataProcessing) {
		for (Company company : dataProcessing.getCompany()) {
			addCompany(company);
		}
	}

	@Override
	public void addCompany(Company company) {
		companydao.addCompany(company);
	}

	@Override
	public Double getBalance(BankAccount bankAccount) {
		return companydao.getBalance(bankAccount);
	}

	@Override
	public Company getCompany(String cnpj, Integer account) {
		return companydao.getCompany(cnpj, account);
	}

}
