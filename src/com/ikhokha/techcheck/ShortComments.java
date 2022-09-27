package com.ikhokha.techcheck;

public class ShortComments extends CommentMetricProcessor {
	public ShortComments(String key) {
		super(key);
	}
	
	boolean isConditionMet(String fileLine) {
		return fileLine.length() < 15;
	}
}