package com.bnp.interview.utils;

public class SwaggerConstants {

	// ------------------ COMMON ---------------------------
	public final static String SAVE_AMOUNT = "Add Amount to specific account";
	public final static String WITHDRAWAL_AMOUNTS = "make a withdrawal from a specific account";
	public final static String ACCOUNT_HISTORY = "get operation history account";
	public final static String GET_ACCOUNT_INFO = "Get amount from a specific acount";
	public final static String GET_ALL_OPERATION_INFO = "Get all amount operation";
	public final static String BAD_PARAMETER = "Bad parameter";
	public final static String INTERNAL_SERVER_ERROR = "Internal Server Error";
	public final static String NOT_FOUND = "Not found";
	public final static String BAD_PARAMETER_DESCRIPTION = "Missing parameter";
	public final static String INTERNAL_SERVER_ERROR_DESCRIPTION = "Internal Error";
	public final static String INSUFFICIENT_BALANCE_ERROR = "insufficient balance";
	public final static String INSUFFICIENT_BALANCE_ERROR_DESCRIPTION = "insufficient balance to do this operation";
	public final static String HTTP_400 = "{\n" +
			"&nbsp;&nbsp;\"description\": \"Bad parameter\",\n" +
			"&nbsp;&nbsp;\"message\": \"xxx\"\n" +
			"}";
	
	public final static String HTTP_404 = "{\n" +
			"&nbsp;&nbsp;\"description\": \"Not found\",\n" +
			"&nbsp;&nbsp;\"message\": \"xxx\"\n" +
			"}";

	public final static String HTTP_500 = "{\n" +
			"&nbsp;&nbsp;\"description\": \"Internal Server Error\",\n" +
			"&nbsp;&nbsp;\"message\": \"xxx\"\n" +
			"}";
}