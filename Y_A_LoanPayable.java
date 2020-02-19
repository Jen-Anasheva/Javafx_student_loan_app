/**
 * Program Name: Y_A_LoanPayable.java
 * Purpose: holds a constant value called ANNUAL_RATE_TO_MONTHLY_RATE and an abstract method calculateLoanPayment() to be implemented
 * Coder: Yevgeniya Anasheva
 * Date: Apr. 1, 2019
 */

public interface Y_A_LoanPayable
{
	//this constant is used to convert whatever annual prime interest rate the user enters to the equivalents monthly rate decimal equivalent
	double ANNUAL_RATE_TO_MONTHLY_RATE = 1.0/1200;
	
	/*
	 * Method Name: calculateLoanPayment()
	 * Purpose: will return a double value that represents the loan payment amount, and it will accept three arguments: 
	 * 					a double representing the OSL or CSL principal amount, a double representing the annual prime interest rate, 
	 * 					and an int value that represents the amortization period in months.
	 * Accepts: a double for OSL or CSL principal amount, a double for prime interest rate and an int that represents the amortization
	 * Returns: a double
	 */
	public abstract double calculateLoanPayment(double amount, double interest, int amortization);
	
}//end class