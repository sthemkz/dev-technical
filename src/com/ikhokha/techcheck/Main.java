package com.ikhokha.techcheck;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final int MaxNumberOfThreads = (int) Math.ceil((double) Runtime.getRuntime().availableProcessors() / 2);

		Map<String, Integer> totalProcessedOutputMetricsResults = new HashMap<>();
		ArrayList<CommentMetricProcessor> commentMetrics = getCommentMetric();
		
		ArrayList<CommentAnalyzer> tasks = getTasks(commentMetrics);
		ExecutorService tasksExecutorService = Executors.newFixedThreadPool(MaxNumberOfThreads);
		List<Future<Map<String, Integer>>> processedOutputMetricsResults = tasksExecutorService.invokeAll(tasks);
		tasksExecutorService.shutdown();
		
		for (Future<Map<String, Integer>> processedOutputMetricsResult : processedOutputMetricsResults) {
			addReportResults(processedOutputMetricsResult.get(), totalProcessedOutputMetricsResults);
		}

		System.out.println("RESULTS\n=======");
		totalProcessedOutputMetricsResults.forEach((key, value) -> System.out.println(key + " : " + value));

	}

	/**
	 * This method adds the result counts from a source map to the target map
	 * 
	 * @param finalInputMetricsResults           the source map
	 * @param finalOutputMetricsResults          the target map
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	private static void addReportResults(Map<String, Integer> finalInputMetricsResults,
			Map<String, Integer> finalOutputMetricsResults) {
		for (Map.Entry<String, Integer> entry : finalInputMetricsResults.entrySet()) {
			finalOutputMetricsResults.putIfAbsent(entry.getKey(), 0);
			finalOutputMetricsResults.put(entry.getKey(),
					entry.getValue() + finalOutputMetricsResults.get(entry.getKey()));
		}
	}
/**
 * This method create an ArrayList with each metric
 * A new metric is add as object on this method
 * @return ArrayList containing tracked metrics
 */
	private static ArrayList<CommentMetricProcessor> getCommentMetric() {
		return new ArrayList<>(Arrays.asList(new SearchLinkUrl("SPAM"), new SearchStringOccurance("QUESTIONS", "?"),
				new SearchStringOccurance("SHAKER_MENTIONS", "Shaker"),
				new SearchStringOccurance("MOVER_MENTIONS", "Mover"), new ShortComments("SHORTER_THAN_15")));
	}

	private static ArrayList<CommentAnalyzer> getTasks(ArrayList<CommentMetricProcessor> commentMetrics) {
		ArrayList<CommentAnalyzer> tasks = new ArrayList<>();
		File documentPath = new File("docs");
		File[] inputCommentFiles = documentPath.listFiles((directory, fileName) -> fileName.endsWith(".txt"));

		for (File inputCommentFile : inputCommentFiles) {
			tasks.add(new CommentAnalyzer(inputCommentFile, commentMetrics));
		}
		return tasks;
	}
}
