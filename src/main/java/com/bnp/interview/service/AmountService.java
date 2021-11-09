package com.bnp.interview.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bnp.interview.models.Amount;
import com.bnp.interview.models.OperationHistory;
import com.bnp.interview.models.Withdrawal;

public interface AmountService {

	ResponseEntity<Amount> save(Amount amount);

	ResponseEntity<Withdrawal> withdrawalAmount(Withdrawal withdrawal);

	ResponseEntity<List<OperationHistory>> findAllOperationHistory();

}
