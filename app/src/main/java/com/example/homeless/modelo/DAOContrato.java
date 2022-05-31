package com.example.homeless.modelo;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOContrato {
    private DatabaseReference databaseReference;

    public DAOContrato(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Contrato.class.getSimpleName());
    }

    public Task<Void> agregarContrato(Contrato con){
        return databaseReference.push().setValue(con);
    }

    public Task<Void> actualizarContrato(String key, HashMap<String,Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> eliminarContrato(String key){
        return databaseReference.child(key).removeValue();
    }

    public Query get(String key){
        if (key == null){
            return databaseReference.orderByKey();
        }

        return databaseReference.orderByKey().startAt(key);
    }
}
