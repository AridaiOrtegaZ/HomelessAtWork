package com.example.homeless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class menuUsuario extends AppCompatActivity {
    Button empleados, salir, botonContrato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);
        botonContrato = findViewById(R.id.btnContrataciones);
        empleados = findViewById(R.id.btnEmpleados);
        salir = findViewById(R.id.btnRegresarInicio);

        empleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menuUsuario.this, menuPrincipal_usuario.class));
            }
        });


        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(menuUsuario.this, inicio_sesion.class));
            }
        });

        botonContrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cambiarVentana = new Intent(menuUsuario.this, listaContrato.class);
                startActivity(cambiarVentana);
                finish();
            }
        });

    }

}