package view;

import controller.Controller;
import dto.QuantifiedItemDTO;
import dto.UpdateDTO;

/**
 * a display that shows the customer the latest added item and the current total
 * 
 * @author Linus Johannisson
 */
public class Display extends GeneralDisplay implements Observer {
	public Display() {
		super("display");
		display.setSize(300, 500);
		Controller.getController().addObserverForScans(this);
	}

	/**
	 * adds the itemToDisplay to the displayedText
	 * 
	 * @param itemToDisplay
	 */
	private void showItem(QuantifiedItemDTO itemToDisplay) {
		addText(itemToDisplay.getDescription() + addSpaces(25 - itemToDisplay.getDescription().length())
				+ itemToDisplay.getPrice() * itemToDisplay.getQuantity());
		if (itemToDisplay.getQuantity() > 1) {
			generateQuantity(itemToDisplay);
		}
	}

	/**
	 * adds the quantity of itemToDisplay to the displayed text
	 * 
	 * @param itemToDisplay
	 */
	private void generateQuantity(QuantifiedItemDTO itemToDisplay) {
		addText(itemToDisplay.getQuantity() + " st x " + itemToDisplay.getPrice());
	}
	/**
	 * updates the display with the latest addied item and new cost
	 */
	@Override
	public void update(UpdateDTO updateInfo) {
		resetDisplayText();
		if(updateInfo.getItem() != null) {
			showItem(updateInfo.getItem());			
		}
		generateTotal(updateInfo);
		lastUpdate = updateInfo;
		display();

	}
}
