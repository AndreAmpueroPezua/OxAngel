package com.tfd.oxangel.Activitys.Client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tfd.oxangel.Includes.MyToolbar;
import com.tfd.oxangel.Models.UserClients;
import com.tfd.oxangel.Providers.AuthProvider;
import com.tfd.oxangel.Providers.ClientProvider;
import com.tfd.oxangel.R;

import dmax.dialog.SpotsDialog;

public class RegisterActivity extends AppCompatActivity {

    AuthProvider mAuthProvider;
    ClientProvider mClientProvider;

    Button mBtGoToRegister,mBtGoToCancel;
    TextInputEditText mEditName,mEditEmail,mEditPassword,mEditPhone;
    AlertDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MyToolbar.show(this,"",true);
        // Libreria Spots progress dialog
        mDialog = new SpotsDialog.Builder().setContext(RegisterActivity.this).setMessage("Espera men").build();
        //Intanciando Button, EditText
        mEditName = findViewById(R.id.editRgName);
        mEditEmail = findViewById(R.id.editRgEmail);
        mEditPassword = findViewById(R.id.editRgPassword);
        mEditPhone = findViewById(R.id.editRgPhone);
        mBtGoToRegister = findViewById(R.id.btnRgRegistrar);
        mBtGoToCancel = findViewById(R.id.btnRgCancelar);
        //Firebase
        mAuthProvider = new AuthProvider();
        mClientProvider = new ClientProvider();
        //Click
        mBtGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventClickRegisterUser();
            }
        });
    }

    private void eventClickRegisterUser() {
        String name = mEditName.getText().toString();
        String email = mEditEmail.getText().toString();
        String password = mEditPassword.getText().toString();
        String phone = mEditPhone.getText().toString();
        if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !phone.isEmpty()){
            if(password.length() >= 6 ){
                mDialog.show();
                toRegisterUser(name,email,password,phone);
            } else {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
    private void toRegisterUser(String name, String email, String password, String phone) {
        mAuthProvider.AuthRegister(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mDialog.hide();
                if(task.isSuccessful()){
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    UserClients clients = new UserClients(id,name,email,phone);
                    toCreateUserClient(clients);
                }
            }
        });
    }
    void toCreateUserClient (UserClients client){
        mClientProvider.toCreate(client).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /*
    private void saveUser(String idUsers, String name, String email, String phone) {
        String SelectUser = mSharedPreferences.getString("user","");
        UserClients userClients = new UserClients();
        userClients.setName(name);
        userClients.setEmail(email);
        userClients.setPhone(phone);
        if (SelectUser.equals("client")) {
            mDatabase.child("Users").child("Clients").child(idUsers).setValue(userClients).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Fallo", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (SelectUser.equals("company")) {

        }
    }*/
}