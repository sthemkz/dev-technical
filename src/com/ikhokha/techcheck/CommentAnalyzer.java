package com.ikhokha.techcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class CommentAnalyzer implements Callable<Map<String, Integer>> {
	private ArrayList<CommentMetricProcessor> commentMetrics;
	private File inputFile;
	private Map<String, Integer> processedOutputMetricsResults = new HashMap<>();

	public CommentAnalyzer(File inputFile, ArrayList<CommentMetricProcessor> commentMetrics) {
		this.inputFile = inputFile;
		this.commentMetrics = commentMetrics;
	}

	private void commentMetricPopulate(CommentMetricProcessor commentMetric, String inputFile) {
		if (commentMetric.isConditionMet(inputFile)) {
			incrementOccurrence(commentMetric.getKey());
		}
	}

	/**
	 * This method increments a counter by 1 for a match type on the countMap.
	 * Uninitialized keys will be set to 1
	 * 
	 * @param countMap the map that keeps track of counts
	 * @param key      the key for the value to increment
	 */
	private void incrementOccurrence(String key) {
		processedOutputMetricsResults.putIfAbsent(key, 0);
		processedOutputMetricsResults.put(key, processedOutputMetricsResults.get(key) + 1);
	}

	@Override
	public Map<String, Integer> call() {

		try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

			String fileLine = null;
			while ((fileLine = reader.readLine()) != null) {
				for (CommentMetricProcessor commentMetric : commentMetrics) {
					commentMetricPopulate(commentMetric,fileLine);
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + inputFile.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error processing file: " + inputFile.getAbsolutePath());
			e.printStackTrace();
		}

		return processedOutputMetricsResults;
	}
}
