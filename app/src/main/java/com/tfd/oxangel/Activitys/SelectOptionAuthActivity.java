package com.tfd.oxangel.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tfd.oxangel.Activitys.Client.RegisterActivity;
import com.tfd.oxangel.Activitys.Company.CompanyRegisterActivity;
import com.tfd.oxangel.Includes.MyToolbar;
import com.tfd.oxangel.R;

public class SelectOptionAuthActivity extends AppCompatActivity {
    SharedPreferences mSharedPreferences;
    Button mBtGoToLogin, mBtGoToRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option_auth);
        MyToolbar.show(this,"",true);
        mSharedPreferences =  getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        //Intaciando Button
        mBtGoToLogin = findViewById(R.id.btnGoToLogin);
        mBtGoToRegister = findViewById(R.id.btnGoToRegister);
        // Click
        mBtGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
        mBtGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });
    }

    private void goToLogin() {
        Intent i = new Intent(SelectOptionAuthActivity.this,LoginActivity.class);
        startActivity(i);
    }

    private void goToRegister() {
        String TyUser = mSharedPreferences.getString("User","");
        if (TyUser.equals("Client")) {
            Intent i = new Intent(SelectOptionAuthActivity.this, RegisterActivity.class);
            startActivity(i);
        } else if (TyUser.equals("Company")) {
            Intent i = new Intent(SelectOptionAuthActivity.this, CompanyRegisterActivity.class);
            startActivity(i);
        }
    }
}