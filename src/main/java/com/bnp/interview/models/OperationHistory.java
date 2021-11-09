package com.bnp.interview.models;

import com.bnp.interview.enumeration.Operations;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OperationHistory extends Amount {

	@JsonProperty("operation")
	private Operations operation;

	public OperationHistory(Long id, String balance, String date, Double amount, Operations operation) {
		super(id, balance, date, amount);
		this.operation = operation;
	}

}
