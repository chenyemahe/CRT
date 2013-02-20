package com.google.chineserestaurant;

import com.google.chineserestaurant.util.Util;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class LaunchActivity extends Activity{
	
    private Activity mActivity;
    private SharedPreferences mCrtPrefs;
    private boolean firstLaunch;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCrtPrefs = getSharedPreferences(Util.Pref_Name, 0);
		//only show launcher page at first time
		firstLaunch = mCrtPrefs.getBoolean(Util.First_Launch, true);
		if(!firstLaunch){
            startActivity(new Intent(this,LoginActivity.class));
            this.finish();
            return;
		}
		mCrtPrefs.edit().putBoolean(Util.First_Launch, false).commit();
		setContentView(R.layout.launch_activity);
		mActivity = this;
		Handler x = new Handler();
		//delay thread run
		x.postDelayed(new StartLoginActivity(), Util.Lauch_Show_Time);
	}
	
	private class StartLoginActivity implements Runnable{

		@Override
		public void run() {
			startActivity(new Intent(getApplication(),LoginActivity.class));
			mActivity.finish();
		}
	}
}
