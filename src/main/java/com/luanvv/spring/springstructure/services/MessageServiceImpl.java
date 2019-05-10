package com.luanvv.spring.springstructure.services;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageSource ms;
	
	@Override
	public String get(String message, Locale locale) {
		return ms.getMessage(message, null, locale);
	}
	
	@Override
	public String get(String message, Locale locale, Object...args) {
		return ms.getMessage(message, args, locale);
	}
	
	@Override
	public String getMessage(String code, Object[] args, Locale locale) {
		return ms.getMessage(code, args, locale);
	}
}
