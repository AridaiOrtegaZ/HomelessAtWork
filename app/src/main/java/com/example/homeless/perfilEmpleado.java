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
import com.google.android.material.textview.MaterialTextView;

public class perfilEmpleado extends AppCompatActivity {
    String nombreDelContratador = "Ari";
    EditText edit_contratador, edit_domicilio, edit_oficio;
    LottieAnimationView animacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_empleado);

        MaterialTextView textNombreEmpleado = (MaterialTextView) findViewById(R.id.textNombreEmpleado);
        MaterialTextView textEdadEmpleado = (MaterialTextView) findViewById(R.id.textEdadEmpleado);
        MaterialTextView textDomicilioEmpleado = (MaterialTextView) findViewById(R.id.textDomicilioEmpleado);
        animacion = (LottieAnimationView) findViewById(R.id.animacionGuardado);
        edit_contratador = (EditText)findViewById(R.id.edit_contratador);
        edit_domicilio = (EditText)findViewById(R.id.edit_domicilio);
        edit_oficio = (EditText)findViewById(R.id.edit_oficio);

        ImageButton btnMenuUsuario = (ImageButton) findViewById(R.id.btnMenuUsuario);
        Button btnGuardarContratacion = (Button)findViewById(R.id.btnGuardarContratacion);

        animacion.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Intent cambiarVentana = new Intent(perfilEmpleado.this, menuPrincipal_usuario.class);
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

        DAOContrato daoContrato = new DAOContrato();

        DAOEmpleado daoEmpleado = new DAOEmpleado();
        Empleado emp_mostrar = (Empleado)getIntent().getSerializableExtra("Mostrar");
        if (emp_mostrar != null){
            textNombreEmpleado.setText(emp_mostrar.getNombre());
            textEdadEmpleado.setText(emp_mostrar.getEdad());
            textDomicilioEmpleado.setText(emp_mostrar.getUbicacion());

        }

        btnMenuUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cambiarVentana = new Intent(perfilEmpleado.this, menuPrincipal_usuario.class);
                startActivity(cambiarVentana);
                finish();
            }
        });

        btnGuardarContratacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contrato con  = new Contrato(emp_mostrar.getNombre(),edit_contratador.getText().toString(), edit_domicilio.getText().toString(), edit_oficio.getText().toString());
                daoContrato.agregarContrato(con).addOnSuccessListener(suc -> {
                    animacion.setVisibility(View.VISIBLE);
                    animacion.playAnimation();
                });
            }
        });
    }
}