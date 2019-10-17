package sample;

public class Model {
	public float calculte(float num1, float num2, String operator){
		switch (operator){
			case "%":
				return num1 / (num2 * 100);
			case "+":
				return num1 + num2;
			case "-":
				return num1 - num2;
			case "*":
				return num1 * num2;
			case "/":
				if(num2==0)
					return 0;
				return num1 / num2;

			default:
				return 0;
		}
	}

	public float operations(float number, String operator){
		switch (operator){
			case "+/-":
				return 0-number;
			case "1/x":
				return 1/number;
			case "√":
				return (float) Math.sqrt(number);
			case "x²":
				return number*number;
			default:
				return 0;
		}
	}
}
