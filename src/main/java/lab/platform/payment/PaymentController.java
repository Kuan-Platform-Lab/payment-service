package lab.platform.payment;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
class PaymentController {

	private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
	private static final BigDecimal LIMIT = new BigDecimal("10000");

	record PaymentRequest(long orderId, BigDecimal amount) {}
	record PaymentResult(long orderId, String status) {}

	@PostMapping
	PaymentResult pay(@RequestBody PaymentRequest req) {
		String status = req.amount().compareTo(LIMIT) <= 0 ? "APPROVED" : "DECLINED";
		log.info("payment orderId={} status={}", req.orderId(), status);
		return new PaymentResult(req.orderId(), status);
	}
}
