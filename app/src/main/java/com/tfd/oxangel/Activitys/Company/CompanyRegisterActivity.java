package com.tfd.oxangel.Activitys.Company;

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
import com.tfd.oxangel.Activitys.Client.RegisterActivity;
import com.tfd.oxangel.Includes.MyToolbar;
import com.tfd.oxangel.Models.UserClients;
import com.tfd.oxangel.Models.UserCompany;
import com.tfd.oxangel.Providers.AuthProvider;
import com.tfd.oxangel.Providers.ClientProvider;
import com.tfd.oxangel.Providers.CompanyProvider;
import com.tfd.oxangel.R;

import dmax.dialog.SpotsDialog;

public class CompanyRegisterActivity extends AppCompatActivity {

    AuthProvider mAuthProvider;
    CompanyProvider mCompanyProvider;

    Button mBtGoToRegister,mBtGoToCancel;
    TextInputEditText mEditRazonSocial,mEditEmailCoporativo,mEditPassword,mEditRuc,mEditPhone,mEditDirection;
    AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_register);
        MyToolbar.show(this,"",true);
        mDialog = new SpotsDialog.Builder().setContext(CompanyRegisterActivity.this).setMessage("Espera men").build();
        mEditRazonSocial = findViewById(R.id.editCpRgRazonSocial);
        mEditEmailCoporativo = findViewById(R.id.editCpRgEmailCorporativo);
        mEditPassword = findViewById(R.id.editCpRgPassword);
        mEditRuc = findViewById(R.id.editCpRgRuc);
        mEditPhone = findViewById(R.id.editCpRgPhone);
        mEditDirection = findViewById(R.id.editCpRgDirection);
        //Firebase
        mAuthProvider = new AuthProvider();
        mCompanyProvider = new CompanyProvider();

        mBtGoToRegister = findViewById(R.id.btnCpRgRegistrar);
        mBtGoToCancel = findViewById(R.id.btnCpRgCancelar);
        mBtGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventClickRegisterUser();
            }
        });
    }
    private void eventClickRegisterUser() {
        String name = mEditRazonSocial.getText().toString();
        String email = mEditEmailCoporativo.getText().toString();
        String password = mEditPassword.getText().toString();
        String ruc = mEditRuc.getText().toString();
        String phone = mEditPhone.getText().toString();
        String direction = mEditDirection.getText().toString();
        if(!name.isEmpty() && !email.isEmpty() && !password.isEmpty() && !ruc.isEmpty() && !phone.isEmpty() && !direction.isEmpty()){
            if(password.length() >= 6 ){
                mDialog.show();
                toRegisterUser(name,email,password,ruc,phone,direction);
            } else {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
    private void toRegisterUser(String name, String email, String password, String ruc, String phone ,String direction) {
        mAuthProvider.AuthRegister(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mDialog.hide();
                if(task.isSuccessful()){
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    UserCompany company = new UserCompany(id,name,email,ruc,phone,direction);
                    toCreateUserCompany(company);
                }
            }
        });
    }
    void toCreateUserCompany (UserCompany company){
        mCompanyProvider.toCreate(company).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CompanyRegisterActivity.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CompanyRegisterActivity.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}