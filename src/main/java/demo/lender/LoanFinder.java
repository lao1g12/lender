package demo.lender;

import org.apache.commons.math3.util.Precision;

import java.util.*;

public class LoanFinder {

	private LenderService lenderService;
	private LoanService loanService;
	private Random random;

	public LoanFinder(LenderService lenderService, LoanService loanService, Random random) {
		this.lenderService = lenderService;
		this.loanService = loanService;
		this.random = random;
	}

	public Loan findLoan(double amount) {
		if(!lenderService.checkAmount(amount)){
			return null;
		}
		double lowestRate = lenderService.getLowestRate();
		ArrayList suitableLenders = findSuitableLoans(amount, lowestRate);
		ArrayList poolOfLenders = findPoolOfLenders(amount, suitableLenders);
		double rate = Precision.round(lenderService.findAverageRate(poolOfLenders, amount),3);
		double totalRepayment = Precision.round(loanService.calculateCost(amount, rate), 2);
		double monthlyRepayment = Precision.round(loanService.calculateRepayments(totalRepayment), 2);
		return new Loan(amount, rate, monthlyRepayment, totalRepayment);
	}

	ArrayList findPoolOfLenders(double amount, ArrayList<Lender> suitableLenders){
		double portionRatio = amount/lenderService.checkAmount(suitableLenders);
		ArrayList<Lender> poolOfPortions = lenderService.getLenderPortions(suitableLenders, portionRatio);
		int sizeOfPool = poolOfPortions.size();
		if (sizeOfPool < (amount/10)) {
			suitableLenders.sort(Comparator.comparing(Lender::getAmount));
			Lender lender = suitableLenders.get(suitableLenders.size() - 1);
			double requiredPortions = (amount / 10) - sizeOfPool;
			for (int i = 0; i < requiredPortions; i++) {
				Lender newLender = new Lender(lender.getName(), 10, lender.getRate());
				poolOfPortions.add(newLender);
			}
		}
		return poolOfPortions;
	}

	ArrayList<Lender> findSuitableLoans(double amount, double rate) {
		ArrayList<Lender> potentialLenders = lenderService.findSuitableLenders(rate);
		if (lenderService.checkAmount(potentialLenders) < amount){
			potentialLenders = findSuitableLoans(amount, rate+0.005);
		}
		return potentialLenders;
	}
}
