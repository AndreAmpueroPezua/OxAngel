package com.tfd.oxangel.Providers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthProvider {
    FirebaseAuth firebaseAuth;
    public AuthProvider() {
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public Task<AuthResult> AuthRegister(String email, String password){
        return firebaseAuth.createUserWithEmailAndPassword(email,password);
    }
    public Task<AuthResult> AuthLogin(String email, String password){
        return firebaseAuth.signInWithEmailAndPassword(email,password);
    }
}
