package roger.training.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import roger.training.util.ConvertMoney;

public class ConvertMoneyTest {

	@Test
	public void testConvert() {
		assertEquals("-120", String.valueOf(ConvertMoney.toCents("-1.20")));
		assertEquals("120", String.valueOf(ConvertMoney.toCents("1.20")));
		assertEquals("1020", String.valueOf(ConvertMoney.toCents("10.20")));
		assertEquals("10.20", String.valueOf(ConvertMoney.fromCents("1020").toString()));
		assertEquals("0.00", String.valueOf(ConvertMoney.fromCents(null)));
		assertEquals("0", String.valueOf(ConvertMoney.toCents(null)));
	}

}
