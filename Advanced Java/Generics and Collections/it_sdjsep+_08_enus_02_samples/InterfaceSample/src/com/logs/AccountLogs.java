package com.logs;

public interface AccountLogs {
	
	public default String getLogs()
	{
		return "log captured...";
	}

}
