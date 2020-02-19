/**
 * Program Name: Y_A_NegativeValueException.java
 * Purpose: an exception that will be thrown if the user tries to enter negative values
 * Coder: Yevgeniya Anasheva
 * Date: Apr. 15, 2019
 */

public class Y_A_NegativeValueException extends Exception
{

	//constructor
	Y_A_NegativeValueException()
	{
		super("The number entered was negative, it will be converted to positive");
	}
	
}//end class