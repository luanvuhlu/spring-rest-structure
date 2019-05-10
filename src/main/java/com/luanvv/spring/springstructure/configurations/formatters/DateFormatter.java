package com.luanvv.spring.springstructure.configurations.formatters;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import com.google.common.base.Strings;
import com.luanvv.spring.springstructure.services.MessageService;

public class DateFormatter implements Formatter<LocalDateTime> {

	private static final String DEFAULT_DATE_FORMAT_KEY = "label.common.placeholder.date.format_YYYYMMDDHHmm";

	@Autowired
	private MessageService messageSource;

	private String pattern;

	/**
	 * Create a new default DateFormatter.
	 */
	public DateFormatter() {
		// empty
	}

	/**
	 * Create a new DateFormatter for the given date pattern.
	 */
	public DateFormatter(String pattern) {
		this();
		this.pattern = pattern;
	}

	/**
	 * Gets the pattern.
	 *
	 * @param locale the locale
	 * @return the pattern
	 */
	protected String getPattern(Locale locale) {
		if (Strings.isNullOrEmpty(pattern)) {
			this.pattern = messageSource.get(DEFAULT_DATE_FORMAT_KEY, locale);
		}
		return pattern;
	}

	@Override
	public String print(LocalDateTime dateTime, Locale locale) {
		return getDateFormat(locale).format(dateTime);
	}

	@Override
	public LocalDateTime parse(String text, Locale locale) throws ParseException {
		return LocalDateTime.parse(text, getDateFormat(locale));
	}

	protected DateTimeFormatter getDateFormat(Locale locale) {
		return DateTimeFormatter.ofPattern(getPattern(locale), locale);
	}

}
