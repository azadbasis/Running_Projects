package com.file.android.azharul.simplefilereadwrite;

/**
 * Created by Azharul on 3/31/2016.
 */
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class FileManager {

    public final static String FILE_NAME = "myfile.txt";
    private String root = Environment.getExternalStorageDirectory().toString();
    private File myFile;
    private Context myContext;

    public FileManager(Context context){
        this.myContext=context;
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Create file in external storage */
    public void createDirectory(){

        // Directory Make
        File myDir = new File(root + "/myfiles");
        myDir.mkdir();
        // Create File
        myFile = new File(myDir,FILE_NAME);
        Toast.makeText(myContext,"File Created!",Toast.LENGTH_SHORT).show();

    }

    public void write(String data ){

        try {
            FileOutputStream outputStream = new FileOutputStream( myFile);
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
            streamWriter.write(data);
            streamWriter.close();
            Toast.makeText(myContext,"File Saved!",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String read(){

        try {
            FileInputStream inputStream = new FileInputStream(myFile);
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            StringBuilder builder = new StringBuilder();
            String receivedText = "";
            while ((receivedText = bufferedReader.readLine()) != null){
                builder.append(receivedText);
            }
            inputStream.close();
            return builder.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "error";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }

    }

}
