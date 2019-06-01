package dto;

public class UpdateDTO {
	private QuantifiedItemDTO item;
	private double cost;
	private double vat;
	private String text;
	public UpdateDTO(QuantifiedItemDTO item, double cost, double vat, String text) {
		this.item = item;
		this.cost = cost;
		this.vat = vat;
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
	 * @return the vat
	 */
	public double getVat() {
		return vat;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

}
