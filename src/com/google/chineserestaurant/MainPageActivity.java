package com.google.chineserestaurant;

import com.google.chineserestaurant.locationHelper.LocationHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainPageActivity extends Activity implements OnClickListener{

	private Button search;
	private EditText zip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainpage_activity);
		zip = (EditText) findViewById(R.id.editText1);
		search = (Button) findViewById(R.id.location_search);
		search.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.location_search:
				LocationHelper my = new LocationHelper(this);
				my.restaurantLocation(zip.getText().toString());
				startActivity(new Intent(this,MapViewActivity.class));
				break;
		}
		
	}
}
