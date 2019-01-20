package demo.lender;

public class LoanService {

	public static final int TIME_YEARS = 3;
	public static final int FREQUENCY_OF_COMPOUND_PER_YEAR = 12;

	public double calculateCost(double initialAmount, double rate) {
		double monthlyRate = (1+rate/ FREQUENCY_OF_COMPOUND_PER_YEAR);
		double equivalentInterestRate = Math.pow((monthlyRate), FREQUENCY_OF_COMPOUND_PER_YEAR*TIME_YEARS);
		return equivalentInterestRate * initialAmount;
	}

	public double calculateRepayments(double totalRepayment) {
		return totalRepayment/(TIME_YEARS*FREQUENCY_OF_COMPOUND_PER_YEAR);
	}
}
