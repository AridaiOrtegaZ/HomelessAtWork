package com.example.homeless;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.homeless.modelo.Contrato;
import com.example.homeless.modelo.DAOContrato;
import com.example.homeless.modelo.DAOEmpleado;
import com.example.homeless.modelo.Empleado;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listaContrato extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    DAOContrato dao;
    RVAdaptadorContrato adaptador;
    String key = null;
    boolean isLoading = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contrato);

        ImageButton botonRegresar = (ImageButton)findViewById(R.id.btnRegresarMenu);
        swipeRefreshLayout = findViewById(R.id.swipContrato);
        recyclerView = findViewById(R.id.recyclerContrato);


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adaptador = new RVAdaptadorContrato(this);
        recyclerView.setAdapter(adaptador);
        dao = new DAOContrato();
        llenarDatos();


        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(listaContrato.this, menuPrincipal_admin.class));
            }
        });
    }

    public void llenarDatos(){
        swipeRefreshLayout.setRefreshing(true);
        dao.get(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Contrato> arrayList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Contrato con = snapshot.getValue(Contrato.class);
                    con.setKey(snapshot.getKey());
                    arrayList.add(con);
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