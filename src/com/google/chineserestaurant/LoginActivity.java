package com.google.chineserestaurant;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

    private Button skip;
    private Button login;
    private Button register;
    private TextView userName;
    private TextView password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setView();
    }

    private void setView() {
        skip = (Button) findViewById(R.id.button_skip);
        login = (Button) findViewById(R.id.button_login);
        register = (Button) findViewById(R.id.button_register);
        skip.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        userName = (TextView) findViewById(R.id.e_username);
        password = (TextView) findViewById(R.id.e_password);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button_skip:
            skip();
            break;
        case R.id.button_login:
            logIn();
            break;
        case R.id.button_register:
            register();
            break;
        }
    }

    private void skip() {
        startActivity(new Intent(this, MainPageActivity.class));
    }

    private void logIn() {
        String userNameInfo = userName.getText().toString();
        String passwordInfo = password.getText().toString();
        if (userNameInfo == null || userNameInfo.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.log_in_no_user_name_input), Toast.LENGTH_LONG).show();
            return;
        }
        if (passwordInfo == null || passwordInfo.equals("")) {
            Toast.makeText(this, getResources().getString(R.string.log_in_no_password_input), Toast.LENGTH_LONG).show();
            return;
        }
        if (hasUserInfo(userNameInfo, passwordInfo)) {
            Intent intent = new Intent(this, MainPageActivity.class);
            intent.putExtra(getResources().getString(R.string.intent_user_log_in), true);
            intent.putExtra(getResources().getString(R.string.intent_user_name), userNameInfo);
            intent.putExtra(getResources().getString(R.string.intent_password), passwordInfo);
            startActivity(intent);
        } else {
            Toast.makeText(this, getResources().getString(R.string.log_in_no_user_info_found), Toast.LENGTH_LONG).show();
        }
    }

    private boolean hasUserInfo(String userNameInfo, String passwordInfo) {
        //TODO:check remote database for user info
        return true;
    }

    private void register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
