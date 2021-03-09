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
@Table(name = "company_transaction_history", schema = "public")
public class CompanyTransactionHistory {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column
		private long id;

		@Column
		private long company_id;

		@Column
		private long company_account_id;

		@Column
		private Double initial_company_balance;

		@Column
		private Double final_company_balance;
		
		@Column
		private Double gross_transaction_amount;
		
		@Column
		private Double service_fee;

		@Column
		private Double debit_amount;
		
		@Column
		private Double credit_amount;

		@Column
		@CreationTimestamp
		private Date transaction_date;

}
