package view;

import controller.Controller;
import dto.UpdateDTO;
/**
 * displays the total revenue since the program was started.
 * @author Linus Johannisson
 *
 */
public class TotalRevenueDisplay extends GeneralDisplay implements Observer{
	private double totalRevenue;
	public TotalRevenueDisplay() {
		super("Total Revenue");
		totalRevenue = 0;
		Controller.getController().addObserverForSalesEnd(this);	
	}
	/**
	 * adds the last runs revenue to the total revenue and displays the total revenue
	 * @param updateInfo updateDTO storing the increase of revenue
	 */
	@Override
	public void update(UpdateDTO updateInfo) {
		totalRevenue += updateInfo.getCost();
		resetDisplayText();
		addText("total revenue: " + Math.round(totalRevenue));
		display();
	}

}
