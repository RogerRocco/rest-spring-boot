package roger.training.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import roger.training.util.validation.Cents;
import roger.training.util.validation.Customer;
import roger.training.util.validation.DataValidation;
import roger.training.util.validation.Date;
import roger.training.util.validation.Status;

public class BankslipValidation extends DataValidation {

	public static boolean isValid(String json) {

		boolean valid = false;

		if (isNullOrEmpty(json)) {
			valid = false;
		} else {
			JSONObject jsonObj = null;
			try {
				jsonObj = (JSONObject) new JSONParser().parse(json);
			} catch (ParseException e) {
				valid = false;
			}
			if (isNullOrEmpty((String) jsonObj.get("customer")) || isNullOrEmpty((String) jsonObj.get("total_in_cents"))
					|| isNullOrEmpty((String) jsonObj.get("due_date")) || isNullOrEmpty((String) jsonObj.get("status")))
				valid = false;
			else if (!Customer.isValid((String) jsonObj.get("customer")))
				valid = false;
			else if (!Date.isValid((String) jsonObj.get("due_date")))
				valid = false;
			else if (!Cents.isValid((String) jsonObj.get("total_in_cents").toString()))
				valid = false;
			else if (!Status.isValid((String) jsonObj.get("status")))
				valid = false;
			else
				valid = true;
		}
		return valid;
	}

	public static boolean isNullOrEmpty(String param) {
		return param == null || param.trim().length() == 0;
	}

}
