package roger.training.util.validation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Date extends DataValidation {

	final static String DATE_FORMAT = "yyyy-MM-dd";

	public static boolean isValid(String date) {
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
