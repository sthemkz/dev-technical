package com.ikhokha.techcheck;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchLinkUrl extends CommentMetricProcessor {

	private final String UrlRegex = "\\b((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\b";

	public SearchLinkUrl(String key) {
		super(key);
	}
    
	boolean isConditionMet(String fileLine) {
		Pattern urlPattern = Pattern.compile(UrlRegex);
		Matcher urlMatcher = urlPattern.matcher(fileLine);
		return urlMatcher.find();
	}
}
