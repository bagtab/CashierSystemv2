package startup;

import java.io.IOException;
import exceptions.ExceptionLogger;
import view.ChangeMachine;
import view.Display;
import view.Printer;
import view.TotalRevenueDisplay;
import view.View;

public class Main {
	public static void main(String[] args) {
		View view = new View();
		new TotalRevenueDisplay();
		new Display();
		new ChangeMachine();
		new Printer();
		view.startNewSale();
		try {
			while(true) {
				view.scanItems();
				view.checkForDiscount();
				view.PayForSale();
			}
		} catch (IOException e) {
			ExceptionLogger.getExceptionLogger().writeToLog("main.Main", e);
			e.printStackTrace();
		}
	}

}
