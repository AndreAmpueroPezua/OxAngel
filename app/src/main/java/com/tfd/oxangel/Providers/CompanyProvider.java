package com.tfd.oxangel.Providers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tfd.oxangel.Models.UserCompany;

import java.util.HashMap;
import java.util.Map;

public class CompanyProvider {
    DatabaseReference mDatabase;
    public CompanyProvider (){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Companys");
    }
    public Task<Void> toCreate (UserCompany company) {
        Map <String,Object> map = new HashMap<>();
        map.put("razonsocial",company.getRazonSocial());
        map.put("emailcorporativo",company.getEmailCoporativo());
        map.put("ruc",company.getRuc());
        map.put("phone",company.getPhone());
        map.put("direction",company.getDirection());
        return  mDatabase.child(company.getId()).setValue(map);
    }
}
