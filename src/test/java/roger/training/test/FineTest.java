package roger.training.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import roger.training.util.Fine;

public class FineTest {

	@Test
	public void calculate() throws Exception {

		BigDecimal amount = new BigDecimal("1000");
		Calendar c = Calendar.getInstance();

		// 4 meses e 1 dia
		c.setTime(new Date());
		c.add(Calendar.MONTH, -4);
		c.add(Calendar.DATE, -1);
		assertEquals(new BigDecimal(40), Fine.calculate(amount, c.getTime()));

		// 3 meses e 1 dia
		c.setTime(new Date());
		c.add(Calendar.MONTH, -3);
		c.add(Calendar.DATE, -1);
		assertEquals(new BigDecimal(30), Fine.calculate(amount, c.getTime()));

		// 2 meses e 1 dia (vencido)
		c.setTime(new Date());
		c.add(Calendar.MONTH, -2);
		c.add(Calendar.DATE, -1);
		assertEquals(new BigDecimal(20), Fine.calculate(amount, c.getTime()));

		// 1 mes e 1 dia (vencido por mais de 15 dias)
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		c.add(Calendar.DATE, -11);
		assertEquals(new BigDecimal(10), Fine.calculate(amount, c.getTime()));

		// 1 mes e 1 dia (vencido por menos de 10 dias)
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		c.add(Calendar.DATE, -10);
		assertEquals(new BigDecimal(5), Fine.calculate(amount, c.getTime()));

		// 1 mes, dia do vencimento
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		assertEquals(new BigDecimal(0), Fine.calculate(amount, c.getTime()));

	}
}
