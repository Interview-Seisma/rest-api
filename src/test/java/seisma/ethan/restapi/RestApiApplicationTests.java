package seisma.ethan.restapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import seisma.ethan.restapi.utils.EmployeeUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RestApiApplicationTests {
	EmployeeUtil util = new EmployeeUtil();

	@Test
	void contextLoads() {
	}

	@Test
	public void testIncomeTax() {
		assertEquals(922, util.incomeTax(60050));

	}

	@Test
	public void testGrossIncome() {
		assertEquals(5004, util.grossIncome(60050));
	}

	@Test
	public void testSuperAnnual() {
		assertEquals(450, util.superAnnual(60050,9));
	}

	@Test
	public void testNetIncome() {
		assertEquals(4082, util.netIncome(60050));
	}
}
