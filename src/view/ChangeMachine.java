package view;

import controller.Controller;
import dto.UpdateDTO;

/**
 * a machine that tells that gives the cashier the change to give to the
 * customer.
 * 
 * @author Linus Johannisson
 */
public class ChangeMachine implements Observer {
	private int[] moneyInBills;
	private int moneyToReturn;
	public ChangeMachine() {
		Controller.getController().addObserverForSalesEnd(this);
	}
	/**
	 * returns the amount of money in a new window
	 * 
	 * @param amount
	 *            money to return
	 */
	public void getChange(Double amount) {
		if (amount > 0) {
			moneyToReturn = amount.intValue();
			getCash();
			returnCash(StringOfMoney());
		}
	}
	/**
	 * prints money if <code>ItemDTO</code> in updateInfo is null. amount of money is defined by cost in updateInfo 
	 */
	@Override
	public void update(UpdateDTO updateInfo) {
		if(updateInfo.getItem() == null) {
			int amount = (int) updateInfo.getReturnCash();
			if (amount > 0) {
				moneyToReturn = amount;
				getCash();
				returnCash(StringOfMoney());
			}
		}
	}

	/**
	 * creates a string representation of the money that is returned
	 * 
	 * @return a String representation of the money that is returned
	 */
	private String StringOfMoney() {
		String textOfMoney = "";
		for (int i = 0; i < 10; i++) {
			if (moneyInBills[i] > 0) {
				textOfMoney += moneyInBills[i] + " x " + getTextOfValue(i) + System.lineSeparator();
			}
		}
		return textOfMoney;
	}

	/**
	 * returns the value corresponding to the right money bill
	 * 
	 * @param integer
	 *            integer that corresponds to a money bill
	 * @return value corresponding to the integer
	 */
	private int getTextOfValue(int integer) {
		switch (integer) {
		case 0:
			return 1000;
		case 1:
			return 500;
		case 2:
			return 200;
		case 3:
			return 100;
		case 4:
			return 50;
		case 5:
			return 20;
		case 6:
			return 10;
		case 7:
			return 5;
		case 8:
			return 2;
		default:
			return 1;
		}
	}

	/**
	 * transforms the change to a cash representation
	 */
	private void getCash() {
		moneyInBills = new int[10];
		addAmountOfValue(1000, 0);
		addAmountOfValue(500, 1);
		addAmountOfValue(200, 2);
		addAmountOfValue(100, 3);
		addAmountOfValue(50, 4);
		addAmountOfValue(20, 5);
		addAmountOfValue(10, 6);
		addAmountOfValue(5, 7);
		addAmountOfValue(2, 8);
		addAmountOfValue(1, 9);

	}

	/**
	 * calculates the amount of the given value needed for optimal cash
	 * representation
	 * 
	 * @param value
	 *            the value to convert to cash
	 * @param i
	 *            internal numeration in moneyInBills
	 */
	private void addAmountOfValue(int value, int i) {
		while (moneyToReturn >= value) {
			moneyInBills[i]++;
			moneyToReturn -= value;
		}
	}

	/**
	 * creates a window with the money that is returned
	 * 
	 * @param textOfMoney
	 *            text representation of the cash to return
	 */
	private void returnCash(String textOfMoney) {
		new PrintedMoney(textOfMoney);	
	}
	private class PrintedMoney extends GeneralDisplay{
		public PrintedMoney(String textToPrint) {
			super("money for customer");
			display.setSize(300, 300);
			addText(textToPrint);
			display();
		}
	}
}
