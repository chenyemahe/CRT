package com.google.chineserestaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LaunchActivity extends Activity{

	//private final static String TAG = "Launch_Page";
	private final static String title = "Loading....";
	private Activity my = this;
	private final static int time = 2500;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.launch_activity);
		setTitle(title);
		Handler x = new Handler();
		x.postDelayed(new loginActivity(), time);
	}
	
	private class loginActivity implements Runnable{

		@Override
		public void run() {
			startActivity(new Intent(getApplication(),LoginActivity.class));
			my.finish();
		}
	}
}
