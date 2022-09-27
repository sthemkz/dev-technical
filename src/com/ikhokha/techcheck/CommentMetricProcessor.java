package com.ikhokha.techcheck;

public abstract class CommentMetricProcessor {
	private String key;

	public CommentMetricProcessor(String key) {
		this.key = key;
	}

	abstract boolean isConditionMet(String fileLine);

	String getKey() {
		return this.key;
	}

}
