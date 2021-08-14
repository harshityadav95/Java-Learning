package com.logs;

public interface AccountLogs {
	
	public default String getLogs()
	{
		return logMessage("here is new log...");
	}
	private String logMessage(String message)
	{
	return message;
	}

}
