package exceptions;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionLogger {
	File errorLog;
	PrintWriter writer;

	private ExceptionLogger() {
		
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
			errorLog = new File("errorLog " + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
			if(!errorLog.exists()) {
				errorLog.createNewFile();				
			}
			exception.printStackTrace(writer);
		} catch (IOException e) {
			System.out.println("file errorLog couldnt be created or found, the error log will not work");
		}
	}
}
