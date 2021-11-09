package com.bnp.interview.unit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bnp.interview.models.Withdrawal;
import com.bnp.interview.service.AmountService;
import com.bnp.interview.service.AmountServiceImpl;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AmountServiceTest {

	@Autowired
	private AmountService amountService;

	@Test
	public void isNullOrEmpty() {
		boolean actualResult = AmountServiceImpl.canWithdrawal(300.50, 450.00);
		assertFalse(actualResult);
	}

	@Test
	public void isValidDate() {
		boolean actualResult = AmountServiceImpl.canWithdrawal(450.00, 300.50);
		assertTrue(actualResult);
	}

	@Test
	public void withdrawalInsufficientBalance() throws Exception {
		final Withdrawal withdrawal = Withdrawal.getInstance();
		withdrawal.setAmount(6000.00);
		ResponseEntity<Withdrawal> result = this.amountService.withdrawalAmount(withdrawal);
		Assert.assertEquals(406, result.getStatusCodeValue());
	}

	@Test
	public void withdrawalSuccess() throws Exception {
		final Withdrawal withdrawal = Withdrawal.getInstance();
		withdrawal.setAmount(300.00);
		ResponseEntity<Withdrawal> result = this.amountService.withdrawalAmount(withdrawal);
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
}
