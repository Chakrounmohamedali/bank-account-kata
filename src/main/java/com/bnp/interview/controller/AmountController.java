package com.bnp.interview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnp.interview.exception.CustomHTTPError;
import com.bnp.interview.models.Amount;
import com.bnp.interview.models.OperationHistory;
import com.bnp.interview.models.Withdrawal;
import com.bnp.interview.service.AmountService;
import com.bnp.interview.utils.SwaggerConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "amount")
public class AmountController {

	@Autowired
	public AmountService amountService;

	@ApiOperation(value = SwaggerConstants.SAVE_AMOUNT, response = Amount.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = SwaggerConstants.HTTP_400),
			@ApiResponse(code = 500, message = SwaggerConstants.HTTP_500) })
	@PostMapping("/add")
	public ResponseEntity<Object> saveAmount(@RequestBody final Amount amount) {
		if (amount.getAmount() == null || amount.getBalance() == null || amount.getId() == null
				|| amount.getDate() == null)
			return ResponseEntity.status(400).body(
					new CustomHTTPError(SwaggerConstants.BAD_PARAMETER, SwaggerConstants.BAD_PARAMETER_DESCRIPTION));
		ResponseEntity<Amount> response = this.amountService.save(amount);
		if (response.getStatusCodeValue() == 500) {
			return ResponseEntity.status(500).body(new CustomHTTPError(SwaggerConstants.INTERNAL_SERVER_ERROR,
					SwaggerConstants.INTERNAL_SERVER_ERROR_DESCRIPTION));
		} else
			return ResponseEntity.status(200).body(response.getBody());
	}

	@ApiOperation(value = SwaggerConstants.WITHDRAWAL_AMOUNTS, response = Withdrawal.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = SwaggerConstants.HTTP_400),
			@ApiResponse(code = 404, message = SwaggerConstants.HTTP_404),
			@ApiResponse(code = 500, message = SwaggerConstants.HTTP_500) })
	@PostMapping("/withdrawal")
	public ResponseEntity<Object> withdrawalAmount(@RequestBody final Withdrawal withdrawal) {
		if (withdrawal.getAmount() == null || withdrawal.getId() == null || withdrawal.getDate() == null)
			return ResponseEntity.status(400).body(
					new CustomHTTPError(SwaggerConstants.BAD_PARAMETER, SwaggerConstants.BAD_PARAMETER_DESCRIPTION));
		ResponseEntity<Withdrawal> response = this.amountService.withdrawalAmount(withdrawal);
		if (response.getStatusCodeValue() == 500) {
			return ResponseEntity.status(500).body(new CustomHTTPError(SwaggerConstants.INTERNAL_SERVER_ERROR,
					SwaggerConstants.INTERNAL_SERVER_ERROR_DESCRIPTION));
		} else if (response.getStatusCodeValue() == 406) {
			return ResponseEntity.status(406).body(new CustomHTTPError(SwaggerConstants.INSUFFICIENT_BALANCE_ERROR,
					SwaggerConstants.INSUFFICIENT_BALANCE_ERROR_DESCRIPTION));
		} else
			return ResponseEntity.status(200).body(response.getBody());
	}

	@ApiOperation(value = SwaggerConstants.ACCOUNT_HISTORY, response = OperationHistory.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = SwaggerConstants.HTTP_400),
			@ApiResponse(code = 404, message = SwaggerConstants.HTTP_404),
			@ApiResponse(code = 500, message = SwaggerConstants.HTTP_500) })
	@GetMapping("/history")
	public ResponseEntity<List<OperationHistory>> getAllOperationHistory() {
		return this.amountService.findAllOperationHistory();
	}

}
