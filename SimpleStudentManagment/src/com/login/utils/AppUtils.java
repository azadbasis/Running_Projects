package com.login.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class AppUtils {

	
	
	public static void toast(Context ctx,String msg)
	{
		Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static int  checkLogin(Context ctx,String username,String password)
	{
		SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(ctx);
		String pusername=pref.getString(Constants.KEY_USERNAME,"");
		String ppassword=pref.getString(Constants.KEY_PASSWORD,"");
		if(pusername.length()==0)
		{
			return 0;
		}else if(pusername.equals(username)&&ppassword.equals(password))
		{
			return 1;
		}else
		{
			return 2;
		}
	}
}
