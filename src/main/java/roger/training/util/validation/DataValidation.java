package roger.training.util.validation;

import org.apache.commons.lang3.EnumUtils;

import roger.training.model.Status;

public abstract class DataValidation {

	public static boolean isValid(String param) {
		return EnumUtils.isValidEnum(Status.class, param);
	}

}
