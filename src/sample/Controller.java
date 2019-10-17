package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;

public class Controller {

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
			setLabelText("");
			setStart(false);
		}
		String val = ((Button)event.getSource()).getText();
		if(selectedChar(val,0))
			setLabelText("");
		setLabelText(res.getText() + val);
	}

	/**
	 * The main calculatin
	 * operations happens here
	 * @param event
	 */
	@FXML
	public void processOperators(ActionEvent event){
		String val = pressedButton(event);
		clearOperations(val);
		if(!val.equals("="))
			makeCalculation(val);
		else
			makeOperation();
	}

	/**
	 * makes calculation for
	 * special characters
	 * on calculators panel
	 * @param event
	 */
	@FXML
	public void operations(ActionEvent event){
		String val = pressedButton(event);
		setNum1(Float.parseFloat(res.getText()));
		float modelRes = model.operations(num1,val);
		setLabelText(String.valueOf(modelRes));
		setStart(true);
	}

	/**
	 * seeks for the button event
	 * and returns the value in
	 * pressed button
	 * @param event
	 * @return
	 */
	public String pressedButton(ActionEvent event){
		return ((Button)event.getSource()).getText();
	}

	/**
	 * gets the operand
	 * and the first value
	 * @param val
	 */
	public void makeCalculation(String val){
		if(!operator.isEmpty())
			return;
		setOperator(val);
		setNum1(Float.parseFloat(res.getText()));
		setLabelText("");
	}

	/**
	 * takes the values and returns result
	 */
	public void makeOperation(){
		if(operator.isEmpty())
			return;
		float num2 = Float.parseFloat(res.getText());
		setLabelText(String.valueOf(getResult(num2)));
		setOperator("");
		setStart(true);
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
	 * sets the first value of operation as given
	 */
	public void setNum1(float num1){ this.num1 = num1; }

	/**
	 * returns the last operand
	 * @param val
	 */
	public void setOperator(String val){ this.operator = val;	}

	/**
	 * returns the last result
	 * @return
	 */
	public float getResult(float num2) {
		return  model.calculte(num1,num2,operator);
	}

	/**
	 * sets the start
	 * so the operations can began
	 * @param start
	 */
	public void setStart(boolean start) { this.start = start; }

	/**
	 * sets the label as given value
	 * @param val
	 */

	public void setLabelText(String val){ res.setText(val); }
	/**
	 * according to operator
	 * wheter resets the calculation
	 * or last value entered
	 * @param operator identifier
	 */
	public void clearOperations(String operator){
		if(operator.equals("C")){
			setNum1(0);
			setStart(false);
			setLabelText("0");
		} if(operator.equals("CE")){
			setNum1(0);
			setLabelText("0");
		}
		return;
	}
}
