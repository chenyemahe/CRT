package com.google.chineserestaurant;

import com.google.chineserestaurant.util.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LaunchActivity extends Activity{
	
    private Activity mActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launch_activity);
		mActivity = this;
		Handler x = new Handler();
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
