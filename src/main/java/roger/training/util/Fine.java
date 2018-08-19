package roger.training.util;

import java.math.BigDecimal;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Fine {

	public static int howLongMonths(Date dueDate) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(dueDate);
		c.add(Calendar.DATE, 1);
		dueDate = c.getTime();
		return Period.between(dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
				(new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getMonths();
	}

	public static boolean isMoreThanTenDaysExpired(Date dueDate) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(dueDate);
		c.add(Calendar.DATE, 10);
		return (howLongMonths(c.getTime())) > 0;
	}

	public static BigDecimal calculate(BigDecimal amount, Date dueDate) throws Exception {
		BigDecimal rate, temp, interest, rounded, cents = new BigDecimal(0);
		int months = howLongMonths(dueDate);

		if (months > 0) {
			rate = isMoreThanTenDaysExpired(dueDate) ? new BigDecimal(0.01) : new BigDecimal(0.005);
			temp = amount.multiply(rate);
			interest = temp.multiply(new BigDecimal(months));
			rounded = interest.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			cents = new BigDecimal(rounded.intValueExact());
		}
		return cents;
	}

}
