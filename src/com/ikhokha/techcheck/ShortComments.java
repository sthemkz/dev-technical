package com.ikhokha.techcheck;

public class  ShortComments extends CommentMetricProcessor {
	  public ShortComments( String key) {
        super(key);	
  }
   
	  boolean results(String line){
		  return line.length()<15;
	  }
  
  }