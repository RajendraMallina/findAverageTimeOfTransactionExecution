package com.techmojo.transaction.problem.builder;

import com.techmojo.transaction.problem.Exceptions.InvalidDataException;
import com.techmojo.transaction.problem.domain.Transaction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is parsing the text file which contains series of transactional
 * laws(T1234, 2020 – 03 – 01 , 3:15 pm, start) and calculates the average
 * running time of the each transaction by building Transaction data.
 * 
 * @author Rajendra Mallina
 *
 */
public class TransactionDataBuilder {

	// Which stores the list of transaction with their values.
	private Map<String, Transaction> listOfTransactions = new HashMap<String, Transaction>();

	// The map stores the transaction id and the average execution time of the
	// transaction.
	public Map<String, Double> transactionAvgTime = new HashMap<String, Double>();

	// Flag bits to verify the transaction data(start time or end time)
	private static final String START_FLAG = "start";
	private static final String END_FLAG = "end";

	// The following are the possible exception cases while parsing the transaction
	// data.
	private static final String ERROR_MESSAGE1 = "The transaction should be end with start(case Insensitive) or end(case Insensitive) keywords ";
	private static final String ERROR_MESSAGE2 = "Transction need to start before end";
	private static final String ERROR_MESSAGE3 = "Transaction should be end before it start again";

	/**
	 * This method is parsing the transaction data text file(line by line);
	 * 
	 * @param file The Transaction data text file.
	 * @throws InvalidDataException
	 */
	public void buildTransactionData(File file) throws InvalidDataException {

		try {
			FileInputStream fis = new FileInputStream(file);

			InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(isr);

			String str;
			while ((str = reader.readLine()) != null) {
				getTransactionData(str);
			}

			reader.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method will build the required trasactional data like each transaction
	 * object and average execution time of each transaction.
	 * 
	 * @param data - The transaction data(start or end)
	 * @throws InvalidDataException
	 */
	private void getTransactionData(String data) throws InvalidDataException {

		/*
		 * The data contains 4 parts which separated by ',' 1)Transaction id
		 * 2)Transaction Date 3)Transaction Time 4)Transaction Flag(start or end)
		 * 
		 * Example :- T1234, 2020 – 03 – 01 , 3:15 pm, start
		 */
		String[] transactionData = data.split(",");
		String transactionId = transactionData[0];
		String trasactionStartAndEndFalg = transactionData[3].toLowerCase().replace(" ", "");
		String time = transactionData[1].replace(" ", "") + transactionData[2];

		/*
		 * If transaction already present in the transaction data map(already started
		 * and not ended) if the transaction data was ended we have to add end otherwise
		 * we have to raise exception.
		 * 
		 */
		if (listOfTransactions.containsKey(transactionId)) {

			// If the transaction flag was start.
			if (trasactionStartAndEndFalg.equals(START_FLAG)) {

				// The transaction should not start again without end.

				throw new InvalidDataException(ERROR_MESSAGE3 + "Transaction :- " + data);

			} else if (trasactionStartAndEndFalg.equals(END_FLAG)) {

				Transaction temp = listOfTransactions.get(transactionId);

				// If the transaction has end keyword then the start of the transaction should
				// not be null
				if (temp.getStartTime() != null) {

					temp.setEndTime(time);
					listOfTransactions.put(transactionId, temp);

					DateAndTimeOperations operations = new DateAndTimeOperations();

					Transaction t = listOfTransactions.get(transactionId);
					String startTime = t.getStartTime();
					String endTime = t.getEndTime();

					/*
					 * If transaction already present in the transaction avg time map then we have
					 * to calculate average of current execution time and previous execution time of
					 * the transaction.
					 */
					if (transactionAvgTime.containsKey(transactionId)) {

						Double currentTime = transactionAvgTime.get(transactionId);
						Double trascationTime = operations.getHoursBetweenDates(startTime, endTime);
						Double avgTime = ((currentTime + trascationTime) / 2.0);

						transactionAvgTime.put(transactionId, avgTime);

					} else {

						// If transaction not present in the transaction avg time map then we have to
						// add transaction id and its execution time.
						Double trascationTime = operations.getHoursBetweenDates(startTime, endTime);
						transactionAvgTime.put(transactionId, trascationTime);

					}

					// After calculating the current transaction execution time remove transaction
					// from list.
					listOfTransactions.remove(transactionId);

				}

			} else {

				// Transaction should contain start or end keyword

				throw new InvalidDataException(ERROR_MESSAGE1 + "Transaction :- " + data);

			}
		} else {

			// If Map doesn't contain the transaction data the transaction is going to
			// start.
			Transaction trans = new Transaction();
			trans.setTransactionID(transactionId);

			if (trasactionStartAndEndFalg.equals(START_FLAG)) {

				trans.setStartTime(time);
				listOfTransactions.put(transactionId, trans);

			} else if (trasactionStartAndEndFalg.equals(END_FLAG)) {

				// If the transaction data ended with the keyword end so transaction should not
				// end without starting.
				throw new InvalidDataException(ERROR_MESSAGE2 + "Transaction :- " + data);

			} else {

				// Transaction should end with end or start keywords.
				throw new InvalidDataException(ERROR_MESSAGE1 + "Transaction :- " + data);

			}
		}

	}

	public void printAvgTimeOfTransactions() {

		DateAndTimeOperations operations = new DateAndTimeOperations();
		for (Map.Entry<String, Double> entry : transactionAvgTime.entrySet()) {
			System.out.println("Transaction ID - " + entry.getKey() + ", Average Execution Time = "
					+ operations.convertMilliSecondsToHours(entry.getValue()) + " hours");
		}
	}

}
