package dto;

public class UpdateDTO {
	private QuantifiedItemDTO item;
	private double cost;
	private double returnCash;
	private String text;
	public UpdateDTO(QuantifiedItemDTO item, double cost, double returnCash, String text) {
		this.item = item;
		this.cost = cost;
		this.returnCash = returnCash;
		this.text = text;
	}

	/**
	 * @return the item
	 */
	public QuantifiedItemDTO getItem() {
		return item;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @return the returnCash
	 */
	public double getReturnCash() {
		return returnCash;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

}
