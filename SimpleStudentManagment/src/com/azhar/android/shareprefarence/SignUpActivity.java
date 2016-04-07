package com.azhar.android.shareprefarence;






import com.login.utils.AppUtils;
import com.login.utils.Constants;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class SignUpActivity extends Activity {
	
	private EditText etusername,etpassword,etconpassword;
	private CheckBox cbterms;
	Spinner spinnercountry,spinnerstates;
	RadioButton radiomale,radiofemale;
	private Context mctx;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
	
		etusername=(EditText)findViewById(R.id.etusername);
		etpassword=(EditText)findViewById(R.id.etpassword);
		etconpassword=(EditText)findViewById(R.id.etconpassword);
		spinnercountry=(Spinner)findViewById(R.id.spinnercountry);
		spinnerstates=(Spinner)findViewById(R.id.spinnerstates);
		cbterms=(CheckBox)findViewById(R.id.cbterms);
		radiomale=(RadioButton)findViewById(R.id.radiomale);
		radiofemale=(RadioButton)findViewById(R.id.radiofemale);
		mctx=getApplicationContext();
	}

	public void doregister(View v)
	{
		String username=etusername.getText().toString();
		String password=etpassword.getText().toString();
		String conpassword=etconpassword.getText().toString();
		
		if(username.length()==0)
		{
			etusername.setText("Enter username");
		}else if(password.length()==0)
		{
			etpassword.setText("Enter  password");
		}else if(conpassword.length()==0)
		{
			etconpassword.setError("Confirm the password");
		}else if(!password.equals(conpassword))
		{
			AppUtils.toast(mctx, "password and confirmd password do not match");
		}else if(!radiomale.isChecked()||radiofemale.isChecked())
		{
			AppUtils.toast(mctx, "Select Gender");
		}else if(!cbterms.isChecked())
		{
			AppUtils.toast(mctx, "Please, accept terms");
		}else{
			
			String gen="";
			String country=spinnercountry.getSelectedItem().toString();
			String state=spinnerstates.getSelectedItem().toString();
		if(radiomale.isChecked())
		{
			gen="Male";
		}else if(radiofemale.isChecked())
		{
			gen="Female";
		}
			SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(mctx);
			Editor edit=pref.edit();
			edit.putString(Constants.KEY_USERNAME, username);
			edit.putString(Constants.KEY_PASSWORD, password);
			edit.putString("Gender", gen);
			edit.putString("Country", country);
			edit.putString("States", state);
			edit.commit();
			finish();
			AppUtils.toast(mctx, "Registered");
			
		
		}
	}
	
	
}
