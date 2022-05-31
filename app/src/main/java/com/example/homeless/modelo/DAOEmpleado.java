package com.example.homeless.modelo;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOEmpleado {
    private DatabaseReference databaseReference;

    public DAOEmpleado(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Empleado.class.getSimpleName());
    }

    public Task<Void> agregarEmpleado(Empleado emp){
        return databaseReference.push().setValue(emp);
    }

    public Task<Void> actualizarEmpleado(String key, HashMap<String,Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> eliminarEmpleado(String key){
        return databaseReference.child(key).removeValue();
    }

    public Query get(String key){
            if (key == null){
            return databaseReference.orderByKey();
        }

        return databaseReference.orderByKey().startAt(key);
    }
}
