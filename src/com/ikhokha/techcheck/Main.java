package com.ikhokha.techcheck;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		
		Map<String, Integer> totalResults = new HashMap<>();
				
		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));
		ArrayList<CommentMetricProcessor> commentMetrics= new ArrayList<>(Arrays.asList(new SearchLinkUrl("SPAM"),new SearchOccurance( "QUESTIONS", "?"),new SearchOccurance( "SHAKER_MENTIONS", "Shaker"),new SearchOccurance( "MOVER_MENTIONS", "Mover"),new ShortComments( "SHORTER_THAN_15")));
		
		for (File commentFile : commentFiles) {
			
			CommentAnalyzer commentAnalyzer = new CommentAnalyzer(commentFile,commentMetrics);
			Map<String, Integer> fileResults = commentAnalyzer.analyze();
			addReportResults(fileResults, totalResults);
						
		}
		
		System.out.println("RESULTS\n=======");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
	}
	
	/**
	 * This method adds the result counts from a source map to the target map 
	 * @param source the source map
	 * @param target the target map
	 */
	private static void addReportResults(Map<String, Integer> source, Map<String, Integer> target) {

		for (Map.Entry<String, Integer> entry : source.entrySet()) {
			target.putIfAbsent(entry.getKey(), 0);
			target.put(entry.getKey(), entry.getValue()+target.get(entry.getKey()));
		}
		
	}

}
