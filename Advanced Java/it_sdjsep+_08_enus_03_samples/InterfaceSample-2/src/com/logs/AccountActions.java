package com.logs;

public class AccountActions implements AccountLogs {

	public void fundTransfer()
	{
		System.out.println(getLogs());
	}
}
