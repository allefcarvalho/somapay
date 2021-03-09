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
@Table(name = "bank_account", schema = "public")
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private Integer agency;

	@Column
	private Integer account;

	@Column
	private Integer verifying_digit;

	@Column
	private Integer operation;

	@Column
	private double balance;

	@Column
	@CreationTimestamp
	private Date createAccount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getAgency() {
		return agency;
	}

	public void setAgency(Integer agency) {
		this.agency = agency;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public Integer getVerifying_digit() {
		return verifying_digit;
	}

	public void setVerifying_digit(Integer verifying_digit) {
		this.verifying_digit = verifying_digit;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(Date createAccount) {
		this.createAccount = createAccount;
	}

}
