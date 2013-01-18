package com.google.chineserestaurant;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener{

    private Button mCancel;
    private Button mSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        mCancel = (Button) findViewById(R.id.bt_cancel);
        mSubmit = (Button) findViewById(R.id.bt_register);
        mCancel.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.bt_cancel:
            this.finish();
            break;
        case R.id.bt_register:
            submitToRemoteServer();
            this.finish();
            break;
        }
    }
    
    private void submitToRemoteServer(){
        Toast.makeText(this, "Cangratulation! Register Success!", Toast.LENGTH_LONG).show();
    }
}
