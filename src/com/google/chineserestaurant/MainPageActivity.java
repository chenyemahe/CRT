package com.google.chineserestaurant;

import com.google.chineserestaurant.util.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainPageActivity extends Activity implements OnClickListener {

    private Button search;
    private EditText zip;
    private EditText city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage_activity);
        zip = (EditText) findViewById(R.id.et_zip);
        city = (EditText) findViewById(R.id.et_city);
        search = (Button) findViewById(R.id.location_search);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.location_search:
            if (isZipCode(zip.getText().toString())) {
                Intent intent = new Intent(this, SearchResultTabActivity.class);
                intent.putExtra(Util.Intent_Zip_Code, zip.getText().toString());
                intent.putExtra(Util.Intent_City_Name, city.getText().toString());
                startActivity(intent);
            }else{
                Toast.makeText(this, getResources().getString(R.string.main_page_zip_error), Toast.LENGTH_LONG).show();
            }
            break;
        }

    }

    /**
     * true if input is zip code
     * @param zipCode input in zip
     * @return boolean
     */
    private boolean isZipCode(String zipCode) {
        if(zipCode == null || zip.length() != 5) {
            return false;
        }
        return true;
    }
}
