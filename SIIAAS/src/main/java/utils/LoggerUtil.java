package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Logger Utility Class
 * 
 * Purpose:
 * 1. Print logs in console
 * 2. Maintain clean reusable logging methods
 * 3. Used across framework
 * 
 * Why Utility Class?
 * 
 * Instead of writing:
 * 
 * Logger logger = LoggerFactory.getLogger(...)
 * 
 * in every class,
 * we create reusable methods here.
 */

public class LoggerUtil {

    /*
     * Create Logger Object
     * 
     * LoggerFactory is provided by SLF4J
     */
    public static final Logger logger =

            LoggerFactory.getLogger(LoggerUtil.class);

    /*
     * INFO LOG
     * 
     * Used for normal execution steps
     * 
     * Example:
     * Launching browser
     * Entering username
     * Clicking login
     */
    public static void info(String message) {

        logger.info(message);
    }

    /*
     * ERROR LOG
     * 
     * Used when test fails
     * or exceptions occur
     */
    public static void error(String message) {

        logger.error(message);
    }

    /*
     * WARNING LOG
     * 
     * Used for warnings
     * 
     * Example:
     * Element taking longer to load
     */
    public static void warn(String message) {

        logger.warn(message);
    }

    /*
     * DEBUG LOG
     * 
     * Used for debugging purpose
     * 
     * Mostly used by automation engineers
     * during framework debugging
     */
    public static void debug(String message) {

        logger.debug(message);
    }
}