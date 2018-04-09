package com.hobsons.exams.exceptions;

/**
 * Custom exception to throw when data read errors occur
 *
 */
public class DataException extends Exception {
		
	public DataException(String message,Throwable t){
		super(message,t);
	}
	public DataException(String msg){
		super(msg);
	}
}
