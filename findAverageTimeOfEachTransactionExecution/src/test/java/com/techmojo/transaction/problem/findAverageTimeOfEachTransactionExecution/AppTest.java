package com.techmojo.transaction.problem.findAverageTimeOfEachTransactionExecution;

import com.techmojo.transaction.problem.App;
import com.techmojo.transaction.problem.Exceptions.InvalidDataException;
import com.techmojo.transaction.problem.builder.DateAndTimeOperations;
import com.techmojo.transaction.problem.builder.TransactionDataBuilder;
import com.techmojo.transaction.problem.domain.Transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.io.File;
import org.junit.Test;


/**
 * The JUNIT test cases of the project.
 * 
 * @author Rajendra Mallina
 *
 */
public class AppTest {

	DateAndTimeOperations operations = new DateAndTimeOperations();
	TransactionDataBuilder builder = new TransactionDataBuilder();
	
	@Test
	public void testTransactioBuilder() throws InvalidDataException {
		
		File file = new File("testdata/test1.txt");
		builder.buildTransactionData(file);
		double value = builder.transactionAvgTime.get("T1234");
		assertEquals(180000.0, value, 0.00);
	}
	
	@Test
	public void testTransaction() throws InvalidDataException {
		
		File file = new File("testdata/test2.txt");
		builder.buildTransactionData(file);
		double value = builder.transactionAvgTime.get("T1234");
		assertEquals(390000.0, value, 0.00);
	}
	
	@Test(expected = InvalidDataException.class)
	public void testEndWithOutStartException() throws InvalidDataException {
		
		File file = new File("testdata/test4.txt");
		builder.buildTransactionData(file);
		
	}
	
	@Test(expected = InvalidDataException.class)
	public void testInvalidStartFlagException() throws InvalidDataException {
		
		File file = new File("testdata/test3.txt");
		builder.buildTransactionData(file);
	}

	@Test(expected = InvalidDataException.class)
	public void testStartWithOutEndException() throws InvalidDataException {
		
		File file = new File("testdata/test5.txt");
		builder.buildTransactionData(file);
	}
	
	@Test(expected = InvalidDataException.class)
	public void testEndFlagException() throws InvalidDataException {
		
		File file = new File("testdata/test6.txt");
		builder.buildTransactionData(file);
	}
	
	@Test
	public void testTimeBetweenTwoDates() {

		double milliSeconds = operations.getHoursBetweenDates("2020–03–01 3:15 pm", "2020–03–01 4:15 pm");
		assertEquals(3600000.00, milliSeconds, 0.00);

	}

	@Test
	public void testTimeBetweenTwoDates2() {

		double milliSeconds = operations.getHoursBetweenDates("2020–03–01 3:15 pm", "2020–03–01 4:15 pm");
		assertNotEquals(3600000, milliSeconds);

	}
	
	@Test
	public void testParseDateError() {

		double milliSeconds = operations.getHoursBetweenDates("2020-–03–01 3:15 pm", "2020–03–01 4:15 pm");
		assertNotEquals(3600000, milliSeconds);

	}

	@Test
	public void testMilliSecondsToHours() {

		String hour = operations.convertMilliSecondsToHours(3600000.00);
		assertEquals("1.00", hour);

	}

	@Test
	public void testMilliSecondsToHours2() {

		String hour = operations.convertMilliSecondsToHours(3600000.00);
		assertNotEquals("1", hour);

	}

	@Test
	public void testFileNotFouned() throws InvalidDataException{
		
		File file = new File("testdata/notfound.txt");
		builder.buildTransactionData(file);
		
	}
	
	@Test
	public void testInvalidDataException(){
		
		try {
			
			throw new InvalidDataException("exception");
			
		} catch (Exception e) {
			
			assertEquals("exception", e.toString());
		}
	}
	
	@Test
	public void testApp() throws InvalidDataException {
		
		App.main(new String[] {});
		
		App app = new App();
		assertTrue(app instanceof App);
	}
	
	@Test
	public void testTransactionData() {
		
		Transaction tn = new Transaction();
		tn.setTransactionID("T1235");
		tn.setStartTime("2020–03–01 3:15 pm");
		tn.setEndTime("2020–03–01 3:18 pm");
		
		assertEquals("T1235", tn.getTransactionID());
		assertEquals("2020–03–01 3:15 pm", tn.getStartTime());
		assertEquals("2020–03–01 3:18 pm", tn.getEndTime());
	}
}
