How to run:
Currently the .exe doesn't work. However the Jar file (in the target file) can be run in a similar way to the .exe
using "java -jar [Jar Name] [Market File] [Amount]". Otherwise the main method can be run in an IDE (However the
argument entry needs to be commented out and the string inputs need to be uncommented.

How it works:
This programme will load in the market data to an arraylist in memory. It will then take in the request, check the
amount is in stock. It will then create a pool of lenders who can offer the lowest interest rate to fill the loan
(Will incrementally increase the max rate considered till it finds a pool great enough to fill the loan).
The system then takes a portion of each lenders loan amount and adds it to a "pool of loans" made up of £10 loans
similar to how zopa does their own matching algorithm. When figuring out the number of £10 loans, it will always round
down to avoid rounding errors. The system will then check the difference between the amount provided in the "pool of loans"
and the amount requested. If they are not equal then the extra £10 loans are taken from the suitable lender offering
the largest amount to loan. The rate is then figured out based on the lenders individual rates. The total loan cost
is then figured out using this rate and then the details of repayment are presented back to the user.

Assumptions:
-The system does not update the market data, this is because it was not specified and also the data is loading on
each request so persistence is useless.
-Lenders must only offer values in £10 increments.
-Loans must be between £1000-£15000 of increments of £100
-Market data: The first row of the data is ignored as it is for column names.
