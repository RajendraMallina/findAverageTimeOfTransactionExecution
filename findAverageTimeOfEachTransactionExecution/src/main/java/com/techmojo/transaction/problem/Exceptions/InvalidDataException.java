package com.techmojo.transaction.problem.Exceptions;

/**
 * 
 * @author Rajendra Mallina
 *
 */
public class InvalidDataException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2701952341790783548L;
	private String exceptionMessage;
	
		
	public InvalidDataException(String message) {
		
		this.exceptionMessage = message;
		
	}
	
	@Override
	public String toString() {
		
		return exceptionMessage;
	}

}
