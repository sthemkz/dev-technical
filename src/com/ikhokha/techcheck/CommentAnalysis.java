package com.ikhokha.techcheck;

public abstract class CommentAnalysis {
    private String key;
    public  CommentAnalysis(String key){
    this.key=key;
 
    }
   abstract boolean results(String line);
   String getKey(){
        return this.key;
    }
    
}
