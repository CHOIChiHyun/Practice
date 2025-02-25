package calculator;

public class Calculator {
	double result = 0;
	
	public double add(Double num1, Double num2) {
		result = num1 + num2;
		return result;
	}
	
	public double sub(Double num1, Double num2) {
		result = num1 - num2;
		return result;
	}
	
	public double mul(Double num1, Double num2) {
		result = num1 * num2;
		return result;
	}
	
	public double div(Double num1, Double num2) {
		result = num1 / num2;
		return result;
	}
}
