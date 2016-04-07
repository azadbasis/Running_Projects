package com.file.android.azharul.csedictionary;

import java.util.ArrayList;

/**
 * Created by Azharul on 3/31/2016.
 */
public class WordDefinition {

    String word,definition;

    public WordDefinition(String word, ArrayList<String> allDefinition) {
        this.word = word;
       StringBuilder stringBuilder=new StringBuilder();
        for (String string:allDefinition
             ) {
            stringBuilder.append(string);
        }
        this.definition=stringBuilder.toString();
    }


    public WordDefinition(String word, String allDefinition) {
        this.word = word;
        StringBuilder stringBuilder=new StringBuilder();

        this.definition=allDefinition;
    }

}
