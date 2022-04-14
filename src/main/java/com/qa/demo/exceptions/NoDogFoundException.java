package com.qa.demo.exceptions;

public class NoDogFoundException extends Exception{
	
	public NoDogFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
