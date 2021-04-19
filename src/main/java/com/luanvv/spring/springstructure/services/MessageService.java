package com.luanvv.spring.springstructure.services;

import java.util.Locale;

import org.springframework.stereotype.Service;

/**
 * The Interface MessageService.
 * @author luanv
 */
public interface MessageService {

	/**
	 * Gets the.
	 *
	 * @param message the message
	 * @param locale the locale
	 * @return the string
	 */
	String get(String message, Locale locale);

	/**
	 * Gets the.
	 *
	 * @param message the message
	 * @param locale the locale
	 * @param args the args
	 * @return the string
	 */
	String get(String message, Locale locale, Object...args);

	/**
	 * Gets the message.
	 *
	 * @param code the code
	 * @param args the args
	 * @param locale the locale
	 * @return the message
	 */
	String getMessage(String code, Object[] args, Locale locale);
	
}
