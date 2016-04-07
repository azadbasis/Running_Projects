package com.azhar.android.shareprefarence;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends Activity {

	TextView tvwelcome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		tvwelcome=(TextView)findViewById(R.id.tvwelcome);
		Intent intent=getIntent();
		String username=intent.getStringExtra("user");
		tvwelcome.setText("Hi "+username+" welcome to android world");
		
	}

	
}
