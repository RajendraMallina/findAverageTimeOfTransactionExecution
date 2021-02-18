package com.techmojo.transaction.problem;

import java.io.File;

import com.techmojo.transaction.problem.Exceptions.InvalidDataException;
import com.techmojo.transaction.problem.builder.TransactionDataBuilder;

/**
 * Problem Statement :- 
 * 
 * Given a file which contains series of transactional laws 
 * Try to find out the average time between start and end of each transaction  
 * T1234, 2020 – 03 – 01 , 3:15 pm, start 
 * T1235, 2020 – 03 – 01 , 3:16 pm, start 
 * T1236, 2020 – 03 – 01 , 3:17 pm, start 
 * T1234, 2020 – 03 – 01 , 3:18 pm, End 
 * T1235, 2020 – 03 – 01 , 3:18 pm, End 
 * 
 * @author Rajendra Mallina
 *
 */
public class App {

	public static void main(String[] args) throws InvalidDataException {
		
		File file = new File("testdata/test1.txt");
		TransactionDataBuilder builder = new TransactionDataBuilder();
		builder.buildTransactionData(file);
		builder.printAvgTimeOfTransactions();
		
	}

}
