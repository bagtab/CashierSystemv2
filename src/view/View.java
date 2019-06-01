package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import controller.Controller;

/**
 * the main class used for the cashier to communicate with the program. it
 * currently simulates an user interface through the console.
 * 
 * @author Linus Johannisson
 *
 */
public class View {
	private Controller controller;
	private boolean stillScanning;
	private BufferedReader reader;
	public View() {
		controller = Controller.getController();
	}

	/**
	 * starts a new sale
	 */
	public void startNewSale() {
		controller.startNewSale();
	}

	/**
	 * initializes the program for scanning items through the console. the input
	 * options are: scan #itemID#(example: "scan 0"), addMany #quantity#(example:
	 * "addMany 7")and done(example: "done")
	 * 
	 * @throws IOException
	 */
	public void scanItems() {
		stillScanning = true;
		reader = new BufferedReader(new InputStreamReader(System.in));
		while (stillScanning) {
			String input;
			try {
				input = reader.readLine();
			} catch (IOException e) {
				input = "";
			}
			try {
				executeInput(input);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * executes the entered input for scanning items
	 * 
	 * @param dividedInput
	 * @throws IOException
	 *             exception thrown due to input not matching existing commands
	 */
	private void executeInput(String input) throws IOException {
		input = input.trim();
		String[] dividedInput = input.split("\\s+");
		if (dividedInput.length > 2 || dividedInput.length == 0) {
			throw new IOException("input must match existing commands");
		}

		if (getCommand(dividedInput).equals("addMany")) {
			controller.scanManyItems(extractData(dividedInput));
		}
		if (getCommand(dividedInput).equals("scan")) {
			controller.scanItem(extractData(dividedInput));
		}
		if (getCommand(dividedInput).equals("done")) {
			stillScanning = false;
		}
	}

	/**
	 * initializes the program for a customerID entered in the Console.
	 * 
	 * @throws IOException exception thrown by the 
	 */
	public void checkForDiscount() throws IOException {
		String input = reader.readLine();
		controller.applyDiscount(input);
	}

	/**
	 * initializes the program for payment entered in the Console. the payment must
	 * be written with numbers
	 * 
	 * @throws IOException
	 */
	public void PayForSale() throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		String input = reader.readLine();
		controller.payAndEndSale(Double.parseDouble(input));
	}

	/**
	 * extracts the data part of the input String array
	 * 
	 * @param dividedInput
	 *            input String split space symbols
	 * @return the Data from dividedInput
	 */
	private int extractData(String[] dividedInput) {
		return Integer.parseInt(dividedInput[1]);
	}

	/**
	 * extracts the command part from the input String array
	 * 
	 * @param dividedInput
	 *            input String split space symbols
	 * @return the command of the input
	 */
	private String getCommand(String[] dividedInput) {
		return dividedInput[0];
	}
}
