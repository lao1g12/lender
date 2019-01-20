package demo.lender;

import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.Comparator;

public class LenderService {
	private ArrayList<Lender> lenders = new ArrayList<>();

	public void loadLender(Lender lender) {
		lenders.add(lender);
	}

	public ArrayList findSuitableLenders(double rate){
		ArrayList<Lender> suitableLenders = new ArrayList<>();
		lenders.forEach(
				(lender) -> {
					if (lender.getRate() <= rate){
						suitableLenders.add(lender);
					}
				}
		);
		return suitableLenders;
	}

	public ArrayList getLenderPortions(ArrayList<Lender> suitableLenders, double portionRatio) {
		ArrayList<Lender> lenderPortions = new ArrayList<>();
		suitableLenders.forEach(
				lender -> {
					double ratioAmount1 = lender.getAmount()*portionRatio;
					double ratioAmount2 = ratioAmount1/10;
					double ratioAmount = Math.floor(ratioAmount2);
					if (ratioAmount == 0){
						ratioAmount = 1;
					}
					for (int i = 0; i < ratioAmount ; i++) {
						Lender newLender = new Lender(lender.getName(), 10, lender.getRate());
						lenderPortions.add(newLender);
					}
				}
		);
		return lenderPortions;
	}

	public double checkAmount(ArrayList<Lender> lenderList){
		return lenderList.stream().mapToDouble(Lender::getAmount).sum();
	}

	public boolean checkAmount(double amount){
		double value = lenders.stream().mapToDouble(Lender::getAmount).sum();
		return (amount <= value);
	}

	public double getLowestRate(){
		return lenders
				.stream()
				.min(Comparator.comparing(Lender::getRate))
				.get().getRate();
	}

	public double findAverageRate(ArrayList<Lender> poolOfLenders, double amount) {
		double rate = 0;
		for (Lender lender : poolOfLenders) {
			double shareOfAmount = lender.getAmount() / amount;
			rate += shareOfAmount * lender.getRate();
		}
		return rate;
	}

}
