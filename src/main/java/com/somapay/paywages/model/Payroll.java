package com.somapay.paywages.model;

import java.util.List;

public class Payroll {	

	private Double total_payroll;

	private Double service_fee;
	
	private Company company;

	private List<Employee> employee;

	public Double getTotal_payroll() {
		return total_payroll;
	}

	public void setTotal_payroll(Double total_payroll) {
		this.total_payroll = total_payroll;
	}

	public Double getService_fee() {
		return service_fee;
	}

	public void setService_fee(Double service_fee) {
		this.service_fee = service_fee;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

}
