package com.example.homeless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.homeless.modelo.DAOEmpleado;
import com.example.homeless.modelo.Empleado;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class menuPrincipal_usuario extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    DAOEmpleado dao;
    RVAdaptadorUsuario adaptador;
    String key = null;
    boolean isLoading = false;
    ImageButton botonRegrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_usuario);

        swipeRefreshLayout = findViewById(R.id.swipUsuario);
        recyclerView = findViewById(R.id.recyclerEmpleados2);
        botonRegrear = findViewById(R.id.btnMenuUsuario);

        botonRegrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuPrincipal_usuario.this, menuUsuario.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adaptador = new RVAdaptadorUsuario(this);
        recyclerView.setAdapter(adaptador);
        dao = new DAOEmpleado();
        llenarDatos();
    }


    public void llenarDatos(){
        swipeRefreshLayout.setRefreshing(true);
        dao.get(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Empleado> arrayList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Empleado emp = snapshot.getValue(Empleado.class);
                    emp.setKey(snapshot.getKey());
                    arrayList.add(emp);
                    key = snapshot.getKey();

                }
                adaptador.setItems(arrayList);
                adaptador.notifyDataSetChanged();
                isLoading = false;
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}