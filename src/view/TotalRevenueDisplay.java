package view;

import controller.Controller;
import dto.UpdateDTO;
/**
 * displays the total revenue since the program was started.
 * @author Linus Johannisson
 *
 */
public class TotalRevenueDisplay extends GeneralDisplay implements Observer{
	private int totalRevenue;
	public TotalRevenueDisplay() {
		super("Total Revenue");
		totalRevenue = 0;
		Controller.getController().addObserverForSalesEnd(this);
	}

	@Override
	public void update(UpdateDTO updateInfo) {
		totalRevenue += updateInfo.getCost();
		resetDisplayText();
		addText("total revenue: " + totalRevenue);
		display();
	}

}
