package demo.lender;

import org.apache.commons.math3.util.Precision;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class LenderApplication {

	public static void main(String[] args) {
		Random random = new Random();
		LenderService lenderService = new LenderService();
		LoanService loanService = new LoanService();
		LoanFinder loanFinder = new LoanFinder(lenderService, loanService, random);

		String fileLocation = args[0];
		double amount = Double.parseDouble(args[1]);
//		String fileLocation = "Market Data For Exercise.csv";
//		double amount = Double.parseDouble("15000");
		if((amount%100) != 0){
			System.out.println("Amount entered must be multiples of 100");
			return;
		}
		if(amount < 1000){
			System.out.println("Amount must be greater than 1000");
			return;
		}
		if(amount > 15000){
			System.out.println("Amount must be less than 15000");
			return;
		}
		List<Lender> lenders = readLendersFromCSV(fileLocation);

		for (Lender l : lenders) { lenderService.loadLender(l); }
		Loan loan = loanFinder.findLoan(amount);
		if(loan == null){
			System.out.println("Requested amount is more than what is in stock");
			return;
		}

		System.out.println("Requested Amount: £"+loan.getRequestedAmount());
		System.out.println("Rate: "+ Precision.round(loan.getRate()*100, 1)+"%");
		System.out.println("Monthly Repayment: £"+loan.getMonthlyRepayment());
		System.out.println("Total Repayment: £"+loan.getTotalRepayment());
	}


	private static List<Lender> readLendersFromCSV(String fileName) {
		List<Lender> lenders = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
	    try (
	    		BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
	    	  	String line = br.readLine();
				line = br.readLine();
	    	  	while (line != null) {
	    	  		String[] attributes = line.split(",");
	    	  		Lender lender = createLender(attributes);
	    	  		lenders.add(lender);
					line = br.readLine();

	    	  	}
	    } catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
			return lenders;
	}
	private static Lender createLender(String[] metadata) {
		String lender = metadata[0];
		double amountAvailable = Double.parseDouble(metadata[1]);
		double rate = Double.parseDouble(metadata[2]);
		return new Lender(lender, rate, amountAvailable);
	}

}

