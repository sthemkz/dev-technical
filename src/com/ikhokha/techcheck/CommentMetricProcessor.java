package com.ikhokha.techcheck;
/**
 * Abstract class that is a parent for all metrics
 * It is used as a common type in a list that holds all of the derived classes
 * 
 */
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
