package alanard.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	static Logger logger = LogManager.getRootLogger();
	
	public static void entry(String s) {
		logger.entry(s);
	}
	
	public static void error(String s) {
		logger.error(s);
	}
	
	public static void info(String s) {
		logger.error(s);		
	}
	
	public static void debug(String s) {
		logger.debug(s);		
	}

	public static void warn(String s) {
		logger.warn(s);		
	}
	
	public static void fatal(String s) {
		logger.fatal(s);
	}
}
