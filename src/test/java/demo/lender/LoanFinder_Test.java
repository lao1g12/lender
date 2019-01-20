package demo.lender;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class LoanFinder_Test {

	private LenderService lenderService;
	private Random random;
	private LoanFinder loanFinder;
	private LoanService loanService;
	private Lender lender1;
	private Lender lender2;
	private Lender lender3;
	private Lender lender4;

	@Before
	public void setup(){
		initMocks(this);
		lenderService = new LenderService();
		loanService = new LoanService();
		random = new Random();
		loanFinder = new LoanFinder(lenderService, loanService, random);
		lender1 = new Lender("alice",400, 0.03);
		lender2 = new Lender("bob",450, 0.031);
		lender3 = new Lender("phil",100, 0.03);
		lender4 = new Lender("nancy",300, 0.05);
		lenderService.loadLender(lender1);
		lenderService.loadLender(lender2);
		lenderService.loadLender(lender3);
		lenderService.loadLender(lender4);
	}

	@Test
	public void requestLoan(){
		Loan loan = loanFinder.findLoan(500);
		assertEquals(new Loan(500,0.03,15.2,547.03), loan);
	}

	@Test
	public void requestLoan_initialPoolTooSmall(){
		Loan loan = loanFinder.findLoan(1200);
		assertEquals(new Loan(1200,0.035,37.02,1332.65), loan);
	}

	@Test
	public void requestLoan_tooMuch_null(){
		assertNull(loanFinder.findLoan(2100));
	}

	@Test
	public void findPoolOfLenders(){
		ArrayList<Lender> suitableLenders = new ArrayList<>();
		suitableLenders.add(lender1);
		suitableLenders.add(lender2);
		suitableLenders.add(lender3);
		ArrayList<Lender> poolOfLenders = loanFinder.findPoolOfLenders(500, suitableLenders);
		assertEquals(21, getCount(poolOfLenders, lender1));
		assertEquals(24, getCount(poolOfLenders, lender2));
		assertEquals(5, getCount(poolOfLenders, lender3));
		assertEquals(0, getCount(poolOfLenders, lender4));

	}

	private long getCount(ArrayList<Lender> poolOfLenders, Lender expectedLender) {
		return poolOfLenders.stream().filter(lender -> expectedLender.getName().equals(lender.getName())).count();
	}
}
