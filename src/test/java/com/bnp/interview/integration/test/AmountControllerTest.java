package com.bnp.interview.integration.test;

import java.net.URI;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bnp.interview.models.Amount;
import com.bnp.interview.models.Withdrawal;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AmountControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	private final static String amountUri = "/amount/add";
	private final static String withdrawalUri = "/amount/withdrawal";
	
	@Test
	public void saveAmount() throws Exception {
		HttpEntity<Amount> request = new HttpEntity<>(this.createAmount());
		ResponseEntity<Amount> result = this.restTemplate.postForEntity(new URI(amountUri), request, Amount.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void saveAmountBadParameter() throws Exception {
		final Amount amount = this.createAmount();
		amount.setAmount(null);
		HttpEntity<Amount> request = new HttpEntity<>(amount);
		ResponseEntity<Amount> result = this.restTemplate.postForEntity(new URI(amountUri), request, Amount.class);
		Assert.assertEquals(400, result.getStatusCodeValue());
	}

	private Amount createAmount() {
		return new Amount(1L, "sdf-sdf-1", "2021-07-27T22:25:00", 2500.00);
	}

	@Test
	public void validWithdrawal() throws Exception {
		final Withdrawal withdrawal = this.getWithdrawalObject();
		withdrawal.setAmount(200.6);
		HttpEntity<Withdrawal> request = new HttpEntity<>(withdrawal);
		ResponseEntity<Withdrawal> result = this.restTemplate.postForEntity(new URI(withdrawalUri), request,
				Withdrawal.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
	}
	
	@Test
	public void withdrawalBadParameter() throws Exception {
		final Withdrawal withdrawal = this.getWithdrawalObject();
		withdrawal.setAmount(null);
		HttpEntity<Withdrawal> request = new HttpEntity<>(withdrawal);
		ResponseEntity<Withdrawal> result = this.restTemplate.postForEntity(new URI(withdrawalUri), request, Withdrawal.class);
		Assert.assertEquals(400, result.getStatusCodeValue());
	}
	
	@Test
	public void withdrawalInsufficientBalance() throws Exception {
		final Withdrawal withdrawal = this.getWithdrawalObject();
		withdrawal.setAmount(6000.00);
		HttpEntity<Withdrawal> request = new HttpEntity<>(withdrawal);
		ResponseEntity<Withdrawal> result = this.restTemplate.postForEntity(new URI(withdrawalUri), request, Withdrawal.class);
		Assert.assertEquals(406, result.getStatusCodeValue());
	}

	private Withdrawal getWithdrawalObject() {
		Withdrawal withdrawal = Withdrawal.getInstance();
		withdrawal.setAmount(3000.52);
		withdrawal.setDate("2021-07-27T22:25:00");
		withdrawal.setId(1L);
		return withdrawal;
	}
}
