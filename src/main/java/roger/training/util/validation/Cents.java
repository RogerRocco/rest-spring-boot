package roger.training.util.validation;

import java.math.BigDecimal;

public class Cents extends DataValidation {

	public static boolean isValid(String cents) {
		boolean valid = false;
		if (cents.contains(".") || cents.contains(",")) {
			valid = false;
		} else {
			BigDecimal bigDecimal = new BigDecimal(cents);
			valid = bigDecimal.signum() == 0 || bigDecimal.scale() <= 0 || bigDecimal.stripTrailingZeros().scale() <= 0;
		}
		return valid;
	}

}
