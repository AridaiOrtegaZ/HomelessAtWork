package com.example.homeless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.homeless.modelo.DAOEmpleado;
import com.example.homeless.modelo.Empleado;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listaEmpleado extends AppCompatActivity {
    Button botonAgregar;
    ImageButton botonRegresar;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    DAOEmpleado dao;
    RVAdptador adaptador;
    boolean isLoading = false;
    String key = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empleado);

        swipeRefreshLayout = findViewById(R.id.swip);
        recyclerView = findViewById(R.id.recyclerEmpleados);
        botonAgregar = findViewById(R.id.btnAgregarHomeless);
        botonRegresar = findViewById(R.id.btnRegresarMenu);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adaptador = new RVAdptador(this);
        recyclerView.setAdapter(adaptador);
        dao = new DAOEmpleado();
        llenarDatos();

        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(listaEmpleado.this, registarEmpleado.class));
            }
        });

        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(listaEmpleado.this, menuPrincipal_admin.class));
            }
        });

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