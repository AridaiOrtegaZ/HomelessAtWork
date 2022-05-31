package com.example.homeless;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;
import com.example.homeless.modelo.Contrato;
import com.example.homeless.modelo.DAOContrato;
import com.example.homeless.modelo.DAOEmpleado;
import com.example.homeless.modelo.Empleado;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;

public class RegistrarContrato extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_contrato);

        EditText edit_contratado = (EditText)findViewById(R.id.edit_contratado);
        EditText edit_contratador = (EditText)findViewById(R.id.edit_contratador);
        EditText edit_domicilio = (EditText) findViewById(R.id.edit_domicilio);
        EditText edit_oficio = (EditText) findViewById(R.id.edit_oficio);
        ImageButton botonRegresar = (ImageButton) findViewById(R.id.btnMenuUsuario);

        Button btnActualizarContratacion = (Button) findViewById(R.id.btnActualizarContratacion);
        LottieAnimationView animacion = (LottieAnimationView) findViewById(R.id.animacionGuardado);

        DAOContrato daoContrato = new DAOContrato();
        Contrato con_edit = (Contrato) getIntent().getSerializableExtra("Editar");
        if (con_edit != null){

            edit_contratado.setText(con_edit.getNombreContratado());
            edit_contratador.setText(con_edit.getNombreDelContratador());
            edit_domicilio.setText(con_edit.getDomicilio());
            edit_oficio.setText(con_edit.getOficio());

        }

        btnActualizarContratacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("nombreContratado",edit_contratado.getText().toString());
                hashMap.put("nombreDelContratador",edit_contratador.getText().toString());
                hashMap.put("domicilio",edit_domicilio.getText().toString());
                hashMap.put("oficio",edit_oficio.getText().toString());
                daoContrato.actualizarContrato(con_edit.getKey(),hashMap).addOnSuccessListener(suc-> {
                    animacion.setVisibility(View.VISIBLE);
                    animacion.playAnimation();
                    //finish();
                });
            }
        });

        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrarContrato.this,listaContrato.class));
            }
        });




        animacion.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Intent cambiarVentana = new Intent(RegistrarContrato.this, listaContrato.class);
                startActivity(cambiarVentana);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}