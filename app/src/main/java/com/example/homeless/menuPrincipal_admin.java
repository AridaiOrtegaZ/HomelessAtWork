package com.example.homeless;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class menuPrincipal_admin extends AppCompatActivity {
    Button botonRegresar,botonContrato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_admin);

        botonRegresar = findViewById(R.id.btnRegresarInicio);
        botonContrato = findViewById(R.id.btnContrataciones);

        MaterialButton btnHomeless = (MaterialButton)findViewById(R.id.btnHomeless);
        btnHomeless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cambiarVentana = new Intent(menuPrincipal_admin.this, listaEmpleado.class);
                startActivity(cambiarVentana);
                finish();
            }
        });

        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cambiarVentana = new Intent(menuPrincipal_admin.this, inicio_sesion.class);
                startActivity(cambiarVentana);
                finish();
            }
        });

        botonContrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cambiarVentana = new Intent(menuPrincipal_admin.this, listaContrato.class);
                startActivity(cambiarVentana);
                finish();
            }
        });
    }
}