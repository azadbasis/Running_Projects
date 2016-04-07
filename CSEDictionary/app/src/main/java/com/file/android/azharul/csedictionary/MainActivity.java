package com.file.android.azharul.csedictionary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    Button button;
    EditText editText;
    SharedPreferences sharedPreferences;
    static final String SHARED_NAME_STRING="sharedp";
    static final String USER_NAME_STRING="user";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.enterButton);
        editText=(EditText)findViewById(R.id.userNameEditText);

        Log.d("DICTIONARY","MainActivity started");


        sharedPreferences=getSharedPreferences(SHARED_NAME_STRING,MODE_PRIVATE);
        String userNameString=sharedPreferences.getString(USER_NAME_STRING,"");
        editText.setText(userNameString);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string=editText.getText().toString();
                Intent intent=new Intent(MainActivity.this,DictionaryListActivity.class);
                intent.putExtra("user",string);

                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(USER_NAME_STRING,string);
                editor.commit();
                startActivity(intent);
            }
        });
    }

}
