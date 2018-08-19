package roger.training.util.validation;

import org.apache.commons.lang3.EnumUtils;
import org.json.simple.JSONObject;

public class Status extends DataValidation {

	public static boolean isValidStatus(JSONObject json) {
		boolean valid = false;
		if (EnumUtils.isValidEnum(roger.training.model.Status.class, (String) json.get("status")))
			valid = true;
		return valid;
	}

}
