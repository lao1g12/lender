package demo.lender;

import java.util.Objects;

public class Loan {

	private double requestedAmount;
	private double rate;
	private double monthlyRepayment;
	private double totalRepayment;

	public Loan(double requestedAmount, double rate, double monthlyRepayment, double totalRepayment) {
		this.requestedAmount = requestedAmount;
		this.rate = rate;
		this.monthlyRepayment = monthlyRepayment;
		this.totalRepayment = totalRepayment;
	}

	public double getRequestedAmount() {
		return requestedAmount;
	}

	public void setRequestedAmount(double requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getMonthlyRepayment() {
		return monthlyRepayment;
	}

	public void setMonthlyRepayment(double monthlyRepayment) {
		this.monthlyRepayment = monthlyRepayment;
	}

	public double getTotalRepayment() {
		return totalRepayment;
	}

	public void setTotalRepayment(double totalRepayment) {
		this.totalRepayment = totalRepayment;
	}

	@Override
	public String toString() {
		return "Loan{" +
				"requestedAmount=" + requestedAmount +
				", rate=" + rate +
				", monthlyRepayment=" + monthlyRepayment +
				", totalRepayment=" + totalRepayment +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Loan loan = (Loan) o;
		return Double.compare(loan.requestedAmount, requestedAmount) == 0 &&
				Double.compare(loan.rate, rate) == 0 &&
				Double.compare(loan.monthlyRepayment, monthlyRepayment) == 0 &&
				Double.compare(loan.totalRepayment, totalRepayment) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(requestedAmount, rate, monthlyRepayment, totalRepayment);
	}
}
