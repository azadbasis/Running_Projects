package com.azhar.android.shareprefarence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Feature extends Activity {

    Button btnmarks,btnprofile;
    TextView tvFeature;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature);

    setTitle("Features");
        tvFeature = (TextView) findViewById(R.id.tvFeature);
        Intent intent = getIntent();
        String username = intent.getStringExtra("user");
        tvFeature.setText("Hi " + username + " welcome to Student management Featureas!");

        btnmarks=(Button)findViewById(R.id.btnmarks);
        btnprofile=(Button)findViewById(R.id.btnprofile);


       btnmarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Feature.this,MyApp.class);
                startActivity(intent);
            }
        });


        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Feature.this,Profile.class);
                startActivity(intent);
            }
        });


    }




}
