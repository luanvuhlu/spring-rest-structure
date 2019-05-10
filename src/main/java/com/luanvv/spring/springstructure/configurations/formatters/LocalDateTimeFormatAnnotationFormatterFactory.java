package com.luanvv.spring.springstructure.configurations.formatters;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.support.EmbeddedValueResolutionSupport;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.format.annotation.DateTimeFormat;

public class LocalDateTimeFormatAnnotationFormatterFactory extends EmbeddedValueResolutionSupport
		implements AnnotationFormatterFactory<DateTimeFormat> {

	private static final Set<Class<?>> FIELD_TYPES;

	static {
		Set<Class<?>> fieldTypes = new HashSet<>(2);
		fieldTypes.add(LocalDateTime.class);
		fieldTypes.add(Long.class);
		FIELD_TYPES = Collections.unmodifiableSet(fieldTypes);
	}

	@Override
	public Set<Class<?>> getFieldTypes() {
		return FIELD_TYPES;
	}

	@Override
	public Printer<?> getPrinter(DateTimeFormat annotation, Class<?> fieldType) {
		return getFormatter(annotation, fieldType);
	}

	@Override
	public Parser<?> getParser(DateTimeFormat annotation, Class<?> fieldType) {
		return getFormatter(annotation, fieldType);
	}

	protected Formatter<LocalDateTime> getFormatter(DateTimeFormat annotation, Class<?> fieldType) {
		return new DateFormatter(resolveEmbeddedValue(annotation.pattern()));
	}

}
