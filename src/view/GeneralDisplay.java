package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import dto.UpdateDTO;

public class GeneralDisplay {
	protected JFrame display;
	protected String displayedText;
	protected UpdateDTO lastUpdate;
	protected JTextArea textField;
	public GeneralDisplay(String title){
		display = new JFrame(title);
		displayedText = "";
		display.setLayout(new BorderLayout());
		display.setVisible(true);
		textField = new JTextArea(displayedText);
		textField.setEditable(false);
		display.add(textField, BorderLayout.NORTH);
		display.setSize(300, 300);

	}
	/**
	 * puts the displayedText in the textField
	 */
	protected void display() {
		textField.setText(displayedText);
	}
	/**
	 * appends addedText to displayedText
	 * @param addedText
	 */
	protected void addText(String addedText) {
		displayedText += addedText + System.lineSeparator();
	}
	/**
	 * generats string with amountOfspaces number of spaces
	 * @param amountOfSpaces
	 * @return a string with spaces
	 */
	protected String addSpaces(int amountOfSpaces) {
		String spaces = "";
		while (amountOfSpaces > 0) {
			spaces += ' ';
			amountOfSpaces--;
		}
		return spaces;
	}
	/**
	 * adds the total to the displayed text
	 * @param updateInfo
	 */
	protected void generateTotal(UpdateDTO updateInfo) {
		addText("total" + addSpaces(20) + updateInfo.getCost());
	}

	/**
	 * empties the displayedText
	 */
	protected void resetDisplayText() {
		displayedText = "";
	}
}