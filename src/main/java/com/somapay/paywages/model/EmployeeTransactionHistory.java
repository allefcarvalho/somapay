package com.somapay.paywages.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "employee_transaction_history", schema = "public")
public class EmployeeTransactionHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private long company_id;

	@Column
	private long company_account_id;

	@Column
	private long employee_id;

	@Column
	private long employee_account_id;

	@Column
	private Double initial_employee_balance;

	@Column
	private Double final_employee_balance;

	@Column
	private Double transaction_amount;

	@Column
	@CreationTimestamp
	private Date transaction_date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}

	public long getCompany_account_id() {
		return company_account_id;
	}

	public void setCompany_account_id(long company_account_id) {
		this.company_account_id = company_account_id;
	}

	public long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(long employee_id) {
		this.employee_id = employee_id;
	}

	public long getEmployee_account_id() {
		return employee_account_id;
	}

	public void setEmployee_account_id(long employee_account_id) {
		this.employee_account_id = employee_account_id;
	}

	public Double getInitial_employee_balance() {
		return initial_employee_balance;
	}

	public void setInitial_employee_balance(Double initial_employee_balance) {
		this.initial_employee_balance = initial_employee_balance;
	}

	public Double getFinal_employee_balance() {
		return final_employee_balance;
	}

	public void setFinal_employee_balance(Double final_employee_balance) {
		this.final_employee_balance = final_employee_balance;
	}

	public Double getTransaction_amount() {
		return transaction_amount;
	}

	public void setTransaction_amount(Double transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

}
