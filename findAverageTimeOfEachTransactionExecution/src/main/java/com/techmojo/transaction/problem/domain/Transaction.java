package com.techmojo.transaction.problem.domain;

/**
 * The following class is the POJO class for the Transaction which contain the
 * transaction information(Transaction ID and start & end times)
 * 
 * @author Rajendra Mallina
 *
 */
public class Transaction {

	private String transactionID;
	private String startTime;
	private String endTime;

	public Transaction() {
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
