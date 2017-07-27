package com.ngc.transformers.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EmailValidator {

	INSTANCE;
	
	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	public boolean validate(final String hex) {
		if(hex == null || hex.isEmpty()){
			return false;
		}
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}
	
}
