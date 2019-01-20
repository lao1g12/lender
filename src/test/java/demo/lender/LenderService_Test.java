package demo.lender;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

public class LenderService_Test {


	private LenderService lenderService;
	private Lender lender1;
	private Lender lender2;
	private Lender lender3;
	private Lender lender4;

	@Before
	public void setup(){
		initMocks(this);
		lenderService = new LenderService();
		lender1 = new Lender("alice",200, 0.03);
		lender2 = new Lender("bob",400, 0.033);
		lender3 = new Lender("phil",300, 0.04);
		lender4 = new Lender("steve",10, 0.02);
		lenderService.loadLender(lender1);
		lenderService.loadLender(lender2);
		lenderService.loadLender(lender3);
		lenderService.loadLender(lender4);
	}

	@Test
	public void getLowestRate(){
		assertEquals(0.02, lenderService.getLowestRate(),0.0005);
	}
	@Test
	public void suitableLendersCheck(){
		ArrayList<Lender> suitableLenders = lenderService.findSuitableLenders(0.03+0.005);
		assertEquals("alice", suitableLenders.get(0).getName());
		assertEquals("bob", suitableLenders.get(1).getName());
		assertEquals("steve", suitableLenders.get(2).getName());
		assertEquals(3, suitableLenders.size());
	}

	@Test
	public void lenderPortions_test(){
		ArrayList<Lender> suitableLenders = lenderService.findSuitableLenders(0.03+0.005);
		assertEquals(40, lenderService.getLenderPortions(suitableLenders, 0.6666).size());
	}

	@Test
	public void lenderPortions_containsTenPortion(){
		ArrayList<Lender> suitableLenders = lenderService.findSuitableLenders(0.02);
		assertEquals(1, lenderService.getLenderPortions(suitableLenders, 0.6666).size());
	}

	@Test
	public void checkAmountCustomList_test(){
		ArrayList<Lender> suitableLenders = lenderService.findSuitableLenders(0.03+0.005);
		assertEquals(610,lenderService.checkAmount(suitableLenders), 0.5);
	}

	@Test
	public void checkAmountStock_test(){
		assertTrue(lenderService.checkAmount(500));
		assertFalse(lenderService.checkAmount(2000));
	}

	@Test
	public void findAverageRate_test(){
		ArrayList<Lender> suitableLenders = lenderService.findSuitableLenders(0.03+0.005);
		assertEquals(0.032, lenderService.findAverageRate(suitableLenders, 600),0.0005);
	}
}
