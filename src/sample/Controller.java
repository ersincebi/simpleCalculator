package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;

public class Controller {
	//http://javafx.com/javafx/8.0.221

	@FXML
	private Label res;
	private float num1 = 0;
	private String operator = "";
	private boolean start = true;
	private Model model = new Model();

	/**
	 * If any number clicked this
	 * function will occur
	 * and fill the label
	 * @param event
	 */
	@FXML
	public void processNum(ActionEvent event){
		if(start){
			res.setText("");
			start = false;
		}
		String val = ((Button)event.getSource()).getText();
		if(selectedChar(val,0))
			res.setText("");
		res.setText(res.getText() + val);
	}

	/**
	 * The main calculatin
	 * operations happens here
	 * @param event
	 */
	@FXML
	public void processOperators(ActionEvent event){
		String val = ((Button)event.getSource()).getText();

		clearOperations(val);

		if(!val.equals("=")){
			if(!operator.isEmpty())
				return;
			operator = val;
			num1 = Float.parseFloat(res.getText());
			res.setText("");
		} else {
			if(operator.isEmpty())
				return;
			float num2 = Float.parseFloat(res.getText());
			float modelRes = model.calculte(num1,num2,operator);
			res.setText(String.valueOf(modelRes));
			operator = "";
			start = true;
		}
	}

	/**
	 * makes calculation for
	 * special characters
	 * on calculators panel
	 * @param event
	 */
	@FXML
	public void operations(ActionEvent event){
		String val = ((Button)event.getSource()).getText();
		num1 = Float.parseFloat(res.getText());
		float modelRes = model.operations(num1,val);
		res.setText(String.valueOf(modelRes));
		start = true;
	}

	/**
	 * checks the first input
	 * is it 0 or not
	 *
	 * @param val value in the label
	 *           I make it this way, because I may use
	 *           this function for another purposes
	 * @param at which charecter to check
	 * @return boolean
	 */
	public boolean selectedChar(String val, int at){
		return ("0".equals(val.charAt(at)));
	}

	/**
	 * according to operator
	 * wheter resets the calculation
	 * or last value entered
	 * @param operator identifier
	 */
	public void clearOperations(String operator){
		if(operator.equals("C")){
			num1 = 0;
			start = false;
			res.setText("0");
		} if(operator.equals("CE")){
			num1 = 0;
			res.setText("0");
		}
		return;
	}
}
