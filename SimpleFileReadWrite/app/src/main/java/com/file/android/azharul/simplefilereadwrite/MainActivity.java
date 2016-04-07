package com.file.android.azharul.simplefilereadwrite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView outputDataTV;
    EditText inputDataTV;
    FileManager fileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outputDataTV = (TextView) findViewById(R.id.outputDataTVID);
        inputDataTV = (EditText) findViewById(R.id.inputDataETID);
        fileManager = new FileManager(getApplicationContext());
    }

    public void saveData(View view){

        if(fileManager.isExternalStorageWritable()){
            fileManager.createDirectory();
            fileManager.write(inputDataTV.getText().toString());
        }
        inputDataTV.getText().clear();
    }

    public void viewData(View view){

        outputDataTV.setText(fileManager.read());
    }
}

