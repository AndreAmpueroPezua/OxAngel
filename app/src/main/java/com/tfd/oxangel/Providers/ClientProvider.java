package com.tfd.oxangel.Providers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tfd.oxangel.Models.UserClients;

import java.util.HashMap;
import java.util.Map;

public class ClientProvider {
    DatabaseReference mDatabase;
    public ClientProvider (){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients");
    }
    public Task<Void> toCreate (UserClients client) {
        Map<String,Object> map = new HashMap<>();
        map.put("name",client.getName());
        map.put("email",client.getEmail());
        map.put("phone",client.getPhone());
        return  mDatabase.child(client.getId()).setValue(map);
    }
}
