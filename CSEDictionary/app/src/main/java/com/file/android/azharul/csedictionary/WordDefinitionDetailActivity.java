package com.file.android.azharul.csedictionary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WordDefinitionDetailActivity extends AppCompatActivity {

    TextView wordTextView,definitionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_definition_detail);
        wordTextView=(TextView)findViewById(R.id.wordTextView);
        definitionTextView=(TextView)findViewById(R.id.definitionTextView);
        Log.d("DICTIONARY","ThirdActivity started");

        wordTextView.setText(getIntent().getStringExtra("word"));
        definitionTextView.setText(getIntent().getStringExtra("definition"));
    }
}
