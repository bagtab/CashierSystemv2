package view;

import controller.Controller;
import dto.UpdateDTO;

/**
 * creates a visual representation of the printed receipt
 */
public class Printer implements Observer {
	/**
	 * initializes the Printer and adds it to interfaces updated by
	 * <Code>Controller</Code>
	 */
	public Printer() {
		Controller.getController().addObserverForSalesEnd(this);
	}

	private class PrintedMoney extends GeneralDisplay {
		public PrintedMoney(String textToPrint) {
			super("money for customer");
			resetDisplayText();
			addText(textToPrint);
			display.setSize(300, 500);
			display();
		}
	}

	/**
	 * creates a visual representation of the printed receipt
	 */
	@Override
	public void update(UpdateDTO updateInfo) {
		new PrintedMoney(updateInfo.getText());
	}
}
