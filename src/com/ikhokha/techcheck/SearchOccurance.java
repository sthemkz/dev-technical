package com.ikhokha.techcheck;

public class SearchOccurance extends CommentAnalysis {
    private String searchValue;
	  

    public SearchOccurance(String key,String searchValue) {
        super(key);
        this.searchValue=searchValue;
}
 
    boolean results(String line){
        return line.contains(this.searchValue);
    }  
}
