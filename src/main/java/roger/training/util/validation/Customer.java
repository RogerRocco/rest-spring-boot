package roger.training.util.validation;

public class Customer extends DataValidation {

	public static boolean isValid(String customerStr) {
		boolean valid = false;
		if (customerStr.equals(""))
			valid = false;
		else
			valid = true;
		return valid;
	}

}
