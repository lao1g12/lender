package demo.lender;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class LoanService_Test {

	private LoanService loanService;

	@Before
	public void setup(){
		initMocks(this);
		loanService = new LoanService();
	}
	@Test
	public void compoundInterest(){
		assertEquals(547.03, loanService.calculateCost(500.00, 0.03), 0.005);
	}

	@Test
	public void compoundInterest2(){
		assertEquals(404.61, loanService.calculateCost(325.25, 0.073), 0.005);
	}

	@Test
	public void calculateRepayments(){
		assertEquals(11.24, loanService.calculateRepayments(404.61), 0.005);
	}
}
