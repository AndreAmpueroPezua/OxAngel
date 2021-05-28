package com.tfd.oxangel.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tfd.oxangel.R;

public class MainActivity extends AppCompatActivity {
    Button mBtUser, mBtCompany;
    SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Instanciando Button
        mBtUser = findViewById(R.id.btnClient);
        mBtCompany = findViewById(R.id.btnCompany);
        //Utilizando SharedPreferences
        mSharedPreferences =  getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        //Click
        mBtCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("User","Company");
                editor.apply();
                goToSelectAuth();
            }
        });
        mBtUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("User","Client");
                editor.apply();
                goToSelectAuth();
            }
        });
    }

    private void goToSelectAuth() {
        Intent intent = new Intent(MainActivity.this,SelectOptionAuthActivity.class);
        startActivity(intent);
    }
}