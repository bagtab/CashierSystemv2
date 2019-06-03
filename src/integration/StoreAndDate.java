package integration;

public class StoreAndDate {
	private String name = "butiken";
	private String dateAndTime = "1/1 2019 00:00:00";
	private String address = "gatagatan 3";
	/**
	 * private constructor for StoreAndDate. is private due to Singleton pattern.
	 */
	private StoreAndDate(){
		
	}
	/**
	 * holds the singleton instance of storeAndDate
	 * @author mrjoh
	 *
	 */
	private static class StoreAndDateHolder {
		private static StoreAndDate storeData = new StoreAndDate();
	}
	/**
	 * gets the singleton instance of StoreAndDate
	 * @return Singleton instance of StoreAndDate
	 */
	public static StoreAndDate getStoreAndDate() {
		return StoreAndDateHolder.storeData;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the dateAndTime
	 */
	public String getDateAndTime() {
		return dateAndTime;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
}
