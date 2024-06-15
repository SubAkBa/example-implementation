package com.example.jwtfilter.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageUtil {

	private final MessageSource messageSource;

	public String getMessage(String messageKey) {
		return getMessage(messageKey, null);
	}

	public String getMessage(String messageKey, Object[] args) {
		try {
			return messageSource.getMessage(messageKey, args, Locale.KOREAN);
		} catch (NoSuchMessageException exception) {
			return null;
		}
	}
}
