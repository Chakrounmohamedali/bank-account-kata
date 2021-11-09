package com.bnp.interview.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

@ToString
@JsonPropertyOrder({ "acountId", "amount", "date" })
public class Withdrawal {
	private static Withdrawal instance;

	@ApiModelProperty(position = 0, required = true, example = "1")
	@JsonProperty("acountId")
	private Long id;
	@ApiModelProperty(position = 1, required = true, example = "6000.52")
	@JsonProperty("amount")
	private Double amount;
	@ApiModelProperty(position = 2, required = true, example = "2021-07-27T22:25:00")
	@JsonProperty("date")
	private String date;

	public static synchronized Withdrawal getInstance() { // atomic operation
		if (instance == null) {
			instance = new Withdrawal();
		}
		return instance;
	}

	private Withdrawal() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Withdrawal [id=" + id + ", amount=" + amount + ", date=" + date + "]";
	}

}
