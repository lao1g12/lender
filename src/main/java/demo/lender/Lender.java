package demo.lender;

public class Lender {

	public String name;
	public double amount;
	public double rate;

	public Lender(String name, double amount, double rate) {
		this.name = name;
		this.amount = amount;
		this.rate = rate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Lender{" +
				"name='" + name + '\'' +
				", amount=" + amount +
				", rate=" + rate +
				'}';
	}
}
