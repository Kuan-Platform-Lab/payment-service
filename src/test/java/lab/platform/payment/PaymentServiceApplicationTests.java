package lab.platform.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class PaymentServiceApplicationTests {
	@Test
	void declinesAboveLimit() {
		var c = new PaymentController();
		assertEquals("APPROVED", c.pay(new PaymentController.PaymentRequest(1, new BigDecimal("100"))).status());
		assertEquals("DECLINED", c.pay(new PaymentController.PaymentRequest(2, new BigDecimal("20000"))).status());
	}
}
