package exceptions;

public class DatabaseFailureException extends RuntimeException {

	/**
	 * don't understand serialversionUID, auto-generated to remove warnings
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * creates a new instance representing the condition described in the specified
	 * message.
	 * 
	 * @param errorMessage
	 *            message that describes what went wrong
	 */
	public DatabaseFailureException(String errorMessage) {
		super(errorMessage);
	}
}
