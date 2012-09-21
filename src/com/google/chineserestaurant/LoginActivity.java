package com.google.chineserestaurant;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.support.v4.app.NavUtils;

public class LoginActivity extends Activity implements OnClickListener{

	private final static String TAG = "LoginActivity";
	private Button skip;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        skip = (Button) findViewById(R.id.button_skip);
        skip.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.button_skip:
				startActivity(new Intent(this,MainPageActivity.class));
				break;		
		}
		// TODO Auto-generated method stub
		
	}

    
}
