package roger.training.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class ConvertMoney {

	public static Integer toCents(Object amount) {
		BigDecimal result = new BigDecimal((String) Optional.ofNullable(amount).orElse("0"));
		BigDecimal bigDecimalAmount = result.setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal amountInCents = bigDecimalAmount.movePointRight(2);
		return amountInCents.intValueExact();
	}

	public static BigDecimal fromCents(Object amount) {
		return new BigDecimal(Optional.ofNullable((String) Optional.ofNullable(amount).orElse("0")).orElse("0"))
				.movePointLeft(2);
	}

}
