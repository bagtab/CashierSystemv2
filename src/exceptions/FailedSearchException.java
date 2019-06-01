package exceptions;
/**
 * exception thrown if the String ItemID couldn't find a matching ItemDTO in the database.
 * @author mrjoh
 *
 */
public class FailedSearchException extends Exception {
	/**
	 * 
	 * @param errorMessage message for the exception
	 */
	public FailedSearchException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
