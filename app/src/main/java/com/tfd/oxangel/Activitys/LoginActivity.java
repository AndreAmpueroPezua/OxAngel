package com.tfd.oxangel.Activitys;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tfd.oxangel.Includes.MyToolbar;
import com.tfd.oxangel.R;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText mEditLgEmail,mEditLgPassword;
    Button mBtLgSession;
    FirebaseAuth mFbAuth;
    DatabaseReference mDatabase;
    AlertDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Toolbar
        MyToolbar.show(this,"",true);
        //Instanciando EditText, Button
        mEditLgEmail = findViewById(R.id.editLgEmail);
        mEditLgPassword = findViewById(R.id.editLgPassword);
        mBtLgSession = findViewById(R.id.btnLgSession);
        //Conexion a Firebase Auth, Database
        mFbAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // Libreria Spots progress dialog
        mDialog = new SpotsDialog.Builder().setContext(LoginActivity.this).setMessage("Espera men").build();
        //Click
        mBtLgSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
    }

    private void goToLogin() {
        String email = mEditLgEmail.getText().toString();
        String password = mEditLgPassword.getText().toString();
        if (!email.isEmpty() && !password.isEmpty()){
            if(password.length() >= 6 ){
                mDialog.show();
                mFbAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Exito", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "La contrasea y/o Correo electronico son incorrectos", Toast.LENGTH_SHORT).show();
                        }
                        mDialog.dismiss();
                    }
                });
            } else {
                Toast.makeText(LoginActivity.this, "Longitud de contraseña", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(LoginActivity.this, "Debe ingresar los datos correctondientes", Toast.LENGTH_SHORT).show();
        }
    }
}