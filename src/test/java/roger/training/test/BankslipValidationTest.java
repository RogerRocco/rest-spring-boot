package roger.training.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import roger.training.util.BankslipValidation;
import roger.training.util.validation.Cents;
import roger.training.util.validation.Customer;
import roger.training.util.validation.Date;
import roger.training.util.validation.Status;

public class BankslipValidationTest {

	@Test
	public void testIndividualComponents() {
		assertFalse(BankslipValidation.isNullOrEmpty("NOT NULL TEXT"));
		assertTrue(BankslipValidation.isNullOrEmpty(null));
		assertTrue(BankslipValidation.isNullOrEmpty(""));

		assertTrue(Date.isValid("2018-12-31"));
		assertFalse(Date.isValid("2018-12-32"));
		assertFalse(Date.isValid("2018-13-01"));
		assertFalse(Date.isValid("2018-02-29"));
		assertFalse(Date.isValid("2017-02-29"));

		assertTrue(Cents.isValid("100"));
		assertFalse(Cents.isValid("100.00"));
		assertFalse(Cents.isValid("100,00"));

		assertTrue(Customer.isValid("valid String"));
		assertFalse(Customer.isValid(""));

		assertTrue(Status.isValid("PENDING"));
		assertTrue(Status.isValid("CANCELED"));
		assertTrue(Status.isValid("PAID"));
		assertFalse(Status.isValid("DO-NOT-EXISTS"));
	}

	@Test
	public void testingBankslipJsonFormat() {

		/*
		 * This is the only valid json
		 */
		String correctJson = "{\n" + "\"due_date\" : \"2018-01-01\" ,\n" + "\"total_in_cents\" : \"100000\" ,\n"
				+ "\"customer\" : \"A Nice Company\" ,\n" + "\"status\" : \"PENDING\"\n" + "}";
		assertTrue(BankslipValidation.isValid(correctJson));

		/*
		 * no date value
		 */
		String failJson = null;
		assertFalse(BankslipValidation.isValid(failJson));

		/*
		 * no date value
		 */
		failJson = "{\"total_in_cents\" : \"100000\" ,\n" + "\"customer\" : \"A Nice Company\" ,\n"
				+ "\"status\" : \"PENDING\"\n" + "}";
		assertFalse(BankslipValidation.isValid(failJson));

		/*
		 * no total_in_cents value
		 */
		failJson = "{\n" + "\"due_date\" : \"2018-01-01\" ,\n" + "\"customer\" : \"A Nice Company\" ,\n"
				+ "\"status\" : \"PENDING\"\n" + "}";
		assertFalse(BankslipValidation.isValid(failJson));

		/*
		 * no customer value
		 */
		failJson = "{\n" + "\"due_date\" : \"2018-01-01\" ,\n" + "\"total_in_cents\" : \"100000\" ,\n"
				+ "\"status\" : \"PENDING\"\n" + "}";
		assertFalse(BankslipValidation.isValid(failJson));

		/*
		 * no status value
		 */
		failJson = "{\n" + "\"due_date\" : \"2018-01-01\" ,\n" + "\"total_in_cents\" : \"100000\" ,\n"
				+ "\"customer\" : \"A Nice Company\" }";
		assertFalse(BankslipValidation.isValid(failJson));

		/*
		 * no wrong Integer in total_in_cents
		 */
		failJson = "{\n" + "\"due_date\" : \"2018-01-01\" ,\n" + "\"total_in_cents\" : \"100.000\" ,\n"
				+ "\"customer\" : \"A Nice Company\" ,\n" + "\"status\" : \"PENDING\"\n" + "}";
		assertFalse(BankslipValidation.isValid(failJson));

		/*
		 * invalid status (MAYBE_PENDING doesn't exist)
		 */
		failJson = "{\n" + "\"due_date\" : \"2018-01-01\" ,\n" + "\"total_in_cents\" : \"100000\" ,\n"
				+ "\"customer\" : \"A Nice Company\" ,\n" + "\"status\" : \"MAYBE_PENDING\"\n" + "}";
		assertFalse(BankslipValidation.isValid(failJson));

		/*
		 * invalid date
		 */
		failJson = "{\n" + "\"due_date\" : \"2018-13-01\" ,\n" + "\"total_in_cents\" : \"100000\" ,\n"
				+ "\"customer\" : \"A Nice Company\" ,\n" + "\"status\" : \"PENDING\"\n" + "}";
		assertFalse(BankslipValidation.isValid(failJson));

	}

}
