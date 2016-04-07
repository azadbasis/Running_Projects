package com.file.android.azharul.csedictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Azharul on 3/31/2016.
 */
public class DictionaryLoader {

    //Use BufferedReader if you want to get long strings from a stream,

    public static void loadData(BufferedReader bufferedReader, DictionaryDatabaseHelper dictionaryDatabaseHelper) {

        ArrayList<WordDefinition> allWords=new ArrayList<WordDefinition>();

        try {

            BufferedReader fileReader=bufferedReader;

            try {

                int c=17;
                c=fileReader.read();
                while (c!=(-1)) {

                    StringBuilder stringBuilder=new StringBuilder();

                    while ((char)c!='\n'&&c!=-1) {
                        try {
                            stringBuilder.append((char)c);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            System.out.println(stringBuilder.length());
                            //e.printStackTrace();
                        }
                        c= fileReader.read();
                        if (c==-1) {
                            return;
                        }
                    }


                    String wordString=stringBuilder.toString();

                    ArrayList<String> definition=new ArrayList<String>();
                    while (c=='\n'||c=='\t') {
                        c= fileReader.read();
                        if (c=='\n'||c=='\t'||c=='\r') {
                            StringBuilder stringBuilder2=new StringBuilder();
                            while (c!='\n') {
                                stringBuilder2.append((char)c);
                                c=fileReader.read();
                            }
                            String definitionString=stringBuilder2.toString();
                            definition.add(definitionString);
                        }else {
                            break;
                        }

                    }

                    wordString=wordString.trim();
                    //Logger.log("word Loaded: "+(++counter)+" :"+wordString);
                    allWords.add(new WordDefinition(wordString, definition));
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try {

                dictionaryDatabaseHelper.initializeDatabaseFromTheFirstTime(allWords);
                fileReader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}


/*InputStream reads bytes one at a time - no character set processing and no buffering. It's the parent class for stream input, but rarely used becuase one of its subclasses is usually more appropriate (eg BufferedReader)

BufferedReader reads characters and buffers its input.

If your input is text, always use BufferedReader.*/