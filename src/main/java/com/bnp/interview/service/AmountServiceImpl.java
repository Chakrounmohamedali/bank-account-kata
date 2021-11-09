package com.bnp.interview.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bnp.interview.enumeration.Operations;
import com.bnp.interview.models.Amount;
import com.bnp.interview.models.OperationHistory;
import com.bnp.interview.models.Withdrawal;

@Service
public class AmountServiceImpl implements AmountService {

	@Override
	public ResponseEntity<Amount> save(Amount amount) {
		try {
			return ResponseEntity.ok(amount); // no
												// persistance
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public synchronized ResponseEntity<Withdrawal> withdrawalAmount(Withdrawal withdrawal) {
		var actualSold = 5000.0;
		try {
			ResponseEntity<Withdrawal> response = canWithdrawal(actualSold, withdrawal.getAmount())
					? ResponseEntity.ok(withdrawal)
					: new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			actualSold = canWithdrawal(actualSold, withdrawal.getAmount()) ? actualSold -= withdrawal.getAmount()
					: actualSold;
			return response;
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static boolean canWithdrawal(double actualSold, Double amount) {
		return amount <= actualSold;
	}

	@Override
	public ResponseEntity<List<OperationHistory>> findAllOperationHistory() {
		List<OperationHistory> operations = new ArrayList<>();
		operations.add(new OperationHistory(1L, "sdf-sdf-1", "2021-07-27T22:25:00", 2500.2, Operations.deposit));
		operations.add(new OperationHistory(1L, "sdf-sdf-1", "2021-07-27T22:25:00", 1050.2, Operations.withdrawal));
		return ResponseEntity.ok(operations);
	}
}
