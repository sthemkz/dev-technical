package com.ikhokha.techcheck;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchLinkUrl extends CommentMetricProcessor {

    private final String URL_REGEX = "\\b((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?\\b";
	  

    public SearchLinkUrl(String key) {
        super(key);

}
 
    boolean results(String line){
     Pattern  urlPattern= Pattern.compile(this.URL_REGEX);
     Matcher urlMatcher = urlPattern.matcher(line);
     return urlMatcher.find();
    }     

}
