package com.somapay.paywages.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.somapay.paywages.model.Authentication;
import com.somapay.paywages.model.BankAccount;
import com.somapay.paywages.model.DataProcessing;
import com.somapay.paywages.model.Payroll;
import com.somapay.paywages.services.AuthenticationServices;
import com.somapay.paywages.services.CompanyServices;
import com.somapay.paywages.services.EmployeeServices;
import com.somapay.paywages.services.PayrollServices;
import com.somapay.paywages.services.ResponseServices;

@RestController
public class ApiController {

	@Autowired
	private AuthenticationServices authenticationServices;

	@Autowired
	private CompanyServices companyServices;
	
	@Autowired
	private EmployeeServices employeeServices;
	
	@Autowired
	private PayrollServices payrollServices;
	
	@Autowired
	private ResponseServices responseServices;

	@GetMapping(path = "/gettoken/", consumes = "application/json", produces = "application/json")
	public String getToken(@RequestBody Authentication authentication) {
		return authenticationServices.getToken(authentication);
	}

	@PostMapping(path = "/addcompany/", consumes = "application/json", produces = "application/json")
	public String addCompany(@RequestHeader String token, @RequestBody DataProcessing dataProcessing) {
		Boolean active_status = authenticationServices.tokenValidation(token);
		if (active_status) {
			companyServices.dataProcessing(dataProcessing);
			return responseServices.messageInfo("saved data.");
		} else {
			return responseServices.messageError("invalid token.");
		}
	}
	
	@GetMapping(path = "/getbalance/pj/", consumes = "application/json", produces = "application/json")
	public String getBalancePj(@RequestHeader String token, @RequestBody BankAccount bankAccount) {
		Boolean active_status = authenticationServices.tokenValidation(token);
		if (active_status) {
			Double balance = companyServices.getBalance(bankAccount);
			return balance.toString();
		} else {
			return responseServices.messageError("invalid token.");
		}
	}
	
	@PostMapping(path = "/addemployee/", consumes = "application/json", produces = "application/json")
	public String addEmployee(@RequestHeader String token, @RequestBody DataProcessing dataProcessing) {
		Boolean active_status = authenticationServices.tokenValidation(token);
		if (active_status) {
			employeeServices.dataProcessing(dataProcessing);
			return responseServices.messageInfo("saved data.");
		} else {
			return responseServices.messageError("invalid token.");
		}
	}
	
	@GetMapping(path = "/getbalance/pf/", consumes = "application/json", produces = "application/json")
	public String getBalancePf(@RequestHeader String token, @RequestBody BankAccount bankAccount) {
		Boolean active_status = authenticationServices.tokenValidation(token);
		if (active_status) {
			Double balance = employeeServices.getBalance(bankAccount);
			return balance.toString();
		} else {
			return responseServices.messageError("invalid token.");
		}
	}
	
	@PostMapping(path = "/payroll/", consumes = "application/json", produces = "application/json")
	public String payRoll(@RequestHeader String token, @RequestBody Payroll payroll) {
		Boolean active_status = authenticationServices.tokenValidation(token);
		if (active_status) {
			payroll.setCompany(companyServices.getCompany(payroll.getCompany().getCnpj(), payroll.getCompany().getBankAccount().getAccount()));			
			return payrollServices.processPayroll(payroll);
		} else {
			return responseServices.messageError("invalid token.");
		}
	}

}
