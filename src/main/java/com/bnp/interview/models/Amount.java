package com.bnp.interview.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({ "acountId", "balance", "date", "amount" })
public class Amount {

	@ApiModelProperty(position = 0, required = true, example = "1")
	@JsonProperty("acountId")
	private Long id;
	@ApiModelProperty(position = 1, required = true, example = "sdf-sdf-1")
	@JsonProperty("balance")
	private String balance;
	@ApiModelProperty(position = 2, required = true, example = "2021-07-27T22:25:00")
	@JsonProperty("date")
	private String date;
	@ApiModelProperty(position = 3, required = true, example = "2500.00")
	@JsonProperty("amount")
	private Double amount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Amount(Long id, String balance, String date, Double amount) {
		super();
		this.id = id;
		this.balance = balance;
		this.date = date;
		this.amount = amount;
	}

}
