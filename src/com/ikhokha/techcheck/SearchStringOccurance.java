package com.ikhokha.techcheck;

public class SearchStringOccurance extends CommentMetricProcessor {
	private String searchValue;

	public SearchStringOccurance(String key, String searchValue) {
		super(key);
		this.searchValue = searchValue;
	}
	
	boolean isConditionMet(String fileLine) {
		return fileLine.contains(this.searchValue);
	}
}

