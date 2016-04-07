package com.azhar.android.shareprefarence;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.login.utils.AppUtils;


public class LoginActivity extends Activity implements OnClickListener {


	private EditText etusername,etpassword;
	private Button btnlogin,btnregister,btnclear;
	private Context mctx;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etusername=(EditText)findViewById(R.id.etusername);
        etpassword=(EditText)findViewById(R.id.etpassword);
        btnlogin=(Button)findViewById(R.id.btnlogin);
        btnregister=(Button)findViewById(R.id.btnregister);
        btnclear=(Button)findViewById(R.id.btnclear);
        
        btnlogin.setOnClickListener(this);
        btnregister.setOnClickListener(this);
        btnclear.setOnClickListener(this);
        
        mctx=getApplicationContext();
        
    }

	@Override
	public void onClick(View v) {

		if(v==btnclear){
			etusername.setText("");
			etpassword.setText("");
		}else if(v==btnlogin){
			doLogin();
		}else if(v==btnregister)
		{
			startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
		}
	}
	
	private void doLogin(){
		
		String username=etusername.getText().toString();
		String password=etpassword.getText().toString();
		
		if(username.length()==0)
		{
			//AppUtils.toast(mctx, "Enter User name");
			etusername.setError("Enter User name");
			
		}else if(password.length()==0)
		{
			etpassword.setError("Enter Password");
		}else
		{
			int res=AppUtils.checkLogin(mctx,username,password);
			if(res==0)
			{
				AppUtils.toast(mctx, "Please, register first");
			}else if(res==1)
			{
				Intent intent=new Intent(this, WelcomeActivity.class);
				intent.putExtra("user", username);
				startActivity(intent);
			}else if(res==2)
			{
				AppUtils.toast(mctx, "please, Enter valid credentials");
			}
		}
	}

}
