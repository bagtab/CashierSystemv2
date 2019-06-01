package exceptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExceptionLogger {
	File errorLog;
	FileWriter writer;

	private ExceptionLogger() {
		errorLog = new File("errorLog");
		try {
			if (!errorLog.exists()) {
				errorLog.createNewFile();
			}
			writer = new FileWriter(errorLog);
		} catch (IOException e) {
			System.out.println("file errorLog couldnt be created or found, the error log will not work");
		}
	}
	private static class ExceptionLoggerHolder {
		private static ExceptionLogger instance = new ExceptionLogger();
	}
	/**
	 * @return Singleton instance of Exceptionlogger
	 */
	public static ExceptionLogger getExceptionLogger() {
		return ExceptionLoggerHolder.instance;
	}

	/**
	 * logs the the exception when its thrown to the log file
	 * 
	 * @param source
	 *            text describing where the exception happened.
	 * @param exception
	 *            Exception to be logged.
	 */
	public void writeToLog(String source, Exception exception) {
		try {
			writer.append("exception happened in" + source + " : " + exception.getMessage());
		} catch (IOException newException) {
			// don't know how to log an exception in the exception logger, left empty
		} catch (NullPointerException newException) {
			// error message already sent
		}
	}

	/**
	 * sends the exception log to the developer. not actually implemented.
	 */
	public void sendLogToDeveloper() {
		try {
			writer.close();
		} catch (IOException e) {
			// don't know how to log an exception in the exception logger, left empty
		}
	}
}
